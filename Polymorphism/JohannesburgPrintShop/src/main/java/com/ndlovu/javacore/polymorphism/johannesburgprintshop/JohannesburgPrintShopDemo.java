/*
 * PrintFast in Johannesburg used to bill every job with a giant if-else chain.
 * Thandi and Siphamandla replace that fragile approach with a small polymorphism demo.
 * A shared PrintJob type holds the common details, while document, banner, and merchandise jobs each price themselves.
 * The dashboard simply loops through the queue and calls calculateCost(), letting Java choose the correct method at runtime.
 * This keeps the example simple, practical, and easy to revise.
 */

package com.ndlovu.javacore.polymorphism.johannesburgprintshop;

import java.util.List;

/**
 * Runs the Johannesburg print shop billing demo and shows polymorphism in action.
 */
public class JohannesburgPrintShopDemo {

    /**
     * Builds the job queue, prints each quote, and shows how one method call dispatches to different job types.
     */
    public static void main(String[] args) {
        // Polymorphism: one list holds different subclasses through the shared PrintJob parent type.
        List<PrintJob> printQueue = List.of(
                new DocumentPrintJob("D-101", "City College", 120, 2.25),
                new BannerPrintJob("B-202", "Joburg Expo", 3.0, 1.5, 180.00),
                new MerchandisePrintJob("M-303", "Lebo's Team", "T-shirts", 40, 95.00, 250.00)
        );

        printDashboardHeader();

        // Method dispatch: the same calculateCost() call uses the correct overridden version at runtime.
        for (PrintJob activeJob : printQueue) {
            System.out.println(activeJob.baseSummary());
            System.out.println("Quoted cost: R" + String.format("%.2f", activeJob.calculateCost()));
            System.out.println();
        }

        // Overloading: the same method name can accept a discount label and build a slightly different summary.
        System.out.println(printQueue.get(2).summarize("Seasonal promo applied"));
        System.out.println();
        System.out.println("Thandi's billing team no longer needs a long if-else chain.");
    }

    /**
     * Prints a short heading so the output reads like a shop billing sheet.
     */
    private static void printDashboardHeader() {
        System.out.println("=== Johannesburg Print Shop ===\n");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - polymorphism: One PrintJob reference type can point to different print job objects.
    - overloading: summarize() and calculateCost() show how one method name can serve different needs.
    - overriding: Each subclass provides its own calculateCost() implementation.
    - runtime binding: Java decides which calculateCost() to call while the program is running.
    - method dispatch: The JVM sends the call to the correct subclass automatically.

    Interview questions this code helps answer:
    - What is polymorphism in Java?
    - How does overriding support different business rules?
    - What is method overloading?
    - Why is runtime binding useful?
    - When should you use a parent type in a list?

    Common mistake and how this code avoids it:
    - Mistake: putting all pricing rules in one class and using if-statements everywhere. This code keeps each pricing rule inside its own subclass and lets the parent type handle the queue.
    */
}
