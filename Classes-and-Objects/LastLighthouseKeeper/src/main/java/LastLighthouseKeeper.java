import lastlighthousekeeper.Archivable;
import lastlighthousekeeper.Lighthouse;
import lastlighthousekeeper.ShipLogEntry;
import lastlighthousekeeper.WeatherReading;

/*
 * In 1987, Cape Solitude lighthouse was automated and nearly every keeper was dismissed,
 * but Elias Mokoena stayed behind as a voluntary caretaker on the stormy South African coast.
 * He recorded every passing ship, weather shift, and equipment failure in handwritten notebooks
 * over three decades of lonely service. Priya, a young coastal engineer, arrives with a strict
 * deadline: convert Elias's records into a structured digital system before the tower is demolished.
 * This program models their world using classes and objects so the knowledge survives the lighthouse.
 */
public class LastLighthouseKeeper {

    /**
     * Runs a narrated demonstration showing classes, objects, constructors,
     * encapsulation, and getters/setters in the lighthouse digitisation story.
     * This exists to give students a realistic end-to-end flow they can study.
     */
    public static void main(String[] args) {
        System.out.println("=== CAPE SOLITUDE DIGITAL ARCHIVE ===");
        System.out.println("Priya arrives with fourteen notebooks and one month before demolition.\n");

        // Object creation through constructors: each class is instantiated with story-specific data.
        Lighthouse capeSolitude = new Lighthouse("Cape Solitude", "South African South Coast", true, 30);
        ShipLogEntry cargoVesselLog = new ShipLogEntry(
            "1987-11-03",
            "MV Umzimkulu",
            "Medical Supplies",
            "Polished Fresnel lens after salt spray"
        );
        WeatherReading stormFrontReading = new WeatherReading(
            "1987-11-03",
            "Cold front with heavy swell",
            42.5,
            1.8
        );

        System.out.println("Initial lighthouse status:");
        System.out.println(capeSolitude.describeStatus() + "\n");

        // Encapsulation with setter usage: update private state only through controlled methods.
        if (stormFrontReading.getWindSpeedKnots() > 40.0) {
            capeSolitude.setBeaconOperational(false);
            cargoVesselLog.setMaintenanceActionTaken(
                "Replaced beacon fuse after lightning surge"
            );
        }

        // Getter usage: read object state safely to drive realistic story output.
        System.out.println("After storm evaluation:");
        System.out.println("Beacon operational? " + capeSolitude.isBeaconOperational());
        System.out.println("Recorded action: " + cargoVesselLog.getMaintenanceActionTaken() + "\n");

        // Polymorphism via interface contract: different entry objects are treated uniformly.
        Archivable[] priyaArchiveBatch = new Archivable[] { cargoVesselLog, stormFrontReading };

        System.out.println("Priya's digitised records:");
        for (Archivable archiveRecord : priyaArchiveBatch) {
            System.out.println("- " + archiveRecord.toArchiveLine());
        }

        // Setter and getter together: simulate one year passing and update historical state.
        capeSolitude.setYearsMaintainedByElias(capeSolitude.getYearsMaintainedByElias() + 1);
        System.out.println("\nOne year later, status report:");
        System.out.println(capeSolitude.describeStatus());
        System.out.println("\nPriya preserves Elias's knowledge before the lighthouse is lost.");
    }
}

/* ═══ STUDY NOTES ═══
Java concepts demonstrated:
1) class - Lighthouse, ShipLogEntry, and WeatherReading model real story entities.
2) object - main() creates objects that hold distinct state from Elias's records.
3) constructor - each object is initialized with complete, meaningful startup data.
4) encapsulation - fields are private and exposed safely through methods.
5) getter/setter - controlled read/write access protects and updates state.
6) abstract class - NotebookEntry shares common date behavior across record types.
7) interface - Archivable enforces a shared archive format for different objects.

Interview questions this code prepares you for:
1) Why are private fields important, and how do getters/setters support encapsulation?
2) When should you use an abstract class versus an interface in Java?
3) How do constructors help keep objects valid from the moment they are created?
4) How can polymorphism simplify processing mixed object types?
5) How does object-oriented design map software models to real-world workflows?

Common mistake and how this code avoids it:
- Mistake: exposing fields directly as public, which allows uncontrolled changes.
- Avoided by: keeping fields private and forcing updates through scenario-specific methods.
*/
