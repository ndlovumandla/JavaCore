/*
 * Cape Town Marathon timing readers capture split and finish values for thousands of runners.
 * These values arrive as long milliseconds from RFID chip systems at each checkpoint.
 * Results processing must convert configuration Strings, compute precise pace values, and format finish times.
 * Yusuf demonstrates wrapper classes and type conversion to produce reliable race-day output.
 * The same flow also validates bib formats that mix letters and digits using Character utilities.
 */

package com.ndlovu.javacore.wrappers.marathon;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs a beginner-friendly marathon timing pipeline focused on wrappers and type conversions.
 */
public class CapeTownMarathonTimingSystemDemo {

    /**
     * Executes parsing, conversion, pace calculation, and result formatting for sample runners.
     */
    public static void main(String[] args) {
        List<RunnerTimingRecord> runnerTimingRecords = new ArrayList<>();
        runnerTimingRecords.add(new RunnerTimingRecord("CTM-A204", 12_720_000L, "39"));
        runnerTimingRecords.add(new RunnerTimingRecord("CTM-B118", 14_095_000L, "49"));
        runnerTimingRecords.add(new RunnerTimingRecord("CTM-C077", 15_560_000L, "59"));

        ArrayList<Integer> displayPaceMinutesPerKmList = new ArrayList<>();

        System.out.println("=== Cape Town Marathon Results Engine ===");

        for (RunnerTimingRecord runnerTimingRecord : runnerTimingRecords) {
            Integer parsedAgeLimit = runnerTimingRecord.parseAgeCategoryUpperLimit();

            // Widening conversion: int becomes long without cast to compare with long millisecond data.
            long ageLimitAsLong = parsedAgeLimit;

            // Widening to double: long milliseconds converted for decimal pace arithmetic.
            double finishSeconds = runnerTimingRecord.getFinishTimeMilliseconds() / 1000.0;
            double marathonDistanceKm = 42.195;
            double paceSecondsPerKm = finishSeconds / marathonDistanceKm;

            // Double wrapper highlights object form used in reporting pipelines.
            Double paceMinutesPerKmWrapper = Double.valueOf(paceSecondsPerKm / 60.0);

            // Narrowing + casting: convert decimal pace to whole minutes for compact display column.
            int displayPaceMinutes = (int) paceMinutesPerKmWrapper.doubleValue();

            // Autoboxing: primitive int is automatically wrapped as Integer in ArrayList.
            displayPaceMinutesPerKmList.add(displayPaceMinutes);

            // Character wrapper methods parse mixed bib values containing letters and digits.
            Character firstBibCharacter = runnerTimingRecord.getBibNumber().charAt(0);
            boolean bibStartsWithLetter = Character.isLetter(firstBibCharacter);

            String formattedFinishTime = formatSecondsToClockTime((int) finishSeconds);

            System.out.println("Runner bib: " + runnerTimingRecord.getBibNumber());
            System.out.println("Bib starts with letter: " + bibStartsWithLetter);
            System.out.println("Parsed age category max (Integer): " + parsedAgeLimit);
            System.out.println("Age limit as long (widening): " + ageLimitAsLong);
            System.out.println("Finish time: " + formattedFinishTime);
            System.out.println("Pace minutes/km (Double): " + Math.round(paceMinutesPerKmWrapper * 100.0) / 100.0);
            System.out.println("Display pace after narrowing cast: " + displayPaceMinutes + " min/km");
            System.out.println();
        }

        System.out.println("Display pace list (autoboxed Integer values): " + displayPaceMinutesPerKmList);
    }

    /**
     * Converts total seconds into HH:MM:SS text for race results display screens.
     */
    private static String formatSecondsToClockTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int remainingSecondsAfterHours = totalSeconds % 3600;
        int minutes = remainingSecondsAfterHours / 60;
        int seconds = remainingSecondsAfterHours % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - Integer: parses String age-category config values into numeric form.
    - Double: wraps decimal pace values used in race analytics.
    - autoboxing: primitive display pace values are auto-boxed into ArrayList<Integer>.
    - widening: int to long and long to double conversions happen without data-loss casts.
    - narrowing: decimal pace is narrowed to int for compact result board display.
    - casting: explicit (int) cast controls narrowing behavior.
    - Character: validates bib format using Character helper methods.

    Interview questions this code prepares you to answer:
    - What is the difference between widening and narrowing conversions in Java?
    - Why is explicit casting required for narrowing numeric conversions?
    - How does autoboxing work with Java collections?
    - Why might wrappers like Integer and Double be useful over primitives?
    - How can Character APIs help parse mixed-format identifiers?

    Common mistake and how this code avoids it:
    - Mistake: dividing long values with integer arithmetic when decimal precision is needed.
      Avoidance: the code widens to double before pace calculations to preserve precision.
    */
}
