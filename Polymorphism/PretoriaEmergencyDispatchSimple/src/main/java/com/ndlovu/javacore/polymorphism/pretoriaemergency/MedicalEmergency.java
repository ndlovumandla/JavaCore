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
    public MedicalEmergency(String incidentReference, String location, int priorityLevel,
                            String patientCondition, boolean ambulanceAccess) {
        super(incidentReference, location, priorityLevel);
        this.patientCondition = patientCondition;
        this.ambulanceAccess = ambulanceAccess;
    }

    /**
     * Produces the medical response because the ambulance team needs triage and transport information.
     */
    @Override
    public String respond() {
        return baseSummary() + "\n"
                + "Service: Ambulance\n"
                + "Response: Send paramedics and prepare oxygen kit.\n"
                + "ETA: " + (8 - priorityLevel) + " minutes\n"
                + "Condition: " + patientCondition + ", ambulance access: " + ambulanceAccess;
    }
}
