package com.ndlovu.javacore.packagesaccess.jse.core;

/**
 * Contains sensitive disclosure risk calculations that should stay inside core package boundaries.
 */
public class CoreDisclosureEngine {

    private String lastCalculationFingerprint = "No calculation yet";

    /**
     * Calculates risk score for disclosure package inside core package only.
     */
    int packageCalculateDisclosureRiskScore(String companyCode) {
        // default access (package-private) prevents direct cross-package calls from API layer.
        int score = Math.abs(companyCode.hashCode() % 100);
        lastCalculationFingerprint = privateBuildFingerprint(companyCode, score);
        return score;
    }

    /**
     * Returns a safe summary for diagnostics without exposing calculation internals.
     */
    public String publicSafeEngineSummary() {
        return "Core summary fingerprint prefix: " + lastCalculationFingerprint.substring(0, 8) + "...";
    }

    /**
     * Provides a protected extension point for deliberate internal subclassing.
     */
    protected String protectedEngineHealthNote() {
        return "Protected engine health check passed.";
    }

    /**
     * Builds sensitive internals kept private to the core calculation class.
     */
    private String privateBuildFingerprint(String companyCode, int score) {
        // private ensures fingerprint formula cannot be called from outside this class.
        return companyCode + "-FPR-" + (score * 13 + 7);
    }
}
