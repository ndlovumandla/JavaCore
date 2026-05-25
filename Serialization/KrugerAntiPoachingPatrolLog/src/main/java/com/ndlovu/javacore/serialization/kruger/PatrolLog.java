package com.ndlovu.javacore.serialization.kruger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one ranger patrol log that is persisted locally between base-station sync events.
 */
public class PatrolLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String patrolId;
    private final String rangerName;
    private final String routeName;
    private final List<String> timestampedSightings;
    private final List<String> snareDiscoveries;
    private final List<String> suspectDescriptions;
    private boolean syncedWithBaseStation;

    private transient String rangerPersonalPin;

    /**
     * Creates a patrol log snapshot containing offline field observations and route state.
     */
    public PatrolLog(
            String patrolId,
            String rangerName,
            String routeName,
            List<String> timestampedSightings,
            List<String> snareDiscoveries,
            List<String> suspectDescriptions,
            boolean syncedWithBaseStation,
            String rangerPersonalPin) {
        this.patrolId = patrolId;
        this.rangerName = rangerName;
        this.routeName = routeName;
        this.timestampedSightings = new ArrayList<>(timestampedSightings);
        this.snareDiscoveries = new ArrayList<>(snareDiscoveries);
        this.suspectDescriptions = new ArrayList<>(suspectDescriptions);
        this.syncedWithBaseStation = syncedWithBaseStation;

        // transient excludes sensitive PIN data from serialized checkpoint files.
        this.rangerPersonalPin = rangerPersonalPin;
    }

    /**
     * Returns a quick summary used by rangers and base operators during sync operations.
     */
    public String summaryLine() {
        return "Patrol=" + patrolId
                + " | Ranger=" + rangerName
                + " | Route=" + routeName
                + " | Sightings=" + timestampedSightings.size()
                + " | Snares=" + snareDiscoveries.size()
                + " | Suspects=" + suspectDescriptions.size()
                + " | Synced=" + syncedWithBaseStation;
    }

    /**
     * Returns whether sensitive PIN data is currently available in active memory.
     */
    public boolean hasPinInMemory() {
        return rangerPersonalPin != null && !rangerPersonalPin.isBlank();
    }

    /**
     * Marks the log as synced once data reaches the park base station successfully.
     */
    public void markSyncedWithBaseStation() {
        syncedWithBaseStation = true;
    }

    /**
     * Re-establishes transient PIN state after restart so tablet access can continue securely.
     */
    public void restorePinAfterRestart(String recoveredPin) {
        rangerPersonalPin = recoveredPin;
    }

    /**
     * Ensures deserialized patrol logs recover clean defaults for transient and optional fields.
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        // defaultReadObject restores all non-transient fields serialized by ObjectOutputStream.
        objectInputStream.defaultReadObject();

        // Transient fields are not serialized, so set secure default after restoration.
        rangerPersonalPin = null;

        // Compatibility guard: older files still recover with valid empty collections.
        ensureCollectionDefaults();
    }

    /**
     * Ensures collection fields are never null after deserialization across software versions.
     */
    private void ensureCollectionDefaults() {
        if (timestampedSightings == null) {
            throw new IllegalStateException("Recovered patrol file is missing sightings collection.");
        }
        if (snareDiscoveries == null) {
            throw new IllegalStateException("Recovered patrol file is missing snare collection.");
        }
        if (suspectDescriptions == null) {
            throw new IllegalStateException("Recovered patrol file is missing suspect collection.");
        }
    }
}
