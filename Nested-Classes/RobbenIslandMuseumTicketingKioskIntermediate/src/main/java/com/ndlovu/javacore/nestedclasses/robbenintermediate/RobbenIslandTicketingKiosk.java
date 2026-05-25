package com.ndlovu.javacore.nestedclasses.robbenintermediate;

/**
 * Represents one physical museum kiosk with session logic and printable ticket output.
 */
public class RobbenIslandTicketingKiosk extends AbstractMuseumKiosk {

    /**
     * Creates a kiosk with identity and payment dependency for live booking operations.
     */
    public RobbenIslandTicketingKiosk(String kioskCode, PaymentGateway paymentGateway) {
        super(kioskCode, paymentGateway);
    }

    /**
     * Opens a member inner booking session that is meaningful only inside this kiosk.
     */
    @Override
    public BookingSession startBookingSession(
            String selectedTourTime,
            int visitorCount,
            String ticketType,
            int ticketPriceCents) {
        System.out.println("Starting booking session on " + getKioskCode() + "...");
        return new BookingSession(selectedTourTime, visitorCount, ticketType, ticketPriceCents);
    }

    /**
     * Member inner class representing one active customer booking tied to this kiosk.
     */
    public class BookingSession {

        private final String selectedTourTime;
        private final int visitorCount;
        private final String ticketType;
        private final int ticketPriceCents;
        private final String bookingReference;
        private boolean paymentConfirmed;
        private String paymentStatusMessage;

        /**
         * Captures booking details so one kiosk can track one customer flow safely.
         */
        public BookingSession(String selectedTourTime, int visitorCount, String ticketType, int ticketPriceCents) {
            this.selectedTourTime = selectedTourTime;
            this.visitorCount = visitorCount;
            this.ticketType = ticketType;
            this.ticketPriceCents = ticketPriceCents;
            this.bookingReference = getKioskCode() + "-" + System.currentTimeMillis();
            this.paymentConfirmed = false;
            this.paymentStatusMessage = "Awaiting confirmation";
        }

        /**
         * Prints booking preview so visitors verify details before payment confirmation.
         */
        public void showBookingPreview() {
            System.out.println("Booking preview:");
            System.out.println("- Kiosk: " + getKioskCode());
            System.out.println("- Reference: " + bookingReference);
            System.out.println("- Tour time: " + selectedTourTime);
            System.out.println("- Visitors: " + visitorCount);
            System.out.println("- Ticket type: " + ticketType);
            System.out.println("- Total amount: R" + String.format("%.2f", calculateTotalAmountCents() / 100.0));
            System.out.println("- Payment: " + paymentStatusMessage);
        }

        /**
         * Calculates payable amount so payment and printing use one consistent total.
         */
        public int calculateTotalAmountCents() {
            return visitorCount * ticketPriceCents;
        }

        /**
         * Confirms payment via gateway so ticket creation is allowed only after authorization.
         */
        public void confirmPayment() {
            // Encapsulation: payment state is private and changed only through this domain method.
            paymentStatusMessage = getPaymentGateway().authorizePayment(bookingReference, calculateTotalAmountCents());
            paymentConfirmed = paymentStatusMessage.startsWith("APPROVED");
        }

        /**
         * Creates immutable ticket data after successful payment for handoff to printing module.
         */
        public TicketRecord createTicketRecord() {
            if (!paymentConfirmed) {
                throw new IllegalStateException("Payment not confirmed. Ticket cannot be printed.");
            }

            // Static nested class: immutable handoff object that does not need BookingSession reference.
            return new TicketRecord(
                    bookingReference,
                    getKioskCode(),
                    selectedTourTime,
                    visitorCount,
                    ticketType,
                    calculateTotalAmountCents(),
                    paymentStatusMessage);
        }
    }

    /**
     * Static nested immutable ticket object sent from kiosk to downstream print systems.
     */
    public static class TicketRecord {

        private final String bookingReference;
        private final String kioskCode;
        private final String selectedTourTime;
        private final int visitorCount;
        private final String ticketType;
        private final int totalAmountCents;
        private final String paymentStatusMessage;

        /**
         * Stores finalized booking snapshot so printing can run without mutable session state.
         */
        public TicketRecord(
                String bookingReference,
                String kioskCode,
                String selectedTourTime,
                int visitorCount,
                String ticketType,
                int totalAmountCents,
                String paymentStatusMessage) {
            this.bookingReference = bookingReference;
            this.kioskCode = kioskCode;
            this.selectedTourTime = selectedTourTime;
            this.visitorCount = visitorCount;
            this.ticketType = ticketType;
            this.totalAmountCents = totalAmountCents;
            this.paymentStatusMessage = paymentStatusMessage;
        }

        /**
         * Returns formatted ticket output for printer and operator logs.
         */
        public String printableLine() {
            return "Reference=" + bookingReference
                    + " | Kiosk=" + kioskCode
                    + " | Tour=" + selectedTourTime
                    + " | Visitors=" + visitorCount
                    + " | Type=" + ticketType
                    + " | Total=R" + String.format("%.2f", totalAmountCents / 100.0)
                    + " | Payment=" + paymentStatusMessage;
        }
    }
}
