package com.ndlovu.javacore.inheritance.rangerfieldguide;

import java.time.LocalDate;

/**
 * Represents a shared sighting entry in Nomsa's field guide.
 */
public abstract class Animal {

    protected final String speciesName;
    protected final LocalDate sightingDate;
    protected final String gpsCoordinates;
    protected final String behaviouralNotes;

    /**
     * Stores the common sighting details that every animal report needs.
     */
    protected Animal(String speciesName, LocalDate sightingDate, String gpsCoordinates, String behaviouralNotes) {
        this.speciesName = speciesName;
        this.sightingDate = sightingDate;
        this.gpsCoordinates = gpsCoordinates;
        this.behaviouralNotes = behaviouralNotes;
    }

    /**
     * Builds the shared sighting summary so subclasses can reuse the common report text.
     */
    protected String baseReport() {
        return "Species: " + speciesName + "\n"
                + "Date: " + sightingDate + "\n"
                + "GPS: " + gpsCoordinates + "\n"
                + "Notes: " + behaviouralNotes;
    }

    /**
     * Returns the species-specific description used in the ranger's report.
     */
    public abstract String describe();
}
