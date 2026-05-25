package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Base checked exception for expected container validation failures.
 */
public class CargoValidationException extends Exception {

    /**
     * Creates a checked validation failure message for queue-level recovery handling.
     */
    public CargoValidationException(String message) {
        super(message);
    }
}
