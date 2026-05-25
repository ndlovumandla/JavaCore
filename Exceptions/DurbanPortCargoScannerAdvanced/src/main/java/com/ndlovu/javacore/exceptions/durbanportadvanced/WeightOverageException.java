package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Checked exception thrown when scanned weight exceeds bill-of-lading declared weight.
 */
public class WeightOverageException extends CargoValidationException {

    /**
     * Creates a specific checked exception for weight overage violations.
     */
    public WeightOverageException(String message) {
        super(message);
    }
}
