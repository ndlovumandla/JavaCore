package com.ndlovu.javacore.abstraction.hospitalbilling;

/**
 * Models direct private cash billing with a fixed administration fee.
 */
public final class PrivateCashBilling extends AbstractBillingChannel {

    private final double adminFee;

    /**
     * Creates a private cash billing record with shared and channel-specific values.
     */
    public PrivateCashBilling(String patientName, String treatmentCode, double baseTreatmentAmount,
                              double adminFee) {
        super(patientName, treatmentCode, baseTreatmentAmount);
        this.adminFee = adminFee;
    }

    /**
     * Calculates private patient amount by adding the administration fee.
     */
    @Override
    public double calculateAmount() {
        return baseTreatmentAmount + adminFee;
    }

    /**
     * Generates the private cash invoice used by front-desk billing.
     */
    @Override
    public String generateInvoice() {
        return invoiceHeader() + "\n"
                + "Channel: Private Cash\n"
                + "Admin fee: R" + String.format("%.2f", adminFee) + "\n"
                + "Patient payable: R" + String.format("%.2f", calculateAmount());
    }

    /**
     * Processes direct private payment for immediate settlement.
     */
    @Override
    public String processPayment() {
        return "Private payment of R" + String.format("%.2f", calculateAmount()) + " received and receipted.";
    }

    /**
     * Returns audit detail for private billing traceability.
     */
    @Override
    public String complianceLog() {
        return "AUDIT[PrivateCash] patient=" + patientName + " treatment=" + treatmentCode;
    }
}
