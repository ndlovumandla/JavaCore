package com.ndlovu.javacore.abstraction.smarttraffic;

/**
 * Models an emergency vehicle override sensor that can prioritize response routes.
 */
public final class EmergencyOverrideSensor extends AbstractTrafficDevice {

    private boolean overrideArmed;

    /**
     * Creates an override sensor and starts with override mode disarmed.
     */
    public EmergencyOverrideSensor(String intersectionCode) {
        super(intersectionCode);
        this.overrideArmed = false;
    }

    /**
     * Activates the sensor and arms override mode for emergency routing.
     */
    @Override
    public void activate() {
        super.activate();
        overrideArmed = true;
    }

    /**
     * Deactivates the sensor and disarms override mode to return to normal control.
     */
    @Override
    public void deactivate() {
        super.deactivate();
        overrideArmed = false;
    }

    /**
     * Reports sensor status so operators can confirm emergency priority readiness.
     */
    @Override
    public String reportStatus() {
        return deviceType() + " at " + intersectionCode + " overrideArmed=" + overrideArmed + " active=" + active;
    }

    /**
     * Identifies this hardware class in status reports.
     */
    @Override
    protected String deviceType() {
        return "EmergencyOverrideSensor";
    }
}
