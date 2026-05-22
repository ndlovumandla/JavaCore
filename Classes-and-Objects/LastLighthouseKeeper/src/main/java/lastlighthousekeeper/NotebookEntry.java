package lastlighthousekeeper;

/**
 * Real-world role: base class for dated records found in Elias's notebooks.
 */
public abstract class NotebookEntry implements Archivable {
    private String entryDate;

    /**
     * Initializes the common date field for every notebook record.
     * This exists so subclasses inherit one reliable source of date data.
     */
    public NotebookEntry(String entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * Returns the date of this notebook entry.
     * This exists to provide safe read-only access to encapsulated state.
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     * Updates the date of this notebook entry.
     * This exists to allow controlled correction of transcription mistakes.
     */
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
}
