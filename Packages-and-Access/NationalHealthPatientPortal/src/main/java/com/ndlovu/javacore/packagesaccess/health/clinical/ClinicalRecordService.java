package com.ndlovu.javacore.packagesaccess.health.clinical;

/**
 * Manages clinical patient notes for authorized care providers.
 */
public class ClinicalRecordService {

    private String latestDiagnosis = "No diagnosis captured";
    private String latestClinicalNote = "No clinical note captured";

    /**
     * Allows GP workflows to update diagnosis and notes through a controlled public API.
     */
    public void gpWriteClinicalNote(String diagnosisText, String clinicalNoteText) {
        // public access is required for trusted GP application flows.
        latestDiagnosis = diagnosisText;
        latestClinicalNote = clinicalNoteText;
    }

    /**
     * Returns a safe summary for clinical users without exposing private internals directly.
     */
    public String gpReadClinicalSummary() {
        return "Diagnosis=" + latestDiagnosis + " | Note=" + latestClinicalNote;
    }

    /**
     * Provides a protected extension point for approved internal portal modules.
     */
    protected String protectedClinicalIntegrityCheck() {
        // protected allows inheritance-based internal checks while blocking unrelated callers.
        return "Clinical integrity check complete.";
    }

    /**
     * Produces a private compliance token used only inside this class.
     */
    private String privateComplianceToken() {
        // private keeps sensitive token logic inaccessible outside this class.
        return "CLINICAL-TOKEN-" + latestDiagnosis.length();
    }

    /**
     * Exposes a safe marker derived from private token logic for diagnostics.
     */
    public String safeClinicalTokenPreview() {
        return privateComplianceToken().substring(0, 13) + "...";
    }
}
