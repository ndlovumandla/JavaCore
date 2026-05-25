# Pretoria Book Swap Library

This project models a community book swap library in Hatfield, Pretoria. It demonstrates practical Java Collections choices for catalogue grouping, duplicate protection, queue tracking, iteration, and report sorting. The design uses a `HashMap` for genres to book lists, a `HashSet` for ISBN uniqueness, an `ArrayList` for grouped books, a `LinkedList` for swap activity order, and `Iterator` + `Collections` utilities for report generation.

## Storyline

In Hatfield, a community book-swap library tracks incoming titles and outgoing swaps every day. Coordinators need to group titles by genre, block duplicate ISBN entries, and preserve request order for fair exchanges. The system combines multiple collection types so each workflow is efficient and clear. End-of-day reports summarize library activity without manual notebook reconciliation.

## Project Structure

```text
PretoriaBookSwapLibrary/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── collections/
                            └── pretoriabookswap/
                                ├── PretoriaBookSwapDemo.java
                                ├── BookRecord.java
                                ├── GenreReportProvider.java
                                └── BookSwapLibraryService.java
```

## How to Run

From the PretoriaBookSwapLibrary folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/collections/pretoriabookswap/*.java
java -cp out com.ndlovu.javacore.collections.pretoriabookswap.PretoriaBookSwapDemo
```

## Concepts Demonstrated

- ArrayList
- HashMap
- HashSet
- Iterator
- LinkedList
- Collections
