# Smart Traffic Grid

This project models the City of Johannesburg's smart traffic pilot in Sandton. It uses a shared `TrafficDevice` interface to define the common contract (`activate`, `deactivate`, `reportStatus`) and an abstract base class to manage shared state logic. Concrete device classes then provide their own internal behaviour for traffic lights, pedestrian crossings, and emergency override sensors.

## Project Structure

```text
SmartTrafficGrid/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── abstraction/
                            └── smarttraffic/
                                ├── SmartTrafficGridDemo.java
                                ├── TrafficDevice.java
                                ├── RemoteMonitor.java
                                ├── AbstractTrafficDevice.java
                                ├── StandardTrafficLight.java
                                ├── PedestrianCrossing.java
                                └── EmergencyOverrideSensor.java
```

## How to Run

From the `SmartTrafficGrid` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/abstraction/smarttraffic/*.java
java -cp out com.ndlovu.javacore.abstraction.smarttraffic.SmartTrafficGridDemo
```

## Storyline

Naledi needs one command system that can control different traffic devices across Sandton intersections. By using an interface contract and an abstract base class, the city can issue the same commands to all devices while each type keeps its own hardware-specific behaviour.
