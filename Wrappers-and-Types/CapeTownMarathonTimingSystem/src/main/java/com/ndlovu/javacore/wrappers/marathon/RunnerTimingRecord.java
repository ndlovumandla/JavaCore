package com.ndlovu.javacore.wrappers.marathon;

/**
 * Represents one runner's marathon timing data captured from RFID checkpoints.
 */
public class RunnerTimingRecord {

    private final String bibNumber;
    private final long finishTimeMilliseconds;
    private final String ageCategoryUpperLimitText;

    /**
     * Stores raw timing and configuration values as they arrive in the timing engine.
     */
    public RunnerTimingRecord(String bibNumber, long finishTimeMilliseconds, String ageCategoryUpperLimitText) {
        this.bibNumber = bibNumber;
        this.finishTimeMilliseconds = finishTimeMilliseconds;
        this.ageCategoryUpperLimitText = ageCategoryUpperLimitText;
    }

    /**
     * Returns runner bib for display and bib-format parsing operations.
     */
    public String getBibNumber() {
        return bibNumber;
    }

    /**
     * Returns raw finish milliseconds from chip readers for pace and display conversions.
     */
    public long getFinishTimeMilliseconds() {
        return finishTimeMilliseconds;
    }

    /**
     * Parses age category upper limit from String config to Integer for numeric comparisons.
     */
    public Integer parseAgeCategoryUpperLimit() {
        // Integer wrapper parse converts String configuration into numeric type.
        return Integer.valueOf(ageCategoryUpperLimitText);
    }
}
