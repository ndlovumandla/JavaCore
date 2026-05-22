package com.ndlovu.javacore.exceptions.durbanport;

import java.util.Set;

/**
 * Validates container records for the Durban port scanning engine.
 */
public class ContainerScanner {

    private static final Set<String> ALLOWED_HAZMAT_CODES = Set.of("HZ100", "HZ220", "HZ330");

    /**
     * Validates one container record and throws specific exceptions when a rule fails.
     */
    public void scanContainer(ContainerRecord containerRecord)
            throws CargoValidationException, InvalidContainerIdException,
            ManifestMissingException, WeightOverageException {
        // Checked exception path: container ID must follow a known pattern.
        if (!containerRecord.containerId().matches("^DUR-[A-Z]{3}-\\d{4}$")) {
            throw new InvalidContainerIdException("Invalid ID format: " + containerRecord.containerId());
        }

        // Checked exception path: every scanned container must have a manifest number.
        if (containerRecord.manifestReference() == null || containerRecord.manifestReference().isBlank()) {
            throw new ManifestMissingException("Missing manifest for container " + containerRecord.containerId());
        }

        // Checked exception path: scanned weight cannot exceed bill-of-lading weight.
        if (containerRecord.scannedWeightKg() > containerRecord.bolWeightKg()) {
            throw new WeightOverageException(
                    "Weight overage for " + containerRecord.containerId()
                            + ": scanned=" + containerRecord.scannedWeightKg()
                            + "kg, declared=" + containerRecord.bolWeightKg() + "kg");
        }

        // Unchecked exception path: unknown hazmat code represents bad runtime data.
        if (!containerRecord.hazmatCode().equals("NONE") && !ALLOWED_HAZMAT_CODES.contains(containerRecord.hazmatCode())) {
            throw new UnknownHazmatCodeException("Unknown hazmat code: " + containerRecord.hazmatCode());
        }
    }

    /**
     * Represents one container scan record in the port queue.
     */
    public record ContainerRecord(
            String containerId,
            String manifestReference,
            int scannedWeightKg,
            int bolWeightKg,
            String hazmatCode) {
    }

    /**
     * Base checked exception for validation failures that callers are expected to handle.
     */
    public static class CargoValidationException extends Exception {

        /**
         * Creates a checked validation exception message for queue-level handling.
         */
        public CargoValidationException(String message) {
            super(message);
        }
    }

    /**
     * Checked exception for malformed container IDs.
     */
    public static class InvalidContainerIdException extends CargoValidationException {

        /**
         * Creates a detailed message for malformed ID failures.
         */
        public InvalidContainerIdException(String message) {
            super(message);
        }
    }

    /**
     * Checked exception for missing manifest references.
     */
    public static class ManifestMissingException extends CargoValidationException {

        /**
         * Creates a detailed message for manifest lookup failures.
         */
        public ManifestMissingException(String message) {
            super(message);
        }
    }

    /**
     * Checked exception for bill-of-lading weight mismatch.
     */
    public static class WeightOverageException extends CargoValidationException {

        /**
         * Creates a detailed message for overage failures.
         */
        public WeightOverageException(String message) {
            super(message);
        }
    }

    /**
     * Unchecked exception for unexpected hazmat values from external runtime data.
     */
    public static class UnknownHazmatCodeException extends RuntimeException {

        /**
         * Creates a runtime exception message for unknown hazmat codes.
         */
        public UnknownHazmatCodeException(String message) {
            super(message);
        }
    }
}
