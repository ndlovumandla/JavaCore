/*
 * Delaire Graff in Stellenbosch harvests across many blocks, and each pick affects later blending decisions.
 * Anton records block, varietal, yield, Brix, and pick time, but notebook logs are difficult to search and sort.
 * Claudia is replacing the paper workflow with a Java collections-based harvest tracker.
 * The system keeps pick order, groups yield by varietal, checks uniqueness, and builds sorted report output.
 * This example shows how different collection types solve different operational needs in one realistic flow.
 */

package com.ndlovu.javacore.collections.stellenboschharvest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Runs the Stellenbosch harvest simulation and demonstrates practical collection choices.
 */
public class StellenboschHarvestLogDemo {

    /**
     * Starts the season simulation, processes the harvest log, and prints a blending report.
     */
    public static void main(String[] args) {
        LinkedList<HarvestEntry> seasonalPickOrder = new LinkedList<>();
        seedHarvestData(seasonalPickOrder);

        HashMap<String, Double> yieldByVarietal = new HashMap<>();
        HashSet<Integer> harvestedBlocks = new HashSet<>();
        HashSet<String> harvestedVarietals = new HashSet<>();

        System.out.println("=== Delaire Graff Harvest Log (March) ===");
        System.out.println();

        printPickOrder(seasonalPickOrder);
        processSeasonLog(seasonalPickOrder, yieldByVarietal, harvestedBlocks, harvestedVarietals);
        printBlendingReport(yieldByVarietal, harvestedBlocks, harvestedVarietals);
    }

    /**
     * Adds sample harvest picks so the demo has realistic records to process.
     */
    private static void seedHarvestData(LinkedList<HarvestEntry> seasonalPickOrder) {
        seasonalPickOrder.add(new HarvestEntry(3, "Cabernet Sauvignon", 1420.0, 24.3, "06:10"));
        seasonalPickOrder.add(new HarvestEntry(11, "Shiraz", 1310.0, 23.8, "06:45"));
        seasonalPickOrder.add(new HarvestEntry(7, "Chardonnay", 1180.0, 22.9, "07:20"));
        seasonalPickOrder.add(new HarvestEntry(3, "Cabernet Sauvignon", 0.0, 24.1, "07:40"));
        seasonalPickOrder.add(new HarvestEntry(19, "Merlot", 1265.0, 23.5, "08:05"));
        seasonalPickOrder.add(new HarvestEntry(14, "Pinot Noir", 980.0, 22.4, "08:35"));
    }

    /**
     * Prints all picks in original harvest order to preserve operational sequence context.
     */
    private static void printPickOrder(LinkedList<HarvestEntry> seasonalPickOrder) {
        System.out.println("Pick order sequence:");
        for (HarvestEntry harvestEntry : seasonalPickOrder) {
            System.out.println(" - " + harvestEntry.asLine());
        }
        System.out.println();
    }

    /**
     * Iterates through the season log, removes invalid entries safely, and updates summary collections.
     */
    private static void processSeasonLog(
            LinkedList<HarvestEntry> seasonalPickOrder,
            HashMap<String, Double> yieldByVarietal,
            HashSet<Integer> harvestedBlocks,
            HashSet<String> harvestedVarietals) {

        // Iterator allows safe removal of bad records while traversing the LinkedList.
        Iterator<HarvestEntry> seasonIterator = seasonalPickOrder.iterator();
        while (seasonIterator.hasNext()) {
            HarvestEntry seasonalPick = seasonIterator.next();

            // Edge-case handling: remove zero or negative yields to protect report quality.
            if (seasonalPick.kilogramYield() <= 0) {
                seasonIterator.remove();
                continue;
            }

            // HashMap groups yields by varietal for blending totals.
            yieldByVarietal.merge(seasonalPick.varietal(), seasonalPick.kilogramYield(), Double::sum);

            // HashSet tracks unique blocks and varietals with fast duplicate checks.
            harvestedBlocks.add(seasonalPick.blockNumber());
            harvestedVarietals.add(seasonalPick.varietal());
        }
    }

    /**
     * Builds and prints a sorted summary report for Anton's cellar blending review.
     */
    private static void printBlendingReport(
            HashMap<String, Double> yieldByVarietal,
            HashSet<Integer> harvestedBlocks,
            HashSet<String> harvestedVarietals) {

        // ArrayList stores report lines so Collections utilities can sort output predictably.
        ArrayList<String> varietalReportLines = new ArrayList<>();
        for (Map.Entry<String, Double> varietalEntry : yieldByVarietal.entrySet()) {
            varietalReportLines.add(varietalEntry.getKey() + " total: " + String.format("%.1f", varietalEntry.getValue()) + " kg");
        }

        // Collections.sort organizes varietals alphabetically for easy cellar review.
        Collections.sort(varietalReportLines);

        // Collections utility is also used to sort unique block numbers for clean operations reporting.
        List<Integer> sortedBlocks = new ArrayList<>(harvestedBlocks);
        Collections.sort(sortedBlocks);

        System.out.println("Blending report summary:");
        System.out.println("Unique blocks harvested: " + sortedBlocks);
        System.out.println("Unique varietals harvested: " + harvestedVarietals.size());
        System.out.println("Varietal totals:");
        for (String reportLine : varietalReportLines) {
            System.out.println(" - " + reportLine);
        }
        System.out.println();
        System.out.println("Claudia can now search, group, and sort the season in seconds.");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - ArrayList: stores report lines and supports utility-based sorting.
    - HashMap: groups and accumulates kilogram yield by varietal key.
    - HashSet: tracks unique blocks and varietals with duplicate-safe insertion.
    - Iterator: traverses and safely removes invalid harvest entries during processing.
    - LinkedList: preserves harvest pick-order sequence from field operations.
    - Collections: sorts report lines and block lists for readable output.

    Interview questions this code helps answer:
    - When should LinkedList be preferred for operational sequence data?
    - Why use HashMap for grouped totals instead of nested loops?
    - What problem does Iterator.remove solve compared to for-each loops?
    - Why is HashSet useful for uniqueness checks?
    - What are common uses of Collections.sort in reporting workflows?

    Common mistake and how this code avoids it:
    - Mistake: modifying a collection directly inside a for-each loop. This code uses Iterator.remove during traversal to avoid concurrent modification errors.
    */
}
