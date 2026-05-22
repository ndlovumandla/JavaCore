package lastlighthousekeeper;

/**
 * Real-world role: captures weather conditions that affect lighthouse operations.
 */
public class WeatherReading extends NotebookEntry {
    private final String weatherCondition;
    private final double windSpeedKnots;
    private double seaVisibilityNauticalMiles;

    /**
     * Creates a weather reading object from notebook values.
     * This exists so weather data can be tracked independently from ship logs.
     */
    public WeatherReading(
        String entryDate,
        String weatherCondition,
        double windSpeedKnots,
        double seaVisibilityNauticalMiles
    ) {
        super(entryDate);
        this.weatherCondition = weatherCondition;
        this.windSpeedKnots = windSpeedKnots;
        this.seaVisibilityNauticalMiles = seaVisibilityNauticalMiles;
    }

    /**
     * Returns the weather condition label.
     * This exists to expose environmental state through encapsulated access.
     */
    public String getWeatherCondition() {
        return weatherCondition;
    }

    /**
     * Returns wind speed in knots.
     * This exists to support maintenance decisions based on storm intensity.
     */
    public double getWindSpeedKnots() {
        return windSpeedKnots;
    }

    /**
     * Returns sea visibility in nautical miles.
     * This exists to evaluate navigation risk for passing ships.
     */
    public double getSeaVisibilityNauticalMiles() {
        return seaVisibilityNauticalMiles;
    }

    /**
     * Updates visibility when Priya corrects notebook conversions.
     * This exists to permit controlled edits through a setter.
     */
    public void setSeaVisibilityNauticalMiles(double seaVisibilityNauticalMiles) {
        this.seaVisibilityNauticalMiles = seaVisibilityNauticalMiles;
    }

    /**
     * Converts this weather reading to a standardized archive line.
     * This exists so weather entries can be stored alongside ship entries.
     */
    @Override
    public String toArchiveLine() {
        return getEntryDate() + " | Weather: " + weatherCondition
            + " | Wind(knots): " + windSpeedKnots
            + " | Visibility(nm): " + seaVisibilityNauticalMiles;
    }
}
