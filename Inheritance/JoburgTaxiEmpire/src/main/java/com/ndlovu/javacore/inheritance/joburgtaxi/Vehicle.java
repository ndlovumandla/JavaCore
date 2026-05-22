package com.ndlovu.javacore.inheritance.joburgtaxi;

/**
 * Represents a shared fleet vehicle in Thandeka Transport's system.
 */
public abstract class Vehicle {

    protected final String registrationNumber;
    protected final String driverName;
    protected int fuelLevelPercent;
    protected final int maxPassengers;

    /**
     * Stores the common vehicle details that every fleet type needs.
     */
    protected Vehicle(String registrationNumber, String driverName, int fuelLevelPercent, int maxPassengers) {
        this.registrationNumber = registrationNumber;
        this.driverName = driverName;
        this.fuelLevelPercent = fuelLevelPercent;
        this.maxPassengers = maxPassengers;
    }

    /**
     * Refuels the vehicle so the demo can show shared admin logic.
     */
    public void refuel(int addedFuelPercent) {
        if (addedFuelPercent <= 0) {
            throw new IllegalArgumentException("Fuel added must be greater than zero.");
        }
        fuelLevelPercent = Math.min(100, fuelLevelPercent + addedFuelPercent);
    }

    /**
     * Builds the shared fleet summary that every subclass reuses.
     */
    protected String baseSummary() {
        return "Registration: " + registrationNumber + "\n"
                + "Driver: " + driverName + "\n"
                + "Fuel level: " + fuelLevelPercent + "%\n"
                + "Max passengers: " + maxPassengers;
    }

    /**
     * Calculates the day's fare so each vehicle type can apply its own pricing rule.
     */
    public abstract double calculateFare(double tripDistanceKm);

    /**
     * Performs the daily checklist so each vehicle type can report its own safety or route tasks.
     */
    public abstract String dailyChecklist();

    /**
     * Produces a human-readable daily fleet report for the demo.
     */
    public String dailyReport(double tripDistanceKm) {
        return baseSummary() + "\n"
                + "Fare for trip: R" + String.format("%.2f", calculateFare(tripDistanceKm)) + "\n"
                + "Checklist: " + dailyChecklist();
    }
}
