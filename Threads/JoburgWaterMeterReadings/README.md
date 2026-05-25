# Joburg Water Meter Readings

This project simulates City of Johannesburg field agents uploading meter readings in parallel. It demonstrates Java multithreading basics using `Thread`, `Runnable`, `synchronized`, `sleep`, and `join`. The example also shows what a race condition looks like and how a deadlock risk is avoided by using a consistent lock order.

## Storyline

Johannesburg field teams capture household meter readings across different zones at the same time. A central municipal database receives uploads from multiple agents, which creates contention on shared state. The demo shows how unsynchronized updates can corrupt totals, then fixes the flow with synchronized critical sections and lock-order discipline. The main thread uses `join()` so final reports run only after all upload threads are finished.

## Project Structure

```text
JoburgWaterMeterReadings/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── threads/
                            └── watermeter/
                                ├── JoburgWaterMeterDemo.java
                                └── MunicipalWaterDatabase.java
```

## How to Run

From the `JoburgWaterMeterReadings` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/threads/watermeter/*.java
java -cp out com.ndlovu.javacore.threads.watermeter.JoburgWaterMeterDemo
```
