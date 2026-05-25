/*
 * The JSE trading floor in Sandton now runs electronically, but terminal behavior still mirrors old floor discipline.
 * Each terminal owns a private order book that tracks buy and sell orders for that specific machine.
 * Because the order book has no meaning outside its terminal context, it is modeled as a member inner class.
 * Rorisang also uses a static nested immutable trade record so reports can move safely between systems.
 * For ranking terminal performance, she applies an anonymous Comparator implementation directly in reporting flow.
 * The result is a clean, encapsulated design that is easy to understand and practical for revision.
 */

package com.ndlovu.javacore.nestedclasses.jseintermediate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Demonstrates an intermediate nested-classes design for JSE terminal operations and reporting.
 */
public class JSETradingTerminalIntermediateDemo {

    /**
     * Runs a realistic simulation of two terminals, then prints a ranked performance report.
     */
    public static void main(String[] args) {
        TradeProcessingPolicy throughputPolicy = new TradeProcessingPolicy() {
            /**
             * Maps processed-order counts to throughput bands for quick operations interpretation.
             */
            @Override
            public String classifyTerminalThroughput(int totalOrdersProcessed) {
                if (totalOrdersProcessed >= 5) {
                    return "High";
                }
                if (totalOrdersProcessed >= 3) {
                    return "Medium";
                }
                return "Low";
            }
        };

        JSETradingTerminal alphaTerminal = new JSETradingTerminal("SANDTON-ALPHA-01", throughputPolicy);
        JSETradingTerminal bravoTerminal = new JSETradingTerminal("SANDTON-BRAVO-09", throughputPolicy);

        JSETradingTerminal.OrderBook alphaOrderBook = alphaTerminal.openOrderBook();
        alphaOrderBook.placeBuyOrder("NPN", 200, 34150);
        alphaOrderBook.placeSellOrder("AGL", 110, 19900);
        alphaOrderBook.placeBuyOrder("MTN", 150, 10875);

        JSETradingTerminal.OrderBook bravoOrderBook = bravoTerminal.openOrderBook();
        bravoOrderBook.placeBuyOrder("SOL", 125, 13080);
        bravoOrderBook.placeSellOrder("FSR", 310, 6490);
        bravoOrderBook.placeBuyOrder("BTI", 90, 70100);
        bravoOrderBook.placeSellOrder("SBK", 140, 18420);

        List<JSETradingTerminal.TradeRecord> terminalPerformanceRecords = new ArrayList<>();
        terminalPerformanceRecords.add(alphaOrderBook.closeSessionAndCreateRecord());
        terminalPerformanceRecords.add(bravoOrderBook.closeSessionAndCreateRecord());

        terminalPerformanceRecords.sort(new Comparator<JSETradingTerminal.TradeRecord>() {
            /**
             * Sorts records by descending order count so the strongest terminal appears first.
             */
            @Override
            public int compare(
                    JSETradingTerminal.TradeRecord firstRecord,
                    JSETradingTerminal.TradeRecord secondRecord) {
                // Anonymous class: one-off comparator behavior kept close to report logic.
                return Integer.compare(
                        secondRecord.totalOrdersProcessed(),
                        firstRecord.totalOrdersProcessed());
            }
        });

        System.out.println();
        System.out.println("=== JSE Terminal Performance Report ===");
        for (int reportPosition = 0; reportPosition < terminalPerformanceRecords.size(); reportPosition++) {
            JSETradingTerminal.TradeRecord tradeRecord = terminalPerformanceRecords.get(reportPosition);
            System.out.println((reportPosition + 1) + ". " + tradeRecord.summaryLine());
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - inner class: OrderBook is tied to one JSETradingTerminal instance and uses outer state directly.
    - member class: OrderBook is declared as a member inside terminal type to model ownership.
    - static nested class: TradeRecord is immutable and does not require outer instance references.
    - anonymous class: Comparator is created inline for one-time performance sorting behavior.
    - encapsulation: counters and totals are private and modified only through order methods.

    Interview questions this code prepares you to answer:
    - What is the difference between a member inner class and a static nested class?
    - Why would you choose an anonymous class for a Comparator?
    - How does encapsulation protect domain metrics in a trading model?
    - When should nested classes be preferred over separate top-level classes?
    - How can an abstract base class and interface improve design clarity?

    Common mistake and how this code avoids it:
    - Mistake: making order metrics public and letting outside code change them arbitrarily.
      Avoidance: terminal metrics stay private and are updated only by explicit domain methods.
    */
}
