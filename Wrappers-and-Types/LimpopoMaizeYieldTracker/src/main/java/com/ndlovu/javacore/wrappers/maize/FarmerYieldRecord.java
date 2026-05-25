package com.ndlovu.javacore.wrappers.maize;

/**
 * Represents one farmer's seasonal maize yield entry captured by a field officer.
 */
public class FarmerYieldRecord {

    private final String farmerName;
    private final String districtName;
    private final String yieldKgPerHectareText;

    /**
     * Stores raw captured values exactly as they appear in the legacy database.
     */
    public FarmerYieldRecord(String farmerName, String districtName, String yieldKgPerHectareText) {
        this.farmerName = farmerName;
        this.districtName = districtName;
        this.yieldKgPerHectareText = yieldKgPerHectareText;
    }

    /**
     * Returns the farmer name for reporting and subsidy certificate output.
     */
    public String getFarmerName() {
        return farmerName;
    }

    /**
     * Returns district name used for grouping and code extraction.
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Parses legacy String yield into an Integer value for downstream processing.
     */
    public Integer parseYieldAsInteger() {
        // Integer wrapper conversion: parse String field from legacy database into number.
        return Integer.valueOf(yieldKgPerHectareText);
    }
}
