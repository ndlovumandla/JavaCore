/*
 * Medicross Hospital in Bloemfontein bills patients through medical aid, government subsidy, and private cash channels.
 * Sister Modiegi needs one dashboard command flow that works for all channels, even though each follows different rules.
 * Karabo solves this by defining a BillingMethod interface contract and an abstract base class for shared billing state.
 * Each billing class implements the same commands while applying channel-specific legal and pricing logic.
 * New billing channels can be added later by implementing the contract without rewriting existing system flow.
 */

package com.ndlovu.javacore.abstraction.hospitalbilling;

import java.util.List;

/**
 * Runs the hospital billing demo and shows interface-driven abstraction in action.
 */
public class BloemfonteinHospitalBillingDemo {

    /**
     * Creates billing entries, runs a unified billing flow, and prints invoices for each channel.
     */
    public static void main(String[] args) {
        // Contract-based programming: the system handles all billing channels through one interface type.
        List<BillingMethod> billingQueue = List.of(
                new MedicalAidBilling("Lerato Molefe", "CARD-101", 5200.00, 0.80),
                new GovernmentSubsidyBilling("Mpho Khumalo", "MAT-220", 3100.00, 1800.00),
                new PrivateCashBilling("Thabo Dube", "ORTH-315", 2400.00, 250.00)
        );

        printBillingHeader();

        // Unified command flow: same methods, different concrete behavior via interface + abstract class design.
        for (BillingMethod billingMethod : billingQueue) {
            System.out.println(billingMethod.generateInvoice());
            System.out.println(billingMethod.processPayment());

            // Multiple inheritance through interfaces: each channel also supports compliance logging behavior.
            ComplianceLoggable loggableMethod = (ComplianceLoggable) billingMethod;
            System.out.println(loggableMethod.complianceLog());
            System.out.println();
        }

        System.out.println("Sister Modiegi now runs one consistent workflow across all billing channels.");
    }

    /**
     * Prints a heading so the output reads like the hospital finance dashboard.
     */
    private static void printBillingHeader() {
        System.out.println("=== Medicross Bloemfontein Billing Console ===\n");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - abstract class: AbstractBillingChannel stores shared billing state and common invoice structure.
    - interface: BillingMethod defines the channel contract for calculateAmount, generateInvoice, and processPayment.
    - implements: Concrete billing classes implement required methods while extending shared base logic.
    - contract: The billing dashboard can call the same commands on any billing channel safely.
    - multiple inheritance: Classes gain multiple behaviors by implementing both BillingMethod and ComplianceLoggable.

    Interview questions this code helps answer:
    - When should you choose an interface versus an abstract class?
    - What does a "contract" mean in Java OOP design?
    - How does Java support multiple inheritance through interfaces?
    - Why is shared state often placed in an abstract base class?
    - How do interface-based designs reduce future change effort?

    Common mistake and how this code avoids it:
    - Mistake: hardcoding billing logic with if-else blocks in one class. This design keeps each channel's rules in its own class and uses a shared contract for the workflow.
    */
}
