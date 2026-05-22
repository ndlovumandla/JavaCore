package com.ndlovu.javacore.exceptions.capetownwater;

/**
 * Validates household usage records and calculates per-person daily consumption.
 */
public class WaterRationingEngine {

    private static final double DAILY_LIMIT_LITRES = 50.0;

    /**
     * Evaluates one CSV record and returns a structured usage result for dashboard display.
     */
    public HouseholdUsageResult evaluateHouseholdRecord(String rawRecord, int lineNumber)
            throws CorruptDataFileException, MissingHouseholdIdException, NegativeUsageException {
        // Corrupt-data check: each row must contain ID, resident count, and daily litres.
        String[] recordParts = rawRecord.split(",");
        if (recordParts.length != 3) {
            throw new CorruptDataFileException("Line " + lineNumber + " has invalid column count.");
        }

        String householdId = recordParts[0].trim();
        if (householdId.isBlank()) {
            throw new MissingHouseholdIdException("Line " + lineNumber + " is missing a household ID.");
        }

        int residentCount;
        double dailyLitres;

        try {
            // try: number parsing can fail for corrupted file content.
            residentCount = Integer.parseInt(recordParts[1].trim());
            dailyLitres = Double.parseDouble(recordParts[2].trim());
        } catch (NumberFormatException parseFailure) {
            throw new CorruptDataFileException("Line " + lineNumber + " has non-numeric values.");
        }

        if (residentCount <= 0) {
            throw new CorruptDataFileException("Line " + lineNumber + " must have residents greater than zero.");
        }

        if (dailyLitres < 0) {
            throw new NegativeUsageException("Line " + lineNumber + " has negative litre usage.");
        }

        double litresPerPerson = dailyLitres / residentCount;
        boolean limitBreached = litresPerPerson > DAILY_LIMIT_LITRES;

        return new HouseholdUsageResult(householdId, residentCount, dailyLitres, litresPerPerson, limitBreached);
    }

    /**
     * Checks queue load and raises an unchecked overload exception when processing pressure is unsafe.
     */
    public void guardAgainstOverload(int queueSize) {
        if (queueSize > 10_000) {
            throw new SystemOverloadException("Queue size " + queueSize + " exceeds simulation safety threshold.");
        }
    }

    /**
     * Holds one household's validated usage result for reporting.
     */
    public record HouseholdUsageResult(
            String householdId,
            int residentCount,
            double dailyLitres,
            double litresPerPerson,
            boolean limitBreached) {
    }
}
