package com.ndlovu.javacore.collections.pretoriabookswap;

import java.util.List;

/**
 * Defines the reporting contract for generating monthly genre availability summaries.
 */
public interface GenreReportProvider {

    /**
     * Generates sorted genre report lines so volunteers can review stock quickly.
     */
    List<String> generateMonthlyGenreReport();
}
