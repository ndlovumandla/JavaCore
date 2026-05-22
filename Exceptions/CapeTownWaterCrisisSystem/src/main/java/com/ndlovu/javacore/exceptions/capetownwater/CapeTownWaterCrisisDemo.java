/*
 * Cape Town's Day Zero period forced strict water rationing to avoid municipal supply collapse.
 * Fatima had to flag households manually, but paperwork errors made response time too slow.
 * Aidan's simulation reads household usage records from a file and calculates litres per person per day.
 * The system must keep running even when some rows are corrupt, missing IDs, or have invalid values.
 * This demo uses custom checked and unchecked exceptions with try-catch-finally to protect the full queue.
 */

package com.ndlovu.javacore.exceptions.capetownwater;

import java.util.List;

/**
 * Runs the Day Zero rationing simulation and demonstrates robust queue-safe exception handling.
 */
public class CapeTownWaterCrisisDemo {

    /**
     * Loads usage data, processes each record, and logs every attempt without crashing the full run.
     */
    public static void main(String[] args) {
        UsageDataSource fileUsageDataSource = new FileUsageDataSource();
        WaterRationingEngine waterRationingEngine = new WaterRationingEngine();
        String dataFilePath = "data/household_usage.csv";

        System.out.println("=== Cape Town Day Zero Rationing Simulation ===\n");

        try {
            // try: data-file reading can fail and is handled as a checked exception.
            List<String> householdUsageLines = fileUsageDataSource.readUsageLines(dataFilePath);
            waterRationingEngine.guardAgainstOverload(householdUsageLines.size());

            int lineNumber = 1;
            for (String householdUsageLine : householdUsageLines) {
                System.out.println("Processing line " + lineNumber + ": " + householdUsageLine);

                try {
                    // try: evaluate each household independently so one bad row does not kill the queue.
                    WaterRationingEngine.HouseholdUsageResult householdResult =
                            waterRationingEngine.evaluateHouseholdRecord(householdUsageLine, lineNumber);

                    System.out.println("Result: household=" + householdResult.householdId()
                            + ", litres/person=" + String.format("%.2f", householdResult.litresPerPerson())
                            + ", breachedLimit=" + householdResult.limitBreached());
                } catch (MissingHouseholdIdException missingHouseholdIdException) {
                    // catch: specific checked exception path with targeted operational response.
                    System.out.println("Result: HOLD FOR REVIEW - " + missingHouseholdIdException.getMessage());
                } catch (NegativeUsageException negativeUsageException) {
                    System.out.println("Result: DATA CORRECTION NEEDED - " + negativeUsageException.getMessage());
                } catch (CorruptDataFileException corruptDataFileException) {
                    System.out.println("Result: CORRUPT RECORD - " + corruptDataFileException.getMessage());
                } finally {
                    // finally: always write a scan log line, regardless of success or failure.
                    System.out.println("Audit log: processed line " + lineNumber);
                    System.out.println();
                }

                lineNumber++;
            }

            System.out.println("Simulation completed: queue continued despite record-level failures.");
        } catch (DataSourceReadException dataSourceReadException) {
            System.out.println("FATAL: Could not load source data - " + dataSourceReadException.getMessage());
        } catch (SystemOverloadException systemOverloadException) {
            // catch unchecked exception to demonstrate graceful handling of runtime overload risks.
            System.out.println("FATAL: System overload protection triggered - " + systemOverloadException.getMessage());
        } finally {
            System.out.println("System log: Day Zero simulation run closed.");
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - try: wraps risky file reads and record parsing steps that may fail.
    - catch: handles each failure type with targeted recovery behavior.
    - finally: always writes logs, whether processing succeeds or fails.
    - throws: methods declare checked exceptions that callers must handle.
    - custom exception: domain-specific failures are represented with clear exception types.
    - checked: file and validation exceptions are compile-time enforced.
    - unchecked: overload is treated as a runtime condition with SystemOverloadException.

    Interview questions this code helps answer:
    - What is the difference between checked and unchecked exceptions?
    - Why should specific catch blocks come before general ones?
    - How does finally help operational reliability?
    - When should you create custom exceptions?
    - What is the purpose of throws in a method signature?

    Common mistake and how this code avoids it:
    - Mistake: stopping the whole batch when one record fails. This code isolates each record in its own try-catch-finally block so processing continues safely.
    */
}
