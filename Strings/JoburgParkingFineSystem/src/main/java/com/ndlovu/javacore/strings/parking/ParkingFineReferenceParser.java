package com.ndlovu.javacore.strings.parking;

/**
 * Normalizes and validates Joburg parking fine reference numbers from public input.
 */
public class ParkingFineReferenceParser {

    /**
     * Normalizes raw user input before validation so lookup logic receives consistent formatting.
     */
    public String normalizeReference(String rawReferenceInput) {
        if (rawReferenceInput == null) {
            return "";
        }

        // trim removes accidental spaces before and after user input.
        String trimmedReference = rawReferenceInput.trim();

        // toUpperCase makes matching case-insensitive for user-entered values.
        String uppercaseReference = trimmedReference.toUpperCase();

        // Replace underscores because users often type wrong separators.
        return uppercaseReference.replace('_', '-');
    }

    /**
     * Validates normalized reference and returns either a parsed summary or error reason.
     */
    public String validateAndDescribe(String normalizedReference) {
        if (normalizedReference.isBlank()) {
            return "Invalid: reference is empty after normalization.";
        }

        if (!startsWithRequiredPrefix(normalizedReference)) {
            return "Invalid: reference must start with ZA-.";
        }

        if (!containsOnlyAsciiAllowedCharacters(normalizedReference)) {
            return "Invalid: reference contains unsupported non-ASCII characters.";
        }

        if (!hasExpectedLength(normalizedReference)) {
            return "Invalid: reference length must be 17 (example ZA-SB-004521-2024).";
        }

        if (!hasExpectedDashLayout(normalizedReference)) {
            return "Invalid: reference separators are misplaced.";
        }

        String suburbCode = extractSuburbCode(normalizedReference);
        String sequenceNumber = extractSequenceNumber(normalizedReference);
        String issueYear = extractIssueYear(normalizedReference);

        if (!isTwoAsciiLetters(suburbCode)) {
            return "Invalid: suburb code must be two letters.";
        }

        if (!isAllAsciiDigits(sequenceNumber)) {
            return "Invalid: sequence must contain six digits.";
        }

        if (!isAllAsciiDigits(issueYear)) {
            return "Invalid: year must contain four digits.";
        }

        return "Valid reference | Suburb=" + suburbCode
                + " | Sequence=" + sequenceNumber
                + " | Year=" + issueYear;
    }

    /**
     * Checks prefix using exact String comparison rules needed by official reference format.
     */
    public boolean startsWithRequiredPrefix(String normalizedReference) {
        // substring + equals demonstrate exact prefix verification.
        return normalizedReference.length() >= 3 && normalizedReference.substring(0, 3).equals("ZA-");
    }

    /**
     * Validates fixed format length so position-based parsing remains safe.
     */
    public boolean hasExpectedLength(String normalizedReference) {
        // length protects later charAt and substring operations from out-of-range access.
        return normalizedReference.length() == 17;
    }

    /**
     * Ensures dashes appear exactly where format rules require them.
     */
    public boolean hasExpectedDashLayout(String normalizedReference) {
        // charAt validates the exact delimiter positions in the structured format.
        return normalizedReference.charAt(2) == '-'
                && normalizedReference.charAt(5) == '-'
                && normalizedReference.charAt(12) == '-';
    }

    /**
     * Extracts suburb code segment using substring for downstream lookup context.
     */
    public String extractSuburbCode(String normalizedReference) {
        // substring isolates [2-letter suburb code].
        return normalizedReference.substring(3, 5);
    }

    /**
     * Extracts six-digit sequence using indexOf boundaries to demonstrate String searching.
     */
    public String extractSequenceNumber(String normalizedReference) {
        int secondDashIndex = normalizedReference.indexOf('-', 3);
        int thirdDashIndex = normalizedReference.indexOf('-', secondDashIndex + 1);
        return normalizedReference.substring(secondDashIndex + 1, thirdDashIndex);
    }

    /**
     * Extracts final year segment from the normalized fine reference.
     */
    public String extractIssueYear(String normalizedReference) {
        return normalizedReference.substring(13, 17);
    }

    /**
     * Checks suburb segment contains ASCII letters only.
     */
    public boolean isTwoAsciiLetters(String suburbCode) {
        if (suburbCode.length() != 2) {
            return false;
        }
        return isAsciiLetter(suburbCode.charAt(0)) && isAsciiLetter(suburbCode.charAt(1));
    }

    /**
     * Checks whether a segment contains only ASCII digits 0-9.
     */
    public boolean isAllAsciiDigits(String textSegment) {
        for (int characterIndex = 0; characterIndex < textSegment.length(); characterIndex++) {
            char segmentCharacter = textSegment.charAt(characterIndex);
            if (segmentCharacter < '0' || segmentCharacter > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Ensures only ASCII letters, digits, and dashes are present to avoid Unicode look-alikes.
     */
    public boolean containsOnlyAsciiAllowedCharacters(String normalizedReference) {
        for (int characterIndex = 0; characterIndex < normalizedReference.length(); characterIndex++) {
            char referenceCharacter = normalizedReference.charAt(characterIndex);
            boolean asciiDigit = referenceCharacter >= '0' && referenceCharacter <= '9';
            boolean asciiUpperLetter = referenceCharacter >= 'A' && referenceCharacter <= 'Z';
            boolean dashCharacter = referenceCharacter == '-';
            if (!asciiDigit && !asciiUpperLetter && !dashCharacter) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether a character is an uppercase ASCII letter.
     */
    private boolean isAsciiLetter(char characterValue) {
        return characterValue >= 'A' && characterValue <= 'Z';
    }

    /**
     * Shows Unicode code points for training and debugging suspicious reference characters.
     */
    public String unicodeBreakdown(String normalizedReference) {
        StringBuilder breakdownBuilder = new StringBuilder();
        for (int characterIndex = 0; characterIndex < normalizedReference.length(); characterIndex++) {
            char referenceCharacter = normalizedReference.charAt(characterIndex);
            breakdownBuilder
                    .append('[')
                    .append(referenceCharacter)
                    .append(" -> U+")
                    .append(String.format("%04X", (int) referenceCharacter))
                    .append("] ");
        }
        return breakdownBuilder.toString().trim();
    }
}
