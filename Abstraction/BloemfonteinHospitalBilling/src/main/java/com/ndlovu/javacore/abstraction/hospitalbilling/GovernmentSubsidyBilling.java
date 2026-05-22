package com.ndlovu.javacore.abstraction.hospitalbilling;

/**
 * Models billing through government subsidy schedules and capped patient contribution.
 */
public final class GovernmentSubsidyBilling extends AbstractBillingChannel {

    private final double subsidyAmount;

    /**
     * Creates a subsidy billing record with shared and channel-specific values.
     */
    public GovernmentSubsidyBilling(String patientName, String treatmentCode, double baseTreatmentAmount,
                                    double subsidyAmount) {
        super(patientName, treatmentCode, baseTreatmentAmount);
        this.subsidyAmount = subsidyAmount;
    }

    /**
     * Calculates payable amount after subsidy while preventing negative billing values.
     */
    @Override
    public double calculateAmount() {
        // Edge-case handling: no patient should be billed a negative amount.
        return Math.max(0.0, baseTreatmentAmount - subsidyAmount);
    }

    /**
     * Generates the subsidy invoice so finance can validate state-funded deductions.
     */
    @Override
    public String generateInvoice() {
        return invoiceHeader() + "\n"
                + "Channel: Government Subsidy\n"
                + "Subsidy value: R" + String.format("%.2f", subsidyAmount) + "\n"
                + "Patient payable: R" + String.format("%.2f", calculateAmount());
    }

    /**
     * Processes subsidy billing and confirms the final patient contribution.
     */
    @Override
    public String processPayment() {
        return "Subsidy applied and patient payment of R" + String.format("%.2f", calculateAmount()) + " processed.";
    }

    /**
     * Returns audit detail needed for public-sector billing compliance.
     */
    @Override
    public String complianceLog() {
        return "AUDIT[GovernmentSubsidy] patient=" + patientName + " treatment=" + treatmentCode;
    }
}
