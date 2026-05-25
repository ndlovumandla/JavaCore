package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Unchecked exception thrown for unexpected hazmat codes from runtime feed data.
 */
public class UnknownHazmatCodeException extends RuntimeException {

    /**
     * Creates a runtime exception message for unsupported hazmat values.
     */
    public UnknownHazmatCodeException(String message) {
        super(message);
    }
}
