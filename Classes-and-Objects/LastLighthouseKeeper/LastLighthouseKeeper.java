/*
 * The Last Lighthouse Keeper
 * 
 * In 1987, the remote Cape Solitude lighthouse off the South African coast was fully automated — all keepers dismissed except one: Elias Mokoena, 63, who refused to leave. The government allowed him to stay as a voluntary caretaker. Elias keeps meticulous handwritten logs of every ship that passes, every weather pattern, and every mechanical fault he fixes alone.
 * When a young coastal engineer named Priya is sent to digitise his records, she finds thirty years of data scrawled in fourteen notebooks. She must write a Java system to model Elias's lighthouse, its log entries, equipment states, and weather readings — turning his analogue life's work into structured objects before the government's deadline.
 */

public class LastLighthouseKeeper {

    /**
     * Represents the Cape Solitude Lighthouse and its current state.
     */
    static class Lighthouse {
        private String name;
        private String location;
        private boolean isOperational;
        private int yearsMaintained;

        public Lighthouse(String name, String location, boolean isOperational, int yearsMaintained) {
            this.name = name;
            this.location = location;
            this.isOperational = operational;
            this.yearsMaintained = yearsMaintained;
        }

        public String getName() { return name; }
        public String getLocation() { return location; }
        public boolean isOperational() { return isOperational; }
        public int getYearsMaintained() { return yearsMaintained; }

        public void setOperational(boolean operational) {
            this.isOperational = operational;
        }
    }

    /**
     * Represents a single log entry made by Elias Mokoena.
     */
    static class LogEntry {
        private String date;
        private String shipName;
        private String weatherCondition;
        private String maintenanceAction;
        private String notes;

        public LogEntry(String date, String shipName, String weatherCondition, String maintenanceAction, String notes) {
            this.date = date;
            this.shipName = shipName;
            this.weatherCondition = weatherCondition;
            this.maintenanceAction = maintenanceAction;
            this.notes = notes;
        }

        public String getDate() { return date; }
        public String getShipName() { return shipName; }
        // ... other getters

        public void printLog() {
            System.out.println("Date: " + date + " | Ship: " + shipName + " | Weather: " + weatherCondition);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== CAPE SOLITUDE LIGHTHOUSE LOG SYSTEM ===");
        // Demo code...
    }

    /* ═══ STUDY NOTES ═══ */
    // ... full study notes
}