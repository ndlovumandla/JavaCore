package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Validates Durban port container ID formatting rules.
 */
public class ContainerIdValidator implements ContainerValidator {

    /**
     * Checks the ID pattern and throws a checked exception when the record format is invalid.
     */
    @Override
    public void validate(CargoContainerRecord cargoContainerRecord) throws CargoValidationException {
        if (cargoContainerRecord.containerId() == null
                || !cargoContainerRecord.containerId().matches("^DUR-[A-Z]{3}-\\d{4}$")) {
            throw new MalformedContainerIdException("Malformed container ID: " + cargoContainerRecord.containerId());
        }
    }
}
