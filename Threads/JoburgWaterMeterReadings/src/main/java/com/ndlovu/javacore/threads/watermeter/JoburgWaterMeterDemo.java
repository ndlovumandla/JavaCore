/*
 * City of Johannesburg used to force meter-reading agents to upload one by one at the depot.
 * Ayesha modernized the process so multiple agents upload in parallel using threads.
 * Because many agents write to the same municipal store, synchronized updates are required to avoid data corruption.
 * This simulation adds network delay with sleep, waits for all uploads using join, and shows how race conditions appear on unsynchronized counters.
 * It also demonstrates deadlock-safe locking by enforcing a consistent lock acquisition order.
 */

package com.ndlovu.javacore.threads.watermeter;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs a beginner-friendly multithreading simulation of meter uploads from field agents.
 */
public class JoburgWaterMeterDemo {

    /**
     * Starts agent threads, waits for completion, and prints billing-ready results.
     */
    public static void main(String[] args) throws InterruptedException {
        MunicipalWaterDatabase municipalWaterDatabase = new MunicipalWaterDatabase();

        List<Thread> agentThreads = new ArrayList<>();

        // Thread + Runnable: each agent upload task runs concurrently.
        agentThreads.add(new Thread(new AgentUploadTask("Agent-A", "Soweto", 130, municipalWaterDatabase)));
        agentThreads.add(new Thread(new AgentUploadTask("Agent-B", "Alexandra", 95, municipalWaterDatabase)));
        agentThreads.add(new Thread(new AgentUploadTask("Agent-C", "Soweto", 105, municipalWaterDatabase)));
        agentThreads.add(new Thread(new AgentUploadTask("Agent-D", "Randburg", 120, municipalWaterDatabase)));

        System.out.println("=== Joburg Meter Upload Simulation ===\n");

        for (Thread agentThread : agentThreads) {
            agentThread.start();
        }

        // join: billing must wait until all field agents complete uploads.
        for (Thread agentThread : agentThreads) {
            agentThread.join();
        }

        System.out.println();
        System.out.println("All uploads complete. Billing run may now start.");
        System.out.println("Safe committed uploads: " + municipalWaterDatabase.getSafeUploadCounter());
        System.out.println("Unsafe counter value (race condition demo): " + municipalWaterDatabase.getUnsafeUploadCounter());
        System.out.println("Suburb totals snapshot: " + municipalWaterDatabase.suburbTotalsSnapshot());

        System.out.println();
        System.out.println("Note: Unsafe counter may differ from expected due to race conditions.");
    }

    /**
     * Represents one field agent upload job executed as a Runnable task.
     */
    static class AgentUploadTask implements Runnable {

        private final String agentName;
        private final String suburbName;
        private final int meterReadingsUploaded;
        private final MunicipalWaterDatabase municipalWaterDatabase;

        /**
         * Stores one agent's upload payload and database dependency.
         */
        AgentUploadTask(
                String agentName,
                String suburbName,
                int meterReadingsUploaded,
                MunicipalWaterDatabase municipalWaterDatabase) {
            this.agentName = agentName;
            this.suburbName = suburbName;
            this.meterReadingsUploaded = meterReadingsUploaded;
            this.municipalWaterDatabase = municipalWaterDatabase;
        }

        /**
         * Simulates network upload latency, demonstrates race condition behavior, and commits synchronized data.
         */
        @Override
        public void run() {
            try {
                // sleep: simulate variable network latency from handheld devices.
                Thread.sleep(120);

                for (int counterStep = 0; counterStep < 5000; counterStep++) {
                    municipalWaterDatabase.incrementUnsafeCounter();
                }

                municipalWaterDatabase.synchronizeRegionalIndexes();
                municipalWaterDatabase.commitReadingSafely(suburbName, meterReadingsUploaded);
                System.out.println(agentName + " uploaded " + meterReadingsUploaded + " readings for " + suburbName + ".");
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                System.out.println(agentName + " upload interrupted.");
            }
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Thread: each field agent upload runs in its own Thread instance.
    - Runnable: AgentUploadTask implements Runnable so work is decoupled from thread creation.
    - synchronized: commitReadingSafely protects shared suburb totals from corruption.
    - sleep: Thread.sleep simulates upload latency from field devices.
    - join: main waits for all upload threads before starting billing.
    - race condition: incrementUnsafeCounter shows unsafe shared-state updates.
    - deadlock: synchronizeRegionalIndexes uses consistent lock order to avoid deadlock.

    Interview questions this code helps answer:
    - What is the difference between Thread and Runnable?
    - Why do we use synchronized methods for shared data?
    - What problem does join solve in workflow coordination?
    - How does a race condition happen in practice?
    - How can lock ordering prevent deadlock?

    Common mistake and how this code avoids it:
    - Mistake: updating shared state from multiple threads without synchronization. This code isolates safe writes in synchronized methods and uses deterministic lock ordering.
    */
}
