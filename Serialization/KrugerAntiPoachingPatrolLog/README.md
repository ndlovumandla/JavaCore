# Kruger Anti-Poaching Patrol Log

## Storyline

Kruger National Park anti-poaching teams carry rugged Android tablets while patrolling remote areas with no signal. During patrols, rangers record routes, sightings, snare finds, and suspect notes across many hours offline. If a tablet powers down or is damaged before sync, that patrol data must still be recoverable. Thabo solves this by serializing full patrol state to local storage and restoring it after restart. The design also protects sensitive ranger PIN data by marking it as transient.

## Concepts Demonstrated

- Serializable
- transient
- ObjectOutputStream
- ObjectInputStream
- serialVersionUID

## Project Structure

```text
KrugerAntiPoachingPatrolLog/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── serialization/
                            └── kruger/
                                ├── KrugerAntiPoachingPatrolLogDemo.java
                                └── PatrolLog.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/serialization/kruger/*.java
java -cp out com.ndlovu.javacore.serialization.kruger.KrugerAntiPoachingPatrolLogDemo
```

## Output Notes

- The demo writes local checkpoint data to `patrol_checkpoint.bin`.
- Ranger PIN is marked transient and is not persisted in the serialized file.
- The recovery routine restores partial patrol progress after simulated restart.
