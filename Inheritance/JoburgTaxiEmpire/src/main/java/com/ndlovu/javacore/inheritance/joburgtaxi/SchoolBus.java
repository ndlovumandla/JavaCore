package com.ndlovu.javacore.inheritance.joburgtaxi;

/**
 * Represents the school bus fleet that must complete safety checks before pickup.
 */
public final class SchoolBus extends Vehicle {

    private final String schoolName;
    private final double dailyContractFee;

    /**
     * Creates a school bus and passes the shared vehicle data to the parent class.
     */
    public SchoolBus(String registrationNumber, String driverName, int fuelLevelPercent, int maxPassengers,
                     String schoolName, double dailyContractFee) {
        super(registrationNumber, driverName, fuelLevelPercent, maxPassengers);
        this.schoolName = schoolName;
        this.dailyContractFee = dailyContractFee;
    }

    /**
     * Uses a daily contract fee because the school bus is paid as a service agreement.
     */
    @Override
    public double calculateFare(double tripDistanceKm) {
        return dailyContractFee;
    }

    /**
     * Reports the school bus safety check so the fleet can protect learners.
     */
    @Override
    public String dailyChecklist() {
        return "Check seat belts, emergency exits, and attendance list for " + schoolName + ".";
    }
}
