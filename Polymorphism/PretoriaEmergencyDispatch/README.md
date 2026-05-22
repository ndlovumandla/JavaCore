# Pretoria Emergency Dispatch

This project follows Pretoria's unified emergency dispatch centre, where one system handles fire, medical, and police callouts. The dispatcher logs every incident as an emergency, but each service type uses its own response procedure, arrival estimate, and resource plan. Ayanda's Java app demonstrates polymorphism by letting the dashboard call the same `respond()` method on different emergency types. The example keeps the code simple, readable, and focused on what changes at runtime.

## Project Structure

```text
PretoriaEmergencyDispatch/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── polymorphism/
                            └── pretoriaemergency/
                                ├── PretoriaEmergencyDispatchDemo.java
                                ├── Emergency.java
                                ├── FireEmergency.java
                                ├── MedicalEmergency.java
                                └── PoliceEmergency.java
```

## How to Run

From the `PretoriaEmergencyDispatch` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/polymorphism/pretoriaemergency/*.java
java -cp out com.ndlovu.javacore.polymorphism.pretoriaemergency.PretoriaEmergencyDispatchDemo
```

## Storyline

Lebo and Ayanda are modernising Pretoria's 20-year-old dispatch system. The dispatcher still logs incidents from a single dashboard, but fire, medical, and police emergencies each need their own response logic. This app shows how one parent type can drive different behaviour at runtime without long if-statements.
