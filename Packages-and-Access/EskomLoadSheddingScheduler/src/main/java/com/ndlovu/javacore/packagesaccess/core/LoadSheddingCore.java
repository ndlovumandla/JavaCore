package com.ndlovu.javacore.packagesaccess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Eskom's protected core scheduling logic that determines affected areas.
 */
public class LoadSheddingCore {

    private final List<String> internalGridLoadFeed = new ArrayList<>();
    private String latestPublicSchedule = "No schedule generated yet";

    /**
     * Updates internal grid feed data used by core scheduling decisions.
     */
    public void ingestGridLoadSnapshot(String gridSnapshot) {
        // public is used here because trusted upstream systems must supply fresh load data.
        internalGridLoadFeed.add(gridSnapshot);
    }

    /**
     * Returns public-facing schedule text safe for apps and citizens.
     */
    public String generatePublicSchedule() {
        // public API returns only sanitized schedule output, not raw internal feed data.
        latestPublicSchedule = "Stage 3: Blocks A, C, and F from 18:00 to 20:30";
        return latestPublicSchedule;
    }

    /**
     * Allows subclassed internal tools to trigger regulated recalculation logic.
     */
    protected String triggerProtectedRecalculation() {
        // protected allows extension by authorized operations tooling but blocks general external access.
        return "Protected recalculation complete for control-room request.";
    }

    /**
     * Performs package-local operational checks only for classes inside the core package.
     */
    String runPackageSafetyAudit() {
        // default (package-private) keeps this method invisible outside the core package.
        return "Package audit: " + internalGridLoadFeed.size() + " grid snapshots reviewed.";
    }

    /**
     * Builds a sealed internal decision token that must never be exposed directly.
     */
    private String buildPrivateDecisionToken() {
        // private protects sensitive decision internals from all external classes.
        return "CORE-TOKEN-" + internalGridLoadFeed.size();
    }

    /**
     * Produces a safe internal status summary without revealing private decision token details.
     */
    public String safeInternalStatus() {
        String tokenPreview = buildPrivateDecisionToken().substring(0, 10);
        return "Internal status ready. Token preview: " + tokenPreview + "...";
    }
}
