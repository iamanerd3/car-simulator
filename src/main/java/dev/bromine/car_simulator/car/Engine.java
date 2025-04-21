package dev.bromine.car_simulator.car;

public class Engine {
    private final float maxTorqueLbFt = 267f;
    private float outputTorque = 267;
    private final float outputRpm = 750;
    private final float maxTorqueNm = this.maxTorqueLbFt * 1.356f;
    private float gasIntake;
    // TODO: engine rpm

    // #region Getters
    public float getOutputTorque() {
        return this.outputTorque;
    }

    public float getOutputRpm() {
        return this.outputRpm;
    }

    public float getMaxTorqueNm() {
        return this.maxTorqueNm;
    }

    public float getGasIntake() {
        return this.gasIntake;
    }
    // #endregion

    // #region Setters
    public void setGasIntake(float gasIntake) {
        this.gasIntake = gasIntake;
    }
    // #endregion

    public void tick() {
        this.outputTorque = this.maxTorqueNm * this.gasIntake;
    }

    public static void main(String[] args) {
    }
}