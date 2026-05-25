package com.ndlovu.javacore.nestedclasses.jseintermediate;

/**
 * Represents shared behavior for trading terminals operating on the JSE electronic floor.
 */
public abstract class AbstractTradingTerminal {

    private final String terminalCode;
    private final TradeProcessingPolicy tradeProcessingPolicy;

    /**
     * Stores common terminal identity and policy dependencies used by concrete terminals.
     */
    protected AbstractTradingTerminal(String terminalCode, TradeProcessingPolicy tradeProcessingPolicy) {
        this.terminalCode = terminalCode;
        this.tradeProcessingPolicy = tradeProcessingPolicy;
    }

    /**
     * Exposes terminal identity for logs and immutable trade records.
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * Exposes throughput policy so nested order-book logic can classify performance.
     */
    public TradeProcessingPolicy getTradeProcessingPolicy() {
        return tradeProcessingPolicy;
    }

    /**
     * Opens a terminal-specific order-book session to capture trading-day activity.
     */
    public abstract JSETradingTerminal.OrderBook openOrderBook();
}
