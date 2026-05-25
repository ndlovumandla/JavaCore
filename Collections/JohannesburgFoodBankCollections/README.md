# Johannesburg Food Bank Collections

This project models FoodForward SA's Johannesburg hub and demonstrates practical use of the Java Collections Framework. It tracks donors, beneficiaries, and food parcels while showing why different collection types suit different access patterns. The example uses ordered intake tracking, keyed lookups, duplicate protection, queue-based dispatching, iterator-safe mutation, and utility operations from `Collections`.

## Storyline

FoodForward SA's Johannesburg hub coordinates daily food intake and parcel dispatch to beneficiary organizations. Volunteers must track donor deliveries, prevent duplicate records, and maintain dispatch order under time pressure. The application uses different collection types for the right job: ordering, uniqueness checks, fast lookups, and report sorting. This mirrors real logistics work where data structure choices directly affect operational accuracy.

## Project Structure

```text
JohannesburgFoodBankCollections/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── collections/
                            └── foodbank/
                                ├── JohannesburgFoodBankDemo.java
                                ├── FoodBankHubService.java
                                ├── FoodParcel.java
                                ├── Donor.java
                                ├── Beneficiary.java
                                └── FoodCategory.java
```

## How to Run

From the JohannesburgFoodBankCollections folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/collections/foodbank/*.java
java -cp out com.ndlovu.javacore.collections.foodbank.JohannesburgFoodBankDemo
```

## Concepts Demonstrated

- ArrayList
- HashMap
- HashSet
- Iterator
- LinkedList
- Collections utility methods
