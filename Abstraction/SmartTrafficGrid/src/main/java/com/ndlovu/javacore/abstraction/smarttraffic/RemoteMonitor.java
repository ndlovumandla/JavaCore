package com.ndlovu.javacore.abstraction.smarttraffic;

/**
 * Defines extra monitoring capabilities that some traffic devices expose to the central dashboard.
 */
public interface RemoteMonitor {

    /**
     * Returns one-line telemetry so operators can quickly scan health and readiness data.
     */
    String telemetry();
}
