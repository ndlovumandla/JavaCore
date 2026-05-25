package com.ndlovu.javacore.threads.gautrain;

/**
 * Defines fare-debit operations used by turnstile terminals.
 */
public interface FareDebitService {

    /**
     * Debits an account without synchronization to demonstrate race-condition risk.
     */
    void debitFareUnsafe(String cardId, int fareCents) throws InterruptedException;

    /**
     * Debits an account inside a synchronized critical section to prevent data corruption.
     */
    void debitFareSafe(String cardId, int fareCents) throws InterruptedException;
}
