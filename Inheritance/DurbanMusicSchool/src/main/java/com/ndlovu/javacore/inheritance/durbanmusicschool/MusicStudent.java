package com.ndlovu.javacore.inheritance.durbanmusicschool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the academy's shared student record that all instrument streams inherit from.
 */
public abstract class MusicStudent {

    protected static final DateTimeFormatter DATE_STYLE = DateTimeFormatter.ofPattern("d MMM uuuu");

    protected final String studentName;
    protected final int studentAge;
    protected final LocalDate enrollmentDate;
    protected final int monthlyFeeRands;
    protected final String instrumentStream;
    protected int practiceMinutesThisMonth;
    protected int recitalAppearancesThisTerm;

    /**
     * Builds the common student record and keeps the shared enrollment data in one place.
     */
    protected MusicStudent(
            String studentName,
            int studentAge,
            LocalDate enrollmentDate,
            int monthlyFeeRands,
            String instrumentStream) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.enrollmentDate = enrollmentDate;
        this.monthlyFeeRands = monthlyFeeRands;
        this.instrumentStream = instrumentStream;
    }

    /**
     * Adds a practice session to the shared record so the academy can track effort consistently.
     */
    public final void logPracticeMinutes(int minutesPracticed) {
        if (minutesPracticed <= 0) {
            throw new IllegalArgumentException("Practice minutes must be greater than zero.");
        }
        practiceMinutesThisMonth += minutesPracticed;
    }

    /**
     * Records a recital appearance so the shared base class can track performance history.
     */
    public final void recordRecitalPerformance(String recitalTitle) {
        if (recitalTitle == null || recitalTitle.isBlank()) {
            throw new IllegalArgumentException("Recital title must not be blank.");
        }
        recitalAppearancesThisTerm++;
    }

    /**
     * Builds the common part of every report so subclasses can focus on stream-specific rules.
     */
    protected final String buildBaseReport() {
        return "Student: " + studentName + "\n"
                + "Age: " + studentAge + "\n"
                + "Instrument stream: " + instrumentStream + "\n"
                + "Enrollment date: " + DATE_STYLE.format(enrollmentDate) + "\n"
                + "Monthly fee: R" + monthlyFeeRands + "\n"
                + "Practice logged this month: " + practiceMinutesThisMonth + " minutes\n"
                + "Recital appearances this term: " + recitalAppearancesThisTerm;
    }

    /**
     * Returns the instrument-specific grading statement.
     */
    public abstract String assessGrade();

    /**
     * Returns the complete report for one student.
     */
    public abstract String buildReport();
}