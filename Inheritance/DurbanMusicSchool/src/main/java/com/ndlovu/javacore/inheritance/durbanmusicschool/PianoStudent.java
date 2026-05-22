package com.ndlovu.javacore.inheritance.durbanmusicschool;

import java.time.LocalDate;

/**
 * Represents a piano student whose grading depends on technique, repertoire, and disciplined practice.
 */
public final class PianoStudent extends MusicStudent {

    private final int repertoirePiecesCompleted;
    private final int sightReadingScore;

    /**
     * Creates a piano student and passes the common enrollment data to the parent class with super.
     */
    public PianoStudent(
            String studentName,
            int studentAge,
            LocalDate enrollmentDate,
            int monthlyFeeRands,
            int repertoirePiecesCompleted,
            int sightReadingScore) {
        super(studentName, studentAge, enrollmentDate, monthlyFeeRands, "Piano");
        this.repertoirePiecesCompleted = repertoirePiecesCompleted;
        this.sightReadingScore = sightReadingScore;
    }

    /**
     * Evaluates the piano student using repertoire and sight-reading results because pianists are usually graded on technical accuracy and fluency.
     */
    @Override
    public String assessGrade() {
        if (sightReadingScore >= 90 && repertoirePiecesCompleted >= 3 && practiceMinutesThisMonth >= 120) {
            return "Distinction";
        }
        if (sightReadingScore >= 80 && repertoirePiecesCompleted >= 2 && practiceMinutesThisMonth >= 90) {
            return "Merit";
        }
        return "Needs extra technique coaching";
    }

    /**
     * Builds the piano report so the academy can see the shared enrollment record plus the instrument-specific grading summary.
     */
    @Override
    public String buildReport() {
        return buildBaseReport() + "\n"
                + "Piano repertoire pieces: " + repertoirePiecesCompleted + "\n"
                + "Sight-reading score: " + sightReadingScore + "\n"
                + "Piano assessment: " + assessGrade();
    }
}