package com.ndlovu.javacore.threads.newsroom;

/**
 * Represents one article submission from an editorial desk into the publishing queue.
 */
public record ArticleSubmission(String deskName, String headline, int priorityScore) {

    /**
     * Returns a concise label for queue and publication logs.
     */
    public String label() {
        return "[" + deskName + "] " + headline + " (priority " + priorityScore + ")";
    }
}
