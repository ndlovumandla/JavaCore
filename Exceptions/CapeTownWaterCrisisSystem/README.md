# Cape Town Water Crisis System

This project simulates Cape Town's Day Zero-era water rationing checks using Java exception handling. It reads household usage data from a file, calculates per-person daily usage, and applies a 50-litre limit. The design demonstrates checked and unchecked custom exceptions, plus try-catch-finally recovery so bad records do not crash the entire queue.

## Storyline

During Cape Town's water crisis period, municipal systems had to evaluate household usage against strict per-person limits. Data files contained valid records mixed with incomplete lines and invalid values. The processing engine throws targeted exceptions for bad inputs while continuing with recoverable records. This keeps compliance monitoring operational and transparent instead of failing the full run on the first malformed entry.

## Project Structure

```text
CapeTownWaterCrisisSystem/
├── data/
│   └── household_usage.csv
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── exceptions/
                            └── capetownwater/
                                ├── CapeTownWaterCrisisDemo.java
                                ├── UsageDataSource.java
                                ├── FileUsageDataSource.java
                                ├── WaterRationingEngine.java
                                └── WaterExceptions.java
```

## How to Run

From the CapeTownWaterCrisisSystem folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/exceptions/capetownwater/*.java
java -cp out com.ndlovu.javacore.exceptions.capetownwater.CapeTownWaterCrisisDemo
```

## Notes

- The demo reads from `data/household_usage.csv`.
- Some records are intentionally invalid to demonstrate failure handling and recovery.
