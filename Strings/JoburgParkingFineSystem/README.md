# Joburg Parking Fine System

## Storyline

The City of Joburg traffic department uses structured reference numbers to manage parking fines. Citizens submit these references online, but many entries arrive with spacing issues, wrong case, wrong separators, or missing prefixes. Before any lookup can happen, the system must normalize and validate each input string consistently. Rethabile builds a parser that trims whitespace, applies uppercase normalization, validates format length, and extracts core parts safely. This project demonstrates practical String API usage for real municipal workflows.

## Concepts Demonstrated

- String
- charAt
- substring
- Unicode
- ASCII
- length
- equals
- toUpperCase

## Project Structure

```text
JoburgParkingFineSystem/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── strings/
                            └── parking/
                                ├── JoburgParkingFineSystemDemo.java
                                └── ParkingFineReferenceParser.java
```

## How to Run

From this folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/strings/parking/*.java
java -cp out com.ndlovu.javacore.strings.parking.JoburgParkingFineSystemDemo
```
