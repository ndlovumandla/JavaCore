/*
 * The Hatfield community book swap runs on trust: take one book, leave one book.
 * Nandi's handwritten ledger has grown too large to search efficiently by genre or title.
 * Kwame is digitizing the catalogue so duplicate ISBN checks, grouped genres, and monthly reports become reliable.
 * This demo shows how different Java collection types solve different library tasks without overcomplicating the system.
 * The result is a faster, cleaner workflow for volunteers managing hundreds of community book records.
 */

package com.ndlovu.javacore.collections.pretoriabookswap;

import java.util.List;

/**
 * Runs the Pretoria book swap simulation and prints genre availability results.
 */
public class PretoriaBookSwapDemo {

    /**
     * Seeds sample data, executes report generation, and prints dashboard-style output.
     */
    public static void main(String[] args) {
        BookSwapLibraryService bookSwapLibraryService = new BookSwapLibraryService();

        seedSampleBooks(bookSwapLibraryService);
        bookSwapLibraryService.recordSwapActivity("Swap completed: 'The Alchemist' replaced with 'Born a Crime'.");
        bookSwapLibraryService.recordSwapActivity("Swap completed: 'Atomic Habits' replaced with 'Deep Work'.");

        System.out.println("=== Hatfield Community Book Swap ===");
        System.out.println();

        System.out.println("Sorted genres: " + bookSwapLibraryService.sortedGenreList());
        System.out.println("Unique ISBN count: " + bookSwapLibraryService.isbnSnapshot().size());
        System.out.println();

        System.out.println("Recent activity log:");
        for (String activityLine : bookSwapLibraryService.activitySnapshot()) {
            System.out.println(" - " + activityLine);
        }
        System.out.println();

        System.out.println("Monthly genre availability report:");
        List<String> monthlyReportLines = bookSwapLibraryService.generateMonthlyGenreReport();
        for (String reportLine : monthlyReportLines) {
            System.out.println(reportLine);
        }

        System.out.println();
        System.out.println("Nandi can now find genre availability in seconds, not 45 minutes.");
    }

    /**
     * Adds starter catalogue entries and intentionally tests duplicate ISBN protection.
     */
    private static void seedSampleBooks(BookSwapLibraryService bookSwapLibraryService) {
        bookSwapLibraryService.addBook(new BookRecord("9780061122415", "The Alchemist", "Fiction", "Naledi"));
        bookSwapLibraryService.addBook(new BookRecord("9781770104725", "Born a Crime", "Memoir", "Kabelo"));
        bookSwapLibraryService.addBook(new BookRecord("9780735211292", "Atomic Habits", "Self-Help", "Aisha"));
        bookSwapLibraryService.addBook(new BookRecord("9781455586691", "Deep Work", "Self-Help", "Thando"));
        bookSwapLibraryService.addBook(new BookRecord("9780140449136", "Meditations", "Philosophy", "Mpho"));

        try {
            // Edge-case check: duplicate ISBN must be blocked to protect catalogue integrity.
            bookSwapLibraryService.addBook(new BookRecord("9780061122415", "Duplicate Test", "Fiction", "Lebo"));
        } catch (IllegalArgumentException duplicateIsbnException) {
            System.out.println("Duplicate check: " + duplicateIsbnException.getMessage());
            System.out.println();
        }
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - ArrayList: stores each genre's book entries and report line output.
    - HashMap: maps genre names to their list of books for fast grouped lookup.
    - HashSet: prevents duplicate ISBN values.
    - Iterator: traverses HashMap entries during report generation.
    - LinkedList: keeps swap activities in insertion order.
    - Collections: sorts genres/report lines and exposes unmodifiable snapshots.

    Interview questions this code helps answer:
    - Why use HashMap for genre grouping instead of one list?
    - When is HashSet the best choice for duplicate protection?
    - What benefit does Iterator provide over simple loops in map traversal?
    - Why choose LinkedList for ordered activity logs?
    - How do Collections utility methods improve reporting output?

    Common mistake and how this code avoids it:
    - Mistake: exposing internal collections directly, allowing accidental mutation. This code returns unmodifiable snapshots for safer read-only dashboard views.
    */
}
