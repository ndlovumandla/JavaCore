/*
 * The Karoo Seed Bank
 * 
 * Deep in the arid Karoo desert of South Africa, Dr. Fatima Hendricks manages a vital seed
 * preservation vault containing hundreds of indigenous plant species threatened by climate
 * change. For eleven years, she has carefully recorded each seed batch by hand in a paper binder.
 * 
 * University intern Sipho has been tasked with digitizing this important work. This Java program
 * models each seed batch as an object, helping track viability, storage conditions, and generate
 * reports for the upcoming annual audit.
 */

public class KarooSeedBank {

    /**
     * Represents a single batch of seeds in Dr. Hendricks' preservation vault.
     * This class demonstrates encapsulation by protecting sensitive seed data.
     */
    static class SeedBatch {
        // Encapsulated fields (private) - this is the core of encapsulation
        private String speciesName;
        private String collectionDate;
        private double viabilityPercentage;
        private double gpsLatitude;
        private double gpsLongitude;
        private int storageTemperatureCelsius;

        /**
         * Constructor - Creates a new SeedBatch object with all required information
         * @param speciesName The scientific or common name of the plant species
         * @param collectionDate Date when seeds were collected (YYYY-MM-DD)
         * @param viabilityPercentage Percentage of seeds still viable (0-100)
         * @param gpsLatitude GPS latitude coordinate
         * @param gpsLongitude GPS longitude coordinate
         * @param storageTemperatureCelsius Required storage temperature in Celsius
         */
        public SeedBatch(String speciesName, String collectionDate, double viabilityPercentage,
                        double gpsLatitude, double gpsLongitude, int storageTemperatureCelsius) {
            // 'this' keyword refers to the current object's fields
            this.speciesName = speciesName;
            this.collectionDate = collectionDate;
            this.viabilityPercentage = viabilityPercentage;
            this.gpsLatitude = gpsLatitude;
            this.gpsLongitude = gpsLongitude;
            this.storageTemperatureCelsius = storageTemperatureCelsius;
        }

        // Getter methods - allow controlled read access to private fields
        public String getSpeciesName() {
            return speciesName;
        }

        public String getCollectionDate() {
            return collectionDate;
        }

        public double getViabilityPercentage() {
            return viabilityPercentage;
        }

        public double getGpsLatitude() {
            return gpsLatitude;
        }

        public double getGpsLongitude() {
            return gpsLongitude;
        }

        public int getStorageTemperatureCelsius() {
            return storageTemperatureCelsius;
        }

        // Setter methods - allow controlled updates to private fields
        public void setViabilityPercentage(double viabilityPercentage) {
            if (viabilityPercentage >= 0 && viabilityPercentage <= 100) {
                this.viabilityPercentage = viabilityPercentage;
            }
        }

        /**
         * Checks if this seed batch is still viable for planting
         * @return true if viability is above 70%
         */
        public boolean isViable() {
            // Simple business logic relevant to seed preservation
            return viabilityPercentage > 70.0;
        }

        /**
         * Determines if this species should be flagged as high priority
         * @return true if viability is critically low
         */
        public boolean isEndangeredBatch() {
            return viabilityPercentage < 40.0;
        }

        /**
         * Prints a detailed report for this seed batch
         */
        public void printStorageReport() {
            System.out.println("══════════════════════════════════════");
            System.out.println("SEED BATCH REPORT");
            System.out.println("Species: " + speciesName);
            System.out.println("Collected: " + collectionDate);
            System.out.println("Viability: " + viabilityPercentage + "%" );
            System.out.println("Location: " + gpsLatitude + "°S, " + gpsLongitude + "°E");
            System.out.println("Storage Temp: " + storageTemperatureCelsius + "°C");
            System.out.println("Status: " + (isViable() ? "VIABLE" : "LOW VIABILITY"));
            if (isEndangeredBatch()) {
                System.out.println("⚠️  HIGH PRIORITY - ENDANGERED BATCH");
            }
            System.out.println("══════════════════════════════════════\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== KAROO SEED BANK MANAGEMENT SYSTEM ===\n");

        // Creating objects (instances of the SeedBatch class)
        SeedBatch proteaBatch = new SeedBatch("Protea Cynaroides", "2023-05-12", 
                85.5, -32.5, 22.8, 8);
        
        SeedBatch baobabBatch = new SeedBatch("Adansonia Digitata", "2021-11-03", 
                35.2, -24.1, 30.4, 12);
        
        SeedBatch spekboomBatch = new SeedBatch("Portulacaria Afra", "2024-02-28", 
                92.8, -33.9, 25.6, 10);

        // Demonstrating usage
        proteaBatch.printStorageReport();
        baobabBatch.printStorageReport();
        spekboomBatch.printStorageReport();

        // Example of using setter
        System.out.println("Updating Baobab batch viability after testing...");
        baobabBatch.setViabilityPercentage(68.5);
        baobabBatch.printStorageReport();

        System.out.println("Audit complete. Dr. Hendricks' seed vault is now digitized.");
    }

}

/*
 * ═══ STUDY NOTES ═══
 * 
 * Java Concepts Demonstrated:
 * - Class: Blueprint for creating objects (SeedBatch defines what a seed batch contains)
 * - Object: Instance of a class (proteaBatch, baobabBatch are real objects in memory)
 * - Constructor: Special method that initializes new objects with proper values
 * - Encapsulation: Private fields + public getters/setters to protect data
 * - Getter/Setter: Controlled access to private fields
 * 
 * Interview Questions this prepares you for:
 * 1. What is encapsulation and why is it important in OOP?
 * 2. Explain the difference between a class and an object with examples.
 * 3. What is a constructor and when is it called?
 * 4. Why do we use private fields with public getters and setters?
 * 5. How would you modify this program to add inheritance?
 * 
 * Common Mistake & How We Avoided It:
 * Mistake: Making all fields public (breaks encapsulation)
 * How we avoided it: All data fields are private, accessed only through methods.
 */