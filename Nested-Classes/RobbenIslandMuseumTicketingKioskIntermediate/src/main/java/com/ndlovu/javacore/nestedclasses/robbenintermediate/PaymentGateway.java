package com.ndlovu.javacore.nestedclasses.robbenintermediate;

/**
 * Defines payment-authorization behavior used by kiosks before ticket printing.
 */
public interface PaymentGateway {

    /**
     * Attempts payment authorization so booking sessions can confirm valid payment status.
     */
    String authorizePayment(String bookingReference, int totalAmountCents);
}
