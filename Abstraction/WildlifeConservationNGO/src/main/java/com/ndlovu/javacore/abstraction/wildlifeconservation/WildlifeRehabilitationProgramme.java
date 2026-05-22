package com.ndlovu.javacore.abstraction.wildlifeconservation;

/**
 * Represents wildlife rehabilitation centres handling rescues, treatment, and release readiness.
 */
public final class WildlifeRehabilitationProgramme extends ConservationProgramme {

    private final int animalsInCare;
    private final int successfulReleases;

    /**
     * Creates rehabilitation programme data with patient load and release outcomes.
     */
    public WildlifeRehabilitationProgramme(double currentMonthlySpend, int thresholdBreachesThisMonth,
                                           int animalsInCare, int successfulReleases) {
        super("Wildlife Rehabilitation", currentMonthlySpend, thresholdBreachesThisMonth);
        this.animalsInCare = animalsInCare;
        this.successfulReleases = successfulReleases;
    }

    /**
     * Generates the rehabilitation report so the board can evaluate treatment impact.
     */
    @Override
    public String generateReport() {
        return baseContext() + "\n"
                + "Animals in care: " + animalsInCare + "\n"
                + "Successful releases: " + successfulReleases + "\n"
                + "Outcome: " + (successfulReleases >= 15 ? "high rehabilitation throughput" : "clinical support needs review");
    }

    /**
     * Forecasts centre budget using care load and treatment complexity indicators.
     */
    @Override
    public double forecastBudget() {
        double careLoadMultiplier = animalsInCare > 50 ? 1.18 : 1.09;
        double releaseEfficiencyDiscount = successfulReleases > 20 ? 0.97 : 1.00;
        return currentMonthlySpend * careLoadMultiplier * releaseEfficiencyDiscount;
    }
}
