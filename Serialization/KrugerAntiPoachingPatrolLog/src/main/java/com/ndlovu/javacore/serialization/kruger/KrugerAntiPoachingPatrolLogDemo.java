/*
 * Kruger anti-poaching teams patrol remote zones for long hours without mobile signal.
 * Rangers record sightings, snare finds, and suspect details on rugged tablets during patrol.
 * If a tablet restarts before base sync, patrol progress must still be recoverable from local storage.
 * Thabo uses Java serialization to checkpoint the full patrol log and reload it after restart.
 * Sensitive ranger PIN data is marked transient so it is protected and not written to disk.
 */

package com.ndlovu.javacore.serialization.kruger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Demonstrates beginner-friendly patrol-log checkpoint save and recovery in Kruger operations.
 */
public class KrugerAntiPoachingPatrolLogDemo {

    private static final String PATROL_CHECKPOINT_FILE = "patrol_checkpoint.bin";

    /**
     * Runs full flow: create partial log, serialize checkpoint, deserialize after restart, and continue.
     */
    public static void main(String[] args) {
        PatrolLog activePatrolLog = createPartialPatrolLog();

        System.out.println("=== Patrol Tablet Before Restart ===");
        System.out.println("Active patrol: " + activePatrolLog.summaryLine());
        System.out.println("PIN in memory before save: " + activePatrolLog.hasPinInMemory());

        savePatrolCheckpoint(activePatrolLog, PATROL_CHECKPOINT_FILE);

        System.out.println();
        System.out.println("=== Tablet Restart Recovery ===");

        PatrolLog recoveredPatrolLog = loadPatrolCheckpoint(PATROL_CHECKPOINT_FILE);
        if (recoveredPatrolLog == null) {
            System.out.println("Recovery failed. Start emergency paper logging protocol.");
            return;
        }

        System.out.println("Recovered patrol: " + recoveredPatrolLog.summaryLine());
        System.out.println("PIN in memory after deserialize: " + recoveredPatrolLog.hasPinInMemory());

        recoveredPatrolLog.restorePinAfterRestart("7781");
        recoveredPatrolLog.markSyncedWithBaseStation();

        System.out.println("PIN restored after ranger re-authentication: " + recoveredPatrolLog.hasPinInMemory());
        System.out.println("Final patrol status after base sync: " + recoveredPatrolLog.summaryLine());
    }

    /**
     * Builds a realistic partial patrol log representing an interrupted field session.
     */
    private static PatrolLog createPartialPatrolLog() {
        List<String> timestampedSightings = List.of(
                "06:10 - Fresh rhino tracks near Sabie riverbank",
                "08:45 - Herd movement near granite ridge",
                "11:20 - Unidentified vehicle dust trail north perimeter");

        List<String> snareDiscoveries = List.of(
                "07:30 - Wire snare removed near marula thicket",
                "10:05 - Dual snare line dismantled near dry drainage");

        List<String> suspectDescriptions = List.of(
                "09:40 - Two individuals in dark jackets, heading east",
                "12:10 - Boot prints with cut tread pattern near fence gap");

        return new PatrolLog(
                "KNP-PATROL-2026-045",
                "Ranger Mthembu",
                "Lower Sabie North Loop",
                timestampedSightings,
                snareDiscoveries,
                suspectDescriptions,
                false,
                "4429");
    }

    /**
     * Serializes patrol data to tablet storage so field logs survive crashes or power loss.
     */
    private static void savePatrolCheckpoint(PatrolLog patrolLog, String checkpointFilePath) {
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(checkpointFilePath))) {
            // ObjectOutputStream writes Serializable patrol state into checkpoint bytes.
            objectOutputStream.writeObject(patrolLog);
            System.out.println("Patrol checkpoint written to " + checkpointFilePath);
        } catch (IOException ioException) {
            System.out.println("Failed to write checkpoint: " + ioException.getMessage());
        }
    }

    /**
     * Deserializes patrol checkpoint from tablet storage with graceful mismatch handling.
     */
    private static PatrolLog loadPatrolCheckpoint(String checkpointFilePath) {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(checkpointFilePath))) {
            // ObjectInputStream reconstructs patrol object graph from file bytes.
            return (PatrolLog) objectInputStream.readObject();
        } catch (InvalidClassException invalidClassException) {
            // serialVersionUID mismatch indicates incompatible patrol file version.
            System.out.println("Checkpoint version mismatch: " + invalidClassException.getMessage());
            return null;
        } catch (IOException | ClassNotFoundException generalException) {
            System.out.println("Failed to recover checkpoint: " + generalException.getMessage());
            return null;
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Serializable: PatrolLog implements Serializable so full patrol state can be persisted.
    - transient: rangerPersonalPin is excluded from file storage for security.
    - ObjectOutputStream: writes patrol checkpoint objects to local binary file.
    - ObjectInputStream: restores patrol checkpoint objects after restart.
    - serialVersionUID: stable version ID helps compatibility checks during recovery.

    Interview questions this code prepares you to answer:
    - Why must a class implement Serializable before using object streams?
    - Why should sensitive fields like PINs be marked transient?
    - How does serialVersionUID affect deserialization compatibility?
    - What is the difference between ObjectOutputStream and ObjectInputStream responsibilities?
    - How should software handle checkpoint recovery when class versions differ?

    Common mistake and how this code avoids it:
    - Mistake: assuming transient fields are automatically restored after deserialization.
      Avoidance: the code explicitly re-authenticates and restores PIN state after restart.
    */
}
