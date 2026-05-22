package lastlighthousekeeper;

/**
 * Real-world role: represents one ship-sighting log from Elias's notebooks.
 */
public class ShipLogEntry extends NotebookEntry {
    private final String shipName;
    private final String cargoType;
    private String maintenanceActionTaken;

    /**
     * Creates a ship log entry with navigation and maintenance context.
     * This exists so one object captures all details tied to a single sighting.
     */
    public ShipLogEntry(
        String entryDate,
        String shipName,
        String cargoType,
        String maintenanceActionTaken
    ) {
        super(entryDate);
        this.shipName = shipName;
        this.cargoType = cargoType;
        this.maintenanceActionTaken = maintenanceActionTaken;
    }

    /**
     * Returns the ship name from the notebook entry.
     * This exists so readers can query encapsulated ship data.
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Returns the ship's cargo type.
     * This exists for historical and safety analysis of passing vessels.
     */
    public String getCargoType() {
        return cargoType;
    }

    /**
     * Returns the maintenance action linked to this sighting.
     * This exists to connect environmental events to technical responses.
     */
    public String getMaintenanceActionTaken() {
        return maintenanceActionTaken;
    }

    /**
     * Updates the maintenance action text.
     * This exists so Priya can correct transcription wording without replacing objects.
     */
    public void setMaintenanceActionTaken(String maintenanceActionTaken) {
        this.maintenanceActionTaken = maintenanceActionTaken;
    }

    /**
     * Converts this entry to a line fit for the digital archive.
     * This exists to satisfy the shared archive contract from the interface.
     */
    @Override
    public String toArchiveLine() {
        return getEntryDate() + " | Ship: " + shipName
            + " | Cargo: " + cargoType
            + " | Action: " + maintenanceActionTaken;
    }
}
