package dev.bromine.car_simulator.car;

public class Wheel {
    private float radiusInch = 10f;
    private float radiusM = this.radiusInch * 0.0254f; // Convert inches to meters
    private float inputTorque; // Torque from the drivetrain
    private float linearForce; // Linear force at the wheel

    // #region Getters
    public float getRadiusInch() {
        return this.radiusInch;
    }

    public float getRadiusM() {
        return this.radiusM;
    }

    public float getInputTorque() {
        return this.inputTorque;
    }

    public float getLinearForce() {
        return this.linearForce;
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
    void calculateLinearForce() {
        // Calculate the linear force at the wheel based on the torque and radius
        linearForce = this.inputTorque / this.radiusM; // Torque = Force * Radius => Force = Torque / Radius
    }

    void tick() {
        // Update the wheel state based on the input torque
        this.calculateLinearForce();
    }

    public static void main(String[] args) {
    }
}
