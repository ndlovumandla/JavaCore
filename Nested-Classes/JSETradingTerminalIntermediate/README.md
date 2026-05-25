# JSE Trading Terminal Intermediate

This intermediate project demonstrates Java inner and nested classes in a more realistic trading flow.

## Storyline

The Johannesburg Stock Exchange runs fully digital terminals, each with its own internal order-book state. That order book is meaningful only within one terminal session, so it is modeled as a member inner class. Rorisang separates report handoff from terminal state by using a static nested immutable trade record. Terminal performance ranking then uses an anonymous implementation of the JSE comparison logic for report sorting.

## What is included
- Member inner class for each terminal's private order book
- Static nested immutable trade record for reporting
- Anonymous class implementing Comparator for performance ranking
- Encapsulation of terminal state and order metrics
- 2-3 interacting classes plus an interface

## How to run
From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/nestedclasses/jseintermediate/*.java
java -cp out com.ndlovu.javacore.nestedclasses.jseintermediate.JSETradingTerminalIntermediateDemo
```
