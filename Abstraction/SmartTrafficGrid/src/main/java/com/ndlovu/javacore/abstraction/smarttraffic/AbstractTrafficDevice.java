package com.ndlovu.javacore.abstraction.smarttraffic;

/**
 * Holds shared state and common behaviour for all traffic devices in Naledi's smart grid.
 */
public abstract class AbstractTrafficDevice implements TrafficDevice, RemoteMonitor {

    protected final String intersectionCode;
    protected boolean active;

    /**
     * Stores the intersection identifier so every subclass can be tracked by location.
     */
    protected AbstractTrafficDevice(String intersectionCode) {
        this.intersectionCode = intersectionCode;
        this.active = false;
    }

    /**
     * Activates shared device state so all concrete devices follow one lifecycle model.
     */
    @Override
    public void activate() {
        active = true;
    }

    /**
     * Deactivates shared device state so all concrete devices can be safely switched off.
     */
    @Override
    public void deactivate() {
        active = false;
    }

    /**
     * Returns shared telemetry data so the dashboard can show location and activity quickly.
     */
    @Override
    public String telemetry() {
        return "Intersection " + intersectionCode + " active=" + active;
    }

    /**
     * Returns the concrete hardware type so subclasses can describe themselves in status output.
     */
    protected abstract String deviceType();
}
