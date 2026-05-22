import karooseedbank.BatchRiskPolicy;
import karooseedbank.DefaultBatchRiskPolicy;
import karooseedbank.GeoCoordinate;
import karooseedbank.SeedBatch;
import karooseedbank.SeedVaultService;

/*
 * Deep in the Karoo desert, Dr. Fatima Hendricks protects indigenous South African flora
 * by maintaining a seed vault against the pressure of climate change. For eleven years,
 * she has tracked each seed batch in a paper binder, recording species, collection dates,
 * viability scores, origin coordinates, and storage requirements. University intern Sipho
 * must replace that manual process with a reliable Java system before the annual audit.
 * This program models each batch as an object and automates risk checks and storage reporting.
 */
public class KarooSeedBank {

    /**
     * Runs a realistic audit-week simulation for the Karoo seed vault.
     * This exists to demonstrate advanced classes-and-objects design in a real-world flow.
     */
    public static void main(String[] args) {
        System.out.println("=== KAROO SEED BANK DIGITISATION SYSTEM ===");
        System.out.println("Sipho starts digitising Dr. Hendricks's binder before audit week.\n");

        // Strategy pattern setup: policy behavior is injected, not hard-coded inside data objects.
        BatchRiskPolicy auditRiskPolicy = new DefaultBatchRiskPolicy(40.0, 70.0);
        SeedVaultService vaultService = new SeedVaultService(auditRiskPolicy);

        // Domain setup: endangered registry influences policy decisions across all objects.
        vaultService.registerEndangeredSpecies("Aloe dichotoma");
        vaultService.registerEndangeredSpecies("Encephalartos woodii");

        // Object construction: each seed batch is a rich domain object with validated state.
        SeedBatch quiverTreeBatch = new SeedBatch(
            "Aloe dichotoma",
            "2021-04-18",
            62.5,
            new GeoCoordinate(-28.7431, 21.1278),
            6.0
        );

        SeedBatch kingProteaBatch = new SeedBatch(
            "Protea cynaroides",
            "2023-09-02",
            88.3,
            new GeoCoordinate(-33.9583, 18.4756),
            8.0
        );

        SeedBatch woodCycadBatch = new SeedBatch(
            "Encephalartos woodii",
            "2019-12-11",
            39.5,
            new GeoCoordinate(-29.8568, 31.0218),
            7.0
        );

        // Service orchestration: objects interact through a dedicated application service.
        vaultService.registerSeedBatch(quiverTreeBatch);
        vaultService.registerSeedBatch(kingProteaBatch);
        vaultService.registerSeedBatch(woodCycadBatch);

        // Edge-case handling: demonstrate missing species update pathway with safe boolean result.
        boolean updatedMissingSpecies = vaultService.updateBatchViability("Sceletium tortuosum", 77.0);
        System.out.println("Missing species update successful? " + updatedMissingSpecies);

        // Encapsulated mutation: update existing object's viability through controlled setter pathway.
        boolean updatedQuiverTree = vaultService.updateBatchViability("Aloe dichotoma", 58.0);
        System.out.println("Quiver tree viability update successful? " + updatedQuiverTree + "\n");

        // Reporting: service aggregates object data and policy output into audit-ready text.
        System.out.println(vaultService.buildStorageAuditReport());
        System.out.println("Sipho submits the digital report before the annual inspection deadline.");
    }
}

/* ═══ STUDY NOTES ═══
Java concepts demonstrated:
1) class - Each real-world concept (seed batch, coordinate, vault service) is modeled as a class.
2) object - main() creates multiple SeedBatch and GeoCoordinate objects with distinct state.
3) constructor - Constructors enforce complete, valid object initialization from day one.
4) encapsulation - Fields are private and only exposed through guarded getters/setters.
5) getter/setter - Controlled data access and updates prevent invalid state during audits.
6) strategy pattern - BatchRiskPolicy lets rule logic vary without rewriting core models.
7) enum usage - RiskLevel standardizes report categories and avoids fragile string literals.

Interview questions this code prepares you to answer:
1) How do constructors and validation work together to protect object invariants?
2) Why is encapsulation important when modeling scientific or regulated data?
3) When would you choose the Strategy pattern in a domain service?
4) How do services coordinate multiple objects while keeping classes focused?
5) How do you handle edge cases without breaking program flow?

Common mistake and how this code avoids it:
- Mistake: placing all business logic inside one large class with public mutable fields.
- Avoided by: separating responsibilities into domain objects, a policy strategy, and a service layer.
*/
