# Robben Island Museum Ticketing Kiosk Intermediate

This intermediate project demonstrates inner and nested classes through a realistic kiosk booking flow.

## Storyline

Robben Island Museum kiosk software handles booking sessions for tour visitors at the V&A Waterfront. A session carries critical details like selected ferry time, visitor count, ticket type, and payment state, and it should never exist separately from its kiosk. The model therefore uses a member inner class for booking sessions and a static nested immutable ticket record for printer handoff. Payment confirmation is wired through an anonymous listener to reflect real UI button handling.

## Includes
- Member inner class for active booking session
- Static nested immutable ticket record for handoff to printing
- Anonymous class for payment confirmation click listener
- Interface and abstract class for cleaner architecture
- Encapsulation of booking and payment state

## How to run
From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/nestedclasses/robbenintermediate/*.java
java -cp out com.ndlovu.javacore.nestedclasses.robbenintermediate.RobbenIslandMuseumTicketingKioskIntermediateDemo
```
