package dev.bromine.car_simulator.car;

import java.util.HashMap;

public class Drivetrain {

    // Power splitters for transfer case and differentials
    private final PowerSplitter transferCase = new PowerSplitter(); // Front/rear axle split
    private final PowerSplitter frontDifferential = new PowerSplitter(); // Left/right front wheels
    private final PowerSplitter rearDifferential = new PowerSplitter(); // Left/right rear wheels

    private final Transmission transmission = new Transmission(); // Transmission object
    private final Wheel[] wheels = new Wheel[4]; // Array of the 4 wheels that power gets distributed to
    private final HashMap<String, Wheel> wheelMap = new HashMap<>(); // Map of wheels for easy access

    private final float finalDriveRatio = 4.17f; // Final drive ratio for the drivetrain
    private float[] frontWheelPowers = new float[2]; // Power distribution for front wheels
    private float[] rearWheelPowers = new float[2]; // Power distribution for rear wheels
    private float finalDriveTorque;
    private float totalLinearForce; // Total linear force at the wheels

    Drivetrain() {
        // Initialize wheels and add them to the wheelMap
        for (int i = 0; i < wheels.length; i++) {
            wheels[i] = new Wheel(); // Initialize each wheel
            String wheelPosition = (i == 0 ? "FL" : (i == 1 ? "FR" : (i == 2 ? "RL" : i == 3 ? "RR" : "Wheel" + i))); // Assign
                                                                                                                      // wheel
                                                                                                                      // position
            // FL = Front Left, FR = Front Right, RL = Rear Left, RR = Rear Right, rest are
            // WheelX
            wheelMap.put(wheelPosition, wheels[i]); // Add each wheel to the map
        }
    }

    // #region Getters
    public float getFinalDriveRatio() {
        return this.finalDriveRatio;
    }

    public PowerSplitter getTransferCase() {
        return this.transferCase;
    }

    public PowerSplitter getFrontDifferential() {
        return this.frontDifferential;
    }

    public PowerSplitter getRearDifferential() {
        return this.rearDifferential;
    }

    public Transmission getTransmission() {
        return this.transmission;
    }

    public HashMap<String, Wheel> getWheelMap() {
        return this.wheelMap;
    }

    public float[] getFrontWheelPowers() {
        return this.frontWheelPowers;
    }

    public float[] getRearWheelPowers() {
        return this.rearWheelPowers;
    }

    public float getFinalDriveTorque() {
        return this.finalDriveTorque;
    }

    public float getTotalLinearForce() {
        return this.totalLinearForce;
    }
    // #endregion

    // Power distribution logic
    public void calculatePowerDistribution(float enginePower) {
        // Split power between front and rear axles
        float[] axlePowers = this.transferCase.distributePower(enginePower);
        float frontAxlePower = axlePowers[0];
        float rearAxlePower = axlePowers[1];

        // Split power between left and right wheels
        frontWheelPowers = this.frontDifferential.distributePower(frontAxlePower);
        rearWheelPowers = this.rearDifferential.distributePower(rearAxlePower);
    }

    void tick() {
        this.transmission.tick(); // Update transmission state
        this.calculatePowerDistribution(1f);

        finalDriveTorque = this.transmission.getOutputTorque() * this.finalDriveRatio; // Calculate final drive torque
        wheelMap.get("FL")
                .setInputTorque(finalDriveTorque * frontWheelPowers[0]); // Set torque for front left wheel
        wheelMap.get("FR")
                .setInputTorque(finalDriveTorque * frontWheelPowers[1]); // Set torque for front right wheel
        wheelMap.get("RL")
                .setInputTorque(finalDriveTorque * rearWheelPowers[0]); // Set torque for rear left wheel
        wheelMap.get("RR")
                .setInputTorque(finalDriveTorque * rearWheelPowers[1]); // Set torque for rear right wheel

        for (Wheel wheel : wheels) {
            wheel.tick(); // Update each wheel state
        }

        // Calculate total linear force at the wheels
        totalLinearForce = 0f;
        for (Wheel wheel : wheels) {
            totalLinearForce += wheel.getLinearForce(); // Sum up linear forces from all wheels
        }
    }

    public static void main(String[] args) {

    }
}