package com.ndlovu.javacore.nestedclasses.jseintermediate;

/**
 * Models one JSE trading terminal with a private order book and immutable trade report output.
 */
public class JSETradingTerminal extends AbstractTradingTerminal {

    /**
     * Constructs a concrete terminal used for the day trading simulation.
     */
    public JSETradingTerminal(String terminalCode, TradeProcessingPolicy tradeProcessingPolicy) {
        super(terminalCode, tradeProcessingPolicy);
    }

    /**
     * Starts a member inner order-book session tightly coupled to this terminal instance.
     */
    @Override
    public OrderBook openOrderBook() {
        System.out.println("Opening order book for " + getTerminalCode() + "...");
        return new OrderBook();
    }

    /**
     * Member inner class representing a terminal-owned order book for one session.
     */
    public class OrderBook {

        private int buyOrderCount;
        private int sellOrderCount;
        private long grossTradeValueCents;

        /**
         * Registers a buy order so this terminal can track daily activity and value.
         */
        public void placeBuyOrder(String shareCode, int shareQuantity, int unitPriceCents) {
            // Member inner class directly uses outer-terminal identity, showing close coupling.
            System.out.println(getTerminalCode() + " BUY " + shareCode + " x" + shareQuantity);
            buyOrderCount++;

            // Encapsulation: totals are updated only through domain methods, not external writes.
            grossTradeValueCents += (long) shareQuantity * unitPriceCents;
        }

        /**
         * Registers a sell order so this terminal captures balanced order-book flow.
         */
        public void placeSellOrder(String shareCode, int shareQuantity, int unitPriceCents) {
            System.out.println(getTerminalCode() + " SELL " + shareCode + " x" + shareQuantity);
            sellOrderCount++;
            grossTradeValueCents += (long) shareQuantity * unitPriceCents;
        }

        /**
         * Closes the session and creates an immutable static nested trade record for reporting.
         */
        public TradeRecord closeSessionAndCreateRecord() {
            int totalOrdersProcessed = buyOrderCount + sellOrderCount;
            String throughputBand = getTradeProcessingPolicy().classifyTerminalThroughput(totalOrdersProcessed);
            return new TradeRecord(getTerminalCode(), totalOrdersProcessed, grossTradeValueCents, throughputBand);
        }
    }

    /**
     * Static nested immutable object carrying finalized trade metrics between modules.
     */
    public static class TradeRecord {

        private final String terminalCode;
        private final int totalOrdersProcessed;
        private final long grossTradeValueCents;
        private final String throughputBand;

        /**
         * Stores finalized read-only values so reports are safe and predictable.
         */
        public TradeRecord(
                String terminalCode,
                int totalOrdersProcessed,
                long grossTradeValueCents,
                String throughputBand) {
            this.terminalCode = terminalCode;
            this.totalOrdersProcessed = totalOrdersProcessed;
            this.grossTradeValueCents = grossTradeValueCents;
            this.throughputBand = throughputBand;
        }

        /**
         * Returns order count so report sorting can rank terminal performance.
         */
        public int totalOrdersProcessed() {
            return totalOrdersProcessed;
        }

        /**
         * Returns a human-readable summary line for end-of-day operations output.
         */
        public String summaryLine() {
            return "Terminal=" + terminalCode
                    + " | Orders=" + totalOrdersProcessed
                    + " | GrossValue=R" + String.format("%.2f", grossTradeValueCents / 100.0)
                    + " | Throughput=" + throughputBand;
        }
    }
}
