package info.nightscout.androidaps.plugins.pump.common.defs;

import java.util.HashMap;
import java.util.Map;

import info.nightscout.androidaps.MainApp;
import info.nightscout.androidaps.R;
import info.nightscout.androidaps.plugins.pump.common.data.DoseSettings;

/**
 * Created by andy on 02/05/2018.
 *
 * Most of this defintions is intended for VirtualPump only, but they can be used by other plugins.
 */

public enum PumpType {

    GenericAAPS("Generic AAPS", 0.1d, null, //
    new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Percent, //
    new DoseSettings(10, 30, 24 * 60, 0d, 500d), PumpCapability.BasalRate_Duration15and30minAllowed, //
    0.01d, 0.01d, null, PumpCapability.VirtualPumpCapabilities), //

    // Cellnovo

    Cellnovo1("Cellnovo", 0.05d, null, //
    new DoseSettings(0.05d, 30, 24 * 60, 1d, null), //
    PumpTempBasalType.Percent, //
    new DoseSettings(5, 30, 24 * 60, 0d, 200d), PumpCapability.BasalRate_Duration30minAllowed, //
    0.05d, 0.05d, null, PumpCapability.VirtualPumpCapabilities), //

    // Accu-Chek

    AccuChekCombo("Accu-Chek Combo", 0.1d, null, //
    new DoseSettings(0.1d, 15, 12 * 60, 0.1d), //
    PumpTempBasalType.Percent, new DoseSettings(10, 15, 12 * 60, 0d, 500d), PumpCapability.BasalRate_Duration15and30minAllowed, //
    0.01d, 0.01d, DoseStepSize.ComboBasal, PumpCapability.ComboCapabilities), //

    AccuChekSpirit("Accu-Chek Spirit", 0.1d, null, //
    new DoseSettings(0.1d, 15, 12 * 60, 0.1d), //
    PumpTempBasalType.Percent, new DoseSettings(10, 15, 12 * 60, 0d, 500d), PumpCapability.BasalRate_Duration15and30minAllowed, //
    0.01d, 0.1d, null, PumpCapability.VirtualPumpCapabilities), //

    AccuChekInsight("Accu-Chek Insight", 0.05d, DoseStepSize.InsightBolus, //
    new DoseSettings(0.05d, 15, 24 * 60, 0.05d), //
    PumpTempBasalType.Percent, new DoseSettings(10, 15, 12 * 60, 0d, 250d), PumpCapability.BasalRate_Duration15and30minAllowed, //
    0.02d, 0.01d, null, PumpCapability.InsightCapabilities), //

    // Animas
    AnimasVibe("Animas Vibe", 0.05d, null, // AnimasBolus?
    new DoseSettings(0.05d, 30, 12 * 60, 0.05d), //
    PumpTempBasalType.Percent, //
    new DoseSettings(10, 30, 24 * 60, 0d, 300d), PumpCapability.BasalRate_Duration30minAllowed, //
    0.025d, 5d, 0d, null, PumpCapability.VirtualPumpCapabilities), //

    AnimasPing("Animas Ping", AnimasVibe),

    // Dana
    DanaR("DanaR", 0.05d, null, //
    new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Percent, //
    new DoseSettings(10d, 60, 24 * 60, 0d, 200d), PumpCapability.BasalRate_Duration15and30minNotAllowed, //
    0.04d, 0.01d, null, PumpCapability.DanaCapabilities),

    DanaRKorean("DanaR Korean", 0.05d, null, //
    new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Percent, //
    new DoseSettings(10d, 60, 24 * 60, 0d, 200d), PumpCapability.BasalRate_Duration15and30minNotAllowed, //
    0.1d, 0.01d, null, PumpCapability.DanaCapabilities),

    DanaRS("DanaRS", 0.05d, null, //
    new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Percent, //
    new DoseSettings(10d, 60, 24 * 60, 0d, 200d), PumpCapability.BasalRate_Duration15and30minAllowed, //
    0.04d, 0.01d, null, PumpCapability.DanaWithHistoryCapabilities),

    DanaRv2("DanaRv2", DanaRS),

    // Insulet
    Insulet_Omnipod("Insulet Omnipod", 0.05d, null, //
    new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Absolute, //
    new DoseSettings(0.05d, 30, 12 * 60, 0d, 30.0d), PumpCapability.BasalRate_Duration30minAllowed, // cannot exceed max
                                                                                                    // basal rate 30u/hr
    0.05d, 0.05d, null, PumpCapability.VirtualPumpCapabilities),

    // Medtronic
    Medtronic_512_712("Medtronic 512/712", 0.05d, null, //
    new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Absolute, //
    new DoseSettings(0.05d, 30, 24 * 60, 0d, 35d), PumpCapability.BasalRate_Duration30minAllowed, //
    0.05d, 0.05d, null, PumpCapability.MedtronicCapabilities),

    Medtronic_515_715("Medtronic 515/715", Medtronic_512_712),
    Medtronic_522_722("Medtronic 522/722", Medtronic_512_712),

    Medtronic_523_723_Revel("Medtronic 523/723 (Revel)", //
    0.05d, null, new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Absolute, //
    new DoseSettings(0.05d, 30, 24 * 60, 0d, 35d), PumpCapability.BasalRate_Duration30minAllowed, //
    0.025d, 0.025d, DoseStepSize.MedtronicVeoBasal, PumpCapability.MedtronicCapabilities), //

    Medtronic_554_754_Veo("Medtronic 554/754 (Veo)", Medtronic_523_723_Revel), //

    Medtronic_640G("Medtronic 640G", 0.025d, null, //
    new DoseSettings(0.05d, 30, 8 * 60, 0.05d), //
    PumpTempBasalType.Absolute, //
    new DoseSettings(0.05d, 30, 24 * 60, 0d, 35d), PumpCapability.BasalRate_Duration30minAllowed, //
    0.025d, 0.025d, DoseStepSize.MedtronicVeoBasal, PumpCapability.VirtualPumpCapabilities), //

    // Tandem
    TandemTSlim("Tandem t:slim", 0.01d, null, //
    new DoseSettings(0.01d, 15, 8 * 60, 0.4d), PumpTempBasalType.Percent, new DoseSettings(1, 15, 8 * 60, 0d, 250d), PumpCapability.BasalRate_Duration15and30minAllowed, //
    0.1d, 0.001d, null, PumpCapability.VirtualPumpCapabilities),

    TandemTFlex("Tandem t:flex", TandemTSlim), //
    TandemTSlimG4("Tandem t:slim G4", TandemTSlim), //
    TandemTSlimX2("Tandem t:slim X2", TandemTSlim), //
    ;

    private String description;
    private double bolusSize;
    private DoseStepSize specialBolusSize;
    private DoseSettings extendedBolusSettings;
    private PumpTempBasalType pumpTempBasalType;
    private DoseSettings tbrSettings;
    private PumpCapability specialBasalDurations;
    private double baseBasalMinValue; //
    private Double baseBasalMaxValue;
    private double baseBasalStep; //
    private DoseStepSize baseBasalSpecialSteps; //
    private PumpCapability pumpCapability;

    private PumpType parent;
    private static Map<String, PumpType> mapByDescription;

    static {
        mapByDescription = new HashMap<>();

        for (PumpType pumpType : values()) {
            mapByDescription.put(pumpType.getDescription(), pumpType);
        }
    }


    PumpType(String description, PumpType parent) {
        this.description = description;
        this.parent = parent;
    }


    PumpType(String description, PumpType parent, PumpCapability pumpCapability) {
        this.description = description;
        this.parent = parent;
        this.pumpCapability = pumpCapability;
    }


    PumpType(String description, double bolusSize,
            DoseStepSize specialBolusSize, //
            DoseSettings extendedBolusSettings, //
            PumpTempBasalType pumpTempBasalType, DoseSettings tbrSettings,
            PumpCapability specialBasalDurations, //
            double baseBasalMinValue, double baseBasalStep, DoseStepSize baseBasalSpecialSteps,
            PumpCapability pumpCapability) {
        this(description, bolusSize, specialBolusSize, extendedBolusSettings, pumpTempBasalType, tbrSettings,
            specialBasalDurations, baseBasalMinValue, null, baseBasalStep, baseBasalSpecialSteps, pumpCapability);
    }


    PumpType(String description, double bolusSize,
            DoseStepSize specialBolusSize, //
            DoseSettings extendedBolusSettings, //
            PumpTempBasalType pumpTempBasalType, DoseSettings tbrSettings,
            PumpCapability specialBasalDurations, //
            double baseBasalMinValue, Double baseBasalMaxValue, double baseBasalStep,
            DoseStepSize baseBasalSpecialSteps, PumpCapability pumpCapability) {
        this.description = description;
        this.bolusSize = bolusSize;
        this.specialBolusSize = specialBolusSize;
        this.extendedBolusSettings = extendedBolusSettings;
        this.pumpTempBasalType = pumpTempBasalType;
        this.tbrSettings = tbrSettings;
        this.specialBasalDurations = specialBasalDurations;
        this.baseBasalMinValue = baseBasalMinValue;
        this.baseBasalMaxValue = baseBasalMaxValue;
        this.baseBasalStep = baseBasalStep;
        this.baseBasalSpecialSteps = baseBasalSpecialSteps;
        this.pumpCapability = pumpCapability;
    }


    public String getDescription() {
        return description;
    }


    public PumpCapability getPumpCapability() {

        if (isParentSet())
            return this.pumpCapability == null ? parent.pumpCapability : pumpCapability;
        else
            return this.pumpCapability;
    }


    public double getBolusSize() {
        return isParentSet() ? parent.bolusSize : bolusSize;
    }


    public DoseStepSize getSpecialBolusSize() {
        return isParentSet() ? parent.specialBolusSize : specialBolusSize;
    }


    public DoseSettings getExtendedBolusSettings() {
        return isParentSet() ? parent.extendedBolusSettings : extendedBolusSettings;
    }


    public PumpTempBasalType getPumpTempBasalType() {
        return isParentSet() ? parent.pumpTempBasalType : pumpTempBasalType;
    }


    public DoseSettings getTbrSettings() {
        return isParentSet() ? parent.tbrSettings : tbrSettings;
    }


    public double getBaseBasalMinValue() {
        return isParentSet() ? parent.baseBasalMinValue : baseBasalMinValue;
    }


    public Double getBaseBasalMaxValue() {
        return isParentSet() ? parent.baseBasalMaxValue : baseBasalMaxValue;
    }


    public double getBaseBasalStep() {
        return isParentSet() ? parent.baseBasalStep : baseBasalStep;
    }


    public DoseStepSize getBaseBasalSpecialSteps() {
        return isParentSet() ? parent.baseBasalSpecialSteps : baseBasalSpecialSteps;
    }


    public PumpType getParent() {
        return parent;
    }


    private boolean isParentSet() {
        return this.parent != null;
    }


    public static PumpType getByDescription(String desc) {
        if (mapByDescription.containsKey(desc)) {
            return mapByDescription.get(desc);
        } else {
            return PumpType.GenericAAPS;
        }
    }


    public String getFullDescription(String i18nTemplate, boolean hasExtendedBasals) {

        String unit = getPumpTempBasalType() == PumpTempBasalType.Percent ? "%" : "";

        DoseSettings eb = getExtendedBolusSettings();
        DoseSettings tbr = getTbrSettings();

        String extendedNote = hasExtendedBasals ? MainApp.gs(R.string.virtualpump_pump_def_extended_note) : "";

        return String.format(
            i18nTemplate, //
            getStep("" + getBolusSize(), getSpecialBolusSize()), //
            eb.getStep(), eb.getDurationStep(),
            eb.getMaxDuration() / 60, //
            getStep(getBaseBasalRange(), getBaseBasalSpecialSteps()), //
            tbr.getMinDose() + unit + "-" + tbr.getMaxDose() + unit, tbr.getStep() + unit, tbr.getDurationStep(),
            tbr.getMaxDuration() / 60, extendedNote);
    }


    private String getBaseBasalRange() {
        Double maxValue = getBaseBasalMaxValue();

        return maxValue == null ? "" + getBaseBasalMinValue() : getBaseBasalMinValue() + "-" + maxValue;
    }


    private String getStep(String step, DoseStepSize stepSize) {
        if (stepSize != null)
            return step + " [" + stepSize.getDescription() + "] *";
        else
            return "" + step;
    }


    public boolean hasExtendedBasals() {
        return ((getBaseBasalSpecialSteps() != null) || (getSpecialBolusSize() != null));
    }


    public PumpCapability getSpecialBasalDurations() {

        if (isParentSet()) {
            return parent.getSpecialBasalDurations();
        } else {
            return specialBasalDurations == null ? //
            PumpCapability.BasalRate_Duration15and30minNotAllowed
                : specialBasalDurations;
        }
    }


    public double determineCorrectBolusSize(double bolusAmount) {
        if (bolusAmount == 0.0d) {
            return bolusAmount;
        }

        double bolusStepSize;

        if (getSpecialBolusSize() == null) {
            bolusStepSize = getBolusSize();
        } else {
            DoseStepSize specialBolusSize = getSpecialBolusSize();

            bolusStepSize = specialBolusSize.getStepSizeForAmount((double)bolusAmount);
        }

        return Math.round(bolusAmount / bolusStepSize) * bolusStepSize;
    }


    public double determineCorrectExtendedBolusSize(double bolusAmount) {
        if (bolusAmount == 0.0d) {
            return bolusAmount;
        }

        double bolusStepSize;

        if (getExtendedBolusSettings() == null) { // this should be never null
            return 0.0d;
        }

        DoseSettings extendedBolusSettings = getExtendedBolusSettings();

        bolusStepSize = extendedBolusSettings.getStep();

        if (bolusAmount > extendedBolusSettings.getMaxDose()) {
            bolusAmount = extendedBolusSettings.getMaxDose();
        }

        return Math.round(bolusAmount / bolusStepSize) * bolusStepSize;
    }


    public double determineCorrectBasalSize(double basalAmount) {
        if (basalAmount == 0.0d) {
            return basalAmount;
        }

        double basalStepSize;

        if (getBaseBasalSpecialSteps() == null) {
            basalStepSize = getBaseBasalStep();
        } else {
            DoseStepSize specialBolusSize = getBaseBasalSpecialSteps();

            basalStepSize = specialBolusSize.getStepSizeForAmount((double)basalAmount);
        }

        if (basalAmount > getTbrSettings().getMaxDose())
            basalAmount = getTbrSettings().getMaxDose().doubleValue();

        return Math.round(basalAmount / basalStepSize) * basalStepSize;

    }
}
