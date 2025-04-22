package dev.bromine.car_simulator.car;

public class Wheel {
    private float radiusInch = 10f;
    private float radiusM = radiusInch * 0.0254f; // Convert inches to meters
    private float inputTorque; // Torque from the drivetrain
    private float linearForce; // Linear force at the wheel

    void tick() {
        // Update the wheel state based on the input torque
        calculateLinearForce();
    }

    void calculateLinearForce() {
        // Calculate the linear force at the wheel based on the torque and radius
        linearForce = inputTorque / radiusM; // Torque = Force * Radius => Force = Torque / Radius
    }

    // #region Getters
    public float getRadiusInch() {
        return radiusInch;
    }

    public float getRadiusM() {
        return radiusM;
    }

    public float getInputTorque() {
        return inputTorque;
    }

    public float getLinearForce() {
        return linearForce;
    }
    // #endregion

    // #region Setters
    public void setRadiusM(float radiusM) {
        this.radiusM = radiusM;
        this.radiusInch = radiusM / 0.0254f; // Convert meters to inches
    }

    public void setRadiusInch(float radiusInch) {
        this.radiusInch = radiusInch;
        this.radiusM = radiusInch * 0.0254f; // Convert inches to meters
    }

    public void setInputTorque(float inputTorque) {
        this.inputTorque = inputTorque;
    }
    // #endregion
}
