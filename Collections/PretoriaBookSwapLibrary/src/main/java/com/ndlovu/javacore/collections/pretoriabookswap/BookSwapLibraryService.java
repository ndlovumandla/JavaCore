package com.ndlovu.javacore.collections.pretoriabookswap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Manages the book swap catalogue, duplicate checks, and monthly reporting workflow.
 */
public class BookSwapLibraryService implements GenreReportProvider {

    private final HashMap<String, ArrayList<BookRecord>> booksByGenre = new HashMap<>();
    private final HashSet<String> knownIsbns = new HashSet<>();
    private final LinkedList<String> swapActivityLog = new LinkedList<>();

    /**
     * Adds a book to the catalogue while blocking duplicate ISBN entries.
     */
    public void addBook(BookRecord bookRecord) {
        // HashSet gives fast duplicate detection for ISBN uniqueness.
        if (!knownIsbns.add(bookRecord.isbn())) {
            throw new IllegalArgumentException("Duplicate ISBN blocked: " + bookRecord.isbn());
        }

        // HashMap groups books by genre key and ArrayList stores genre members in insertion order.
        booksByGenre.computeIfAbsent(bookRecord.genre(), ignored -> new ArrayList<>()).add(bookRecord);
        swapActivityLog.add("Added: " + bookRecord.title());
    }

    /**
     * Records a swap activity in order so volunteers can review recent actions.
     */
    public void recordSwapActivity(String activityLine) {
        // LinkedList behaves like a queue for chronological activity tracking.
        swapActivityLog.add(activityLine);
    }

    /**
     * Returns all registered genres in sorted order for quick catalogue navigation.
     */
    public List<String> sortedGenreList() {
        ArrayList<String> genreNames = new ArrayList<>(booksByGenre.keySet());
        // Collections.sort keeps report navigation consistent for non-technical volunteers.
        Collections.sort(genreNames);
        return genreNames;
    }

    /**
     * Generates a monthly availability report by iterating through genre groups and sorting titles.
     */
    @Override
    public List<String> generateMonthlyGenreReport() {
        ArrayList<String> reportLines = new ArrayList<>();

        // Iterator demonstrates controlled traversal through HashMap entry data.
        Iterator<Map.Entry<String, ArrayList<BookRecord>>> genreIterator = booksByGenre.entrySet().iterator();
        while (genreIterator.hasNext()) {
            Map.Entry<String, ArrayList<BookRecord>> genreEntry = genreIterator.next();
            ArrayList<BookRecord> genreBooks = new ArrayList<>(genreEntry.getValue());

            // Collections.sort with Comparator sorts books by title inside each genre list.
            genreBooks.sort((leftBook, rightBook) -> leftBook.title().compareToIgnoreCase(rightBook.title()));

            reportLines.add("Genre: " + genreEntry.getKey() + " | Titles available: " + genreBooks.size());
            for (BookRecord genreBook : genreBooks) {
                reportLines.add("  - " + genreBook.displayLine());
            }
        }

        // Collections.sort keeps genre sections alphabetic for predictable reading.
        Collections.sort(reportLines);
        return reportLines;
    }

    /**
     * Returns a read-only ISBN set snapshot for audits and duplicate checks.
     */
    public Set<String> isbnSnapshot() {
        return Collections.unmodifiableSet(knownIsbns);
    }

    /**
     * Returns a read-only activity view so logs can be displayed without accidental edits.
     */
    public List<String> activitySnapshot() {
        return Collections.unmodifiableList(swapActivityLog);
    }
}
