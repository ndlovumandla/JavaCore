# Stellenbosch Wine Estate Harvest Log

This project models a March harvest log for Delaire Graff wine estate in Stellenbosch. It shows how Claudia can replace notebook-based tracking with Java Collections for search, grouping, sorting, and reporting. The demo keeps pick order in a LinkedList, groups yield by varietal in a HashMap, checks unique values with HashSet, builds report output with ArrayList, iterates records with Iterator, and uses Collections utility methods for sorting.

## Storyline

At a Stellenbosch wine estate, harvest teams record grape picks across varietals throughout the day. Manual notebook tracking causes delays when staff need grouped yield totals and sorted daily summaries. The digital log captures entries in order, groups by grape type, and prevents duplicate metadata issues. This gives cellar operations faster, cleaner reporting for production planning.

## Project Structure

```text
StellenboschWineEstateHarvestLog/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── collections/
                            └── stellenboschharvest/
                                ├── StellenboschHarvestLogDemo.java
                                └── HarvestEntry.java
```

## How to Run

From the StellenboschWineEstateHarvestLog folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/collections/stellenboschharvest/*.java
java -cp out com.ndlovu.javacore.collections.stellenboschharvest.StellenboschHarvestLogDemo
```

## Concepts Demonstrated

- ArrayList
- HashMap
- HashSet
- Iterator
- LinkedList
- Collections
