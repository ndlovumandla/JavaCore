# Joburg Taxi Empire

This project follows Mama Thandeka's transport company in Johannesburg. One shared vehicle system stores the common fleet details, while taxis, airport shuttles, and school buses each apply their own fare rules and daily checks. The example shows inheritance, `super`, overriding, the IS-A relationship, and polymorphism without getting too complicated.

## Project Structure

```text
JoburgTaxiEmpire/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── inheritance/
                            └── joburgtaxi/
                                ├── JoburgTaxiEmpireDemo.java
                                ├── Vehicle.java
                                ├── MinibusTaxi.java
                                ├── AirportShuttle.java
                                └── SchoolBus.java
```

## How to Run

From the `JoburgTaxiEmpire` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/inheritance/joburgtaxi/*.java
java -cp out com.ndlovu.javacore.inheritance.joburgtaxi.JoburgTaxiEmpireDemo
```

## Storyline

Mama Thandeka started with one minibus taxi in 1998 and grew Thandeka Transport into a mixed fleet by 2024. Lebo is building a system that can manage the whole fleet without treating every vehicle the same. The base `Vehicle` class keeps the shared details, and each subclass handles its own fare logic and checklist.
