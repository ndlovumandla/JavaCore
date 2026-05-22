package com.ndlovu.javacore.polymorphism.johannesburgprintshop;

/**
 * Represents T-shirt or mug printing charged per unit with a setup fee.
 */
public final class MerchandisePrintJob extends PrintJob {

    private final String merchandiseType;
    private final int quantity;
    private final double unitPrice;
    private final double setupFee;

    /**
     * Creates a merchandise job and reuses the shared print-job details through the parent class.
     */
    public MerchandisePrintJob(String jobReference, String customerName, String merchandiseType,
                               int quantity, double unitPrice, double setupFee) {
        super(jobReference, customerName);
        this.merchandiseType = merchandiseType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.setupFee = setupFee;
    }

    /**
     * Calculates the merchandise price because each unit adds cost plus one setup fee.
     */
    @Override
    public double calculateCost() {
        return setupFee + (quantity * unitPrice);
    }
}
