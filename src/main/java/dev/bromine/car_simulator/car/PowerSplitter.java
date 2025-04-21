package dev.bromine.car_simulator.car;

public class PowerSplitter {
    private float leftPowerDistribution;
    private float rightPowerDistribution;

    public PowerSplitter() {
        this(0.5f, 0.5f); // Default: 50% to the left and 50% to the right
    }

    public PowerSplitter(float leftOutputRatio, float rightOutputRatio) {
        validateAndSetRatios(leftOutputRatio, rightOutputRatio);
    }

    private void validateAndSetRatios(float leftPowerDistribution, float rightPowerDistribution) {
        if (leftPowerDistribution + rightPowerDistribution != 1.0f) {
            throw new IllegalArgumentException("Output ratios must add up to 1.0");
        }
        this.leftPowerDistribution = leftPowerDistribution;
        this.rightPowerDistribution = rightPowerDistribution;
    }

    public float getLeftPowerDistribution() {
        return leftPowerDistribution;
    }

    public float getRightPowerDistribution() {
        return rightPowerDistribution;
    }

    public float[] distributePower(float totalPower) {
        float leftPower = totalPower * leftPowerDistribution;
        float rightPower = totalPower * rightPowerDistribution;
        return new float[] { leftPower, rightPower };
    }
}