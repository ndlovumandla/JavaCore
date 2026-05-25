/*
 * The JSE trading floor in Sandton is now digital, but each terminal still mirrors old floor logic.
 * Every terminal keeps its own internal order book for the buy and sell orders placed on that device.
 * That order book has no meaning outside its terminal, so it is modeled as a member inner class.
 * Rorisang also uses a static nested class for immutable trade records that can be passed around safely.
 * Finally, an anonymous class implements report sorting so top-performing terminals appear first.
 */

package com.ndlovu.javacore.nestedclasses.jse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Runs a beginner-friendly demonstration of inner and nested classes for JSE terminal modeling.
 */
public class JSETradingTerminalDemo {

    /**
     * Executes a realistic terminal session and prints a sorted performance report.
     */
    public static void main(String[] args) {
        TradingTerminal sandtonTerminalA = new TradingTerminal("SANDTON-TERM-A1");
        TradingTerminal sandtonTerminalB = new TradingTerminal("SANDTON-TERM-B7");

        TradingTerminal.OrderBook terminalAOrderBook = sandtonTerminalA.openOrderBook();
        terminalAOrderBook.placeBuyOrder("NPN", 200, 34200);
        terminalAOrderBook.placeSellOrder("AGL", 100, 19850);

        TradingTerminal.OrderBook terminalBOrderBook = sandtonTerminalB.openOrderBook();
        terminalBOrderBook.placeBuyOrder("SOL", 120, 13120);
        terminalBOrderBook.placeSellOrder("FSR", 300, 6530);
        terminalBOrderBook.placeBuyOrder("BTI", 90, 69900);

        TradingTerminal.TradeRecord recordA = terminalAOrderBook.closeSessionAndCreateRecord();
        TradingTerminal.TradeRecord recordB = terminalBOrderBook.closeSessionAndCreateRecord();

        List<TradingTerminal.TradeRecord> terminalPerformanceReport = new ArrayList<>();
        terminalPerformanceReport.add(recordA);
        terminalPerformanceReport.add(recordB);

        terminalPerformanceReport.sort(new Comparator<TradingTerminal.TradeRecord>() {
            /**
             * Compares two terminal records so higher order volume appears first in reports.
             */
            @Override
            public int compare(TradingTerminal.TradeRecord firstRecord, TradingTerminal.TradeRecord secondRecord) {
                // Anonymous class: one-off comparator behavior used only in this reporting scenario.
                return Integer.compare(secondRecord.totalOrders(), firstRecord.totalOrders());
            }
        });

        System.out.println("=== JSE Terminal Performance Report ===");
        for (int reportRank = 0; reportRank < terminalPerformanceReport.size(); reportRank++) {
            TradingTerminal.TradeRecord terminalRecord = terminalPerformanceReport.get(reportRank);
            System.out.println((reportRank + 1) + ". " + terminalRecord.summaryLine());
        }
    }

    /**
     * Represents one JSE trading terminal that owns its internal order-processing state.
     */
    static class TradingTerminal {

        private final String terminalCode;

        /**
         * Stores terminal identity so order book activity is linked to one machine.
         */
        TradingTerminal(String terminalCode) {
            this.terminalCode = terminalCode;
        }

        /**
         * Opens a member inner order book tied to this terminal's context.
         */
        OrderBook openOrderBook() {
            System.out.println("Opening order book for " + terminalCode + "...");
            return new OrderBook();
        }

        /**
         * Member inner class representing the private order book of this specific terminal.
         */
        class OrderBook {

            private int buyOrderCount;
            private int sellOrderCount;
            private long totalTradeValueCents;

            /**
             * Adds a buy order into the terminal-local order book for session tracking.
             */
            void placeBuyOrder(String equityCode, int shareQuantity, int unitPriceCents) {
                // Inner class uses outer terminal identity directly, showing close coupling.
                System.out.println(terminalCode + " BUY " + equityCode + " x" + shareQuantity);
                buyOrderCount++;

                // Encapsulation: order metrics are updated only through controlled methods.
                totalTradeValueCents += (long) shareQuantity * unitPriceCents;
            }

            /**
             * Adds a sell order into the terminal-local order book for session tracking.
             */
            void placeSellOrder(String equityCode, int shareQuantity, int unitPriceCents) {
                System.out.println(terminalCode + " SELL " + equityCode + " x" + shareQuantity);
                sellOrderCount++;
                totalTradeValueCents += (long) shareQuantity * unitPriceCents;
            }

            /**
             * Finalizes session totals and returns an immutable static nested trade record.
             */
            TradeRecord closeSessionAndCreateRecord() {
                int totalOrders = buyOrderCount + sellOrderCount;
                return new TradeRecord(terminalCode, totalOrders, totalTradeValueCents);
            }
        }

        /**
         * Static nested class representing immutable trade summary data for reporting modules.
         */
        static class TradeRecord {

            private final String terminalCode;
            private final int totalOrders;
            private final long totalTradeValueCents;

            /**
             * Captures finalized metrics so reports can be shared without terminal references.
             */
            TradeRecord(String terminalCode, int totalOrders, long totalTradeValueCents) {
                this.terminalCode = terminalCode;
                this.totalOrders = totalOrders;
                this.totalTradeValueCents = totalTradeValueCents;
            }

            /**
             * Returns total order volume for ranking terminal performance reports.
             */
            int totalOrders() {
                return totalOrders;
            }

            /**
             * Returns one readable line for output in end-of-day performance reporting.
             */
            String summaryLine() {
                return "Terminal=" + terminalCode
                        + " | Orders=" + totalOrders
                        + " | TradeValue=R" + String.format("%.2f", totalTradeValueCents / 100.0);
            }
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - inner class: OrderBook is an inner class that belongs to one TradingTerminal instance.
    - member class: OrderBook is declared as a member inside TradingTerminal to model tight coupling.
    - static nested class: TradeRecord is static and immutable for safe reporting transfer.
    - anonymous class: Comparator is created inline for one-off terminal sorting behavior.
    - encapsulation: order counters and trade value are private and updated through methods only.

    Interview questions this code prepares you to answer:
    - When should you choose an inner class over a top-level class?
    - What is the practical difference between inner and static nested classes?
    - Why are anonymous classes useful for one-time interface implementations?
    - How does encapsulation protect business state in trading systems?

    Common mistake and how this code avoids it:
    - Mistake: exposing order-book fields publicly so any code can change totals incorrectly.
      Avoidance: all state is private, and updates are performed only by domain-specific methods.
    */
}
