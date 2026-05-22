package karooseedbank;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Real-world role: represents one preserved seed batch in Dr. Hendricks's vault.
 * It encapsulates all scientific and storage details needed for annual audits.
 */
public class SeedBatch {
    private final String speciesName;
    private final LocalDate collectionDate;
    private double viabilityPercentage;
    private final GeoCoordinate originCoordinate;
    private double storageTemperatureCelsius;

    /**
     * Creates a seed batch object with required scientific metadata.
     * This exists so each record begins in a valid, complete state.
     */
    public SeedBatch(
        String speciesName,
        String collectionDateIso,
        double viabilityPercentage,
        GeoCoordinate originCoordinate,
        double storageTemperatureCelsius
    ) {
        this.speciesName = validateSpeciesName(speciesName);
        this.collectionDate = parseCollectionDate(collectionDateIso);
        this.originCoordinate = validateOriginCoordinate(originCoordinate);
        this.viabilityPercentage = validateViabilityPercentage(viabilityPercentage);
        this.storageTemperatureCelsius = validateStorageTemperature(storageTemperatureCelsius);
    }

    /**
     * Returns species name.
     * This exists so reporting code can read protected state safely.
     */
    public String getSpeciesName() {
        return speciesName;
    }

    /**
     * Returns collection date.
     * This exists so auditors can verify sample age and traceability.
     */
    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    /**
     * Returns viability percentage.
     * This exists so strategy logic can classify risk levels.
     */
    public double getViabilityPercentage() {
        return viabilityPercentage;
    }

    /**
     * Returns collection coordinate.
     * This exists so researchers can trace geographic seed origin.
     */
    public GeoCoordinate getOriginCoordinate() {
        return originCoordinate;
    }

    /**
     * Returns storage temperature in Celsius.
     * This exists so compliance checks can confirm storage conditions.
     */
    public double getStorageTemperatureCelsius() {
        return storageTemperatureCelsius;
    }

    /**
     * Updates viability after germination testing.
     * This exists to support real-world re-testing cycles without replacing objects.
     */
    public void setViabilityPercentage(double viabilityPercentage) {
        this.viabilityPercentage = validateViabilityPercentage(viabilityPercentage);
    }

    /**
     * Updates storage temperature requirement.
     * This exists so staff can apply revised conservation protocols.
     */
    public void setStorageTemperatureCelsius(double storageTemperatureCelsius) {
        this.storageTemperatureCelsius = validateStorageTemperature(storageTemperatureCelsius);
    }

    /**
     * Checks if this batch passes a viability threshold.
     * This exists to provide a simple operational decision for staff workflows.
     */
    public boolean isViableAbove(double minimumViabilityThreshold) {
        return viabilityPercentage >= minimumViabilityThreshold;
    }

    /**
     * Parses and validates ISO date strings.
     * This exists to keep date validation close to object construction.
     */
    private LocalDate parseCollectionDate(String collectionDateIso) {
        if (collectionDateIso == null || collectionDateIso.isBlank()) {
            throw new IllegalArgumentException("Collection date is required.");
        }

        try {
            return LocalDate.parse(collectionDateIso);
        } catch (DateTimeParseException invalidDate) {
            throw new IllegalArgumentException("Collection date must use ISO format YYYY-MM-DD.");
        }
    }

    /**
     * Validates species name quality.
     * This exists to ensure reports remain accurate and searchable.
     */
    private String validateSpeciesName(String speciesName) {
        if (speciesName == null || speciesName.isBlank()) {
            throw new IllegalArgumentException("Species name is required.");
        }
        return speciesName.trim();
    }

    /**
     * Validates coordinate object presence.
     * This exists because origin data is mandatory for compliance records.
     */
    private GeoCoordinate validateOriginCoordinate(GeoCoordinate originCoordinate) {
        if (originCoordinate == null) {
            throw new IllegalArgumentException("Origin coordinate is required.");
        }
        return originCoordinate;
    }

    /**
     * Validates viability percentage bounds.
     * This exists to centralize rule checks for constructor and setter updates.
     */
    private double validateViabilityPercentage(double viabilityPercentage) {
        if (viabilityPercentage < 0.0 || viabilityPercentage > 100.0) {
            throw new IllegalArgumentException("Viability percentage must be between 0 and 100.");
        }
        return viabilityPercentage;
    }

    /**
     * Validates storage temperature bounds.
     * This exists to centralize rule checks for constructor and setter updates.
     */
    private double validateStorageTemperature(double storageTemperatureCelsius) {
        if (storageTemperatureCelsius < -30.0 || storageTemperatureCelsius > 25.0) {
            throw new IllegalArgumentException("Storage temperature must be between -30C and 25C.");
        }
        return storageTemperatureCelsius;
    }
}
