package com.ndlovu.javacore.collections.stellenboschharvest;

/**
 * Represents one vineyard harvest pick captured by Anton during the March season.
 */
public record HarvestEntry(
        int blockNumber,
        String varietal,
        double kilogramYield,
        double brixReading,
        String pickTime) {

    /**
     * Returns a readable line so each pick can be printed in sequence reports.
     */
    public String asLine() {
        return "Block " + blockNumber
                + " | " + varietal
                + " | " + kilogramYield + " kg"
                + " | Brix " + brixReading
                + " | Pick " + pickTime;
    }
}
