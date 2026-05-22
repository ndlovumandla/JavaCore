package com.ndlovu.javacore.polymorphism.pretoriaemergency;

/**
 * Represents a structure fire that Pretoria's dispatch centre must route to firefighters.
 */
public final class FireEmergency extends Emergency {

    private final String buildingType;
    private final boolean peopleInside;

    /**
     * Creates a fire emergency and reuses the shared call details through super.
     */
    public FireEmergency(String incidentReference, String location, int priorityLevel,
                         String buildingType, boolean peopleInside) {
        super(incidentReference, location, priorityLevel);
        this.buildingType = buildingType;
        this.peopleInside = peopleInside;
    }

    /**
     * Produces the fire response because the fire service needs an evacuation plan and fire engine dispatch.
     */
    @Override
    public String respond() {
        // Polymorphism: the same method name behaves differently for each emergency type.
        return baseSummary() + "\n"
                + "Service: Fire Brigade\n"
                + "Response: Send fire engine and evacuation support.\n"
                + "ETA: " + (10 - priorityLevel) + " minutes\n"
                + "Target: " + buildingType + ", people inside: " + peopleInside;
    }
}
