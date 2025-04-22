package dev.bromine.car_simulator.car;

public class Car {
    private final Engine engine;
    private final Drivetrain drivetrain;

    private float acceleratorPedal;
    private float brakePedal;
    private float speedKmh;

    private float curbWeightLb = 4529f;
    private float curbWeightKg = curbWeightLb / 2.205f;

    private long lastTimeNano = System.nanoTime(); // store in nanoseconds

    public Car() {
        engine = new Engine();
        drivetrain = new Drivetrain();
    }

    public void tick() {
        long now = System.nanoTime();
        float deltaTime = (now - lastTimeNano) / 1_000_000_000f; // seconds
        lastTimeNano = now;

        engine.setGasIntake(acceleratorPedal);
        engine.tick();
        drivetrain.getTransmission().setInputTorque(engine.getOutputTorque());
        drivetrain.tick();

        // Calculate speed based on acceleration. Acceleration = Force / Mass
        // Speed = Speed + Acceleration * Time
        // Force = Torque / Radius (assuming a simple model where torque is converted to
        // linear force)
        // Multiply by 3.6 to convert m/s to km/h
        // Multiply by deltaTime to get the change in speed over the time interval
        speedKmh += (drivetrain.getTotalLinearForce() / curbWeightKg) * deltaTime * 3.6f;
        if (speedKmh < 0) {
            speedKmh = 0; // Prevent negative speed
        }
    }

    // #region Getters
    public Engine getEngine() {
        return engine;
    }

    public Drivetrain getDrivetrain() {
        return drivetrain;
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
}