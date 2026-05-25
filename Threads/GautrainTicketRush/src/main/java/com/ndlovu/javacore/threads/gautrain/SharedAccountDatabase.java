package com.ndlovu.javacore.threads.gautrain;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the shared account database touched by all turnstile threads.
 */
public class SharedAccountDatabase implements FareDebitService {

    private final Map<String, Integer> cardBalancesCents = new HashMap<>();
    private final Object fareLock = new Object();
    private final Object auditLock = new Object();

    /**
     * Seeds one card balance so terminals can simulate repeated tap-ins.
     */
    public void seedBalance(String cardId, int openingBalanceCents) {
        cardBalancesCents.put(cardId, openingBalanceCents);
    }

    /**
     * Returns current card balance for reporting after all terminal threads finish.
     */
    public int getBalance(String cardId) {
        return cardBalancesCents.getOrDefault(cardId, 0);
    }

    /**
     * Performs an unsafe read-modify-write flow to demonstrate race conditions.
     */
    @Override
    public void debitFareUnsafe(String cardId, int fareCents) throws InterruptedException {
        int currentBalance = cardBalancesCents.getOrDefault(cardId, 0);

        // sleep simulates network/database latency that widens race-condition windows.
        Thread.sleep(2);

        int updatedBalance = currentBalance - fareCents;
        cardBalancesCents.put(cardId, updatedBalance);
    }

    /**
     * Performs a synchronized read-modify-write so concurrent terminals cannot corrupt balances.
     */
    @Override
    public void debitFareSafe(String cardId, int fareCents) throws InterruptedException {
        // synchronized critical section ensures one thread updates the shared account at a time.
        synchronized (fareLock) {
            int currentBalance = cardBalancesCents.getOrDefault(cardId, 0);
            Thread.sleep(2);
            int updatedBalance = currentBalance - fareCents;
            cardBalancesCents.put(cardId, updatedBalance);
        }
    }

    /**
     * Simulates a two-resource operation and avoids deadlock by locking in one fixed order.
     */
    public void writeFareAndAuditSafely() {
        // Deadlock prevention: always lock fareLock before auditLock.
        synchronized (fareLock) {
            synchronized (auditLock) {
                // No-op body: the key lesson is deterministic lock ordering.
            }
        }
    }
}
