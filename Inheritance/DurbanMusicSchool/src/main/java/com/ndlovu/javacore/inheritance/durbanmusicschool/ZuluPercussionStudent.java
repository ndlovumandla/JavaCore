package com.ndlovu.javacore.inheritance.durbanmusicschool;

import java.time.LocalDate;

/**
 * Represents a traditional Zulu percussion student whose grading depends on rhythm accuracy, ensemble timing, and performance energy.
 */
public final class ZuluPercussionStudent extends MusicStudent {

    private final int rhythmAccuracyScore;
    private final int callAndResponseConfidence;

    /**
     * Creates a percussion student and forwards the shared details to the parent class so the academy keeps one consistent student record.
     */
    public ZuluPercussionStudent(
            String studentName,
            int studentAge,
            LocalDate enrollmentDate,
            int monthlyFeeRands,
            int rhythmAccuracyScore,
            int callAndResponseConfidence) {
        super(studentName, studentAge, enrollmentDate, monthlyFeeRands, "Zulu Percussion");
        this.rhythmAccuracyScore = rhythmAccuracyScore;
        this.callAndResponseConfidence = callAndResponseConfidence;
    }

    /**
     * Evaluates the percussion student using rhythm and ensemble response because that stream depends on timing, listening, and group awareness.
     */
    @Override
    public String assessGrade() {
        if (rhythmAccuracyScore >= 88 && callAndResponseConfidence >= 80 && practiceMinutesThisMonth >= 120) {
            return "Powerful ensemble leadership";
        }
        if (rhythmAccuracyScore >= 75 && practiceMinutesThisMonth >= 90) {
            return "Rhythm on track";
        }
        return "Needs tighter timing practice";
    }

    /**
     * Builds the percussion report so the academy can present the common enrollment details together with the rhythm-focused assessment.
     */
    @Override
    public String buildReport() {
        return buildBaseReport() + "\n"
                + "Rhythm accuracy score: " + rhythmAccuracyScore + "\n"
                + "Call-and-response confidence: " + callAndResponseConfidence + "\n"
                + "Zulu percussion assessment: " + assessGrade();
    }
}