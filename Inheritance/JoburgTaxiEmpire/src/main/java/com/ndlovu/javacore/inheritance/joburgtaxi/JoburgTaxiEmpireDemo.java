package com.ndlovu.javacore.inheritance.joburgtaxi;

import java.util.List;

/**
 * Runs the Joburg taxi empire story and shows inheritance in action.
 */
public class JoburgTaxiEmpireDemo {

    /**
     * Creates the fleet, prints reports, and shows polymorphism through the shared Vehicle type.
     */
    public static void main(String[] args) {
        List<Vehicle> fleet = List.of(
                new MinibusTaxi("JHB-101", "Mama Thandeka", 68, 16, "Soweto-CBD", 25.00),
                new AirportShuttle("JHB-202", "Lebo", 84, 8, 18.50),
                new SchoolBus("JHB-303", "Naledi", 59, 45, "Thandeka Primary", 1200.00)
        );

        // Shared base-class behavior: each vehicle can be refueled the same way.
        fleet.get(0).refuel(10);

        System.out.println("=== Thandeka Transport Daily Fleet Briefing ===\n");

        // Polymorphism: the same method call uses each subclass's fare and checklist logic.
        for (Vehicle fleetVehicle : fleet) {
            System.out.println(fleetVehicle.dailyReport(42));
            System.out.println();
        }

        System.out.println("Summary: every entry is a Vehicle, but each one behaves differently.");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - extends: MinibusTaxi, AirportShuttle, and SchoolBus inherit shared vehicle data from Vehicle.
    - super: Each subclass constructor calls the parent constructor to reuse common setup.
    - method overriding: Each vehicle type provides its own calculateFare() and dailyChecklist().
    - IS-A relationship: A taxi, shuttle, and school bus are all Vehicles.
    - polymorphism: A List<Vehicle> stores different vehicle types and calls the correct method at runtime.

    Interview questions this code helps answer:
    - Why is inheritance useful when several classes share the same fields?
    - What does super do in a constructor?
    - How does method overriding support different business rules?
    - How does polymorphism simplify fleet management code?
    - When would you choose an abstract class over an interface?

    Common mistake and how this code avoids it:
    - Mistake: putting all fare logic in one class and using conditionals everywhere. This code keeps shared data in Vehicle and the special rules in each subclass.
    */
}
