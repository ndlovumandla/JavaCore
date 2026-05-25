/*
 * The Daily Maverick newsroom in Cape Town is battling deadline pressure across five desks.
 * Politics, economy, sport, lifestyle, and investigations each produce stories at the same time.
 * The old sequential pipeline forced one desk to wait for another, causing late publication windows.
 * CTO Yolanda redesigns the system so each desk runs as its own Runnable thread and submits to a synchronized queue.
 * The platform then uses join() to wait for all desks before triggering a final publication build.
 * This simulation shows why multithreading, synchronization, and lock discipline reduce bottlenecks safely.
 */

package com.ndlovu.javacore.threads.newsroom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Drives the full newsroom deadline-night simulation and prints timeline-style output.
 */
public class NewsroomDeadlineEngineDemo {

    /**
     * Starts all desk threads, waits for completion, and performs final publication build checks.
     */
    public static void main(String[] args) throws InterruptedException {
        SynchronizedPublishingQueue synchronizedPublishingQueue = new SynchronizedPublishingQueue();

        List<Thread> editorialDeskThreads = new ArrayList<>();
        editorialDeskThreads.add(createDeskThread(
                "Politics",
                Arrays.asList(
                        "Coalition talks stretch into midnight",
                        "Parliament committee releases spending review",
                        "Premier outlines transport reform agenda"),
                synchronizedPublishingQueue));

        editorialDeskThreads.add(createDeskThread(
                "Economy",
                Arrays.asList(
                        "Rand steadies after central bank briefing",
                        "Port efficiency reforms attract logistics investment",
                        "SME lending report signals cautious recovery"),
                synchronizedPublishingQueue));

        editorialDeskThreads.add(createDeskThread(
                "Sport",
                Arrays.asList(
                        "Stormers secure dramatic away win",
                        "Proteas announce youth-focused test squad",
                        "Cape derby expected to break attendance record"),
                synchronizedPublishingQueue));

        editorialDeskThreads.add(createDeskThread(
                "Lifestyle",
                Arrays.asList(
                        "Cape Town design week spotlights local makers",
                        "Weekend food guide maps hidden neighborhood gems",
                        "Urban wellness startups expand to township clinics"),
                synchronizedPublishingQueue));

        editorialDeskThreads.add(createDeskThread(
                "Investigations",
                Arrays.asList(
                        "Tender database reveals linked shell entities",
                        "Whistleblower interviews detail procurement bypass",
                        "Timeline reconstruction challenges official statement"),
                synchronizedPublishingQueue));

        int expectedTotalArticles = 15;

        System.out.println("=== Daily Maverick Deadline Engine ===");
        System.out.println("Yolanda starts all editorial desks in parallel to remove sequential bottlenecks.\n");

        // Thread.start launches each desk concurrently.
        for (Thread editorialDeskThread : editorialDeskThreads) {
            editorialDeskThread.start();
        }

        // join ensures the platform waits for all desks before final build starts.
        for (Thread editorialDeskThread : editorialDeskThreads) {
            editorialDeskThread.join();
        }

        System.out.println("\nAll desks completed submissions. Running final publication build...\n");

        // Demonstrates deadlock prevention by acquiring multiple locks in a consistent order.
        synchronizedPublishingQueue.runPublicationBuildWithDeadlockPrevention();

        List<ArticleSubmission> publicationBatch = synchronizedPublishingQueue.buildPublicationBatch();

        System.out.println("Top publication order by priority:");
        for (int articleIndex = 0; articleIndex < publicationBatch.size(); articleIndex++) {
            ArticleSubmission articleSubmission = publicationBatch.get(articleIndex);
            System.out.println((articleIndex + 1) + ". " + articleSubmission.label());
        }

        System.out.println();
        System.out.println("Expected article submissions: " + expectedTotalArticles);
        System.out.println("Safe synchronized counter:    " + synchronizedPublishingQueue.getSafeSubmissionCounter());
        System.out.println("Unsafe race counter:          " + synchronizedPublishingQueue.getUnsafeSubmissionCounter());
        System.out.println("Published in final build:     " + publicationBatch.size());

        System.out.println();
        System.out.println("Note: unsafe counter may be lower than expected because unsynchronized increments lose updates.");
        System.out.println("      synchronized queue operations preserve correctness under concurrent load.");
    }

    /**
     * Creates one desk thread using Runnable composition so behavior is separated from thread mechanics.
     */
    private static Thread createDeskThread(
            String deskName,
            List<String> deskHeadlines,
            SynchronizedPublishingQueue synchronizedPublishingQueue) {
        Runnable deskTask = new EditorialDeskTask(deskName, deskHeadlines, synchronizedPublishingQueue);
        return new Thread(deskTask, deskName + "-Desk-Thread");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Thread: each editorial desk executes concurrently using an explicit Thread instance.
    - Runnable: desk behavior is modeled via EditorialDeskTask to keep logic reusable and testable.
    - synchronized: queue mutation runs inside synchronized blocks to protect shared state.
    - sleep: simulated I/O delay increases contention realism and exposes race windows.
    - join: main thread waits for all desks before publication build to enforce completion ordering.
    - race condition: unsafe counter increments show lost updates when no synchronization is used.
    - deadlock: final build method demonstrates lock ordering to avoid circular wait.

    Interview questions this code prepares you for:
    - Why prefer Runnable composition over extending Thread?
    - What makes a block of code a critical section?
    - How does synchronized enforce memory visibility and mutual exclusion?
    - Why is join required before downstream processing in concurrent workflows?
    - How can deterministic lock ordering prevent deadlocks?

    Common mistake and how this code avoids it:
    - Mistake: mixing multiple lock orders across methods, which can deadlock under load.
      Avoidance: runPublicationBuildWithDeadlockPrevention always locks queueLock before publicationMetricsLock.
    */
}
