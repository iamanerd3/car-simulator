package dev.bromine.car_simulator.car;

public class Engine {
    private final float maxTorqueLbFt = 267f;
    private float outputTorque = 267;
    private final float outputRpm = 750;
    private final float maxTorqueNm = maxTorqueLbFt * 1.356f;
    private float gasIntake;

    public void tick() {
        outputTorque = maxTorqueNm * gasIntake;
    }

    // #region Getters
    public float getOutputTorque() {
        return outputTorque;
    }

    public float getOutputRpm() {
        return outputRpm;
    }

    public float getMaxTorqueNm() {
        return maxTorqueNm;
    }

    public float getGasIntake() {
        return gasIntake;
    }
    // #endregion

    // #region Setters
    public void setGasIntake(float gasIntake) {
        this.gasIntake = gasIntake;
    }
    // #endregion
}