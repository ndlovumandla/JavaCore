# Durban Port Cargo Scanner

This project demonstrates Java exception handling using a Durban port cargo-scanning scenario. It shows how a queue can continue processing even when individual container records fail validation. The scanner uses custom checked and unchecked exceptions, while the demo uses try-catch-finally to guarantee logging for every scan.

## Project Structure

```text
DurbanPortCargoScanner/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── exceptions/
                            └── durbanport/
                                ├── DurbanPortCargoScannerDemo.java
                                └── ContainerScanner.java
```

## How to Run

From the DurbanPortCargoScanner folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/exceptions/durbanport/*.java
java -cp out com.ndlovu.javacore.exceptions.durbanport.DurbanPortCargoScannerDemo
```

## What It Demonstrates

- try-catch-finally
- throws
- custom exception hierarchy
- checked vs unchecked exceptions
- safe queue processing without crashing the system
