# Ranger Field Guide

This project follows Nomsa, a Kruger National Park ranger who has kept animal sightings for years in a personal field guide. Themba is building a Java app that replaces her spreadsheet with a cleaner system that treats mammals, birds, and reptiles as related but different animals. The shared `Animal` base class stores the common sighting data, while each subclass adds its own details and description rules. The example shows inheritance, `super`, overriding, the IS-A relationship, and polymorphism in a realistic flow.

## Project Structure

```text
RangerFieldGuide/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── inheritance/
                            └── rangerfieldguide/
                                ├── RangerFieldGuideDemo.java
                                ├── Animal.java
                                ├── Mammal.java
                                ├── Bird.java
                                └── Reptile.java
```

## How to Run

From the `RangerFieldGuide` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/inheritance/rangerfieldguide/*.java
java -cp out com.ndlovu.javacore.inheritance.rangerfieldguide.RangerFieldGuideDemo
```

## Storyline

Nomsa's field guide records species, date, GPS coordinates, and behavioural notes. Mammals, birds, and reptiles each need different extra facts, so Themba models a shared parent class and uses inheritance for the specialized animal groups. Each subclass overrides the description logic to produce a species-specific report for the ranger.
