package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Represents one container scan payload from the Durban port intake queue.
 */
public record CargoContainerRecord(
        String containerId,
        String manifestReference,
        int scannedWeightKg,
        int billOfLadingWeightKg,
        String hazmatCode) {

    /**
     * Formats a short display value so logs can identify the record quickly.
     */
    public String displayKey() {
        return containerId + " / manifest=" + manifestReference;
    }
}
