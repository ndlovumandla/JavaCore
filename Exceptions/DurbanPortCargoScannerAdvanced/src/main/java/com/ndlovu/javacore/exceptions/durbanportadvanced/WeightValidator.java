package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Validates scanned container weight against bill-of-lading declarations.
 */
public class WeightValidator implements ContainerValidator {

    /**
     * Rejects weight overage records to enforce shipping declaration controls.
     */
    @Override
    public void validate(CargoContainerRecord cargoContainerRecord) throws CargoValidationException {
        if (cargoContainerRecord.scannedWeightKg() > cargoContainerRecord.billOfLadingWeightKg()) {
            throw new WeightOverageException(
                    "Weight overage for " + cargoContainerRecord.containerId()
                            + ": scanned=" + cargoContainerRecord.scannedWeightKg()
                            + "kg, declared=" + cargoContainerRecord.billOfLadingWeightKg() + "kg");
        }
    }
}
