package com.ndlovu.javacore.exceptions.durbanportadvanced;

import java.util.List;

/**
 * Runs validator steps in sequence so the scanner can apply modular rule checks.
 */
public class ValidatorChain {

    private final List<ContainerValidator> containerValidators;

    /**
     * Stores the configured validators so rule execution order remains explicit and maintainable.
     */
    public ValidatorChain(List<ContainerValidator> containerValidators) {
        this.containerValidators = List.copyOf(containerValidators);
    }

    /**
     * Executes each validator in order and stops on the first checked failure for that record.
     */
    public void validate(CargoContainerRecord cargoContainerRecord) throws CargoValidationException {
        // Chain-of-responsibility style flow: each validator handles one rule and passes control onward.
        for (ContainerValidator containerValidator : containerValidators) {
            containerValidator.validate(cargoContainerRecord);
        }
    }
}
