package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Checked exception thrown when manifest linkage is absent for a scanned container.
 */
public class MissingManifestException extends CargoValidationException {

    /**
     * Creates a specific checked exception for missing manifest data.
     */
    public MissingManifestException(String message) {
        super(message);
    }
}
