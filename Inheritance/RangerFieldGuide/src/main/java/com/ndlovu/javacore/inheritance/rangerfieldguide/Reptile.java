package com.ndlovu.javacore.inheritance.rangerfieldguide;

import java.time.LocalDate;

/**
 * Represents a reptile sighting in the field guide.
 */
public final class Reptile extends Animal {

    private final boolean venomous;
    private final String baskingSpot;

    /**
     * Creates a reptile sighting and reuses the common animal details through super.
     */
    public Reptile(String speciesName, LocalDate sightingDate, String gpsCoordinates, String behaviouralNotes,
                   boolean venomous, String baskingSpot) {
        super(speciesName, sightingDate, gpsCoordinates, behaviouralNotes);
        this.venomous = venomous;
        this.baskingSpot = baskingSpot;
    }

    /**
     * Describes the reptile with venom status and basking spot because the ranger logs those facts for reptiles.
     */
    @Override
    public String describe() {
        return baseReport() + "\n"
                + "Venomous: " + venomous + "\n"
                + "Basking spot: " + baskingSpot;
    }
}
