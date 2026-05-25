# Robben Island Museum Ticketing Kiosk

This beginner project demonstrates Java inner and nested classes using a realistic museum kiosk scenario.

## Storyline

Robben Island Museum runs self-service ticket kiosks at the V&A Waterfront for ferry-tour bookings. Each kiosk owns one active booking session that tracks tour time, visitor count, ticket type, and payment status. Because a session only has meaning inside its kiosk, the design models it as a member inner class. Nadine also uses a static nested ticket record for printing and an anonymous click-listener class for payment confirmation.

## Story Focus
- A kiosk manages a booking session for one customer.
- The active booking session is modeled as a member inner class.
- Ticket records are modeled as a static nested class so they can be shared safely.
- Payment confirmation uses an anonymous inner class click listener.

## Java Concepts Demonstrated
- inner class
- member class
- static nested class
- anonymous class
- encapsulation

## How to Run
From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/nestedclasses/robbenisland/*.java
java -cp out com.ndlovu.javacore.nestedclasses.robbenisland.RobbenIslandMuseumTicketingKioskDemo
```
