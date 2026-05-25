/*
 * SANAE IV in Antarctica operates for months with limited network access during winter-over.
 * The station software tracks atmosphere, temperature zones, power, alerts, and crew operations.
 * Because satellite links can drop for long periods, checkpoint files are written to local disk.
 * If software crashes or power is cut, the latest checkpoint must restore station state quickly.
 * Micah uses Java serialization with version IDs and transient fields to protect sensitive health data.
 */

package com.ndlovu.javacore.serialization.antarctic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Runs a beginner-friendly SANAE IV checkpoint save and recovery simulation.
 */
public class AntarcticResearchStationSerializationDemo {

    private static final String CHECKPOINT_FILE = "station_checkpoint.bin";

    /**
     * Creates a station snapshot, writes it to disk, reloads it, and prints recovery results.
     */
    public static void main(String[] args) {
        StationSystemSnapshot liveSnapshot = buildLiveSnapshot();

        System.out.println("=== SANAE IV Checkpoint Save ===");
        System.out.println("Live snapshot: " + liveSnapshot.summaryLine());
        System.out.println("Sensitive health entries in memory: " + liveSnapshot.healthReadingCount());

        saveCheckpoint(liveSnapshot, CHECKPOINT_FILE);

        System.out.println();
        System.out.println("=== SANAE IV Recovery ===");

        StationSystemSnapshot recoveredSnapshot = loadCheckpoint(CHECKPOINT_FILE);
        if (recoveredSnapshot == null) {
            System.out.println("Recovery failed. Starting with emergency defaults.");
            return;
        }

        System.out.println("Recovered snapshot: " + recoveredSnapshot.summaryLine());
        System.out.println("Health entries after deserialize (transient): " + recoveredSnapshot.healthReadingCount());

        recoveredSnapshot.rebuildTransientHealthDataWithPlaceholder();
        System.out.println("Health entries after rebuild: " + recoveredSnapshot.healthReadingCount());
    }

    /**
     * Builds realistic station state that will be serialized for local disaster recovery.
     */
    private static StationSystemSnapshot buildLiveSnapshot() {
        Map<String, Double> temperatureByZone = new HashMap<>();
        temperatureByZone.put("Lab-A", 20.3);
        temperatureByZone.put("Server-Room", 17.8);
        temperatureByZone.put("Habitation-Module", 22.1);

        List<String> activeAlerts = List.of(
                "Battery bank maintenance due in 6 hours",
                "External wind gusts above threshold");

        List<String> personnelOnDuty = List.of(
                "Dr. Mokoena",
                "Engineer Naidoo",
                "Medic Dlamini");

        Map<String, String> equipmentStatus = new HashMap<>();
        equipmentStatus.put("OxygenScrubber-01", "Operational");
        equipmentStatus.put("DieselGenerator-02", "Standby");
        equipmentStatus.put("SatelliteUplink", "Unavailable - weather interference");

        Map<String, String> sensitiveHealthReadings = new HashMap<>();
        sensitiveHealthReadings.put("Dr. Mokoena", "Pulse stable, stress moderate");
        sensitiveHealthReadings.put("Engineer Naidoo", "Pulse stable, stress low");
        sensitiveHealthReadings.put("Medic Dlamini", "Pulse elevated after storm checks");

        return new StationSystemSnapshot(
                "SANAE-IV",
                "2026-05-25T21:00:00Z",
                38,
                87,
                temperatureByZone,
                activeAlerts,
                personnelOnDuty,
                equipmentStatus,
                sensitiveHealthReadings);
    }

    /**
     * Writes snapshot bytes to disk so station state survives process crashes or power outages.
     */
    private static void saveCheckpoint(StationSystemSnapshot stationSnapshot, String checkpointFilePath) {
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(checkpointFilePath))) {
            // ObjectOutputStream serializes the full object graph of serializable fields.
            objectOutputStream.writeObject(stationSnapshot);
            System.out.println("Checkpoint written to " + checkpointFilePath);
        } catch (IOException ioException) {
            System.out.println("Checkpoint write failed: " + ioException.getMessage());
        }
    }

    /**
     * Reads checkpoint bytes and reconstructs station state with backward-compatible recovery handling.
     */
    private static StationSystemSnapshot loadCheckpoint(String checkpointFilePath) {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(checkpointFilePath))) {
            // ObjectInputStream reconstructs the serialized object from disk bytes.
            return (StationSystemSnapshot) objectInputStream.readObject();
        } catch (InvalidClassException invalidClassException) {
            // serialVersionUID mismatch handling for files from incompatible software builds.
            System.out.println("Checkpoint version mismatch: " + invalidClassException.getMessage());
            return null;
        } catch (IOException | ClassNotFoundException generalException) {
            System.out.println("Checkpoint read failed: " + generalException.getMessage());
            return null;
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Serializable: StationSystemSnapshot implements Serializable so checkpoint objects can be written to disk.
    - transient: sensitive personnel health readings are excluded from persisted checkpoint files.
    - ObjectOutputStream: writes object state into a binary checkpoint file.
    - ObjectInputStream: rebuilds object state from the checkpoint file.
    - serialVersionUID: version ID supports compatibility checks across software versions.

    Interview questions this code prepares you to answer:
    - Why do Java classes need Serializable for object persistence?
    - What kinds of fields should be marked transient and why?
    - What role does serialVersionUID play during deserialization?
    - How do ObjectOutputStream and ObjectInputStream work together?
    - How should an application recover when a serialized file is from an incompatible class version?

    Common mistake and how this code avoids it:
    - Mistake: assuming transient fields are restored after deserialization.
      Avoidance: the code checks and rebuilds transient health data safely after recovery.
    */
}
