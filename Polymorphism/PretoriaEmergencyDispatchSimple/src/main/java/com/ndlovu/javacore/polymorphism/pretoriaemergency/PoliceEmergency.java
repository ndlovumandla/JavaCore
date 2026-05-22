package com.ndlovu.javacore.polymorphism.pretoriaemergency;

/**
 * Represents an armed robbery or public safety incident handled by police.
 */
public final class PoliceEmergency extends Emergency {

    private final String threatLevel;
    private final boolean suspectArmed;

    /**
     * Creates a police emergency and passes the shared call details to the parent class.
     */
    public PoliceEmergency(String incidentReference, String location, int priorityLevel,
                           String threatLevel, boolean suspectArmed) {
        super(incidentReference, location, priorityLevel);
        this.threatLevel = threatLevel;
        this.suspectArmed = suspectArmed;
    }

    /**
     * Produces the police response because officers need a security-first dispatch plan.
     */
    @Override
    public String respond() {
        return baseSummary() + "\n"
                + "Service: Police\n"
                + "Response: Dispatch patrol cars and secure the scene.\n"
                + "ETA: " + (12 - priorityLevel) + " minutes\n"
                + "Threat: " + threatLevel + ", suspect armed: " + suspectArmed;
    }
}
