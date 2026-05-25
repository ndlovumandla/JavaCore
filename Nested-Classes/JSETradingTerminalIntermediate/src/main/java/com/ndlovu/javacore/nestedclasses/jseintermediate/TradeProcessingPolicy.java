package com.ndlovu.javacore.nestedclasses.jseintermediate;

/**
 * Defines a reporting policy contract used to classify terminal performance levels.
 */
public interface TradeProcessingPolicy {

    /**
     * Classifies terminal throughput so operators can quickly interpret report rankings.
     */
    String classifyTerminalThroughput(int totalOrdersProcessed);
}
