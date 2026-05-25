package com.ndlovu.javacore.packagesaccess.health.billing;

/**
 * Provides billing-code-only access for schemes while excluding diagnosis details.
 */
public class BillingCodeService {

    private String currentBillingCode = "OUTPATIENT-GEN-001";

    /**
     * Returns billing code information that schemes are allowed to view.
     */
    public String schemeReadBillingCode() {
        // public method is intentionally limited to billing-friendly output.
        return "BillingCode=" + currentBillingCode;
    }

    /**
     * Package-private method supports internal billing-package audits only.
     */
    String packageBillingAuditNote() {
        // default (package-private) hides this method from other packages.
        return "Billing package audit confirms code format validity.";
    }
}
