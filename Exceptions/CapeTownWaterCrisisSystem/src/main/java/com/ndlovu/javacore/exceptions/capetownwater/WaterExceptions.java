package com.ndlovu.javacore.exceptions.capetownwater;

/**
 * Base checked exception for expected validation failures in the water-rationing simulation.
 */
class WaterRationingException extends Exception {

    /**
     * Creates a checked exception message for recoverable rationing validation issues.
     */
    WaterRationingException(String message) {
        super(message);
    }
}

/**
 * Checked exception for unreadable or inaccessible usage files.
 */
class DataSourceReadException extends WaterRationingException {

    /**
     * Creates a checked data-source exception with a root cause for diagnostics.
     */
    DataSourceReadException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}

/**
 * Checked exception for malformed lines that break the expected data format.
 */
class CorruptDataFileException extends WaterRationingException {

    /**
     * Creates a checked exception message for malformed usage rows.
     */
    CorruptDataFileException(String message) {
        super(message);
    }
}

/**
 * Checked exception for rows that have no household identifier.
 */
class MissingHouseholdIdException extends WaterRationingException {

    /**
     * Creates a checked exception message for missing IDs.
     */
    MissingHouseholdIdException(String message) {
        super(message);
    }
}

/**
 * Checked exception for invalid negative usage values.
 */
class NegativeUsageException extends WaterRationingException {

    /**
     * Creates a checked exception message for negative litres.
     */
    NegativeUsageException(String message) {
        super(message);
    }
}

/**
 * Unchecked exception for overload conditions where the simulation must stop immediately.
 */
class SystemOverloadException extends RuntimeException {

    /**
     * Creates an unchecked overload exception message.
     */
    SystemOverloadException(String message) {
        super(message);
    }
}
