# Antarctic Research Station Serialization

## Storyline

SANAE IV, South Africa's Antarctic research base, runs for long periods without reliable satellite connectivity. The station's monitoring software tracks air quality, temperature zones, power status, alerts, and crew operations every hour. Because winter storms can cut communication for weeks, the system must frequently save full operating state to local disk. If a crash or power failure happens, the team must recover quickly from the last checkpoint. This project shows a beginner-friendly Java serialization flow for that exact recovery scenario.

## What This Project Demonstrates

- Serializable
- transient
- ObjectOutputStream
- ObjectInputStream
- serialVersionUID

## Project Structure

```text
AntarcticResearchStationSerialization/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── serialization/
                            └── antarctic/
                                ├── AntarcticResearchStationSerializationDemo.java
                                └── StationSystemSnapshot.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/serialization/antarctic/*.java
java -cp out com.ndlovu.javacore.serialization.antarctic.AntarcticResearchStationSerializationDemo
```

## Output Notes

- The demo writes a checkpoint file called `station_checkpoint.bin`.
- Sensitive personnel health data is marked `transient`, so it is not persisted.
- After recovery, the demo rebuilds missing transient data safely.
