public class CarSimulator {
    static Car car = new Car();

    public static void main(String[] args) {
        car.setAcceleratorPedal(1f); // Press gas
        car.tick(); // Update car state

        System.out.println("Total Linear Force: " + car.getDrivetrain().getTotalLinearForce() + " N");
    }
}
