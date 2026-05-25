package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Defines a logging contract so scanner operations can write consistent queue audit entries.
 */
public interface PortScanLogger {

    /**
     * Writes an informational message so operators can trace normal queue progress.
     */
    void info(String message);

    /**
     * Writes a warning message so operators can see recoverable validation issues.
     */
    void warn(String message);

    /**
     * Writes an error message so operators can capture fatal queue or system failures.
     */
    void error(String message);
}
