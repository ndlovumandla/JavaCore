package com.ndlovu.javacore.collections.foodbank;

/**
 * Represents a corporate donor supplying food parcels to the hub.
 */
public record Donor(String donorId, String donorName) {

    /**
     * Builds a short display line for dashboard output.
     */
    public String display() {
        return donorId + " - " + donorName;
    }
}
