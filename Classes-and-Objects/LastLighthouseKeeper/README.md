# The Last Lighthouse Keeper

## Storyline
In 1987, the remote Cape Solitude lighthouse off the South African coast was fully automated, and all keepers were dismissed except one: Elias Mokoena. The government allowed Elias to remain as a voluntary caretaker while the tower waited for final closure. For thirty years, he wrote detailed notes about ship sightings, weather shifts, and mechanical failures in fourteen paper notebooks. Priya, a young coastal engineer, is assigned to digitise those notebooks before the lighthouse is demolished. This Java program models that process by turning the lighthouse, ship logs, and weather readings into structured objects.

## Java Topic Demonstrated
This example focuses on **Classes and Objects**, including:
- class
- object
- constructor
- encapsulation
- getter/setter

It also includes one interface and one abstract class to show how intermediate-level object modeling works in a realistic scenario.

## What the Program Shows
- A package named `lastlighthousekeeper` so each class lives in its own file.
- A `Lighthouse` class with encapsulated state and controlled updates.
- A shared abstract `NotebookEntry` class for dated notebook records.
- Two concrete record types (`ShipLogEntry`, `WeatherReading`) that inherit common behavior.
- An `Archivable` interface to unify how different records are exported.
- A story-driven `main()` flow where Priya digitises entries, reacts to storm conditions, and updates lighthouse status.

## Package Structure
```text
LastLighthouseKeeper/
  src/main/java/
    LastLighthouseKeeper.java                (main app, imports package classes)
    lastlighthousekeeper/
      Archivable.java
      NotebookEntry.java
      Lighthouse.java
      ShipLogEntry.java
      WeatherReading.java
```

## How to Run
Open a terminal in this folder and run:

```powershell
javac -d out src\main\java\lastlighthousekeeper\*.java src\main\java\LastLighthouseKeeper.java
java -cp out LastLighthouseKeeper
```

Expected result:
- You will see narrated console output showing the lighthouse status,
- storm-driven maintenance decisions,
- and Priya's final archived records.

## Why This Matters
This exercise demonstrates how Java objects model real-world systems with clear responsibilities and safe state management. The same design principles are used in production software for domains like logistics, finance, healthcare, and IoT monitoring.
