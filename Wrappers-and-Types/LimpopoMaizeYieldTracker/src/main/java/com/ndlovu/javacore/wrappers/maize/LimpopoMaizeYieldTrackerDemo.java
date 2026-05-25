/*
 * Limpopo agriculture officers capture maize yields from smallholder farmers each season.
 * Legacy systems store yield values as String fields, even though subsidy calculations need numbers.
 * Boitumelo converts these values through parsing, widening, and controlled narrowing for payment schedules.
 * The pipeline also rounds subsidy outputs for certificates and stores values in sorted collections.
 * This demonstration shows how wrapper classes and type conversion support real agricultural administration.
 */

package com.ndlovu.javacore.wrappers.maize;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Runs a beginner-friendly yield-processing flow showing Java wrappers and type conversions.
 */
public class LimpopoMaizeYieldTrackerDemo {

    /**
     * Executes seasonal yield parsing, subsidy math, and reporting conversions end-to-end.
     */
    public static void main(String[] args) {
        List<FarmerYieldRecord> seasonalYieldRecords = new ArrayList<>();
        seasonalYieldRecords.add(new FarmerYieldRecord("Ms. Mashaba", "Sekhukhune", "3850"));
        seasonalYieldRecords.add(new FarmerYieldRecord("Mr. Baloyi", "Mopani", "4120"));
        seasonalYieldRecords.add(new FarmerYieldRecord("Ms. Mulaudzi", "Vhembe", "3675"));

        TreeSet<Integer> sortedYieldValues = new TreeSet<>();

        System.out.println("=== Limpopo Maize Subsidy Processing ===");

        for (FarmerYieldRecord farmerYieldRecord : seasonalYieldRecords) {
            Integer yieldIntegerWrapper = farmerYieldRecord.parseYieldAsInteger();

            // Widening conversion: int value becomes double for precise subsidy arithmetic.
            double yieldForSubsidyMath = yieldIntegerWrapper;

            // Double wrapper usage for explicit object representation in reporting pipelines.
            Double subsidyAmountWrapper = Double.valueOf(yieldForSubsidyMath * 1.37);

            // Casting + narrowing: convert double payout estimate to whole-rand payment schedule value.
            int scheduledPayoutRand = (int) subsidyAmountWrapper.doubleValue();

            // Autoboxing: primitive int automatically boxed to Integer when added to TreeSet.
            sortedYieldValues.add(yieldIntegerWrapper.intValue());

            // Character wrapper: capture district initial for compact certificate code generation.
            Character districtInitial = Character.valueOf(
                    farmerYieldRecord.getDistrictName().charAt(0));

            double roundedCertificateAmount = Math.round(subsidyAmountWrapper * 100.0) / 100.0;

            System.out.println("Farmer: " + farmerYieldRecord.getFarmerName());
            System.out.println("District code: " + districtInitial);
            System.out.println("Parsed yield (Integer): " + yieldIntegerWrapper + " kg/ha");
            System.out.println("Subsidy amount (Double, rounded): R" + roundedCertificateAmount);
            System.out.println("Scheduled payout after narrowing cast: R" + scheduledPayoutRand);
            System.out.println();
        }

        System.out.println("Sorted yields for provincial review: " + sortedYieldValues);
        System.out.println("High-yield threshold met by top farmer: "
                + (sortedYieldValues.last() > 4000));
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Integer: wraps parsed yield values from legacy String database fields.
    - Double: wraps subsidy calculations that require decimal precision.
    - autoboxing: primitive int values are automatically boxed into Integer for TreeSet storage.
    - widening: int yield values are widened to double for arithmetic without data loss.
    - narrowing: double payout values are narrowed to int for whole-rand payment scheduling.
    - casting: explicit (int) cast controls narrowing conversion behavior.
    - Character: district initial is modeled with Character for certificate code formatting.

    Interview questions this code prepares you to answer:
    - What is the difference between widening and narrowing conversions in Java?
    - Why would you use wrapper classes like Integer and Double in application code?
    - What is autoboxing and where does it appear in collection usage?
    - When is explicit casting required during numeric conversion?
    - How do you safely convert String numeric data from legacy systems?

    Common mistake and how this code avoids it:
    - Mistake: performing subsidy math only with int values, which loses decimal precision.
      Avoidance: the code widens to double and uses Double for precise calculation and rounding.
    */
}
