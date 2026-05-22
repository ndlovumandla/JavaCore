package com.ndlovu.javacore.abstraction.hospitalbilling;

/**
 * Defines the billing contract that every hospital payment channel must follow.
 */
public interface BillingMethod {

    /**
     * Calculates the payable amount so the billing desk can quote the patient correctly.
     */
    double calculateAmount();

    /**
     * Generates an invoice so finance records stay consistent across all channels.
     */
    String generateInvoice();

    /**
     * Processes payment so the channel can complete the billing cycle.
     */
    String processPayment();
}
