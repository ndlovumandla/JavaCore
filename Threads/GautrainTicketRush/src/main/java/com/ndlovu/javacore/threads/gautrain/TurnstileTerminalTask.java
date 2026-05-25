package com.ndlovu.javacore.threads.gautrain;

/**
 * Models one Sandton turnstile terminal processing repeated card tap-ins.
 */
public class TurnstileTerminalTask implements Runnable {

    private final String terminalName;
    private final SharedAccountDatabase sharedAccountDatabase;
    private final String cardId;
    private final int fareCents;
    private final int tapCount;
    private final boolean safeMode;

    /**
     * Stores one terminal's workload and whether it should use safe or unsafe debit mode.
     */
    public TurnstileTerminalTask(
            String terminalName,
            SharedAccountDatabase sharedAccountDatabase,
            String cardId,
            int fareCents,
            int tapCount,
            boolean safeMode) {
        this.terminalName = terminalName;
        this.sharedAccountDatabase = sharedAccountDatabase;
        this.cardId = cardId;
        this.fareCents = fareCents;
        this.tapCount = tapCount;
        this.safeMode = safeMode;
    }

    /**
     * Runs terminal debit operations in a thread to simulate simultaneous station tap-ins.
     */
    @Override
    public void run() {
        try {
            for (int tapNumber = 1; tapNumber <= tapCount; tapNumber++) {
                if (safeMode) {
                    sharedAccountDatabase.debitFareSafe(cardId, fareCents);
                    sharedAccountDatabase.writeFareAndAuditSafely();
                } else {
                    sharedAccountDatabase.debitFareUnsafe(cardId, fareCents);
                }
            }
            System.out.println(terminalName + " finished " + tapCount + " tap-ins.");
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            System.out.println(terminalName + " interrupted during upload.");
        }
    }
}
