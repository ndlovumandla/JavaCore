# Pretoria Emergency Dispatch

This project follows Pretoria's unified emergency dispatch centre, where one system handles fire, medical, and police callouts. When a call comes in, the dashboard logs it as an emergency and then calls the same `respond()` method on each job, letting Java choose the right procedure at runtime. The example is kept intentionally simple so it works well for revision and first exposure to polymorphism.

## Project Structure

```text
PretoriaEmergencyDispatchSimple/
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

From the `PretoriaEmergencyDispatchSimple` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/polymorphism/pretoriaemergency/*.java
java -cp out com.ndlovu.javacore.polymorphism.pretoriaemergency.PretoriaEmergencyDispatchDemo
```

## Storyline

Lebo and Ayanda are modernising Pretoria's old dispatch system. Fire, medical, and police emergencies all need different response procedures, so each type gets its own class. The dashboard stays clean because it stores everything as `Emergency` and lets polymorphism handle the rest.
