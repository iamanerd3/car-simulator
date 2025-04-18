public class Transmission {
    // manual gear ratios, index 0 is reverse, 1 is 1st gear, 2 is 2nd gear, etc.
    private float[] manualGearRatios = { 3.97f, 5.25f, 3.27f, 2.19f, 1.60f, 1.30f, 1.00f, 0.78f, 0.65f, 0.58f, 0.52f };
    private float inputRpm = 0; // RPM from the engine
    private float inputTorque; // Torque from the engine
    private int manualGear = 1; // -1 indicates no gear/neutral

    private float outputTorque; // Torque after transmission
    private float outputRpm; // RPM after transmission

    // #region Getters
    public float[] getManualGearRatios() {
        return this.manualGearRatios;
    }

    public float getInputRpm() {
        return this.inputRpm;
    }

    public float getInputTorque() {
        return this.inputTorque;
    }

    public int getManualGear() {
        return this.manualGear;
    }

    public float getOutputTorque() {
        return this.outputTorque;
    }

    public float getOutputRpm() {
        return this.outputRpm;
    }
    // #endregion

    // #region Setters
    public void setInputRpm(float inputRpm) {
        this.inputRpm = inputRpm;
    }

    public void setInputTorque(float inputTorque) {
        this.inputTorque = inputTorque;
    }
    // #endregion

    public void tick() {
        // Update the transmission state based on the current gear and input RPM
        if (manualGear >= 0 && manualGear < manualGearRatios.length) { // check if in reverse or forward gear(s)
            this.outputTorque = this.inputTorque * this.manualGearRatios[this.manualGear];
            this.outputRpm = this.inputRpm / this.manualGearRatios[this.manualGear];
        } else if (manualGear == -1) { // check if in neutral
            // Neutral gear, no output torque
            this.outputTorque = 0;
        } else {
            throw new IllegalArgumentException("Invalid gear selected: " + this.manualGear);
        }
    }

    public static void main(String[] args) {

    }
}
