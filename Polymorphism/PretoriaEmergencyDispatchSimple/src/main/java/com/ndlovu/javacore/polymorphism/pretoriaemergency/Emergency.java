package com.ndlovu.javacore.polymorphism.pretoriaemergency;

/**
 * Represents one emergency call in Pretoria's dispatch centre.
 */
public abstract class Emergency {

    protected final String incidentReference;
    protected final String location;
    protected final int priorityLevel;

    /**
     * Stores the common details that every emergency call needs.
     */
    protected Emergency(String incidentReference, String location, int priorityLevel) {
        this.incidentReference = incidentReference;
        this.location = location;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Builds the shared report text so every emergency starts with the same dispatch details.
     */
    protected String baseSummary() {
        return "Reference: " + incidentReference + "\n"
                + "Location: " + location + "\n"
                + "Priority level: " + priorityLevel;
    }

    /**
     * Returns the service-specific response, which subclasses override so the dashboard can rely on runtime binding.
     */
    public abstract String respond();

    /**
     * Overloaded helper that adds a delay adjustment to the same response name for revision purposes.
     */
    public String respond(int extraMinutes) {
        return respond() + "\nAdjusted by: " + extraMinutes + " minutes";
    }
}
