# Cape Town Marathon Timing System

## Storyline

The Cape Town Marathon timing platform collects chip-reader events for thousands of runners across five checkpoints. RFID devices send raw millisecond values as long numbers, while pacing analytics need decimal calculations. Age category limits arrive from configuration files as Strings and must be parsed to integers before ranking logic can run. Results screens require formatted time text and clean display values for pace. Yusuf builds a beginner-friendly conversion pipeline that demonstrates wrapper classes and type conversion in a real race-day workflow.

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
CapeTownMarathonTimingSystem/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── wrappers/
                            └── marathon/
                                ├── CapeTownMarathonTimingSystemDemo.java
                                └── RunnerTimingRecord.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/wrappers/marathon/*.java
java -cp out com.ndlovu.javacore.wrappers.marathon.CapeTownMarathonTimingSystemDemo
```
