package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Unchecked exception thrown when queue size exceeds safe scanner throughput.
 */
public class ScannerOverloadException extends RuntimeException {

    /**
     * Creates a runtime overload exception message for protective queue shutdown.
     */
    public ScannerOverloadException(String message) {
        super(message);
    }
}
