package com.ndlovu.javacore.polymorphism.pretoriaemergency;

/**
 * Represents a medical emergency that requires ambulance and paramedic support.
 */
public final class MedicalEmergency extends Emergency {

    private final String patientCondition;
    private final boolean ambulanceAccess;

    /**
     * Creates a medical emergency and reuses the common call details with super.
     */
    public MedicalEmergency(String incidentReference, String location, int reportedPriority,
                            String patientCondition, boolean ambulanceAccess) {
        super(incidentReference, location, reportedPriority);
        this.patientCondition = patientCondition;
        this.ambulanceAccess = ambulanceAccess;
    }

    /**
     * Produces the medical response because the ambulance team needs triage and transport details.
     */
    @Override
    public String respond() {
        return baseSummary() + "\n"
                + "Service: Ambulance\n"
                + "Response: Send paramedics and prepare oxygen kit.\n"
                + "ETA: " + (8 - reportedPriority) + " minutes\n"
                + "Condition: " + patientCondition + ", ambulance access: " + ambulanceAccess;
    }
}
