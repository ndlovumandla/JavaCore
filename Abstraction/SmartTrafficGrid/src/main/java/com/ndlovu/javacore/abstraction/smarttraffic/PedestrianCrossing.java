package com.ndlovu.javacore.abstraction.smarttraffic;

/**
 * Models a pedestrian crossing unit with an audio cue for visually impaired pedestrians.
 */
public final class PedestrianCrossing extends AbstractTrafficDevice {

    private boolean audioCueEnabled;

    /**
     * Creates a crossing device and starts with audio support enabled by policy.
     */
    public PedestrianCrossing(String intersectionCode) {
        super(intersectionCode);
        this.audioCueEnabled = true;
    }

    /**
     * Activates the crossing so walk requests and accessibility cues become available.
     */
    @Override
    public void activate() {
        super.activate();
        audioCueEnabled = true;
    }

    /**
     * Deactivates the crossing and disables cues to prevent false walk signals.
     */
    @Override
    public void deactivate() {
        super.deactivate();
        audioCueEnabled = false;
    }

    /**
     * Reports crossing status so operators can check accessibility readiness.
     */
    @Override
    public String reportStatus() {
        return deviceType() + " at " + intersectionCode + " audioCue=" + audioCueEnabled + " active=" + active;
    }

    /**
     * Identifies this hardware class in status reports.
     */
    @Override
    protected String deviceType() {
        return "PedestrianCrossing";
    }
}
