package com.ndlovu.javacore.inheritance.durbanmusicschool;

import java.time.LocalDate;

/**
 * Represents a guitar student whose grading depends on chord fluency, ensemble readiness, and steady practice.
 */
public final class GuitarStudent extends MusicStudent {

    private final int chordsMastered;
    private final boolean readyForBandRoom;

    /**
     * Creates a guitar student and sends the shared student information to the parent constructor using super.
     */
    public GuitarStudent(
            String studentName,
            int studentAge,
            LocalDate enrollmentDate,
            int monthlyFeeRands,
            int chordsMastered,
            boolean readyForBandRoom) {
        super(studentName, studentAge, enrollmentDate, monthlyFeeRands, "Guitar");
        this.chordsMastered = chordsMastered;
        this.readyForBandRoom = readyForBandRoom;
    }

    /**
     * Evaluates the guitar student using chord mastery and ensemble readiness because guitar learners often progress through performance confidence.
     */
    @Override
    public String assessGrade() {
        if (chordsMastered >= 8 && readyForBandRoom && practiceMinutesThisMonth >= 140) {
            return "Stage-ready";
        }
        if (chordsMastered >= 5 && practiceMinutesThisMonth >= 100) {
            return "Progressing well";
        }
        return "Needs stronger chord practice";
    }

    /**
     * Builds the guitar report so the academy can view the shared student record alongside guitar-specific performance indicators.
     */
    @Override
    public String buildReport() {
        return buildBaseReport() + "\n"
                + "Chords mastered: " + chordsMastered + "\n"
                + "Ready for band room: " + readyForBandRoom + "\n"
                + "Guitar assessment: " + assessGrade();
    }
}