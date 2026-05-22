package lastlighthousekeeper;

/**
 * Real-world role: defines a standard behavior for any object that can produce
 * a human-readable archive line for Priya's digital ledger.
 */
public interface Archivable {

    /**
     * Returns a single line summary of a record so Priya can store it consistently.
     * This exists to enforce a shared contract across different story records.
     */
    String toArchiveLine();
}
