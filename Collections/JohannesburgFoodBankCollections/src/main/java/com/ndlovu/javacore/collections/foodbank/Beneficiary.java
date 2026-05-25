package com.ndlovu.javacore.collections.foodbank;

/**
 * Represents a beneficiary organization receiving parcels based on need and location.
 */
public record Beneficiary(String beneficiaryId, String organisationName, int needScore, String zone) {

    /**
     * Builds a readable line for dispatch planning output.
     */
    public String display() {
        return organisationName + " (need=" + needScore + ", zone=" + zone + ")";
    }
}
