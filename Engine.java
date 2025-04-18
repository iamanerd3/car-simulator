public class Engine {
    private float outputTorque = 267;
    private double outputRpm = 750;
    private final float maxTorqueLbFt = 267f;
    private final float maxTorqueNm = this.maxTorqueLbFt * 1.3558179483314f;
    private float gasIntake;

    // #region Getters
    public float getOutputTorque() {
        return this.outputTorque;
    }

    public double getOutputRpm() {
        return this.outputRpm;
    }

    public double getMaxTorqueNm() {
        return this.maxTorqueNm;
    }

    public double getGasIntake() {
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