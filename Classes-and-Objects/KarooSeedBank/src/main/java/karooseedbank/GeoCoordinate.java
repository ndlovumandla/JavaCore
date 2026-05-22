package karooseedbank;

/**
 * Real-world role: stores the exact GPS collection point for each seed batch,
 * so Sipho can preserve origin metadata needed for science and auditing.
 */
public class GeoCoordinate {
    private final double latitude;
    private final double longitude;

    /**
     * Creates a coordinate object after validating geographic boundaries.
     * This exists to prevent invalid location data from entering the vault records.
     */
    public GeoCoordinate(double latitude, double longitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns latitude in decimal degrees.
     * This exists so reports can show where a seed batch was collected.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns longitude in decimal degrees.
     * This exists so reports can show where a seed batch was collected.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Formats coordinates for human-readable audit output.
     * This exists to keep report formatting centralized in the value object.
     */
    public String format() {
        return String.format("%.4f, %.4f", latitude, longitude);
    }

    /**
     * Validates latitude bounds.
     * This exists to enforce real-world coordinate constraints.
     */
    private void validateLatitude(double latitude) {
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90.");
        }
    }

    /**
     * Validates longitude bounds.
     * This exists to enforce real-world coordinate constraints.
     */
    private void validateLongitude(double longitude) {
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180.");
        }
    }
}
