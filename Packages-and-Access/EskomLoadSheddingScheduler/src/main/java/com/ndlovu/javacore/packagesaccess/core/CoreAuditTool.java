package com.ndlovu.javacore.packagesaccess.core;

/**
 * Performs package-level audit checks inside the core package after access control hardening.
 */
class CoreAuditTool {

    /**
     * Runs a package-only audit method to prove default access behavior.
     */
    String runAudit(LoadSheddingCore loadSheddingCore) {
        // This class has default access, so it is visible only inside the same package.
        return loadSheddingCore.runPackageSafetyAudit();
    }
}
