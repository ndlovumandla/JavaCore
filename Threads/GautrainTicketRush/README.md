# Gautrain Ticket Rush

This project simulates morning tap-ins at Sandton station where multiple terminals deduct fares from a shared account database. It demonstrates how race conditions can cause incorrect balances and duplicate charging when reads and writes are unsynchronized. The redesigned version uses synchronized critical sections and thread coordination to keep balances consistent.

## Storyline

At Sandton station, many commuters tap in at once during morning peak. The legacy implementation updated balances unsafely, causing inconsistent fare deductions under concurrent traffic. The redesigned system models each terminal as a thread and protects shared account updates with synchronization. After all terminal threads complete, reporting compares unsafe and safe outcomes to show why thread safety matters.

## Project Structure

```text
GautrainTicketRush/
└── src/
    └── main/
        └── java/
            └── com/
                └── ndlovu/
                    └── javacore/
                        └── threads/
                            └── gautrain/
                                ├── GautrainTicketRushDemo.java
                                ├── FareDebitService.java
                                ├── SharedAccountDatabase.java
                                └── TurnstileTerminalTask.java
```

## How to Run

From the GautrainTicketRush folder:

```bash
javac -d out src/main/java/com/ndlovu/javacore/threads/gautrain/*.java
java -cp out com.ndlovu.javacore.threads.gautrain.GautrainTicketRushDemo
```

## Concepts Demonstrated

- Thread
- Runnable
- synchronized
- sleep
- join
- race condition
- deadlock (and prevention via lock ordering)
