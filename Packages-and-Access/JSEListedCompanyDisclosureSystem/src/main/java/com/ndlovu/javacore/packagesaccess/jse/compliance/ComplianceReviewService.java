package com.ndlovu.javacore.packagesaccess.jse.compliance;

import com.ndlovu.javacore.packagesaccess.jse.core.CoreDisclosureGateway;

/**
 * Runs internal JSE compliance checks before disclosures are released to the public API.
 */
public class ComplianceReviewService {

    private final CoreDisclosureGateway coreDisclosureGateway = new CoreDisclosureGateway();

    /**
     * Reviews a company disclosure request and returns compliance decision text.
     */
    public String publicReviewDisclosure(String companyCode) {
        int riskScore = coreDisclosureGateway.publicGetRiskScoreForCompliance(companyCode);
        String classification = riskScore >= 60 ? "MANUAL_REVIEW" : "AUTO_PASS";
        return "Company=" + companyCode + " | RiskScore=" + riskScore + " | Decision=" + classification;
    }

    /**
     * Returns safe compliance diagnostics from core through gateway-only path.
     */
    public String publicComplianceDiagnostics() {
        return coreDisclosureGateway.publicCoreSummary() + " | " + coreDisclosureGateway.publicProtectedHealthCheck();
    }
}
