/*
 * Robben Island Museum runs self-service kiosks at the V&A Waterfront for fast tour bookings.
 * Each kiosk owns a live booking session that tracks tour time, visitor count, ticket type, and payment state.
 * That session only makes sense inside one kiosk, so it is modeled as a member inner class.
 * Nadine also creates a static nested ticket record so a clean immutable summary can be sent to printing.
 * Finally, the payment confirmation button uses an anonymous inner class as a click listener.
 */

package com.ndlovu.javacore.nestedclasses.robbenisland;

/**
 * Runs a beginner-friendly demonstration of inner and nested classes in a museum kiosk story.
 */
public class RobbenIslandMuseumTicketingKioskDemo {

    /**
     * Starts one kiosk booking flow and prints the resulting ticket record for study.
     */
    public static void main(String[] args) {
        TicketingKiosk waterfrontKiosk = new TicketingKiosk("Kiosk-VA-03");

        TicketingKiosk.BookingSession activeSession =
                waterfrontKiosk.startBookingSession("10:30 Ferry", 3, "Adult");

        activeSession.displaySessionPreview();

        ClickListener paymentConfirmButtonListener = new ClickListener() {
            /**
             * Confirms payment when the user clicks the button, then prints a human-readable result.
             */
            @Override
            public void onClick() {
                // Anonymous class: one-off click behavior for this specific payment button.
                activeSession.confirmPayment("Card Approved");
                System.out.println("Payment button clicked: card authorization accepted.");
            }
        };

        paymentConfirmButtonListener.onClick();

        TicketingKiosk.TicketRecord finalTicketRecord = activeSession.createTicketRecord();
        System.out.println();
        System.out.println("Printed Ticket Record:");
        System.out.println(finalTicketRecord.formattedLine());
    }

    /**
     * Represents a clickable UI behavior in the kiosk screen for simple event handling.
     */
    interface ClickListener {

        /**
         * Executes logic when a kiosk UI button is clicked.
         */
        void onClick();
    }

    /**
     * Represents one physical museum kiosk that owns and controls booking sessions.
     */
    static class TicketingKiosk {

        private final String kioskId;

        /**
         * Stores kiosk identity so sessions can be linked to the correct machine.
         */
        TicketingKiosk(String kioskId) {
            this.kioskId = kioskId;
        }

        /**
         * Creates a new member inner BookingSession tied to this kiosk instance.
         */
        BookingSession startBookingSession(String selectedTourTime, int visitorCount, String ticketType) {
            System.out.println("Opening session at " + kioskId + "...");
            return new BookingSession(selectedTourTime, visitorCount, ticketType);
        }

        /**
         * Member inner class representing a live booking session that belongs to one kiosk.
         */
        class BookingSession {

            private final String selectedTourTime;
            private final int visitorCount;
            private final String ticketType;
            private boolean paymentConfirmed;
            private String paymentStatusMessage;

            /**
             * Captures booking details for one customer flow inside the current kiosk.
             */
            BookingSession(String selectedTourTime, int visitorCount, String ticketType) {
                this.selectedTourTime = selectedTourTime;
                this.visitorCount = visitorCount;
                this.ticketType = ticketType;
                this.paymentConfirmed = false;
                this.paymentStatusMessage = "Awaiting payment";
            }

            /**
             * Prints session details so the customer can verify data before paying.
             */
            void displaySessionPreview() {
                System.out.println("Session preview for " + kioskId + ":");
                System.out.println("- Tour time: " + selectedTourTime);
                System.out.println("- Visitors: " + visitorCount);
                System.out.println("- Ticket type: " + ticketType);
                System.out.println("- Payment: " + paymentStatusMessage);
            }

            /**
             * Updates payment state after gateway confirmation to complete the booking.
             */
            void confirmPayment(String paymentStatusMessage) {
                // Encapsulation: payment fields are private and changed through one method only.
                this.paymentConfirmed = true;
                this.paymentStatusMessage = paymentStatusMessage;
            }

            /**
             * Builds an immutable static nested TicketRecord for printing and external transfer.
             */
            TicketRecord createTicketRecord() {
                if (!paymentConfirmed) {
                    throw new IllegalStateException("Cannot print ticket before payment confirmation.");
                }

                // Static nested class is ideal for a data snapshot that does not need outer instance state.
                return new TicketRecord(kioskId, selectedTourTime, visitorCount, ticketType, paymentStatusMessage);
            }
        }

        /**
         * Static nested class representing the immutable ticket data sent to the print module.
         */
        static class TicketRecord {

            private final String kioskId;
            private final String selectedTourTime;
            private final int visitorCount;
            private final String ticketType;
            private final String paymentStatus;

            /**
             * Stores finalized booking details in a transport-safe immutable object.
             */
            TicketRecord(
                    String kioskId,
                    String selectedTourTime,
                    int visitorCount,
                    String ticketType,
                    String paymentStatus) {
                this.kioskId = kioskId;
                this.selectedTourTime = selectedTourTime;
                this.visitorCount = visitorCount;
                this.ticketType = ticketType;
                this.paymentStatus = paymentStatus;
            }

            /**
             * Produces one readable line for logging or printing the museum ticket.
             */
            String formattedLine() {
                return "Kiosk=" + kioskId
                        + " | Tour=" + selectedTourTime
                        + " | Visitors=" + visitorCount
                        + " | Type=" + ticketType
                        + " | Payment=" + paymentStatus;
            }
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - inner class: BookingSession is an inner class that directly uses outer kiosk state.
    - member class: BookingSession is declared as a member of TicketingKiosk and models tightly coupled behavior.
    - static nested class: TicketRecord is static and immutable, ideal for shareable output data.
    - anonymous class: paymentConfirmButtonListener uses one-off inline behavior for a click event.
    - encapsulation: session/payment fields are private and controlled through clear methods.

    Interview questions this code prepares you to answer:
    - When should you use an inner class instead of a top-level class?
    - What is the difference between a member inner class and a static nested class?
    - Why are anonymous classes useful in UI event handling?
    - How does encapsulation improve correctness in stateful flows?

    Common mistake and how this code avoids it:
    - Mistake: exposing mutable session fields publicly and allowing invalid state transitions.
      Avoidance: payment and booking state remain private and ticket creation checks payment first.
    */
}
