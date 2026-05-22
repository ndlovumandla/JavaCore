package lastlighthousekeeper;

/**
 * Real-world role: models the physical lighthouse and equipment state Elias maintains.
 */
public class Lighthouse {
    private final String lighthouseName;
    private final String coastalLocation;
    private boolean beaconOperational;
    private int yearsMaintainedByElias;

    /**
     * Builds a lighthouse object with the current condition details.
     * This exists so each object starts with meaningful, complete story data.
     */
    public Lighthouse(
        String lighthouseName,
        String coastalLocation,
        boolean beaconOperational,
        int yearsMaintainedByElias
    ) {
        this.lighthouseName = lighthouseName;
        this.coastalLocation = coastalLocation;
        this.beaconOperational = beaconOperational;
        this.yearsMaintainedByElias = yearsMaintainedByElias;
    }

    /**
     * Returns the lighthouse name.
     * This exists so outside code can read private data without breaking encapsulation.
     */
    public String getLighthouseName() {
        return lighthouseName;
    }

    /**
     * Returns the geographic location.
     * This exists to expose contextual story information to reports.
     */
    public String getCoastalLocation() {
        return coastalLocation;
    }

    /**
     * Returns whether the beacon currently works.
     * This exists so maintenance logic can branch on real system state.
     */
    public boolean isBeaconOperational() {
        return beaconOperational;
    }

    /**
     * Returns the years Elias has maintained the tower.
     * This exists to preserve historical continuity in the digital record.
     */
    public int getYearsMaintainedByElias() {
        return yearsMaintainedByElias;
    }

    /**
     * Updates beacon status after inspection or repair.
     * This exists to keep state changes explicit and controlled through setters.
     */
    public void setBeaconOperational(boolean beaconOperational) {
        this.beaconOperational = beaconOperational;
    }

    /**
     * Updates the maintenance-year count as time advances.
     * This exists to model evolving object state across years.
     */
    public void setYearsMaintainedByElias(int yearsMaintainedByElias) {
        this.yearsMaintainedByElias = yearsMaintainedByElias;
    }

    /**
     * Returns a readable status report of the lighthouse.
     * This exists so the demo can narrate current conditions for learners.
     */
    public String describeStatus() {
        return "Lighthouse: " + lighthouseName
            + " | Location: " + coastalLocation
            + " | Beacon operational: " + beaconOperational
            + " | Elias years maintained: " + yearsMaintainedByElias;
    }
}
