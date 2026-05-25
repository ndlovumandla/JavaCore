package com.ndlovu.javacore.exceptions.durbanportadvanced;

import java.util.Set;

/**
 * Validates hazmat codes and flags unknown runtime values.
 */
public class HazmatValidator implements ContainerValidator {

    private static final Set<String> ALLOWED_HAZMAT_CODES = Set.of("NONE", "HZ100", "HZ220", "HZ330", "HZ711");

    /**
     * Throws an unchecked exception when hazmat code is unknown to current policy tables.
     */
    @Override
    public void validate(CargoContainerRecord cargoContainerRecord) {
        if (!ALLOWED_HAZMAT_CODES.contains(cargoContainerRecord.hazmatCode())) {
            throw new UnknownHazmatCodeException("Unknown hazmat code: " + cargoContainerRecord.hazmatCode());
        }
    }
}
