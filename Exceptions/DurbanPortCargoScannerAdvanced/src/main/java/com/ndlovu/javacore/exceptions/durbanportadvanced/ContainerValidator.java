package com.ndlovu.javacore.exceptions.durbanportadvanced;

/**
 * Defines a validation step contract used in the scanner's validator chain.
 */
public interface ContainerValidator {

    /**
     * Validates one container record and throws a checked exception for expected rule failures.
     */
    void validate(CargoContainerRecord cargoContainerRecord) throws CargoValidationException;
}
