package com.ndlovu.javacore.abstraction.wildlifeconservation;

/**
 * Defines the governance contract that every GreenSavanna conservation programme must honour.
 */
public interface ProgrammeGovernance {

    /**
     * Produces a monthly report so the board can review programme outcomes consistently.
     */
    String generateReport();

    /**
     * Forecasts the next budget cycle so finance can plan funding across programmes.
     */
    double forecastBudget();

    /**
     * Sends a board notification so leaders can act quickly on critical programme events.
     */
    String notifyBoard();
}
