package com.ndlovu.javacore.threads.watermeter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the shared municipal database where field agents upload suburb meter totals.
 */
public class MunicipalWaterDatabase {

    private final Map<String, Integer> suburbTotals = new HashMap<>();
    private int unsafeUploadCounter = 0;
    private int safeUploadCounter = 0;

    private final Object northRegionLock = new Object();
    private final Object southRegionLock = new Object();

    /**
     * Updates suburb totals safely so simultaneous agent writes do not corrupt shared records.
     */
    public synchronized void commitReadingSafely(String suburbName, int meterReadingsUploaded) {
        int currentTotal = suburbTotals.getOrDefault(suburbName, 0);
        suburbTotals.put(suburbName, currentTotal + meterReadingsUploaded);
        safeUploadCounter++;
    }

    /**
     * Increments a shared counter without synchronization to demonstrate race-condition risk.
     */
    public void incrementUnsafeCounter() {
        // Race condition: multiple threads can read and write this value at the same time.
        unsafeUploadCounter++;
    }

    /**
     * Simulates a cross-region operation using consistent lock order to avoid deadlock.
     */
    public void synchronizeRegionalIndexes() {
        // Deadlock prevention: every thread acquires locks in the same order.
        synchronized (northRegionLock) {
            synchronized (southRegionLock) {
                // Intentionally minimal work: focus is lock-order safety.
            }
        }
    }

    /**
     * Returns the total uploads written by the unsafe counter.
     */
    public int getUnsafeUploadCounter() {
        return unsafeUploadCounter;
    }

    /**
     * Returns the total uploads written by synchronized commit operations.
     */
    public int getSafeUploadCounter() {
        return safeUploadCounter;
    }

    /**
     * Returns an immutable text snapshot of suburb totals for reporting.
     */
    public String suburbTotalsSnapshot() {
        return suburbTotals.toString();
    }
}
