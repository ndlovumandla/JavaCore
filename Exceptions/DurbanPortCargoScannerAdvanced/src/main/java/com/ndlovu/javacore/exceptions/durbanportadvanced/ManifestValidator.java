package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Validates manifest linkage required for legal cargo entry auditing.
 */
public class ManifestValidator implements ContainerValidator {

    /**
     * Ensures every container has a non-blank manifest reference.
     */
    @Override
    public void validate(CargoContainerRecord cargoContainerRecord) throws CargoValidationException {
        if (cargoContainerRecord.manifestReference() == null || cargoContainerRecord.manifestReference().isBlank()) {
            throw new MissingManifestException("Missing manifest for container " + cargoContainerRecord.containerId());
        }
    }
}
