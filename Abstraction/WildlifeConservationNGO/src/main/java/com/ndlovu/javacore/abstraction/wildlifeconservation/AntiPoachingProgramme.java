package com.ndlovu.javacore.abstraction.wildlifeconservation;

/**
 * Represents anti-poaching patrol operations with route, incident, and ranger activity metrics.
 */
public final class AntiPoachingProgramme extends ConservationProgramme {

    private final int patrolHours;
    private final int incidentsIntercepted;

    /**
     * Creates anti-poaching programme data using shared and operation-specific fields.
     */
    public AntiPoachingProgramme(double currentMonthlySpend, int thresholdBreachesThisMonth,
                                 int patrolHours, int incidentsIntercepted) {
        super("Anti-Poaching Patrols", currentMonthlySpend, thresholdBreachesThisMonth);
        this.patrolHours = patrolHours;
        this.incidentsIntercepted = incidentsIntercepted;
    }

    /**
     * Generates the patrol report so the board can track deterrence outcomes.
     */
    @Override
    public String generateReport() {
        return baseContext() + "\n"
                + "Patrol hours: " + patrolHours + "\n"
                + "Incidents intercepted: " + incidentsIntercepted + "\n"
                + "Outcome: " + (incidentsIntercepted > 0 ? "direct poaching prevention achieved" : "heightened monitoring required");
    }

    /**
     * Forecasts patrol budget by projecting fuel and overtime pressure from patrol intensity.
     */
    @Override
    public double forecastBudget() {
        // Abstract-method implementation: each subtype applies its own financial model.
        double patrolPressureFactor = patrolHours > 400 ? 1.15 : 1.08;
        return currentMonthlySpend * patrolPressureFactor;
    }
}
