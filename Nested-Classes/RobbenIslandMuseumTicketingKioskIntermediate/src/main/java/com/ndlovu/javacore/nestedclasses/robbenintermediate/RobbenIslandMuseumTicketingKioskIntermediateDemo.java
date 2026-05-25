/*
 * Robben Island Museum operates self-service kiosks at the V&A Waterfront for tour bookings.
 * Each kiosk manages an active booking session with tour time, visitor count, ticket type, and payment state.
 * Nadine models that booking session as a member inner class because it only makes sense inside a kiosk.
 * She also uses a static nested immutable ticket record for safe transfer to the printing module.
 * For payment confirmation, she wires a click listener using an anonymous class to keep UI action logic close.
 * The result is a clean intermediate design that is practical, readable, and grounded in real kiosk behavior.
 */

package com.ndlovu.javacore.nestedclasses.robbenintermediate;

/**
 * Runs an intermediate nested-class simulation of museum booking and ticket printing.
 */
public class RobbenIslandMuseumTicketingKioskIntermediateDemo {

    /**
     * Executes a realistic booking flow from session preview to payment and ticket print output.
     */
    public static void main(String[] args) {
        PaymentGateway livePaymentGateway = new PaymentGateway() {
            /**
             * Simulates gateway authorization so kiosk sessions can move from pending to approved.
             */
            @Override
            public String authorizePayment(String bookingReference, int totalAmountCents) {
                if (totalAmountCents <= 0) {
                    return "DECLINED - Invalid amount for " + bookingReference;
                }
                return "APPROVED - Card token accepted for " + bookingReference;
            }
        };

        RobbenIslandTicketingKiosk waterfrontKiosk =
                new RobbenIslandTicketingKiosk("VA-KIOSK-04", livePaymentGateway);

        RobbenIslandTicketingKiosk.BookingSession activeBookingSession =
                waterfrontKiosk.startBookingSession("11:45 Ferry", 4, "Adult", 5600);

        activeBookingSession.showBookingPreview();

        ClickListener paymentConfirmButtonListener = new ClickListener() {
            /**
             * Handles payment-confirm button click to complete payment at the right step in flow.
             */
            @Override
            public void onClick() {
                // Anonymous class: one-off UI event behavior for this confirmation button.
                activeBookingSession.confirmPayment();
                System.out.println("Payment confirmation button clicked. Processing complete.");
            }
        };

        paymentConfirmButtonListener.onClick();

        RobbenIslandTicketingKiosk.TicketRecord finalTicketRecord = activeBookingSession.createTicketRecord();
        System.out.println();
        System.out.println("=== Ticket Print Output ===");
        System.out.println(finalTicketRecord.printableLine());
    }

    /**
     * Represents clickable kiosk UI behavior used for event-driven user actions.
     */
    interface ClickListener {

        /**
         * Executes the action tied to a specific kiosk button click.
         */
        void onClick();
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - inner class: BookingSession is attached to one kiosk instance and uses outer kiosk context.
    - member class: BookingSession is declared inside RobbenIslandTicketingKiosk as an owned component.
    - static nested class: TicketRecord is immutable and can travel to printing safely.
    - anonymous class: both PaymentGateway and ClickListener are implemented inline for one-off behavior.
    - encapsulation: booking and payment fields are private and updated via clear methods only.

    Interview questions this code prepares you to answer:
    - What is the difference between member inner classes and static nested classes?
    - Why would anonymous classes be useful in event-driven code?
    - How does encapsulation protect booking/payment state in a kiosk system?
    - When should you introduce an interface alongside nested classes?
    - What practical benefit does immutable output data provide?

    Common mistake and how this code avoids it:
    - Mistake: creating ticket output before payment is confirmed, causing invalid state.
      Avoidance: createTicketRecord throws if payment is not approved and payment state is private.
    */
}
