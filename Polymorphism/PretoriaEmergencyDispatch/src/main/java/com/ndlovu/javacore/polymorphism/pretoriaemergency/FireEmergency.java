package com.ndlovu.javacore.polymorphism.pretoriaemergency;

/**
 * Represents a structure fire that the Pretoria dispatch centre must route to firefighters.
 */
public final class FireEmergency extends Emergency {

    private final String buildingType;
    private final boolean peopleInside;

    /**
     * Creates a fire emergency and passes the shared call data to the parent class using super.
     */
    public FireEmergency(String incidentReference, String location, int reportedPriority,
                         String buildingType, boolean peopleInside) {
        super(incidentReference, location, reportedPriority);
        this.buildingType = buildingType;
        this.peopleInside = peopleInside;
    }

    /**
     * Produces the fire response because the fire service needs fast evacuation and truck allocation.
     */
    @Override
    public String respond() {
        // Polymorphism: the same method name behaves differently for each emergency type.
        return baseSummary() + "\n"
                + "Service: Fire Brigade\n"
                + "Response: Send engine, ladder truck, and evacuation support.\n"
                + "ETA: " + (10 - reportedPriority) + " minutes\n"
                + "Target: " + buildingType + ", people inside: " + peopleInside;
    }
}
