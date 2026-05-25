/*
 * FoodForward SA's Johannesburg hub coordinates large weekly flows of donated food parcels.
 * Dineo must track donors, beneficiary organizations, and parcel categories while dispatching by zone and need.
 * Tebogo replaces fragile spreadsheet workflows with a Java collections-driven model that fits each access pattern.
 * The system uses keyed lookup for IDs, ordered lists for intake, duplicate protection for parcel IDs, and queue-based dispatching.
 * This demo shows how the Collections Framework supports reliable logistics decisions in a real nonprofit context.
 */

package com.ndlovu.javacore.collections.foodbank;

import java.util.List;

/**
 * Runs the Johannesburg food bank simulation and demonstrates practical collection choices.
 */
public class JohannesburgFoodBankDemo {

    /**
     * Creates sample data, routes parcels by zone, and prints operational outcomes.
     */
    public static void main(String[] args) {
        FoodBankHubService foodBankHubService = new FoodBankHubService();

        registerSampleDonors(foodBankHubService);
        registerSampleBeneficiaries(foodBankHubService);
        intakeSampleParcels(foodBankHubService);

        System.out.println("=== FoodForward SA Johannesburg Hub ===\n");
        System.out.println("Registered donors: " + foodBankHubService.donorSnapshot().size());
        System.out.println("Known parcel IDs: " + foodBankHubService.parcelIdSnapshot().size());
        System.out.println("Intake parcels before dispatch prep: " + foodBankHubService.intakeSnapshot().size());
        System.out.println();

        String dispatchZone = "EAST";
        List<Beneficiary> prioritizedBeneficiaries = foodBankHubService.prioritizedBeneficiariesForZone(dispatchZone);

        System.out.println("Beneficiaries in zone " + dispatchZone + " sorted by need:");
        for (Beneficiary prioritizedBeneficiary : prioritizedBeneficiaries) {
            System.out.println(" - " + prioritizedBeneficiary.display());
        }
        System.out.println();

        foodBankHubService.prepareDispatchQueue(dispatchZone);
        System.out.println("Dispatch queue prepared for zone " + dispatchZone + ": " + foodBankHubService.dispatchQueueSize() + " parcel(s).\n");

        // LinkedList queue behavior: dispatch in FIFO order until no parcels remain.
        FoodParcel queuedParcel = foodBankHubService.dispatchNextParcel();
        while (queuedParcel != null) {
            System.out.println("Dispatching parcel: " + queuedParcel.label());
            queuedParcel = foodBankHubService.dispatchNextParcel();
        }

        System.out.println();
        System.out.println("Intake parcels remaining after dispatch: " + foodBankHubService.intakeSnapshot().size());
        System.out.println("Dineo now has one consistent collections-based workflow instead of fragile spreadsheets.");
    }

    /**
     * Adds donor records so parcel intake can validate source organizations.
     */
    private static void registerSampleDonors(FoodBankHubService foodBankHubService) {
        foodBankHubService.registerDonor(new Donor("DON-001", "Ubuntu Retail Group"));
        foodBankHubService.registerDonor(new Donor("DON-002", "Gauteng Agro Supplies"));
        foodBankHubService.registerDonor(new Donor("DON-003", "Metro Foods Wholesale"));
    }

    /**
     * Adds beneficiary records with need scores for zone-based dispatch planning.
     */
    private static void registerSampleBeneficiaries(FoodBankHubService foodBankHubService) {
        foodBankHubService.registerBeneficiary(new Beneficiary("BEN-101", "Alex Community Kitchen", 92, "EAST"));
        foodBankHubService.registerBeneficiary(new Beneficiary("BEN-102", "Hillbrow Child Nutrition Centre", 88, "CENTRAL"));
        foodBankHubService.registerBeneficiary(new Beneficiary("BEN-103", "Kempton Women Support Hub", 95, "EAST"));
        foodBankHubService.registerBeneficiary(new Beneficiary("BEN-104", "Randburg Shelter Pantry", 76, "WEST"));
    }

    /**
     * Adds parcels to intake and includes one duplicate attempt to demonstrate HashSet protection.
     */
    private static void intakeSampleParcels(FoodBankHubService foodBankHubService) {
        foodBankHubService.receiveParcel(new FoodParcel("PAR-9001", "DON-001", FoodCategory.DRY_GOODS, 60, "EAST"));
        foodBankHubService.receiveParcel(new FoodParcel("PAR-9002", "DON-002", FoodCategory.FRESH_PRODUCE, 35, "EAST"));
        foodBankHubService.receiveParcel(new FoodParcel("PAR-9003", "DON-003", FoodCategory.FROZEN_ITEMS, 20, "WEST"));

        try {
            foodBankHubService.receiveParcel(new FoodParcel("PAR-9002", "DON-001", FoodCategory.DRY_GOODS, 10, "CENTRAL"));
        } catch (IllegalArgumentException duplicateParcelException) {
            System.out.println("Duplicate protection check: " + duplicateParcelException.getMessage());
            System.out.println();
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - ArrayList: intakeLedger stores parcels in insertion order for reconciliation and review.
    - HashMap: donorsById and beneficiariesById provide fast ID-based access.
    - HashSet: knownParcelIds enforces fast duplicate detection for parcel IDs.
    - Iterator: intake parcels are moved safely while iterating without concurrent modification.
    - LinkedList: dispatchQueue models FIFO routing for outgoing parcels.
    - Collections: utility methods sort lists and expose unmodifiable snapshots.

    Interview questions this code helps answer:
    - When would you choose ArrayList over LinkedList in logistics workflows?
    - Why is HashSet useful for duplicate control?
    - How does Iterator.remove differ from removing inside a for-each loop?
    - What are practical benefits of Collections.unmodifiableList and unmodifiableMap?
    - How do you choose between HashMap and other map types?

    Common mistake and how this code avoids it:
    - Mistake: using one collection type for every problem. This design picks collection types by access pattern, improving clarity, performance, and correctness.
    */
}
