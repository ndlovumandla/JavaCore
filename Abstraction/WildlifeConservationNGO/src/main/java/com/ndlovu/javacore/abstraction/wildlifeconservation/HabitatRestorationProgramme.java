package com.ndlovu.javacore.abstraction.wildlifeconservation;

/**
 * Represents habitat restoration work focused on degraded land recovery and plant survival.
 */
public final class HabitatRestorationProgramme extends ConservationProgramme {

    private final int hectaresRecovered;
    private final double seedlingSurvivalRate;

    /**
     * Creates habitat restoration programme data with ecological progress indicators.
     */
    public HabitatRestorationProgramme(double currentMonthlySpend, int thresholdBreachesThisMonth,
                                       int hectaresRecovered, double seedlingSurvivalRate) {
        super("Habitat Restoration", currentMonthlySpend, thresholdBreachesThisMonth);
        this.hectaresRecovered = hectaresRecovered;
        this.seedlingSurvivalRate = seedlingSurvivalRate;
    }

    /**
     * Generates the restoration report so the board can monitor ecosystem recovery quality.
     */
    @Override
    public String generateReport() {
        return baseContext() + "\n"
                + "Hectares recovered: " + hectaresRecovered + "\n"
                + "Seedling survival rate: " + String.format("%.1f", seedlingSurvivalRate * 100) + "%\n"
                + "Outcome: " + (seedlingSurvivalRate >= 0.75 ? "stable restoration trajectory" : "soil intervention required");
    }

    /**
     * Forecasts restoration budget based on land scale and plant survival risk.
     */
    @Override
    public double forecastBudget() {
        // Edge-case handling: poor survival increases replanting cost pressure.
        double riskMultiplier = seedlingSurvivalRate < 0.70 ? 1.20 : 1.07;
        double scaleMultiplier = hectaresRecovered > 40 ? 1.10 : 1.03;
        return currentMonthlySpend * riskMultiplier * scaleMultiplier;
    }
}
