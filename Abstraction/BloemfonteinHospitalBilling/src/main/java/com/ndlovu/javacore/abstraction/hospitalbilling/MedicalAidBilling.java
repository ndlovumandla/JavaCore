package com.ndlovu.javacore.abstraction.hospitalbilling;

/**
 * Models billing through medical aid claim rules and co-payment policy.
 */
public final class MedicalAidBilling extends AbstractBillingChannel {

    private final double coveredPercentage;

    /**
     * Creates a medical aid billing record with shared and channel-specific data.
     */
    public MedicalAidBilling(String patientName, String treatmentCode, double baseTreatmentAmount,
                             double coveredPercentage) {
        super(patientName, treatmentCode, baseTreatmentAmount);
        this.coveredPercentage = coveredPercentage;
    }

    /**
     * Calculates patient co-payment after aid coverage so staff can charge the correct balance.
     */
    @Override
    public double calculateAmount() {
        // Interface contract implementation: this class applies its own legal rate logic.
        return baseTreatmentAmount * (1.0 - coveredPercentage);
    }

    /**
     * Generates the medical aid invoice with coverage detail for claims validation.
     */
    @Override
    public String generateInvoice() {
        return invoiceHeader() + "\n"
                + "Channel: Medical Aid\n"
                + "Coverage: " + (coveredPercentage * 100) + "%\n"
                + "Patient payable: R" + String.format("%.2f", calculateAmount());
    }

    /**
     * Processes a medical aid claim payment to complete the billing transaction.
     */
    @Override
    public String processPayment() {
        return "Medical aid claim submitted and co-payment of R" + String.format("%.2f", calculateAmount()) + " recorded.";
    }

    /**
     * Returns audit detail for compliance and insurer traceability.
     */
    @Override
    public String complianceLog() {
        return "AUDIT[MedicalAid] patient=" + patientName + " treatment=" + treatmentCode;
    }
}
