package dev.bromine.car_simulator.car;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;

public class CarSimulator {
    static Car car = new Car();
    static float gasPedal = 0f;
    static boolean isGasPressed = false; // Flag to track if 'W' is held down
    static DecimalFormat df = new DecimalFormat("0.00"); // Format to always show leading zero

    public static void main(String[] args) {
        // Initialize the UI and input handling
        setupInputHandling();

        // Main simulation loop
        while (true) {
            updateGasPedal();
            car.setAcceleratorPedal(gasPedal);
            car.tick();
            System.out.print("\rSpeed: " + df.format(car.getSpeedKmh()) + " km/h | Gas Pedal: " + df.format(gasPedal));

            try {
                Thread.sleep(50); // Delay to control simulation speed
            } catch (InterruptedException e) {
            }
        }
    }

    // Method to set up input handling
    private static void setupInputHandling() {
        JFrame frame = new JFrame("Car Simulator");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyReleased(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Not used
            }
        });
    }

    // Method to handle key press events
    private static void handleKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            isGasPressed = true; // Set flag when 'W' is pressed
        }
    }

    // Method to handle key release events
    private static void handleKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            isGasPressed = false; // Clear flag when 'W' is released
        }
    }

    // Method to update the gas pedal value
    private static void updateGasPedal() {
        if (isGasPressed) {
            gasPedal = Math.min(gasPedal + 0.03f, 1f); // Increase gas pedal, max 1.0
        } else {
            gasPedal = Math.max(gasPedal - 0.05f, 0f); // Gradually decrease gas pedal
        }
    }
}
