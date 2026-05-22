package com.ndlovu.javacore.inheritance.durbanmusicschool;

import java.time.LocalDate;

/**
 * Represents a voice student whose grading depends on breath control, pitch accuracy, and recital confidence.
 */
public final class VoiceStudent extends MusicStudent {

    private final int pitchAccuracyScore;
    private final int breathControlScore;

    /**
     * Creates a voice student and reuses the common enrollment setup from the base class with super.
     */
    public VoiceStudent(
            String studentName,
            int studentAge,
            LocalDate enrollmentDate,
            int monthlyFeeRands,
            int pitchAccuracyScore,
            int breathControlScore) {
        super(studentName, studentAge, enrollmentDate, monthlyFeeRands, "Voice");
        this.pitchAccuracyScore = pitchAccuracyScore;
        this.breathControlScore = breathControlScore;
    }

    /**
     * Evaluates the voice student using pitch and breath control because a vocal stream needs both musical precision and stamina.
     */
    @Override
    public String assessGrade() {
        if (pitchAccuracyScore >= 90 && breathControlScore >= 85 && recitalAppearancesThisTerm >= 1) {
            return "Excellent recital control";
        }
        if (pitchAccuracyScore >= 80 && breathControlScore >= 75) {
            return "Solid vocal progress";
        }
        return "Needs more pitch coaching";
    }

    /**
     * Builds the voice report so the academy can compare the student record with the stream-specific vocal assessment.
     */
    @Override
    public String buildReport() {
        return buildBaseReport() + "\n"
                + "Pitch accuracy score: " + pitchAccuracyScore + "\n"
                + "Breath control score: " + breathControlScore + "\n"
                + "Voice assessment: " + assessGrade();
    }
}