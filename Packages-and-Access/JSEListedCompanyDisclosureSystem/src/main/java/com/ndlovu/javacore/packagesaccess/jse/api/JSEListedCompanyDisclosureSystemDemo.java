/*
 * The JSE disclosure portal has separate API, compliance, and core calculation layers.
 * A security audit found API code accessing core calculations directly, bypassing compliance checks.
 * Priscilla refactored the system into strict packages with least-privilege access modifiers.
 * Core calculations are package-private, protected is used only for deliberate extension points,
 * and public API calls now flow through compliance review before any disclosure result is returned.
 */

package com.ndlovu.javacore.packagesaccess.jse.api;

import com.ndlovu.javacore.packagesaccess.jse.compliance.ComplianceReviewService;

/**
 * Demonstrates beginner-friendly package and access-modifier enforcement for JSE disclosures.
 */
public class JSEListedCompanyDisclosureSystemDemo {

    /**
     * Runs disclosure requests through compliance layer to show safe access boundaries.
     */
    public static void main(String[] args) {
        ComplianceReviewService complianceReviewService = new ComplianceReviewService();

        System.out.println("=== JSE Disclosure Access Control Demo ===");
        System.out.println(complianceReviewService.publicReviewDisclosure("JSE-ABX"));
        System.out.println(complianceReviewService.publicReviewDisclosure("JSE-KLM"));
        System.out.println(complianceReviewService.publicComplianceDiagnostics());

        System.out.println();
        System.out.println("Note: API package cannot call package-private core risk methods directly.");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - package: classes are split into api, compliance, and core layers.
    - import: API imports compliance service, not core engine internals.
    - public: approved methods expose safe cross-package operations.
    - private: sensitive fingerprint logic stays private in core engine.
    - protected: explicit extension point is available only through controlled inheritance.
    - default: package-private risk method blocks direct access from other packages.
    - access modifier: least-privilege choices enforce architectural policy.

    Interview questions this code prepares you to answer:
    - Why use package-private methods in core domain logic?
    - How do packages help enforce layered architecture?
    - When is protected appropriate compared with public?
    - What risks come from exposing core internals directly to API layers?
    - How does least privilege improve security audits?

    Common mistake and how this code avoids it:
    - Mistake: exposing core calculation methods as public for convenience.
      Avoidance: core risk calculation remains package-private and reachable only via gateway + compliance.
    */
}
