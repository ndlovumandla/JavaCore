package com.ndlovu.javacore.nestedclasses.robbenintermediate;

/**
 * Provides shared kiosk identity and payment dependencies for Robben Island terminals.
 */
public abstract class AbstractMuseumKiosk {

    private final String kioskCode;
    private final PaymentGateway paymentGateway;

    /**
     * Stores kiosk identity and payment gateway used by booking sessions.
     */
    protected AbstractMuseumKiosk(String kioskCode, PaymentGateway paymentGateway) {
        this.kioskCode = kioskCode;
        this.paymentGateway = paymentGateway;
    }

    /**
     * Exposes kiosk code for user-facing logs and immutable ticket records.
     */
    public String getKioskCode() {
        return kioskCode;
    }

    /**
     * Exposes payment gateway so concrete kiosks can authorize booking payments.
     */
    public PaymentGateway getPaymentGateway() {
        return paymentGateway;
    }

    /**
     * Starts a new booking session scoped to one kiosk and one visitor party.
     */
    public abstract RobbenIslandTicketingKiosk.BookingSession startBookingSession(
            String selectedTourTime,
            int visitorCount,
            String ticketType,
            int ticketPriceCents);
}
