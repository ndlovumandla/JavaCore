package karooseedbank;

import java.util.Set;

/**
 * Real-world role: default audit policy used by Sipho for the annual review.
 * It combines viability and endangered-species status to determine urgency.
 */
public class DefaultBatchRiskPolicy implements BatchRiskPolicy {
    private final double criticalViabilityThreshold;
    private final double watchlistViabilityThreshold;

    /**
     * Creates a policy with configured threshold values.
     * This exists so Dr. Hendricks can tune policy rules without rewriting logic.
     */
    public DefaultBatchRiskPolicy(double criticalViabilityThreshold, double watchlistViabilityThreshold) {
        if (criticalViabilityThreshold < 0 || watchlistViabilityThreshold > 100
            || criticalViabilityThreshold > watchlistViabilityThreshold) {
            throw new IllegalArgumentException("Invalid threshold configuration.");
        }

        this.criticalViabilityThreshold = criticalViabilityThreshold;
        this.watchlistViabilityThreshold = watchlistViabilityThreshold;
    }

    /**
     * Evaluates risk level for a seed batch.
     * This exists to centralize decision logic used in reports and alerts.
     */
    @Override
    public RiskLevel classifyRisk(SeedBatch seedBatch, Set<String> endangeredSpeciesRegistry) {
        double viability = seedBatch.getViabilityPercentage();
        boolean speciesIsEndangered = endangeredSpeciesRegistry.contains(seedBatch.getSpeciesName().toLowerCase());

        // Strategy logic: combine multiple business conditions into one reusable policy decision.
        if (viability <= criticalViabilityThreshold || (speciesIsEndangered && viability < watchlistViabilityThreshold)) {
            return RiskLevel.CRITICAL;
        }

        if (viability < watchlistViabilityThreshold || speciesIsEndangered) {
            return RiskLevel.WATCHLIST;
        }

        return RiskLevel.STABLE;
    }
}
