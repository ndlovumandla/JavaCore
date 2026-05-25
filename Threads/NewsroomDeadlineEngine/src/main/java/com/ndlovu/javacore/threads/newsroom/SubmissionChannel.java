package com.ndlovu.javacore.threads.newsroom;

import java.util.List;

/**
 * Defines the contract for article submission channels used by desk threads.
 */
public interface SubmissionChannel {

    /**
     * Submits one article into the shared publication queue.
     */
    void submit(ArticleSubmission articleSubmission) throws InterruptedException;

    /**
     * Drains queued articles after all desks finish, producing the final ordered build list.
     */
    List<ArticleSubmission> buildPublicationBatch();
}
