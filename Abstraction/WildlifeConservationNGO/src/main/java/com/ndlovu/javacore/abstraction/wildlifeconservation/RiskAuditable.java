package com.ndlovu.javacore.abstraction.wildlifeconservation;

/**
 * Adds risk-audit behaviour for programmes that must prove threshold monitoring.
 */
public interface RiskAuditable {

    /**
     * Returns a one-line audit entry showing whether critical thresholds were breached.
     */
    String riskAuditLine();
}
