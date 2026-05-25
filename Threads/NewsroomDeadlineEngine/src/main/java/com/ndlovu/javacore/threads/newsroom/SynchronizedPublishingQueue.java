package com.ndlovu.javacore.threads.newsroom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implements the shared publishing queue that all editorial desks write to concurrently.
 */
public class SynchronizedPublishingQueue implements SubmissionChannel {

    private final Queue<ArticleSubmission> pendingArticles = new LinkedList<>();
    private final Object queueLock = new Object();
    private final Object publicationMetricsLock = new Object();

    private int unsafeSubmissionCounter;
    private int safeSubmissionCounter;

    /**
     * Accepts one article from a desk and updates queue state safely under synchronization.
     */
    @Override
    public void submit(ArticleSubmission articleSubmission) throws InterruptedException {
        // synchronized enforces a critical section for shared queue mutation.
        synchronized (queueLock) {
            pendingArticles.offer(articleSubmission);
            safeSubmissionCounter++;

            // sleep simulates queue I/O latency and contention during peak deadline traffic.
            Thread.sleep(4);
        }
    }

    /**
     * Increments a deliberately unsafe counter to demonstrate race-condition corruption.
     */
    public void incrementUnsafeSubmissionCounter() throws InterruptedException {
        int currentValue = unsafeSubmissionCounter;

        // sleep widens the race window so lost updates become visible in output.
        Thread.sleep(1);

        unsafeSubmissionCounter = currentValue + 1;
    }

    /**
     * Reads the unsafe counter so the demo can compare it with expected values.
     */
    public int getUnsafeSubmissionCounter() {
        return unsafeSubmissionCounter;
    }

    /**
     * Reads the synchronized counter for correctness verification.
     */
    public int getSafeSubmissionCounter() {
        return safeSubmissionCounter;
    }

    /**
     * Drains all pending queue entries and returns a deterministic publication order.
     */
    @Override
    public List<ArticleSubmission> buildPublicationBatch() {
        List<ArticleSubmission> publicationBatch;

        // synchronized prevents concurrent drains while desks are still writing.
        synchronized (queueLock) {
            publicationBatch = new ArrayList<>(pendingArticles);
            pendingArticles.clear();
        }

        publicationBatch.sort(Comparator.comparingInt(ArticleSubmission::priorityScore).reversed());
        return publicationBatch;
    }

    /**
     * Simulates a final build operation that needs two locks and avoids deadlock via lock ordering.
     */
    public void runPublicationBuildWithDeadlockPrevention() {
        // Deadlock prevention rule: always lock queueLock before publicationMetricsLock.
        synchronized (queueLock) {
            synchronized (publicationMetricsLock) {
                // This block intentionally does minimal work; lock ordering is the core lesson.
                int queueSizeSnapshot = pendingArticles.size();
                if (queueSizeSnapshot < 0) {
                    throw new IllegalStateException("Queue size cannot be negative.");
                }
            }
        }
    }
}
