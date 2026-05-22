package com.ndlovu.javacore.inheritance.rangerfieldguide;

import java.time.LocalDate;

/**
 * Represents a bird sighting in the field guide.
 */
public final class Bird extends Animal {

    private final double wingspanMetres;
    private final boolean migratory;

    /**
     * Creates a bird sighting and passes the shared details to the parent class.
     */
    public Bird(String speciesName, LocalDate sightingDate, String gpsCoordinates, String behaviouralNotes,
                double wingspanMetres, boolean migratory) {
        super(speciesName, sightingDate, gpsCoordinates, behaviouralNotes);
        this.wingspanMetres = wingspanMetres;
        this.migratory = migratory;
    }

    /**
     * Describes the bird with wingspan and migration status because that is what the ranger needs to log.
     */
    @Override
    public String describe() {
        return baseReport() + "\n"
                + "Wingspan: " + wingspanMetres + " m\n"
                + "Migratory: " + migratory;
    }
}
