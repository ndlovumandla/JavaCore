package com.ndlovu.javacore.abstraction.smarttraffic;

/**
 * Models a standard traffic light that cycles through vehicle signal phases.
 */
public final class StandardTrafficLight extends AbstractTrafficDevice {

    private String currentSignal;

    /**
     * Creates a traffic light for one intersection and sets a safe default signal.
     */
    public StandardTrafficLight(String intersectionCode) {
        super(intersectionCode);
        this.currentSignal = "RED";
    }

    /**
     * Activates the light and starts normal flow control for vehicles.
     */
    @Override
    public void activate() {
        super.activate();
        currentSignal = "GREEN";
    }

    /**
     * Deactivates the light and locks it on red to keep the junction safe.
     */
    @Override
    public void deactivate() {
        super.deactivate();
        currentSignal = "RED";
    }

    /**
     * Reports light status so the control room can verify lane movement rules.
     */
    @Override
    public String reportStatus() {
        return deviceType() + " at " + intersectionCode + " signal=" + currentSignal + " active=" + active;
    }

    /**
     * Identifies this hardware class in status reports.
     */
    @Override
    protected String deviceType() {
        return "StandardTrafficLight";
    }
}
