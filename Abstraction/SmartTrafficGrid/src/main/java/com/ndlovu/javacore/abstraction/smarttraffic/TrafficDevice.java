package com.ndlovu.javacore.abstraction.smarttraffic;

/**
 * Defines the core contract every traffic control device must honour in the city grid.
 */
public interface TrafficDevice {

    /**
     * Turns the device on so the command centre can put it into active traffic duty.
     */
    void activate();

    /**
     * Turns the device off so maintenance or fallback operation can occur safely.
     */
    void deactivate();

    /**
     * Returns the current device state so operators can monitor the intersection in real time.
     */
    String reportStatus();
}
