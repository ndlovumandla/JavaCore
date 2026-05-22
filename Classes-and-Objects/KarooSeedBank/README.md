# Karoo Seed Bank

## Storyline
Deep in the Karoo desert, botanist Dr. Fatima Hendricks manages a seed preservation vault containing hundreds of indigenous South African species threatened by climate change. For eleven years she tracked seed batches by hand in a paper binder. Each batch records species, collection date, viability percentage, GPS origin, and storage temperature requirements. University intern Sipho now digitises this process in Java before the annual audit. The program models each seed batch as a proper object and applies risk rules to produce audit-ready reports.

## Java Topic Demonstrated
This project focuses on **Classes and Objects** at advanced level, including:
- class
- object
- constructor
- encapsulation
- getter/setter

Additional advanced design elements:
- Strategy pattern (`BatchRiskPolicy`) for swappable audit logic
- Service layer (`SeedVaultService`) for orchestration and reporting
- Validation and edge-case handling for production-style robustness

## Project Structure
```text
KarooSeedBank/
	README.md
	src/main/java/
		KarooSeedBank.java
		karooseedbank/
			BatchRiskPolicy.java
			DefaultBatchRiskPolicy.java
			GeoCoordinate.java
			RiskLevel.java
			SeedBatch.java
			SeedVaultService.java
```

## How to Run
From the `KarooSeedBank` folder:

```powershell
javac -d out src\main\java\karooseedbank\*.java src\main\java\KarooSeedBank.java
java -cp out KarooSeedBank
```

## What You Will See
- Seed batches created as objects with validated constructor data.
- Endangered species registry and policy-based risk classification.
- Viability updates, including a safe missing-species edge case.
- A full storage audit report generated from encapsulated object data.