package com.ndlovu.javacore.packagesaccess.health.billing;

/**
 * Runs internal billing checks from inside the billing package boundary.
 */
class BillingAuditTool {

    /**
     * Executes a package-level check that external packages cannot call directly.
     */
    String runInternalBillingAudit(BillingCodeService billingCodeService) {
        return billingCodeService.packageBillingAuditNote();
    }
}
