package com.ndlovu.javacore.packagesaccess.jse.core;

/**
 * Exposes controlled core capabilities to compliance layer without leaking package-private internals.
 */
public class CoreDisclosureGateway {

    private final CoreDisclosureEngine coreDisclosureEngine = new CoreDisclosureEngine();

    /**
     * Runs controlled disclosure scoring flow for compliance checks.
     */
    public int publicGetRiskScoreForCompliance(String companyCode) {
        // Gateway calls package-private core logic from inside same package.
        return coreDisclosureEngine.packageCalculateDisclosureRiskScore(companyCode);
    }

    /**
     * Returns safe core diagnostics for audit logs.
     */
    public String publicCoreSummary() {
        return coreDisclosureEngine.publicSafeEngineSummary();
    }

    /**
     * Uses a protected engine method via inheritance-aware helper inside this package.
     */
    public String publicProtectedHealthCheck() {
        class EngineInspector extends CoreDisclosureEngine {
            String run() {
                return protectedEngineHealthNote();
            }
        }
        return new EngineInspector().run();
    }
}
