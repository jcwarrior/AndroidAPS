package info.nightscout.androidaps.plugins.pump.medtronic.comm.ui;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.nightscout.androidaps.MainApp;
import info.nightscout.androidaps.plugins.pump.medtronic.comm.MedtronicCommunicationManager;
import info.nightscout.androidaps.plugins.pump.medtronic.comm.history.pump.PumpHistoryEntry;
import info.nightscout.androidaps.plugins.pump.medtronic.data.dto.BasalProfile;
import info.nightscout.androidaps.plugins.pump.medtronic.data.dto.TempBasalPair;
import info.nightscout.androidaps.plugins.pump.medtronic.defs.MedtronicCommandType;
import info.nightscout.androidaps.plugins.pump.medtronic.defs.MedtronicUIResponseType;
import info.nightscout.androidaps.plugins.pump.medtronic.defs.PumpDeviceState;
import info.nightscout.androidaps.plugins.pump.medtronic.events.EventMedtronicDeviceStatusChange;
import info.nightscout.androidaps.plugins.pump.medtronic.events.EventMedtronicPumpValuesChanged;
import info.nightscout.androidaps.plugins.pump.medtronic.util.MedtronicUtil;

/**
 * Created by andy on 6/14/18.
 */

// FIXME we could refactor this and create sperate class for each command, perhaps
public class MedtronicUITask {

    private static final Logger LOG = LoggerFactory.getLogger(MedtronicUITask.class);

    public MedtronicCommandType commandType;
    public Object returnData;
    String errorDescription;
    // boolean invalid = false;
    private Object[] parameters;
    // private boolean received;
    MedtronicUIResponseType responseType;


    public MedtronicUITask(MedtronicCommandType commandType) {
        this.commandType = commandType;
    }


    public MedtronicUITask(MedtronicCommandType commandType, Object... parameters) {
        this.commandType = commandType;
        this.parameters = parameters;
    }


    public void execute(MedtronicCommunicationManager communicationManager) {

        LOG.warn("@@@ In execute. {}", commandType);

        switch (commandType) {
            case PumpModel: {
                returnData = communicationManager.getPumpModel();
            }
                break;

            case GetBasalProfileSTD: {
                returnData = communicationManager.getBasalProfile();
            }
                break;

            case GetRemainingInsulin: {
                returnData = communicationManager.getRemainingInsulin();
            }
                break;

            case RealTimeClock: {
                returnData = communicationManager.getPumpTime();
            }
                break;

            case GetBatteryStatus: {
                returnData = communicationManager.getRemainingBattery();
            }
                break;

            case SetTemporaryBasal: {
                TempBasalPair tbr = getTBRSettings();
                if (tbr != null) {
                    returnData = communicationManager.setTBR(tbr);
                }
            }
                break;

            case ReadTemporaryBasal: {
                returnData = communicationManager.getTemporaryBasal();
            }
                break;

            // case PumpState: {
            // // TODO maybe remove this, data returned is almost useless
            // returnData = communicationManager.getPumpState();
            // }
            // break;

            // case "RefreshData.GetBolus": {
            // returnData = communicationManager.getBolusStatus();
            // }
            // break;

            case Settings:
            case Settings_512: {
                returnData = communicationManager.getPumpSettings();
            }
                break;

            case SetBolus: {
                Double amount = getDoubleFromParameters(0);

                if (amount != null)
                    returnData = communicationManager.setBolus(amount);
            }
                break;

            case CancelTBR: {
                // FIXME check if TBR is actually running
                returnData = communicationManager.cancelTBR();
            }
                break;

            case SetBasalProfileSTD:
            case SetBasalProfileA: {
                BasalProfile profile = (BasalProfile)parameters[0];

                returnData = communicationManager.setBasalProfile(profile);
            }
                break;

            case GetHistoryData: {
                returnData = communicationManager.getPumpHistory((PumpHistoryEntry)parameters[0],
                    (LocalDateTime)parameters[1]);
            }
                break;

            default: {
                LOG.warn("This commandType is not supported (yet) - {}.", commandType);
                // invalid = true;
                responseType = MedtronicUIResponseType.Invalid;
            }

        }

        if (responseType == null) {
            if (returnData == null) {
                errorDescription = communicationManager.getErrorResponse();
                this.responseType = MedtronicUIResponseType.Error;
            } else {
                this.responseType = MedtronicUIResponseType.Data;
            }
        }

    }


    private TempBasalPair getTBRSettings() {
        TempBasalPair tempBasalPair = new TempBasalPair(getDoubleFromParameters(0), //
            false, //
            getIntegerFromParameters(1));

        return tempBasalPair;
    }


    private Float getFloatFromParameters(int index) {
        return (Float)parameters[index];
    }


    public Double getDoubleFromParameters(int index) {
        return (Double)parameters[index];
    }


    public Integer getIntegerFromParameters(int index) {
        return (Integer)parameters[index];
    }


    public Object getResult() {
        return returnData;
    }


    public boolean isReceived() {
        return (returnData != null || errorDescription != null);
    }


    public void postProcess(MedtronicUIPostprocessor postprocessor) {

        EventMedtronicDeviceStatusChange statusChange;
        LOG.warn("@@@ In execute. {}", commandType);

        if (responseType == MedtronicUIResponseType.Data) {
            postprocessor.postProcessData(this);
        }

        if (responseType == MedtronicUIResponseType.Invalid) {
            statusChange = new EventMedtronicDeviceStatusChange(PumpDeviceState.ErrorWhenCommunicating,
                "Unsupported command in MedtronicUITask");
            MainApp.bus().post(statusChange);
        } else if (responseType == MedtronicUIResponseType.Error) {
            statusChange = new EventMedtronicDeviceStatusChange(PumpDeviceState.ErrorWhenCommunicating,
                errorDescription);
            MainApp.bus().post(statusChange);
        } else {
            MainApp.bus().post(new EventMedtronicPumpValuesChanged());
        }

        MedtronicUtil.getPumpStatus().setLastCommunicationToNow();

        MedtronicUtil.setCurrentCommand(null);
    }


    public boolean hasData() {
        return (responseType == MedtronicUIResponseType.Data);
    }


    public Object getParameter(int index) {
        return parameters[index];
    }
}
