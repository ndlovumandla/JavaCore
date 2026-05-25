# Joburg Property Valuation System

## Storyline

The City of Johannesburg rates department processes property valuations for municipal billing across many zones. Each record combines large land values, decimal improvement values, and zone multipliers loaded from String-based config files. The billing engine must parse text safely, perform mixed-type arithmetic, and convert results into statement-friendly rand amounts. It also moves data through collections, which introduces boxing and unboxing behavior throughout the pipeline. This project demonstrates wrapper classes and type conversion with clear beginner-focused examples.

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
JoburgPropertyValuationSystem/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── wrappers/
                            └── property/
                                ├── JoburgPropertyValuationSystemDemo.java
                                └── PropertyValuationRecord.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/wrappers/property/*.java
java -cp out com.ndlovu.javacore.wrappers.property.JoburgPropertyValuationSystemDemo
```
