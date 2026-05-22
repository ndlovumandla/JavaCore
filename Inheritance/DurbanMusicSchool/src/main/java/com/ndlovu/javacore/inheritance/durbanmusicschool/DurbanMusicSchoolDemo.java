package com.ndlovu.javacore.inheritance.durbanmusicschool;

import java.time.LocalDate;
import java.util.List;

/**
 * Runs the Durban Music School demonstration and ties the separate student classes together.
 */
public class DurbanMusicSchoolDemo {

    /**
     * Starts the academy demonstration, creates the student roster, and prints
     * the reports so the inheritance relationships are visible in action.
     */
    public static void main(String[] args) {
        // Polymorphism: one List stores different subclasses through the shared MusicStudent parent type.
        List<MusicStudent> academyRoster = List.of(
                new PianoStudent("Amina Ndlovu", 12, LocalDate.of(2026, 1, 10), 950, 3, 88),
                new GuitarStudent("Liam Jacobs", 14, LocalDate.of(2026, 2, 3), 850, 7, true),
                new VoiceStudent("Naledi Dlamini", 13, LocalDate.of(2026, 1, 28), 900, 2, 91),
                new ZuluPercussionStudent("Sipho Mkhize", 15, LocalDate.of(2026, 1, 18), 780, 5, 84));

        // Shared admin logic: every student can log practice through the same base-class method.
        academyRoster.get(0).logPracticeMinutes(120);
        academyRoster.get(0).recordRecitalPerformance("Autumn Piano Showcase");

        academyRoster.get(1).logPracticeMinutes(150);
        academyRoster.get(1).recordRecitalPerformance("String Ensemble Evening");

        academyRoster.get(2).logPracticeMinutes(110);
        academyRoster.get(2).recordRecitalPerformance("Voices of Durban Concert");

        academyRoster.get(3).logPracticeMinutes(135);
        academyRoster.get(3).recordRecitalPerformance("Community Heritage Rhythm Night");

        printAcademyBanner();

        // Polymorphism: the same method call chooses a different overridden report at runtime.
        for (MusicStudent academyStudent : academyRoster) {
            printStudentReport(academyStudent);
        }

        printStudySummary(academyRoster);
    }

    /**
     * Prints a short heading so the output reads like a real academy briefing.
     * This exists to make the console output easier to follow for a student
     * reading the example for the first time.
     */
    private static void printAcademyBanner() {
        System.out.println("============================================================");
        System.out.println("Bayview Music Academy - Durban Student Management Briefing");
        System.out.println("============================================================");
        System.out.println();
    }

    /**
     * Prints one student report so the different overridden grading rules are
     * easy to compare side by side.
     */
    private static void printStudentReport(MusicStudent academyStudent) {
        System.out.println(academyStudent.buildReport());
        System.out.println();
    }

    /**
     * Prints a short summary that highlights the shared parent type and the
     * different child classes used in the roster.
     */
    private static void printStudySummary(List<MusicStudent> academyRoster) {
        System.out.println("Academy summary: " + academyRoster.size() + " students enrolled.");
        System.out.println("This roster demonstrates that every student IS-A MusicStudent,");
        System.out.println("while each subclass applies a different grading rule.");
    }

    /* ═══ STUDY NOTES ═══
    Java concepts demonstrated:
    - extends: Each instrument class inherits shared enrollment data and behavior from MusicStudent.
    - super: Each subclass calls the parent constructor to reuse common setup instead of duplicating it.
    - method overriding: Each student type replaces assessGrade() and buildReport() with its own rules.
    - IS-A relationship: A PianoStudent, GuitarStudent, VoiceStudent, and ZuluPercussionStudent are all MusicStudent objects.
    - polymorphism: The List<MusicStudent> stores different subclasses, and the runtime chooses the correct report method.

    Interview questions this code helps answer:
    - Why would you use inheritance instead of duplicating the same fields in every class?
    - What does super do in a constructor, and why must it often be the first statement?
    - How does method overriding differ from method overloading?
    - How does polymorphism let one collection hold different child classes?
    - When is it better to keep shared logic in the parent class and stream-specific logic in the child class?

    Common mistake and how this code avoids it:
    - Mistake: putting all grading logic into the base class and losing the stream-specific differences. This example keeps shared admin data in MusicStudent and overrides the grading rules in each subclass.
    */
}