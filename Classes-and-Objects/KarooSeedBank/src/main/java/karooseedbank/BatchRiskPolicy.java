package karooseedbank;

import java.util.Set;

/**
 * Real-world role: strategy contract for classifying a seed batch's audit risk.
 * Sipho can swap policy rules without changing vault data classes.
 */
public interface BatchRiskPolicy {

    /**
     * Classifies the risk level for a batch.
     * This exists so audit logic can evolve independently from data objects.
     */
    RiskLevel classifyRisk(SeedBatch seedBatch, Set<String> endangeredSpeciesRegistry);
}
