package com.ndlovu.javacore.strings.pantype;

/**
 * Provides String-processing helpers for PanType multilingual keyboard input handling.
 */
public class KeyboardStringEngine {

    /**
     * Checks whether every character in text is ASCII (code points 0-127).
     */
    public boolean isAsciiOnly(String keyboardInputText) {
        for (int characterIndex = 0; characterIndex < keyboardInputText.length(); characterIndex++) {
            // charAt reads each character position in the String.
            char currentCharacter = keyboardInputText.charAt(characterIndex);

            // ASCII check: valid only if char value is between 0 and 127.
            if (currentCharacter > 127) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds a Unicode/ASCII report line for each character in the input.
     */
    public String unicodeBreakdown(String keyboardInputText) {
        StringBuilder reportBuilder = new StringBuilder();
        for (int characterIndex = 0; characterIndex < keyboardInputText.length(); characterIndex++) {
            char currentCharacter = keyboardInputText.charAt(characterIndex);
            reportBuilder
                    .append("[")
                    .append(currentCharacter)
                    .append(" -> U+")
                    .append(String.format("%04X", (int) currentCharacter))
                    .append(currentCharacter <= 127 ? " ASCII" : " Unicode")
                    .append("] ");
        }
        return reportBuilder.toString().trim();
    }

    /**
     * Extracts the first click-symbol sequence using substring for linguistic-unit handling.
     */
    public String extractFirstClickSequence(String keyboardInputText) {
        int clickStartIndex = keyboardInputText.indexOf('ǀ');
        if (clickStartIndex < 0 || clickStartIndex + 2 > keyboardInputText.length()) {
            return "No click sequence found";
        }

        // substring isolates a multi-character cluster from the full input String.
        return keyboardInputText.substring(clickStartIndex, clickStartIndex + 2);
    }

    /**
     * Returns a simple display-width estimate for mixed ASCII/Unicode text.
     */
    public int estimatedDisplayWidthUnits(String keyboardInputText) {
        int widthUnits = 0;
        for (int characterIndex = 0; characterIndex < keyboardInputText.length(); characterIndex++) {
            char currentCharacter = keyboardInputText.charAt(characterIndex);

            // Treat basic ASCII as width 1 and other Unicode characters as width 2 for demo purposes.
            widthUnits += currentCharacter <= 127 ? 1 : 2;
        }
        return widthUnits;
    }

    /**
     * Normalizes language tags to uppercase for consistent keyboard profile matching.
     */
    public String normalizeLanguageLabel(String languageLabel) {
        return languageLabel.toUpperCase();
    }

    /**
     * Compares normalized labels to determine whether two inputs refer to the same language profile.
     */
    public boolean sameLanguageProfile(String firstLanguageLabel, String secondLanguageLabel) {
        String normalizedFirst = normalizeLanguageLabel(firstLanguageLabel);
        String normalizedSecond = normalizeLanguageLabel(secondLanguageLabel);

        // equals compares String values, not object references.
        return normalizedFirst.equals(normalizedSecond);
    }
}
