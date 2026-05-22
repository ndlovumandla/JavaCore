/*
 * Pretoria's emergency services run one dispatch centre for fire, medical, and police callouts.
 * Each incident needs a different response protocol, arrival estimate, and resource plan.
 * Lebo and Ayanda modernise the old system so the dashboard can call one method and let Java choose the correct response.
 * This project keeps the example simple while still showing polymorphism, overloading, overriding, runtime binding, and method dispatch.
 */

package com.ndlovu.javacore.polymorphism.pretoriaemergency;

import java.util.List;

/**
 * Runs the Pretoria emergency dispatch demo and shows how one dashboard can manage different emergency types.
 */
public class PretoriaEmergencyDispatchDemo {

    /**
     * Creates the incident queue, prints each response, and shows runtime binding in action.
     */
    public static void main(String[] args) {
        // Polymorphism: one list stores different child classes through the shared Emergency parent type.
        List<Emergency> dispatchQueue = List.of(
                new FireEmergency("F-104", "Mamelodi apartment block", 9, "high-rise building", true),
                new MedicalEmergency("M-221", "Hatfield shopping centre", 7, "cardiac arrest", true),
                new PoliceEmergency("P-318", "Arcadia petrol station", 8, "armed robbery", true)
        );

        printDashboardHeader();

        // Method dispatch: the same respond() call is routed to the correct subclass at runtime.
        for (Emergency activeEmergency : dispatchQueue) {
            System.out.println(activeEmergency.respond());
            System.out.println();
        }

        // Overloading: the same method name can also create a response preview with an adjustment.
        System.out.println("Preview check: " + dispatchQueue.get(1).respond(2));
        System.out.println("Lebo's dashboard handles every emergency through one clean polymorphic call.");
    }

    /**
     * Prints a short heading so the output feels like a dispatch centre briefing.
     */
    private static void printDashboardHeader() {
        System.out.println("=== Pretoria Emergency Dispatch Centre ===\n");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - polymorphism: One Emergency reference type can point to fire, medical, or police objects.
    - overloading: respond() and respond(int) share a name but accept different parameters.
    - overriding: Each subclass provides its own respond() implementation.
    - runtime binding: Java decides which respond() method to call while the program is running.
    - method dispatch: The JVM sends the call to the correct subclass automatically.

    Interview questions this code helps answer:
    - What is polymorphism in Java?
    - How is overriding different from overloading?
    - What is runtime binding?
    - Why is polymorphism useful in a dispatch system?
    - When should you use a parent type reference for child objects?

    Common mistake and how this code avoids it:
    - Mistake: using if-statements to choose the emergency type instead of letting the object decide. This code keeps the response logic inside each subclass and calls respond() through the parent type.
    */
}
