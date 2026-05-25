package com.ndlovu.javacore.serialization.antarctic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents one complete SANAE IV monitoring checkpoint saved for crash recovery.
 */
public class StationSystemSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String stationCode;
    private final String checkpointTimestamp;
    private final int airQualityIndex;
    private final int powerLevelPercent;
    private final Map<String, Double> temperatureByZoneCelsius;
    private final List<String> activeAlerts;
    private final List<String> personnelOnDuty;
    private final Map<String, String> equipmentStatus;

    private transient Map<String, String> personnelHealthReadings;

    /**
     * Creates a snapshot object containing all non-sensitive system state for persistence.
     */
    public StationSystemSnapshot(
            String stationCode,
            String checkpointTimestamp,
            int airQualityIndex,
            int powerLevelPercent,
            Map<String, Double> temperatureByZoneCelsius,
            List<String> activeAlerts,
            List<String> personnelOnDuty,
            Map<String, String> equipmentStatus,
            Map<String, String> personnelHealthReadings) {
        this.stationCode = stationCode;
        this.checkpointTimestamp = checkpointTimestamp;
        this.airQualityIndex = airQualityIndex;
        this.powerLevelPercent = powerLevelPercent;
        this.temperatureByZoneCelsius = new HashMap<>(temperatureByZoneCelsius);
        this.activeAlerts = new ArrayList<>(activeAlerts);
        this.personnelOnDuty = new ArrayList<>(personnelOnDuty);
        this.equipmentStatus = new HashMap<>(equipmentStatus);

        // transient marks sensitive data so it is not serialized to disk.
        this.personnelHealthReadings = new HashMap<>(personnelHealthReadings);
    }

    /**
     * Returns a summary line for console reporting before and after recovery.
     */
    public String summaryLine() {
        return "Station=" + stationCode
                + " | Time=" + checkpointTimestamp
                + " | AQI=" + airQualityIndex
                + " | Power=" + powerLevelPercent + "%"
                + " | Alerts=" + activeAlerts.size()
                + " | CrewOnDuty=" + personnelOnDuty.size();
    }

    /**
     * Returns how many sensitive health entries are currently present in memory.
     */
    public int healthReadingCount() {
        return personnelHealthReadings == null ? 0 : personnelHealthReadings.size();
    }

    /**
     * Rebuilds transient health data after recovery to keep downstream checks safe.
     */
    public void rebuildTransientHealthDataWithPlaceholder() {
        if (personnelHealthReadings == null) {
            personnelHealthReadings = new HashMap<>();
        }
        if (personnelHealthReadings.isEmpty()) {
            personnelHealthReadings.put("Scientist-A", "Health data re-collected after restart");
        }
    }

    /**
     * Restores default transient field state when reading snapshot data from older or current files.
     */
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        // defaultReadObject restores all serializable fields from ObjectInputStream.
        inputStream.defaultReadObject();

        // Graceful recovery path for transient or missing fields from older software versions.
        if (personnelHealthReadings == null) {
            personnelHealthReadings = new HashMap<>();
        }
    }
}
