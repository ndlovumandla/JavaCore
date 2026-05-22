package com.ndlovu.javacore.abstraction.hospitalbilling;

/**
 * Holds shared patient billing state used by all Medicross billing channels.
 */
public abstract class AbstractBillingChannel implements BillingMethod, ComplianceLoggable {

    protected final String patientName;
    protected final String treatmentCode;
    protected final double baseTreatmentAmount;

    /**
     * Stores common billing fields so concrete channels can focus on channel-specific rules.
     */
    protected AbstractBillingChannel(String patientName, String treatmentCode, double baseTreatmentAmount) {
        this.patientName = patientName;
        this.treatmentCode = treatmentCode;
        this.baseTreatmentAmount = baseTreatmentAmount;
    }

    /**
     * Builds a shared invoice header so all channels output a consistent format.
     */
    protected String invoiceHeader() {
        return "Patient: " + patientName + "\n"
                + "Treatment code: " + treatmentCode + "\n"
                + "Base amount: R" + String.format("%.2f", baseTreatmentAmount);
    }
}
