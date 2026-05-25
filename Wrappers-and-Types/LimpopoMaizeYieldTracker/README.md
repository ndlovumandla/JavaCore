# Limpopo Maize Yield Tracker

## Storyline

The Limpopo Department of Agriculture collects maize yield forms from thousands of smallholder farmers every season. Officers capture yields as whole-number kilograms per hectare on paper, but subsidy calculations require precise decimal arithmetic. The legacy database stores yields as String fields, so software must parse text back into numbers before calculations can run. Boitumelo builds a processing pipeline that converts types safely, compares yields to thresholds, rounds values for certificates, and prepares payment schedules. This project demonstrates wrapper classes and type conversion in a realistic agricultural workflow.

## Concepts Demonstrated

- Integer
- Double
- autoboxing
- widening
- narrowing
- casting
- Character

## Project Structure

```text
LimpopoMaizeYieldTracker/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── wrappers/
                            └── maize/
                                ├── LimpopoMaizeYieldTrackerDemo.java
                                └── FarmerYieldRecord.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/wrappers/maize/*.java
java -cp out com.ndlovu.javacore.wrappers.maize.LimpopoMaizeYieldTrackerDemo
```
