package com.ndlovu.javacore.inheritance.joburgtaxi;

/**
 * Represents the luxury airport shuttle service that charges by distance.
 */
public final class AirportShuttle extends Vehicle {

    private final double ratePerKilometre;

    /**
     * Creates a shuttle and reuses the shared fleet details through super.
     */
    public AirportShuttle(String registrationNumber, String driverName, int fuelLevelPercent, int maxPassengers,
                          double ratePerKilometre) {
        super(registrationNumber, driverName, fuelLevelPercent, maxPassengers);
        this.ratePerKilometre = ratePerKilometre;
    }

    /**
     * Calculates a distance-based fare because the shuttle charges per kilometre.
     */
    @Override
    public double calculateFare(double tripDistanceKm) {
        return tripDistanceKm * ratePerKilometre;
    }

    /**
     * Reports the shuttle's daily readiness so airport trips stay reliable.
     */
    @Override
    public String dailyChecklist() {
        return "Check luggage space, clean seats, and confirm airport pickup timing.";
    }
}
