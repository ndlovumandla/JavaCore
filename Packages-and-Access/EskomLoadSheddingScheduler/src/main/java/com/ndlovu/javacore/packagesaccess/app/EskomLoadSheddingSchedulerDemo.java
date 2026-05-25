/*
 * Eskom's scheduling platform separates public schedule access from internal control-room operations.
 * After an access-level incident, developers reviewed classes and methods for least-privilege design.
 * Andile structured the system into packages so sensitive core logic stays hidden by default.
 * Public methods expose safe schedule data, while protected and package-private actions stay restricted.
 * This demo shows how Java access modifiers enforce those boundaries in everyday code.
 */

package com.ndlovu.javacore.packagesaccess.app;

import com.ndlovu.javacore.packagesaccess.core.LoadSheddingCore;

/**
 * Demonstrates package structure and access modifiers in a load-shedding scheduling scenario.
 */
public class EskomLoadSheddingSchedulerDemo {

    /**
     * Runs a realistic flow showing what is accessible and what is intentionally restricted.
     */
    public static void main(String[] args) {
        OperationsConsole operationsConsole = new OperationsConsole();

        operationsConsole.ingestGridLoadSnapshot("GridLoad=89%; ReserveMargin=Low");
        operationsConsole.ingestGridLoadSnapshot("GridLoad=91%; ReserveMargin=Critical");

        System.out.println("=== Eskom Scheduler Access Modifier Demo ===");

        // public access: safe for consumers and external systems.
        System.out.println("Public schedule API output: " + operationsConsole.generatePublicSchedule());

        // protected access: available through subclass operation in this demo.
        System.out.println("Protected operations action: " + operationsConsole.requestControlRoomRecalculation());

        // public wrapper uses private internals safely.
        System.out.println("Internal safe status: " + operationsConsole.safeInternalStatus());

        System.out.println();
        System.out.println("Note: package-private and private members are intentionally inaccessible here.");
    }

    /**
     * Models a trusted operations console that extends core behavior with controlled access.
     */
    static class OperationsConsole extends LoadSheddingCore {

        /**
         * Calls a protected core method, proving protected access works through inheritance.
         */
        public String requestControlRoomRecalculation() {
            return triggerProtectedRecalculation();
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - package: code is separated into app and core packages to enforce boundaries.
    - import: app package imports only the core type it is allowed to use.
    - public: public methods expose safe operations to outside packages.
    - private: private method protects sensitive token logic inside one class only.
    - protected: protected method is accessible to subclasses like OperationsConsole.
    - default: package-private class/method are accessible only within the core package.
    - access modifier: each member uses the least permissive level needed for its role.

    Interview questions this code prepares you to answer:
    - When should a method be public, protected, package-private, or private?
    - What is package-private access in Java and why is it useful?
    - How does protected differ from public across packages?
    - Why is least-privilege design important in infrastructure systems?
    - What role do packages play in access control and architecture?

    Common mistake and how this code avoids it:
    - Mistake: marking internal methods public for convenience, exposing unsafe operations.
      Avoidance: sensitive logic is private/package-private, with only safe wrappers exposed.
    */
}
