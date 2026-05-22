package com.ndlovu.javacore.abstraction.wildlifeconservation;

/**
 * Stores shared programme state and behaviour while leaving domain calculations to subclasses.
 */
public abstract class ConservationProgramme implements ProgrammeGovernance, RiskAuditable {

    protected final String programmeName;
    protected final double currentMonthlySpend;
    protected final int thresholdBreachesThisMonth;

    /**
     * Builds common programme state so each subtype reuses the same governance baseline.
     */
    protected ConservationProgramme(String programmeName, double currentMonthlySpend, int thresholdBreachesThisMonth) {
        this.programmeName = programmeName;
        this.currentMonthlySpend = currentMonthlySpend;
        this.thresholdBreachesThisMonth = thresholdBreachesThisMonth;
    }

    /**
     * Sends a shared board alert message so all programmes follow one escalation standard.
     */
    @Override
    public String notifyBoard() {
        return thresholdBreachesThisMonth > 0
                ? "BOARD ALERT: " + programmeName + " breached critical threshold " + thresholdBreachesThisMonth + " time(s)."
                : "BOARD NOTICE: " + programmeName + " is within safe operating thresholds.";
    }

    /**
     * Returns a shared audit line so governance reporting stays consistent across programme types.
     */
    @Override
    public String riskAuditLine() {
        return "AUDIT[" + programmeName + "] monthlyBreaches=" + thresholdBreachesThisMonth;
    }

    /**
     * Formats base programme context for reuse in concrete monthly reports.
     */
    protected String baseContext() {
        return "Programme: " + programmeName + "\n"
                + "Current monthly spend: R" + String.format("%.2f", currentMonthlySpend);
    }
}
