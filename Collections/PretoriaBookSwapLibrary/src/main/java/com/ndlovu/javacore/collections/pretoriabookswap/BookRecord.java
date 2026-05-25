package com.ndlovu.javacore.collections.pretoriabookswap;

/**
 * Represents one library book entry in Nandi's digitized swap ledger.
 */
public record BookRecord(String isbn, String title, String genre, String currentBorrower) {

    /**
     * Builds a display line so reports can show each book in readable form.
     */
    public String displayLine() {
        return title + " (ISBN " + isbn + ") - borrower: " + currentBorrower;
    }
}
