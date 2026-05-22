package karooseedbank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Real-world role: application service that manages the vault collection,
 * applies audit policy, and produces storage reports for Dr. Hendricks.
 */
public class SeedVaultService {
    private final List<SeedBatch> vaultBatches;
    private final Set<String> endangeredSpeciesRegistry;
    private final BatchRiskPolicy batchRiskPolicy;

    /**
     * Creates the vault service with a chosen risk strategy.
     * This exists so the app can inject policy behavior rather than hard-coding it.
     */
    public SeedVaultService(BatchRiskPolicy batchRiskPolicy) {
        if (batchRiskPolicy == null) {
            throw new IllegalArgumentException("Batch risk policy is required.");
        }

        this.vaultBatches = new ArrayList<>();
        this.endangeredSpeciesRegistry = new HashSet<>();
        this.batchRiskPolicy = batchRiskPolicy;
    }

    /**
     * Adds a seed batch to the vault.
     * This exists to preserve each batch as a first-class object in the digital binder.
     */
    public void registerSeedBatch(SeedBatch seedBatch) {
        if (seedBatch == null) {
            throw new IllegalArgumentException("Seed batch cannot be null.");
        }
        vaultBatches.add(seedBatch);
    }

    /**
     * Marks a species as endangered in the registry.
     * This exists so risk logic can elevate alert levels for threatened flora.
     */
    public void registerEndangeredSpecies(String speciesName) {
        if (speciesName == null || speciesName.isBlank()) {
            throw new IllegalArgumentException("Species name cannot be blank.");
        }
        endangeredSpeciesRegistry.add(speciesName.trim().toLowerCase());
    }

    /**
     * Updates a batch viability reading by species name.
     * This exists to model periodic germination tests in a realistic workflow.
     */
    public boolean updateBatchViability(String speciesName, double newViabilityPercentage) {
        for (SeedBatch vaultBatch : vaultBatches) {
            // Object interaction: domain object mutates its own state through encapsulated setter.
            if (vaultBatch.getSpeciesName().equalsIgnoreCase(speciesName)) {
                vaultBatch.setViabilityPercentage(newViabilityPercentage);
                return true;
            }
        }
        return false;
    }

    /**
     * Builds a detailed storage report line-by-line for all batches.
     * This exists to provide audit-ready output without exposing internal collections.
     */
    public String buildStorageAuditReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("=== KAROO SEED VAULT AUDIT REPORT ===\n");

        for (SeedBatch vaultBatch : vaultBatches) {
            RiskLevel riskLevel = batchRiskPolicy.classifyRisk(vaultBatch, endangeredSpeciesRegistry);

            // Encapsulation + strategy: service reads state via getters and delegates risk logic.
            reportBuilder
                .append("Species: ").append(vaultBatch.getSpeciesName()).append("\n")
                .append("Collected: ").append(vaultBatch.getCollectionDate()).append("\n")
                .append("Viability: ").append(vaultBatch.getViabilityPercentage()).append("%\n")
                .append("Origin GPS: ").append(vaultBatch.getOriginCoordinate().format()).append("\n")
                .append("Storage Requirement: ").append(vaultBatch.getStorageTemperatureCelsius()).append(" C\n")
                .append("Risk Level: ").append(riskLevel).append("\n")
                .append("Is Above 70% Viable: ").append(vaultBatch.isViableAbove(70.0)).append("\n")
                .append("----------------------------------------\n");
        }

        reportBuilder.append("Total batches: ").append(vaultBatches.size()).append("\n");
        return reportBuilder.toString();
    }
}
