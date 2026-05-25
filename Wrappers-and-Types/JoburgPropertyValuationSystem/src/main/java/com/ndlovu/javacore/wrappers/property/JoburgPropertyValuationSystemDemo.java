/*
 * Johannesburg's rates department calculates municipal charges from large property valuations.
 * Land values are recorded as long numbers, while improvements and multipliers require decimals.
 * Zone multipliers arrive as Strings from configuration files and must be parsed safely.
 * Nombuso documents each Java type conversion so the billing engine remains predictable and teachable.
 * This demo shows wrappers, boxing/unboxing, widening, narrowing, casting, and Character parsing.
 */

package com.ndlovu.javacore.wrappers.property;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs a beginner-friendly property billing flow focused on wrapper classes and type conversion.
 */
public class JoburgPropertyValuationSystemDemo {

    /**
     * Executes valuation conversion pipeline and prints statement-ready billing values.
     */
    public static void main(String[] args) {
        List<PropertyValuationRecord> propertyValuationRecords = new ArrayList<>();
        propertyValuationRecords.add(new PropertyValuationRecord("A-20451", 2_850_000L, 915_450.75, "0.0125"));
        propertyValuationRecords.add(new PropertyValuationRecord("B-77803", 3_600_000L, 1_350_100.25, "0.0110"));
        propertyValuationRecords.add(new PropertyValuationRecord("C-11007", 1_980_000L, 620_890.50, "0.0132"));

        List<Integer> statementAmountsRand = new ArrayList<>();

        System.out.println("=== City of Johannesburg Rates Engine ===");

        for (PropertyValuationRecord propertyValuationRecord : propertyValuationRecords) {
            // Widening conversion: long land value becomes double for mixed decimal arithmetic.
            double landValueAsDouble = propertyValuationRecord.getLandValueRand();

            // Double wrapper stores combined valuation in object form for pipeline operations.
            Double totalValuationWrapper = Double.valueOf(
                    landValueAsDouble + propertyValuationRecord.getImprovementValueRand());

            float zoneMultiplier = propertyValuationRecord.parseZoneMultiplier();
            double calculatedCharge = totalValuationWrapper.doubleValue() * zoneMultiplier;

            // Safe narrowing: cap to int range before explicit cast to avoid silent overflow.
            int statementChargeRand = safeNarrowDoubleToInt(calculatedCharge);

            // Autoboxing: primitive int automatically boxed into Integer collection element.
            statementAmountsRand.add(statementChargeRand);

            // Unboxing: Integer to int for simple threshold check.
            int unboxedStatementAmount = statementAmountsRand.get(statementAmountsRand.size() - 1);

            // Character wrapper usage to inspect mixed letter-digit property reference format.
            Character zonePrefix = Character.valueOf(propertyValuationRecord.getPropertyReference().charAt(0));
            boolean prefixIsLetter = Character.isLetter(zonePrefix);

            System.out.println("Property: " + propertyValuationRecord.getPropertyReference());
            System.out.println("Zone prefix letter: " + prefixIsLetter + " (" + zonePrefix + ")");
            System.out.println("Total valuation (Double): R" + String.format("%.2f", totalValuationWrapper));
            System.out.println("Parsed multiplier (float): " + zoneMultiplier);
            System.out.println("Statement amount (Integer): R" + unboxedStatementAmount);
            System.out.println();
        }

        System.out.println("Statement amounts collection: " + statementAmountsRand);
    }

    /**
     * Narrows a double billing value to int rand safely by range-checking before casting.
     */
    private static int safeNarrowDoubleToInt(double doubleBillingValue) {
        if (doubleBillingValue > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (doubleBillingValue < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        // Explicit casting performs narrowing conversion from double to int.
        return (int) Math.round(doubleBillingValue);
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Integer: statement billing values are represented as Integer entries in collections.
    - Double: total valuation and charge computations use Double for decimal precision.
    - autoboxing: int statement values are automatically boxed into List<Integer>.
    - widening: long land values widen to double during mixed arithmetic.
    - narrowing: double charges narrow to int for statement output.
    - casting: explicit (int) behavior is used in safe narrowing workflow.
    - Character: property-reference prefixes are interpreted with Character helper methods.

    Interview questions this code prepares you to answer:
    - What is the difference between widening and narrowing conversions?
    - Why can narrowing conversions be risky without range checks?
    - How does autoboxing/unboxing work with Java collections?
    - Why use wrapper classes like Integer and Double in business pipelines?
    - How can Character methods help parse mixed-format identifiers?

    Common mistake and how this code avoids it:
    - Mistake: directly casting large double values to int without bounds checks, causing overflow bugs.
      Avoidance: safeNarrowDoubleToInt checks range first and then performs explicit narrowing.
    */
}
