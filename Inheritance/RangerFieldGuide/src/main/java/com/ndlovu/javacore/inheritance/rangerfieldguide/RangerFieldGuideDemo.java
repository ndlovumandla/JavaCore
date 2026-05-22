/*
 * Nomsa has spent years keeping a field guide for Kruger National Park, first on paper and later in a spreadsheet.
 * Her notes track species sightings, the date, GPS coordinates, and what the animal was doing at the time.
 * Themba is replacing that spreadsheet with a Java program that keeps mammals, birds, and reptiles in one system.
 * The shared animal details live in a base class, while each animal group adds its own facts and description rules.
 * This small app shows inheritance, super, overriding, IS-A relationships, and polymorphism in a realistic ranger workflow.
 */

package com.ndlovu.javacore.inheritance.rangerfieldguide;

import java.time.LocalDate;
import java.util.List;

/**
 * Runs the ranger field guide demonstration for Nomsa's wildlife sightings.
 */
public class RangerFieldGuideDemo {

    /**
     * Creates a few sightings, prints the reports, and shows how one animal type list can hold many subclasses.
     */
    public static void main(String[] args) {
        // Polymorphism: one list stores different child classes through the shared Animal parent type.
        List<Animal> fieldGuideEntries = List.of(
                new Mammal("Elephant herd", LocalDate.of(2026, 5, 3), "S24.998 E031.588", "Moving slowly toward water", 660, "tight family cluster"),
                new Bird("Lilac-breasted roller", LocalDate.of(2026, 5, 4), "S24.985 E031.603", "Perched above the acacia", 0.45, true),
                new Reptile("Mozambique spitting cobra", LocalDate.of(2026, 5, 5), "S25.004 E031.571", "Staying still near rocks", true, "sun-warmed granite")
        );

        printGuideHeader();

        // Polymorphism: the same method call uses the correct overridden describe() at runtime.
        for (Animal wildlifeEntry : fieldGuideEntries) {
            System.out.println(wildlifeEntry.describe());
            System.out.println();
        }

        System.out.println("Nomsa can now keep a clean field guide without separate spreadsheets for each animal group.");
    }

    /**
     * Prints a short heading so the output feels like a ranger's daily briefing.
     */
    private static void printGuideHeader() {
        System.out.println("=== Kruger Ranger Field Guide ===\n");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - extends: Mammal, Bird, and Reptile inherit shared sighting data from Animal.
    - super: Each subclass calls the parent constructor to reuse the common field-guide setup.
    - method overriding: Each subclass customizes describe() for its own animal group.
    - IS-A relationship: A mammal, bird, and reptile are all animals in the field guide.
    - polymorphism: A List<Animal> can store different animal subclasses and call the right method at runtime.

    Interview questions this code helps answer:
    - Why would you use inheritance for shared wildlife records?
    - What does super do in a subclass constructor?
    - How does method overriding support different data rules?
    - What is polymorphism in a List of parent types?
    - When should you keep common logic in the parent class?

    Common mistake and how this code avoids it:
    - Mistake: repeating the same sighting fields in every animal class. This code stores the shared data once in Animal and keeps the animal-specific details in subclasses.
    */
}
