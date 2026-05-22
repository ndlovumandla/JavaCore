package com.ndlovu.javacore.inheritance.rangerfieldguide;

import java.time.LocalDate;

/**
 * Represents a mammal sighting in the field guide.
 */
public final class Mammal extends Animal {

    private final int gestationDays;
    private final String herdBehaviour;

    /**
     * Creates a mammal sighting and reuses the common animal details through super.
     */
    public Mammal(String speciesName, LocalDate sightingDate, String gpsCoordinates, String behaviouralNotes,
                  int gestationDays, String herdBehaviour) {
        super(speciesName, sightingDate, gpsCoordinates, behaviouralNotes);
        this.gestationDays = gestationDays;
        this.herdBehaviour = herdBehaviour;
    }

    /**
     * Describes the mammal with its gestation and group behaviour because the ranger records those facts for mammals.
     */
    @Override
    public String describe() {
        return baseReport() + "\n"
                + "Gestation: " + gestationDays + " days\n"
                + "Herd behaviour: " + herdBehaviour;
    }
}
