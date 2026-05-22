/*
 * The Soweto Chess Club
 * 
 * Every Saturday morning in a cramped community hall in Soweto, retired mathematics teacher 
 * Mr. Dlamini runs a free chess club for children aged 8 to 17. He has been managing everything 
 * manually with handwritten registers and torn scoresheets for years.
 * 
 * After receiving a donated laptop, his granddaughter Zanele, a first-year computer science 
 * student, builds this Java program to help track players, their ratings, match results, 
 * and generate weekly reports for the club.
 */

public class SowetoChessClub {

    /**
     * Represents a young chess player in Mr. Dlamini's Soweto Chess Club.
     * This class uses encapsulation to protect player data.
     */
    static class ChessPlayer {
        private String playerName;
        private int age;
        private int currentRating;
        private int wins;
        private int losses;
        private int draws;

        /**
         * Constructor for creating a new ChessPlayer
         */
        public ChessPlayer(String playerName, int age, int currentRating) {
            this.playerName = playerName;
            this.age = age;
            this.currentRating = currentRating;
            this.wins = 0;
            this.losses = 0;
            this.draws = 0;
        }

        // Getters
        public String getPlayerName() { return playerName; }
        public int getAge() { return age; }
        public int getCurrentRating() { return currentRating; }
        public int getWins() { return wins; }
        public int getLosses() { return losses; }
        public int getDraws() { return draws; }

        // Setters with validation
        public void setCurrentRating(int newRating) {
            if (newRating > 0) {
                this.currentRating = newRating;
            }
        }

        /**
         * Records a match result for this player
         */
        public void recordMatchResult(String result) {
            if (result.equalsIgnoreCase("win")) {
                wins++;
                currentRating += 15;  // Simple rating adjustment
            } else if (result.equalsIgnoreCase("loss")) {
                losses++;
                currentRating = Math.max(100, currentRating - 10);
            } else if (result.equalsIgnoreCase("draw")) {
                draws++;
                currentRating += 5;
            }
        }

        /**
         * Prints player profile and performance summary
         */
        public void printPlayerProfile() {
            System.out.println("══════════════════════════════════════");
            System.out.println("PLAYER PROFILE");
            System.out.println("Name: " + playerName);
            System.out.println("Age: " + age);
            System.out.println("Rating: " + currentRating);
            System.out.println("Record: " + wins + "W - " + losses + "L - " + draws + "D");
            System.out.println("══════════════════════════════════════\n");
        }
    }

    /**
     * Represents the Chess Club managed by Mr. Dlamini
     */
    static class ChessClub {
        private String clubName;
        private java.util.List<ChessPlayer> members;

        public ChessClub(String clubName) {
            this.clubName = clubName;
            this.members = new java.util.ArrayList<>();
        }

        public void addMember(ChessPlayer player) {
            members.add(player);
        }

        public void printClubRoster() {
            System.out.println("=== " + clubName.toUpperCase() + " ROSTER ===");
            for (ChessPlayer p : members) {
                System.out.println("- " + p.getPlayerName() + " (Rating: " + p.getCurrentRating() + ")");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SOWETO CHESS CLUB MANAGEMENT SYSTEM ===\n");

        ChessClub sowetoClub = new ChessClub("Soweto Saturday Chess Club");

        // Creating player objects
        ChessPlayer thabo = new ChessPlayer("Thabo Mthembu", 12, 850);
        ChessPlayer ayanda = new ChessPlayer("Ayanda Nkosi", 14, 920);
        ChessPlayer zinhle = new ChessPlayer("Zinhle Dlamini", 11, 780);

        sowetoClub.addMember(thabo);
        sowetoClub.addMember(ayanda);
        sowetoClub.addMember(zinhle);

        sowetoClub.printClubRoster();

        thabo.printPlayerProfile();
        ayanda.printPlayerProfile();

        System.out.println("Saturday matches completed...");
        thabo.recordMatchResult("win");
        ayanda.recordMatchResult("draw");
        zinhle.recordMatchResult("loss");

        System.out.println("Updated ratings after matches:\n");
        thabo.printPlayerProfile();
        ayanda.printPlayerProfile();
        zinhle.printPlayerProfile();

        System.out.println("Zanele has successfully digitized the chess club records!");
    }

}

/*
 * ═══ STUDY NOTES ═══
 * 
 * Java Concepts Demonstrated:
 * - Class: Blueprint for ChessPlayer and ChessClub
 * - Object: Instances like thabo, ayanda, sowetoClub
 * - Constructor: Initializes player and club objects
 * - Encapsulation: Private fields with public getters/setters
 * - Getter/Setter: Controlled access to player data
 * 
 * Interview Questions:
 * 1. What is encapsulation and how is it implemented here?
 * 2. Explain the difference between class and object.
 * 3. Why use private fields with getters and setters?
 * 4. How does this code demonstrate real-world modeling?
 * 5. How would you add inheritance to this system (e.g. TournamentPlayer)?
 * 
 * Common Mistake: Exposing fields directly (public int rating). 
 * Avoided by using private fields + controlled methods.
 */