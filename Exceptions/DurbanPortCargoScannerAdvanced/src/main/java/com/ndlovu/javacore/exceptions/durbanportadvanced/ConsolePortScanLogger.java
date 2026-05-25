package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Writes scanner logs to the console for this teaching simulation.
 */
public class ConsolePortScanLogger implements PortScanLogger {

    /**
     * Prints normal operational log entries for successful queue flow.
     */
    @Override
    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    /**
     * Prints warning log entries for recoverable validation failures.
     */
    @Override
    public void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    /**
     * Prints error log entries for serious system-level failures.
     */
    @Override
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
