# Johannesburg Print Shop

This project follows PrintFast in Johannesburg, where Thandi's team handles document printing, banner printing, and merchandise printing. The old if-else billing logic has been replaced with a clean polymorphism example: one shared `PrintJob` type, three specialized subclasses, and one dashboard loop that calls `calculateCost()` without caring about the job type. It is kept intentionally simple for revision and first exposure.

## Project Structure

```text
JohannesburgPrintShop/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── polymorphism/
                            └── johannesburgprintshop/
                                ├── JohannesburgPrintShopDemo.java
                                ├── PrintJob.java
                                ├── DocumentPrintJob.java
                                ├── BannerPrintJob.java
                                └── MerchandisePrintJob.java
```

## How to Run

From the `JohannesburgPrintShop` folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/polymorphism/johannesburgprintshop/*.java
java -cp out com.ndlovu.javacore.polymorphism.johannesburgprintshop.JohannesburgPrintShopDemo
```

## Storyline

Thandi manages a print shop that used to rely on a fragile if-else billing chain. Siphamandla refactors the system so each print job knows how to price itself, and the billing dashboard just loops through the queue and calls `calculateCost()`. That design shows polymorphism, overriding, overloading, runtime binding, and method dispatch in a compact real-world example.
