package com.ndlovu.javacore.inheritance.joburgtaxi;

/**
 * Represents Mama Thandeka's fixed-route minibus taxi service.
 */
public final class MinibusTaxi extends Vehicle {

    private final String fixedRouteName;
    private final double flatFare;

    /**
     * Creates a taxi and passes the shared fleet details to the parent class.
     */
    public MinibusTaxi(String registrationNumber, String driverName, int fuelLevelPercent, int maxPassengers,
                       String fixedRouteName, double flatFare) {
        super(registrationNumber, driverName, fuelLevelPercent, maxPassengers);
        this.fixedRouteName = fixedRouteName;
        this.flatFare = flatFare;
    }

    /**
     * Charges one flat fare because the taxi follows a fixed city route.
     */
    @Override
    public double calculateFare(double tripDistanceKm) {
        return flatFare;
    }

    /**
     * Reports the taxi's daily route check so the fleet can confirm it is ready.
     */
    @Override
    public String dailyChecklist() {
        return "Confirm route sign, count passengers, and keep the Soweto-to-CBD route clear. Route: " + fixedRouteName;
    }
}
