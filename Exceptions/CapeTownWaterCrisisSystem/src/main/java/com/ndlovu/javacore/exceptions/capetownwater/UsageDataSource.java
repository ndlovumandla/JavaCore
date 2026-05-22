package com.ndlovu.javacore.exceptions.capetownwater;

import java.util.List;

/**
 * Defines the contract for loading household water-usage lines from any data source.
 */
public interface UsageDataSource {

    /**
     * Reads raw usage lines so the rationing engine can validate and process each household record.
     */
    List<String> readUsageLines(String filePath) throws DataSourceReadException;
}
