/*
 * Gautrain Sandton handles intense weekday tap-ins across many terminals in parallel.
 * A past race condition caused double charges when two terminals updated shared balances simultaneously.
 * Bongani redesigned the system so each terminal runs as its own thread and shared account writes are synchronized.
 * This simulation compares unsafe and safe debit modes, adds latency with sleep, and waits for all terminals with join.
 * It also shows deadlock-safe lock ordering in multi-resource updates.
 */

package com.ndlovu.javacore.threads.gautrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs a multithreading demonstration of Gautrain tap-in fare deductions.
 */
public class GautrainTicketRushDemo {

    /**
     * Starts terminal threads, waits for completion, and prints balance outcomes.
     */
    public static void main(String[] args) throws InterruptedException {
        int openingBalanceCents = 200_000;
        int fareCents = 1_500;
        int terminals = 6;
        int tapsPerTerminal = 20;

        int expectedFinalBalance = openingBalanceCents - (terminals * tapsPerTerminal * fareCents);

        System.out.println("=== Gautrain Sandton Ticket Rush ===\n");

        int unsafeBalance = runSimulation(openingBalanceCents, fareCents, terminals, tapsPerTerminal, false);
        System.out.println("Unsafe run final balance: " + formatCents(unsafeBalance));

        int safeBalance = runSimulation(openingBalanceCents, fareCents, terminals, tapsPerTerminal, true);
        System.out.println("Safe run final balance:   " + formatCents(safeBalance));

        System.out.println("Expected final balance:   " + formatCents(expectedFinalBalance));
        System.out.println();
        System.out.println("Unsafe mismatch indicates race condition risk; safe run matches expected due to synchronization.");
    }

    /**
     * Runs one scenario (safe or unsafe), starts threads, and joins all terminals before returning balance.
     */
    private static int runSimulation(
            int openingBalanceCents,
            int fareCents,
            int terminals,
            int tapsPerTerminal,
            boolean safeMode) throws InterruptedException {

        SharedAccountDatabase sharedAccountDatabase = new SharedAccountDatabase();
        String commuterCardId = "CARD-7788";
        sharedAccountDatabase.seedBalance(commuterCardId, openingBalanceCents);

        List<Thread> terminalThreads = new ArrayList<>();

        for (int terminalNumber = 1; terminalNumber <= terminals; terminalNumber++) {
            Runnable terminalTask = new TurnstileTerminalTask(
                    "Terminal-" + terminalNumber,
                    sharedAccountDatabase,
                    commuterCardId,
                    fareCents,
                    tapsPerTerminal,
                    safeMode);
            terminalThreads.add(new Thread(terminalTask));
        }

        System.out.println((safeMode ? "SAFE" : "UNSAFE") + " simulation starting...");

        // Thread.start runs each turnstile concurrently.
        for (Thread terminalThread : terminalThreads) {
            terminalThread.start();
        }

        // join ensures billing/reporting waits until all terminal uploads complete.
        for (Thread terminalThread : terminalThreads) {
            terminalThread.join();
        }

        System.out.println((safeMode ? "SAFE" : "UNSAFE") + " simulation finished.\n");
        return sharedAccountDatabase.getBalance(commuterCardId);
    }

    /**
     * Formats cents into readable rand currency output for station reporting.
     */
    private static String formatCents(int cents) {
        return "R" + String.format("%.2f", cents / 100.0);
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Thread: each terminal runs in its own Thread instance.
    - Runnable: TurnstileTerminalTask implements Runnable to define thread work.
    - synchronized: safe fare deduction uses synchronized critical sections.
    - sleep: simulated inside database debit methods to model latency and expose race windows.
    - join: main thread waits for all terminal threads before checking balances.
    - race condition: unsafe mode shows inconsistent balance updates from concurrent writes.
    - deadlock: writeFareAndAuditSafely uses fixed lock order to avoid deadlock.

    Interview questions this code helps answer:
    - Why use Runnable instead of extending Thread?
    - What is a critical section in multithreading?
    - How does synchronized prevent data corruption?
    - Why is join important before downstream processing?
    - How can lock ordering avoid deadlock?

    Common mistake and how this code avoids it:
    - Mistake: reading and writing shared state from multiple threads without locking. This code demonstrates the unsafe outcome and then fixes it with synchronized access.
    */
}
