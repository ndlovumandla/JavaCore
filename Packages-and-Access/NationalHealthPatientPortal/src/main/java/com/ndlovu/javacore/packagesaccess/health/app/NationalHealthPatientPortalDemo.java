/*
 * South Africa's national patient portal connects hospitals, GPs, pharmacies, and schemes.
 * Each role must access only the data permitted by policy and POPIA legal boundaries.
 * Lungelo separates classes into clinical, billing, identity, and app packages to enforce these limits.
 * Public methods expose approved data, protected methods support controlled internal extension,
 * and package-private/private members keep sensitive operations out of reach.
 */

package com.ndlovu.javacore.packagesaccess.health.app;

import com.ndlovu.javacore.packagesaccess.health.billing.BillingCodeService;
import com.ndlovu.javacore.packagesaccess.health.clinical.ClinicalRecordService;
import com.ndlovu.javacore.packagesaccess.health.identity.PortalIdentityService;

/**
 * Demonstrates package boundaries and access modifiers in a healthcare portal scenario.
 */
public class NationalHealthPatientPortalDemo {

    /**
     * Runs role-based access examples to show why restrictive modifiers matter.
     */
    public static void main(String[] args) {
        PortalIdentityService portalIdentityService = new PortalIdentityService();
        ClinicalOpsScreen clinicalOpsScreen = new ClinicalOpsScreen();
        BillingCodeService billingCodeService = new BillingCodeService();

        System.out.println("=== National Health Patient Portal Access Demo ===");

        String gpRole = "GP";
        if (portalIdentityService.isKnownRole(gpRole)) {
            clinicalOpsScreen.gpWriteClinicalNote("Hypertension", "Medication adjusted, review in 4 weeks.");
            System.out.println("GP view: " + clinicalOpsScreen.gpReadClinicalSummary());
            System.out.println("Protected check via subclass: " + clinicalOpsScreen.runClinicalProtectedCheck());
        }

        String schemeRole = "SCHEME";
        if (portalIdentityService.isKnownRole(schemeRole)) {
            System.out.println("Scheme view: " + billingCodeService.schemeReadBillingCode());
        }

        System.out.println("Safe private-token preview: " + clinicalOpsScreen.safeClinicalTokenPreview());
        System.out.println("Note: package-private billing audit and private clinical token are intentionally hidden.");
    }

    /**
     * Represents an internal clinical UI module that can use protected inherited behavior.
     */
    static class ClinicalOpsScreen extends ClinicalRecordService {

        /**
         * Calls a protected method through inheritance to run internal integrity checks.
         */
        public String runClinicalProtectedCheck() {
            return protectedClinicalIntegrityCheck();
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - package: classes are split into clinical, billing, identity, and app packages.
    - import: app package imports only services it is allowed to consume.
    - public: approved cross-package APIs expose allowed healthcare operations.
    - private: sensitive token logic remains available only inside one class.
    - protected: subclassed internal screens can access controlled extension methods.
    - default: package-private audit methods stay inside billing package only.
    - access modifier: least-privilege modifiers map directly to legal access boundaries.

    Interview questions this code prepares you to answer:
    - How do package boundaries strengthen access control design?
    - When should a method be protected instead of public?
    - What does package-private protect that public does not?
    - Why is private useful for sensitive healthcare internals?
    - How does least privilege reduce security and compliance risk?

    Common mistake and how this code avoids it:
    - Mistake: exposing sensitive methods as public for convenience.
      Avoidance: the code keeps sensitive logic private/package-private and exposes only safe outputs.
    */
}
