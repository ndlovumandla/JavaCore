package com.ndlovu.javacore.polymorphism.pretoriaemergency;

/**
 * Represents a shared emergency call in Pretoria's dispatch centre.
 */
public abstract class Emergency {

    protected final String incidentReference;
    protected final String location;
    protected final int reportedPriority;

    /**
     * Stores the common details that every emergency call needs.
     */
    protected Emergency(String incidentReference, String location, int reportedPriority) {
        this.incidentReference = incidentReference;
        this.location = location;
        this.reportedPriority = reportedPriority;
    }

    /**
     * Returns a shared summary so every emergency report starts with the same dispatch data.
     */
    protected String baseSummary() {
        return "Reference: " + incidentReference + "\n"
                + "Location: " + location + "\n"
                + "Priority: " + reportedPriority;
    }

    /**
     * Calculates the service-specific response in the child class so the dashboard can rely on runtime binding.
     */
    public abstract String respond();

    /**
     * Overloaded helper that lets the dispatcher preview a response with a custom eta adjustment.
     */
    public String respond(int extraMinutes) {
        return respond() + "\nAdjusted by: " + extraMinutes + " minutes";
    }
}
