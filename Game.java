import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Game {
    private Player player;              // Player object tracks player's stats
    private TED ted;                    // Ted object used for level guesses
    private Scanner scanner;            // User input
    private int currentLevelIndex = 0;  // Track current Level
    private List<Level> levels;         // List of all levels, containing correct time and locations

    public Game() {                         // Game Constructor
        player = new Player();              // Create player object
        ted = new TED();                    // Create ted object
        scanner = new Scanner(System.in);   // Create scanner object
        levels = createLevels();            // *** will create levels ***
    }

    public void start() {                                                       // Start the Game
        while (currentLevelIndex < levels.size()) {                             // Make sure the current level exists in the list of levels
            Level level = levels.get(currentLevelIndex);                        // *** Will set the current level using a getter ***
            player.setWon(false); // Reset win flag                             // Sets the win condition to false on the player object
            Location currentLocation = new StartLocation();                     // Sets the current location to the Starting level location

            while (!player.isCaught() && !player.hasWon()) {                    // Checking that the player has not been caught or won the game
                currentLocation.describe();                                     // Prints the current location description
                currentLocation.interact(player);                               // Provides interaction options

                System.out.println("Use TED to make a guess? (yes/no)");        // Provides option to use TED
                if (scanner.nextLine().equalsIgnoreCase("yes")) {   // If user chooses yes
                    ted.activate(player, scanner, level);                       // Activate TED and check the player's guess against the current level's correct answer
                }

                currentLocation = chooseNextLocation(scanner, level.getLocations());           // Provide location options to user
            }

            if (player.isCaught()) {                                       // Check to see if player has been caught
                System.out.println("You've been caught. Game over.");       // Print game over message
            } else {                                                // Otherwise, the user was correct and will move to the next level
                System.out.println("You completed all levels!");    // Print level winning message.
            }
        }
    }

    // This method temporarily builds a level with just the Waitress and Bard in the Tavern
    private List<Level> createLevels() {
        // Create character interaction
        // *** Add Level Characters Dialogue Here ***
        List<DialogueOption> waitressDialogues = new ArrayList<>();
        waitressDialogues.add(new DialogueOption("What are today's specials?", true, "Roasted boar with honeyed dates!"));
        waitressDialogues.add(new DialogueOption("Do you know the mayor?", true, "Of course! He’s planning a parade tomorrow."));
        waitressDialogues.add(new DialogueOption("Why is your food so bad?", false, "Excuse me? That’s extremely rude."));

        List<DialogueOption> bardDialogues = new ArrayList<>();
        bardDialogues.add(new DialogueOption("What's your favorite tune?", true, "Hail to the Emperor of course, like we have a choice these days."));
        bardDialogues.add(new DialogueOption("Have you ever played for anyone famous?", true, "Good old Julius came through a while ago. I was very nervous."));
        bardDialogues.add(new DialogueOption("Can you play Messy by Lola?", false, "What kind of song is that, who is Lola"));

        // Create Characters Objects
        // *** Add Level Characters Here ***
        CharacterInteraction waitress = new CharacterInteraction(
                "Waitress",
                "\"Welcome to the tavern. What can I do for you?\"",
                waitressDialogues
        );

        CharacterInteraction bard = new CharacterInteraction(
                "Bard",
                "\"Do you have a song request my friend?\"",
                bardDialogues
        );

        // Create tavern location
        // *** Add Level Characters Here ***
        List<Interactable> tavernItems = new ArrayList<>();
        tavernItems.add(waitress);
        tavernItems.add(bard);

        Location tavern = new SimpleLocation(
                "Tavern",
                "You enter a cozy tavern with the smell of stew and the sound of mugs clinking.",
                tavernItems
        );

        // Build level 1
        List<Location> level1Locations = new ArrayList<>();
        level1Locations.add(new StartLocation());
        level1Locations.add(tavern);

        Level level1 = new Level("44 BC", "Rome", level1Locations);

        return List.of(level1);  // Return just this level for now
    }
    private Location chooseNextLocation(Scanner scanner, List<Location> availableLocations) {
        System.out.println("\nChoose your next location:");

        for (int i = 0; i < availableLocations.size(); i++) {
            System.out.println((i + 1) + ". " + availableLocations.get(i).getClass().getSimpleName());
        }

        while (true) {
            System.out.print("Enter the number of your choice: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= availableLocations.size()) {
                    return availableLocations.get(choice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + availableLocations.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}