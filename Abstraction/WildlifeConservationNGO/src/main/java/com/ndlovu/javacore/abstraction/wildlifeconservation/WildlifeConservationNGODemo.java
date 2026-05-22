/*
 * GreenSavanna NGO runs anti-poaching patrols, habitat restoration, and wildlife rehabilitation centres.
 * Each programme must generate reports, forecast budgets, and notify the board when thresholds are breached.
 * Olumide introduces an abstract base class to share governance behaviour and interfaces to enforce contracts.
 * The dashboard now calls the same methods on all programme objects while each subtype keeps its own logic.
 * This design hides internal complexity and makes it easy to add future programme channels safely.
 */

package com.ndlovu.javacore.abstraction.wildlifeconservation;

import java.util.List;

/**
 * Runs the NGO governance demo and shows abstract-class plus interface contracts in practical use.
 */
public class WildlifeConservationNGODemo {

    /**
     * Builds programme data, executes one unified dashboard flow, and prints governance outputs.
     */
    public static void main(String[] args) {
        // Contract-driven architecture: one interface type manages different conservation programmes.
        List<ProgrammeGovernance> conservationProgrammes = List.of(
                new AntiPoachingProgramme(620000.00, 1, 430, 9),
                new HabitatRestorationProgramme(410000.00, 0, 52, 0.78),
                new WildlifeRehabilitationProgramme(530000.00, 2, 61, 18)
        );

        printBoardHeader();

        // Abstraction in action: dashboard calls the same methods without knowing internal subtype details.
        for (ProgrammeGovernance programme : conservationProgrammes) {
            System.out.println(programme.generateReport());
            System.out.println("Forecast budget: R" + String.format("%.2f", programme.forecastBudget()));
            System.out.println(programme.notifyBoard());

            // Multiple inheritance via interfaces: programme objects also support separate risk-audit behavior.
            RiskAuditable auditableProgramme = (RiskAuditable) programme;
            System.out.println(auditableProgramme.riskAuditLine());
            System.out.println();
        }

        System.out.println("GreenSavanna board now governs diverse programmes through one unified abstraction layer.");
    }

    /**
     * Prints a title so the output reads like a board governance dashboard briefing.
     */
    private static void printBoardHeader() {
        System.out.println("=== GreenSavanna NGO Board Dashboard ===\n");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - abstract class: ConservationProgramme shares common state and board-notification behavior.
    - interface: ProgrammeGovernance and RiskAuditable define stable contracts for all programme types.
    - implements: The abstract base class and concrete subclasses honour interface method requirements.
    - contract: The dashboard relies on method guarantees, not concrete class details.
    - multiple inheritance: Classes inherit behaviour from one abstract class and multiple interfaces.

    Interview questions this code helps answer:
    - Why combine an abstract class with interfaces in one design?
    - What is the benefit of contract-driven coding with interfaces?
    - How does Java support multiple inheritance safely?
    - When should shared logic live in an abstract class?
    - How does abstraction reduce coupling in large systems?

    Common mistake and how this code avoids it:
    - Mistake: placing all programme logic in one large controller class. This design keeps shared governance in the abstract base and programme-specific rules in subclasses.
    */
}
