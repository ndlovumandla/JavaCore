# Durban Port Cargo Scanner Advanced

This project is an advanced Java exception-handling simulation for the Port of Durban container-entry queue. It demonstrates how a scanner can validate ID format, manifest linkage, bill-of-lading weight, and hazmat codes without stopping the full queue when one record fails. The design uses custom checked and unchecked exceptions, a validator-chain pattern, and guaranteed logging through finally blocks.

## Storyline

Durban port operations process high-volume container queues under strict safety rules. Every container must pass identity, manifest, weight, and hazmat validation before clearance. The scanner engine isolates failures per container using a custom exception hierarchy so one bad record does not block compliant freight. A validator chain and guaranteed logging keep the queue moving while preserving clear audit trails.

## Project Structure

```text
DurbanPortCargoScannerAdvanced/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── exceptions/
                            └── durbanportadvanced/
                                ├── DurbanPortCargoScannerAdvancedDemo.java
                                ├── CargoContainerRecord.java
                                ├── CargoScanEngine.java
                                ├── ValidatorChain.java
                                ├── ContainerValidator.java
                                ├── ContainerIdValidator.java
                                ├── ManifestValidator.java
                                ├── WeightValidator.java
                                ├── HazmatValidator.java
                                ├── PortScanLogger.java
                                ├── ConsolePortScanLogger.java
                                ├── CargoValidationException.java
                                ├── MalformedContainerIdException.java
                                ├── MissingManifestException.java
                                ├── WeightOverageException.java
                                ├── UnknownHazmatCodeException.java
                                └── ScannerOverloadException.java
```

## How to Run

From the DurbanPortCargoScannerAdvanced folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/exceptions/durbanportadvanced/*.java
java -cp out com.ndlovu.javacore.exceptions.durbanportadvanced.DurbanPortCargoScannerAdvancedDemo
```

## Highlights

- try, catch, finally
- throws declarations on checked validation rules
- custom exception hierarchy for scan failures
- checked and unchecked exception handling
- queue-safe processing with per-container isolation
