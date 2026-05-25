# JSE Trading Terminal

This beginner project demonstrates Java inner and nested classes using a Johannesburg Stock Exchange terminal storyline.

## Storyline

At the JSE in Sandton, electronic terminals still reflect old trading-floor logic. Each terminal has an order book that tracks buy and sell instructions for that specific terminal only. Since the order book is tightly coupled to terminal context, it is implemented as a member inner class. Immutable trade records are represented with a static nested class, while terminal ranking uses an anonymous comparator implementation.

## Story Focus
- Each terminal contains its own order book that is private to that terminal.
- A member inner class models the order book because it only makes sense inside the terminal.
- A static nested class models immutable trade records that can be shared safely.
- An anonymous class implements terminal performance sorting logic for reports.

## Java Concepts Demonstrated
- inner class
- member class
- static nested class
- anonymous class
- encapsulation

## How to Run
From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/nestedclasses/jse/*.java
java -cp out com.ndlovu.javacore.nestedclasses.jse.JSETradingTerminalDemo
```
