package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Checked exception thrown when a container ID does not match Durban port format rules.
 */
public class MalformedContainerIdException extends CargoValidationException {

    /**
     * Creates a specific checked exception for malformed IDs.
     */
    public MalformedContainerIdException(String message) {
        super(message);
    }
}
