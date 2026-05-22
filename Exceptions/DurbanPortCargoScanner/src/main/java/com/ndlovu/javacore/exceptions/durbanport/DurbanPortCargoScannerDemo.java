/*
 * Durban port scans thousands of cargo containers each day before they enter the yard.
 * Sanele is building a validator that checks ID format, manifest presence, declared weight, and hazmat codes.
 * A single bad record must not crash the full scanning queue, because operations cannot stop.
 * The solution uses custom checked and unchecked exceptions plus try-catch-finally handling around each scan.
 * The scanner always writes a log line in finally, even when validation fails.
 */

package com.ndlovu.javacore.exceptions.durbanport;

import java.util.List;

/**
 * Runs a queue-based simulation of the Durban port scanner with safe exception handling.
 */
public class DurbanPortCargoScannerDemo {

    /**
     * Scans sample container records, handles failures safely, and keeps queue processing alive.
     */
    public static void main(String[] args) {
        ContainerScanner containerScanner = new ContainerScanner();

        List<ContainerScanner.ContainerRecord> dailyScanQueue = List.of(
                new ContainerScanner.ContainerRecord("DUR-ABC-1200", "MAN-881", 18000, 19000, "NONE"),
                new ContainerScanner.ContainerRecord("BAD-12", "MAN-882", 17500, 18000, "NONE"),
                new ContainerScanner.ContainerRecord("DUR-XYZ-0099", "", 15000, 15000, "NONE"),
                new ContainerScanner.ContainerRecord("DUR-KLM-4501", "MAN-883", 22000, 20000, "NONE"),
                new ContainerScanner.ContainerRecord("DUR-TUV-7788", "MAN-884", 12000, 13000, "HZ999")
        );

        System.out.println("=== Port of Durban Cargo Scanner Queue ===\n");

        for (ContainerScanner.ContainerRecord queueContainer : dailyScanQueue) {
            System.out.println("Scanning container: " + queueContainer.containerId());

            try {
                // try: run risky validation logic that can throw many failure types.
                containerScanner.scanContainer(queueContainer);
                System.out.println("Result: PASSED validation and entered the port yard.");
            } catch (ContainerScanner.InvalidContainerIdException malformedIdException) {
                // catch specific checked exception first for precise recovery messaging.
                System.out.println("Result: REJECTED - " + malformedIdException.getMessage());
            } catch (ContainerScanner.ManifestMissingException missingManifestException) {
                System.out.println("Result: HOLD - " + missingManifestException.getMessage());
            } catch (ContainerScanner.WeightOverageException weightOverageException) {
                System.out.println("Result: FLAGGED - " + weightOverageException.getMessage());
            } catch (ContainerScanner.CargoValidationException genericCheckedException) {
                System.out.println("Result: VALIDATION ERROR - " + genericCheckedException.getMessage());
            } catch (ContainerScanner.UnknownHazmatCodeException unknownHazmatRuntimeException) {
                // catch unchecked exception to prevent unexpected runtime data from crashing the queue.
                System.out.println("Result: HAZMAT REVIEW - " + unknownHazmatRuntimeException.getMessage());
            } finally {
                // finally: always write an audit log regardless of pass/fail outcome.
                System.out.println("Port log: scan attempt captured for " + queueContainer.containerId());
                System.out.println();
            }
        }

        System.out.println("Queue completed: scanning continued even when individual records failed.");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - try: wraps risky validation work that can fail per container.
    - catch: handles each failure type so the queue can continue safely.
    - finally: always writes the port log, even when an exception is thrown.
    - throws: scanContainer declares checked exceptions that callers must handle.
    - custom exception: domain-specific exceptions describe real port failures clearly.
    - checked: CargoValidationException hierarchy forces explicit handling in compile-time flow.
    - unchecked: UnknownHazmatCodeException models unexpected runtime data problems.

    Interview questions this code helps answer:
    - What is the difference between checked and unchecked exceptions?
    - Why should catch blocks go from specific to general?
    - When is finally useful in production systems?
    - How do custom exceptions improve readability and maintenance?
    - Why use throws in method signatures?

    Common mistake and how this code avoids it:
    - Mistake: one broad catch(Exception) that hides root causes. This code uses specific custom exception types first, preserving clear recovery paths and debugging context.
    */
}
