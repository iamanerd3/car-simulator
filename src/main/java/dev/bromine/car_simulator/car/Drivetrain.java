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

    void tick() {
        transmission.tick(); // Update transmission state
        calculatePowerDistribution(1f);

        finalDriveTorque = transmission.getOutputTorque() * finalDriveRatio; // Calculate final drive torque
        wheelMap.get("FL").setInputTorque(finalDriveTorque * frontWheelPowers[0]); // Set torque for front left wheel
        wheelMap.get("FR").setInputTorque(finalDriveTorque * frontWheelPowers[1]); // Set torque for front right wheel
        wheelMap.get("RL").setInputTorque(finalDriveTorque * rearWheelPowers[0]); // Set torque for rear left wheel
        wheelMap.get("RR").setInputTorque(finalDriveTorque * rearWheelPowers[1]); // Set torque for rear right wheel

        for (Wheel wheel : wheels) {
            wheel.tick(); // Update each wheel state
        }

        // Calculate total linear force at the wheels
        totalLinearForce = 0f;
        for (Wheel wheel : wheels) {
            totalLinearForce += wheel.getLinearForce(); // Sum up linear forces from all wheels
        }
    }

    // Power distribution logic
    public void calculatePowerDistribution(float enginePower) {
        // Split power between front and rear axles
        float[] axlePowers = transferCase.distributePower(enginePower);
        float frontAxlePower = axlePowers[0];
        float rearAxlePower = axlePowers[1];

        // Split power between left and right wheels
        frontWheelPowers = frontDifferential.distributePower(frontAxlePower);
        rearWheelPowers = rearDifferential.distributePower(rearAxlePower);
    }

    // #region Getters
    public float getFinalDriveRatio() {
        return finalDriveRatio;
    }

    public PowerSplitter getTransferCase() {
        return transferCase;
    }

    public PowerSplitter getFrontDifferential() {
        return frontDifferential;
    }

    public PowerSplitter getRearDifferential() {
        return rearDifferential;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public HashMap<String, Wheel> getWheelMap() {
        return wheelMap;
    }

    public float[] getFrontWheelPowers() {
        return frontWheelPowers;
    }

    public float[] getRearWheelPowers() {
        return rearWheelPowers;
    }

    public float getFinalDriveTorque() {
        return finalDriveTorque;
    }

    public float getTotalLinearForce() {
        return totalLinearForce;
    }
    // #endregion
}