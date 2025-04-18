import math

# Vehicle constants
T_max = 267  # Max engine torque in lb-ft
gear_ratios = [5.25, 3.27, 2.19, 1.60, 1.30, 1.00, 0.78, 0.65, 0.58, 0.52]  # Gear ratios
final_drive_ratio = 4.17  # Final drive ratio
tire_radius = 0.398  # Tire radius in meters (converted from inches)
curb_weight = 2042  # Vehicle weight in kg
drag_coefficient = 0.35  # Estimated drag coefficient
frontal_area = 2.5  # Frontal area in m²
rolling_resistance_coeff = 0.015  # Rolling resistance coefficient
air_density = 1.225  # Air density in kg/m³ (at sea level)

# Function to calculate force at a given throttle and speed
def calculate_force(throttle_percentage, gear, speed_kmh):
    # Convert speed to m/s
    speed_mps = speed_kmh / 3.6

    # Engine torque based on throttle percentage
    T_throttle = (throttle_percentage / 100) * T_max

    # Wheel torque
    G = gear_ratios[gear - 1]  # Get gear ratio for the selected gear
    T_wheel = T_throttle * G * final_drive_ratio

    # Wheel force
    F_wheel = T_wheel / tire_radius

    # Aerodynamic drag force
    F_drag = 0.5 * drag_coefficient * frontal_area * air_density * (speed_mps ** 2)

    # Rolling resistance force
    F_rolling = rolling_resistance_coeff * curb_weight * 9.81

    # Effective force
    F_effective = F_wheel - (F_drag + F_rolling)
    return F_effective

# Example: Calculate forces for different throttle inputs at 3rd gear and 60 km/h
throttle_inputs = [25, 50, 100]  # Throttle percentages
speed = 60  # Speed in km/h
gear = 3  # Selected gear

print("Throttle % | Effective Force (N)")
for throttle in throttle_inputs:
    force = calculate_force(throttle, gear, speed)
    print(f"{throttle}%        | {force:.2f} N")

# Given values
force_newtons = 1082.26  # Force in Newtons
mass_kg = 2054.32  # Mass in kilograms
time_seconds = 1  # Time duration in seconds

# Acceleration calculation (ignoring friction)
acceleration_m_per_s2 = force_newtons / mass_kg

# Final velocity calculation (m/s)
initial_velocity = 0  # Starting from rest
final_velocity_m_s = initial_velocity + (acceleration_m_per_s2 * time_seconds)

# Conversion from m/s to km/h
velocity_kmh = final_velocity_m_s * 3.6

# Conversion from m/s to mph
velocity_mph = final_velocity_m_s * 2.237

# Output
print(f"Velocity after 1 second:")
print(f"  - Velocity in m/s: {final_velocity_m_s:.2f} m/s")
print(f"  - Velocity in km/h: {velocity_kmh:.2f} km/h")
print(f"  - Velocity in mph: {velocity_mph:.2f} mph")
