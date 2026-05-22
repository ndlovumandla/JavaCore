package com.ndlovu.javacore.exceptions.capetownwater;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Loads household water-usage data from a text file for Aidan's simulation.
 */
public class FileUsageDataSource implements UsageDataSource {

    /**
     * Reads all lines from disk and wraps I/O failures in a checked data-source exception.
     */
    @Override
    public List<String> readUsageLines(String filePath) throws DataSourceReadException {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException ioFailure) {
            throw new DataSourceReadException("Could not read usage file: " + filePath, ioFailure);
        }
    }
}
