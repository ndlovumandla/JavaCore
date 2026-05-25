package com.ndlovu.javacore.wrappers.property;

/**
 * Represents one municipal property valuation input used by Johannesburg rates billing.
 */
public class PropertyValuationRecord {

    private final String propertyReference;
    private final long landValueRand;
    private final double improvementValueRand;
    private final String zoneMultiplierText;

    /**
     * Stores raw valuation fields from source systems and config-driven multiplier text.
     */
    public PropertyValuationRecord(
            String propertyReference,
            long landValueRand,
            double improvementValueRand,
            String zoneMultiplierText) {
        this.propertyReference = propertyReference;
        this.landValueRand = landValueRand;
        this.improvementValueRand = improvementValueRand;
        this.zoneMultiplierText = zoneMultiplierText;
    }

    /**
     * Returns property reference used for reporting and zone-code parsing.
     */
    public String getPropertyReference() {
        return propertyReference;
    }

    /**
     * Returns land value captured as a large long from valuation intake.
     */
    public long getLandValueRand() {
        return landValueRand;
    }

    /**
     * Returns improvement value captured as a double for decimal precision.
     */
    public double getImprovementValueRand() {
        return improvementValueRand;
    }

    /**
     * Parses zone multiplier text from config into a float for billing arithmetic.
     */
    public float parseZoneMultiplier() {
        return Float.parseFloat(zoneMultiplierText);
    }
}
