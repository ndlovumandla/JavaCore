package com.ndlovu.javacore.abstraction.hospitalbilling;

/**
 * Provides compliance logging behaviour for channels that must record legal audit context.
 */
public interface ComplianceLoggable {

    /**
     * Returns an audit log line so the hospital can trace regulated billing actions.
     */
    String complianceLog();
}
