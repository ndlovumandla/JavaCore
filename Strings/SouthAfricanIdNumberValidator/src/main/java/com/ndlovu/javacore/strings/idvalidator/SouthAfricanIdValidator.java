package com.ndlovu.javacore.strings.idvalidator;

/**
 * Validates South African 13-digit ID numbers and extracts key encoded identity fields.
 */
public class SouthAfricanIdValidator {

    /**
     * Validates full ID format and checksum, then returns a readable identity summary.
     */
    public String validateAndDescribe(String idNumber) {
        if (!hasExpectedLength(idNumber)) {
            return "Invalid: ID must contain exactly 13 characters.";
        }

        if (!containsOnlyAsciiDigits(idNumber)) {
            return "Invalid: ID must contain ASCII digits 0-9 only.";
        }

        if (!hasValidBirthDateSegment(idNumber)) {
            return "Invalid: Birth-date segment is not plausible.";
        }

        if (!hasValidCitizenshipSegment(idNumber)) {
            return "Invalid: Citizenship segment must be 0 or 1.";
        }

        if (!passesLuhnChecksum(idNumber)) {
            return "Invalid: Luhn checksum failed.";
        }

        return "Valid ID | DOB=" + birthDateFromId(idNumber)
                + " | Gender=" + genderFromId(idNumber)
                + " | Citizenship=" + citizenshipFromId(idNumber);
    }

    /**
     * Checks String length so only complete 13-digit IDs are processed further.
     */
    public boolean hasExpectedLength(String idNumber) {
        // length() verifies fixed ID size before accessing positions with charAt/substring.
        return idNumber != null && idNumber.length() == 13;
    }

    /**
     * Verifies every character is an ASCII numeric digit to block visually similar Unicode digits.
     */
    public boolean containsOnlyAsciiDigits(String idNumber) {
        for (int characterIndex = 0; characterIndex < idNumber.length(); characterIndex++) {
            // charAt() reads each character for strict per-position checks.
            char idCharacter = idNumber.charAt(characterIndex);

            // ASCII range check: accept only '0' to '9'.
            if (idCharacter < '0' || idCharacter > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks encoded YYMMDD segment for a reasonable month/day range.
     */
    public boolean hasValidBirthDateSegment(String idNumber) {
        // substring() slices encoded date pieces from the ID String.
        String monthText = idNumber.substring(2, 4);
        String dayText = idNumber.substring(4, 6);

        int monthValue = Integer.parseInt(monthText);
        int dayValue = Integer.parseInt(dayText);

        return monthValue >= 1 && monthValue <= 12 && dayValue >= 1 && dayValue <= 31;
    }

    /**
     * Validates citizenship code at position 11 where only 0 or 1 is accepted.
     */
    public boolean hasValidCitizenshipSegment(String idNumber) {
        String citizenshipCode = idNumber.substring(10, 11);

        // equals() performs exact String comparison for accepted fixed codes.
        return citizenshipCode.equals("0") || citizenshipCode.equals("1");
    }

    /**
     * Derives formatted date text from YYMMDD segment for demo reporting.
     */
    public String birthDateFromId(String idNumber) {
        String year = idNumber.substring(0, 2);
        String month = idNumber.substring(2, 4);
        String day = idNumber.substring(4, 6);
        return day + "/" + month + "/" + year;
    }

    /**
     * Interprets gender sequence digits where values >= 5000 are usually male.
     */
    public String genderFromId(String idNumber) {
        int genderSequence = Integer.parseInt(idNumber.substring(6, 10));
        return genderSequence >= 5000 ? "Male" : "Female";
    }

    /**
     * Interprets citizenship code where 0 is citizen and 1 is permanent resident.
     */
    public String citizenshipFromId(String idNumber) {
        String citizenshipCode = idNumber.substring(10, 11);
        return citizenshipCode.equals("0") ? "SA Citizen" : "Permanent Resident";
    }

    /**
     * Runs the South African ID Luhn checksum algorithm over all 13 digits.
     */
    public boolean passesLuhnChecksum(String idNumber) {
        int runningTotal = 0;

        for (int positionIndex = 0; positionIndex < idNumber.length(); positionIndex++) {
            int digitValue = idNumber.charAt(positionIndex) - '0';

            if (positionIndex % 2 == 1) {
                int doubledValue = digitValue * 2;
                if (doubledValue > 9) {
                    doubledValue -= 9;
                }
                runningTotal += doubledValue;
            } else {
                runningTotal += digitValue;
            }
        }

        return runningTotal % 10 == 0;
    }

    /**
     * Shows Unicode code-point values for one string to explain ASCII-vs-Unicode differences.
     */
    public String explainUnicodeDigits(String idNumber) {
        StringBuilder unicodeExplanation = new StringBuilder();
        for (int characterIndex = 0; characterIndex < idNumber.length(); characterIndex++) {
            char idCharacter = idNumber.charAt(characterIndex);
            unicodeExplanation
                    .append("[")
                    .append(idCharacter)
                    .append(" -> U+")
                    .append(String.format("%04X", (int) idCharacter))
                    .append("] ");
        }
        return unicodeExplanation.toString().trim();
    }
}
