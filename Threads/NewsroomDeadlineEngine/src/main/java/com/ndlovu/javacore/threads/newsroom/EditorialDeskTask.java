package com.ndlovu.javacore.threads.newsroom;

import java.util.List;

/**
 * Models one editorial desk that prepares stories and submits them through the shared queue.
 */
public class EditorialDeskTask implements Runnable {

    private final String deskName;
    private final List<String> headlinesToPublish;
    private final SynchronizedPublishingQueue synchronizedPublishingQueue;

    /**
     * Stores desk context and queue dependency for one concurrent submission worker.
     */
    public EditorialDeskTask(
            String deskName,
            List<String> headlinesToPublish,
            SynchronizedPublishingQueue synchronizedPublishingQueue) {
        this.deskName = deskName;
        this.headlinesToPublish = headlinesToPublish;
        this.synchronizedPublishingQueue = synchronizedPublishingQueue;
    }

    /**
     * Runs desk submission flow so each desk can publish concurrently under deadline pressure.
     */
    @Override
    public void run() {
        try {
            int prioritySeed = 100;
            for (int headlineIndex = 0; headlineIndex < headlinesToPublish.size(); headlineIndex++) {
                String headline = headlinesToPublish.get(headlineIndex);
                int priorityScore = prioritySeed - (headlineIndex * 3);

                // Race-condition demo: this shared counter is updated without synchronization.
                synchronizedPublishingQueue.incrementUnsafeSubmissionCounter();

                ArticleSubmission articleSubmission = new ArticleSubmission(deskName, headline, priorityScore);

                // synchronized queue submit keeps article insertion and metrics updates consistent.
                synchronizedPublishingQueue.submit(articleSubmission);
            }

            System.out.println(deskName + " desk submitted " + headlinesToPublish.size() + " articles.");
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            System.out.println(deskName + " desk was interrupted before deadline.");
        }
    }
}
