/*
 * South African ID numbers are 13-character strings with encoded identity information.
 * Home Affairs must validate these IDs at high volume while blocking subtle fraud edits.
 * Zanele checks each character using String tools like charAt and substring.
 * She confirms only ASCII digits are used, validates encoded segments, and runs Luhn checksum rules.
 * This demo also shows Unicode look-alike digits that can pass visual checks but fail strict validation.
 */

package com.ndlovu.javacore.strings.idvalidator;

/**
 * Demonstrates beginner-friendly String and Unicode techniques for SA ID validation.
 */
public class SouthAfricanIdNumberValidatorDemo {

    /**
     * Runs multiple sample ID checks and prints validation decisions with explanations.
     */
    public static void main(String[] args) {
        SouthAfricanIdValidator southAfricanIdValidator = new SouthAfricanIdValidator();

        String validCitizenId = "9001015800087";
        String alteredCitizenshipId = "9001015800187";
        String unicodeLookAlikeId = "90010158000\u06617";

        // toUpperCase() normalizes operator input before policy checks.
        String operatorChannel = "home affairs desk".toUpperCase();

        System.out.println("=== South African ID Validation Engine ===");
        System.out.println("Validation channel: " + operatorChannel);
        System.out.println();

        printValidationResult(southAfricanIdValidator, validCitizenId, "Candidate A");
        printValidationResult(southAfricanIdValidator, alteredCitizenshipId, "Candidate B");
        printValidationResult(southAfricanIdValidator, unicodeLookAlikeId, "Candidate C (Unicode look-alike)");

        System.out.println();
        System.out.println("Unicode breakdown for Candidate C:");
        System.out.println(southAfricanIdValidator.explainUnicodeDigits(unicodeLookAlikeId));
    }

    /**
     * Prints one candidate result so trainees can compare valid and invalid string patterns.
     */
    private static void printValidationResult(
            SouthAfricanIdValidator southAfricanIdValidator,
            String candidateId,
            String candidateLabel) {
        System.out.println(candidateLabel + " -> " + candidateId);
        System.out.println(southAfricanIdValidator.validateAndDescribe(candidateId));
        System.out.println();
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - String: all ID processing starts from 13-character String values.
    - charAt: reads each character to validate digits and process checksum logic.
    - substring: extracts encoded ID segments such as birth date and citizenship.
    - Unicode: code-point output shows how look-alike digits differ from ASCII digits.
    - ASCII: strict '0' to '9' checks block non-ASCII numeric look-alikes.
    - length: verifies fixed-size ID strings before deeper validation.
    - equals: compares exact String codes such as citizenship values.
    - toUpperCase: normalizes channel labels for consistent comparisons and display.

    Interview questions this code prepares you to answer:
    - Why is length checking important before charAt and substring operations?
    - What is the difference between ASCII digit validation and broader Unicode digit checks?
    - How can substring and charAt be combined for encoded-string parsing?
    - Why should equals be used for String comparisons instead of ==?
    - How does the Luhn algorithm help detect tampered identifiers?

    Common mistake and how this code avoids it:
    - Mistake: accepting visually similar Unicode digits as valid numeric input.
      Avoidance: the validator enforces strict ASCII digit ranges for every character.
    */
}
