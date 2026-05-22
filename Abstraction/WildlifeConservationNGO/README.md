# Wildlife Conservation NGO

This project models GreenSavanna NGO, which runs anti-poaching patrols, habitat restoration, and wildlife rehabilitation programmes. Every programme follows the same governance commands: generate a report, forecast budget, and notify the board when risk is critical. The design uses an abstract base class for shared logic and interface contracts for consistent behaviour, while each programme keeps its own calculations and report format.

## Project Structure

```text
WildlifeConservationNGO/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── abstraction/
                            └── wildlifeconservation/
                                ├── WildlifeConservationNGODemo.java
                                ├── ProgrammeGovernance.java
                                ├── RiskAuditable.java
                                ├── ConservationProgramme.java
                                ├── AntiPoachingProgramme.java
                                ├── HabitatRestorationProgramme.java
                                └── WildlifeRehabilitationProgramme.java
```

## How to Run

From the `WildlifeConservationNGO` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/abstraction/wildlifeconservation/*.java
java -cp out com.ndlovu.javacore.abstraction.wildlifeconservation.WildlifeConservationNGODemo
```

## Storyline

Olumide designs one governance framework for three very different conservation programmes. The board dashboard calls the same methods on each programme object, while abstraction hides the internal details of patrol metrics, habitat area recovery, and clinical rehabilitation outcomes.
