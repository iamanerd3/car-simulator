package dev.bromine.car_simulator.car;

public class Transmission {
    // manual gear ratios, index 0 is reverse, 1 is 1st gear, 2 is 2nd gear, etc.
    private final float[] manualGearRatios = { 3.97f, 5.25f, 3.27f, 2.19f, 1.60f, 1.30f, 1.00f, 0.78f, 0.65f, 0.58f,
            0.52f };
    private float inputRpm = 0; // RPM from the engine
    private float inputTorque; // Torque from the engine
    private int manualGear = 1; // -1 indicates no gear/neutral

    private float outputTorque; // Torque after transmission
    private float outputRpm; // RPM after transmission

    public void tick() {
        // Update the transmission state based on the current gear and input RPM
        if (manualGear >= 0 && manualGear < manualGearRatios.length) { // check if in reverse or forward gear(s)
            outputTorque = inputTorque * manualGearRatios[manualGear];
            outputRpm = inputRpm / manualGearRatios[manualGear];
        } else if (manualGear == -1) { // check if in neutral
            // Neutral gear, no output torque
            outputTorque = 0;
        } else {
            throw new IllegalArgumentException("Invalid gear selected: " + manualGear);
        }
    }

    // #region Getters
    public float[] getManualGearRatios() {
        return manualGearRatios;
    }

    public float getInputRpm() {
        return inputRpm;
    }

    public float getInputTorque() {
        return inputTorque;
    }

    public int getManualGear() {
        return manualGear;
    }

    public float getOutputTorque() {
        return outputTorque;
    }

    public float getOutputRpm() {
        return outputRpm;
    }
    // #endregion

    // #region Setters
    public void setInputRpm(float inputRpm) {
        this.inputRpm = inputRpm;
    }

    public void setInputTorque(float inputTorque) {
        this.inputTorque = inputTorque;
    }

    public void setManualGear(int manualGear) {
        if (manualGear < -1 || manualGear >= manualGearRatios.length) {
            throw new IllegalArgumentException("Invalid gear selected: " + manualGear);
        }
        this.manualGear = manualGear;
    }
    // #endregion

}
