public class Car {
    private Engine engine;
    private Drivetrain drivetrain;

    private float acceleratorPedal;
    private float brakePedal;
    private float speedKmh;

    private float curbWeightLb = 4529f;
    private float curbWeightKg = this.curbWeightLb / 2.205f;

    public Car() {
        this.engine = new Engine();
        this.drivetrain = new Drivetrain();
    }

    // #region Getters
    public Engine getEngine() {
        return this.engine;
    }

    public Drivetrain getDrivetrain() {
        return this.drivetrain;
    }

    public float getAcceleratorPedal() {
        return acceleratorPedal;
    }

    public float getBrakePedal() {
        return brakePedal;
    }

    public float getSpeedKmh() {
        return speedKmh;
    }

    public float getCurbWeightLb() {
        return curbWeightLb;
    }

    public float getCurbWeightKg() {
        return curbWeightKg;
    }
    // #endregion

    // #region Setters
    public void setAcceleratorPedal(float acceleratorPedal) {
        this.acceleratorPedal = acceleratorPedal;
    }

    public void setBrakePedal(float brakePedal) {
        this.brakePedal = brakePedal;
    }

    public void setCurbWeightLb(float curbWeightLb) {
        this.curbWeightLb = curbWeightLb;
        this.curbWeightKg = curbWeightLb / 2.205f; // Update kg when lb is set
    }

    public void setCurbWeightKg(float curbWeightKg) {
        this.curbWeightKg = curbWeightKg;
        this.curbWeightLb = curbWeightKg * 2.205f; // Update lb when kg is set
    }
    // #endregion

    public void tick() {
        this.engine.setGasIntake(this.acceleratorPedal);
        this.engine.tick();
        this.drivetrain.getTransmission().setInputTorque(this.engine.getOutputTorque());
        this.drivetrain.tick();
    }

    public static void main(String[] args) {
    }
}