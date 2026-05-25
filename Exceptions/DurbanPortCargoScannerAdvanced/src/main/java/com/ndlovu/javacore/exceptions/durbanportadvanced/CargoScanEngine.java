package com.ndlovu.javacore.exceptions.durbanportadvanced;

import java.util.List;

/**
 * Coordinates queue processing, validator execution, exception recovery, and guaranteed logging.
 */
public class CargoScanEngine {

    private final ValidatorChain validatorChain;
    private final PortScanLogger portScanLogger;

    /**
     * Creates a scan engine with injected validator pipeline and logging dependency.
     */
    public CargoScanEngine(ValidatorChain validatorChain, PortScanLogger portScanLogger) {
        this.validatorChain = validatorChain;
        this.portScanLogger = portScanLogger;
    }

    /**
     * Processes the full queue without stopping on record-level failures.
     */
    public void processQueue(List<CargoContainerRecord> cargoQueue) {
        if (cargoQueue.size() > 10_000) {
            throw new ScannerOverloadException("Queue size " + cargoQueue.size() + " exceeds safe limit.");
        }

        for (CargoContainerRecord cargoContainerRecord : cargoQueue) {
            processSingleRecord(cargoContainerRecord);
        }
    }

    /**
     * Processes one record with specific catch branches and always-on finally logging.
     */
    private void processSingleRecord(CargoContainerRecord cargoContainerRecord) {
        try {
            // try: validator chain may raise checked or unchecked exceptions per record.
            scanAndValidate(cargoContainerRecord);
            portScanLogger.info("PASS " + cargoContainerRecord.displayKey());
        } catch (MalformedContainerIdException malformedContainerIdException) {
            // catch specific checked exception for precise operational messaging.
            portScanLogger.warn("REJECT " + malformedContainerIdException.getMessage());
        } catch (MissingManifestException missingManifestException) {
            portScanLogger.warn("HOLD " + missingManifestException.getMessage());
        } catch (WeightOverageException weightOverageException) {
            portScanLogger.warn("FLAG " + weightOverageException.getMessage());
        } catch (CargoValidationException cargoValidationException) {
            portScanLogger.warn("VALIDATION ERROR " + cargoValidationException.getMessage());
        } catch (UnknownHazmatCodeException unknownHazmatCodeException) {
            // catch unchecked exception to prevent bad runtime data from crashing queue processing.
            portScanLogger.error("HAZMAT REVIEW " + unknownHazmatCodeException.getMessage());
        } finally {
            // finally: audit line must always be written for legal traceability.
            portScanLogger.info("AUDIT scanned " + cargoContainerRecord.containerId());
        }
    }

    /**
     * Runs validation and exposes checked exceptions explicitly through throws.
     */
    private void scanAndValidate(CargoContainerRecord cargoContainerRecord) throws CargoValidationException {
        validatorChain.validate(cargoContainerRecord);
    }
}
