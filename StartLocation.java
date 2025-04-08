import java.util.Scanner;

/**
 * StartLocation represents the first location the player encounters in a level.
 * This class extends the abstract Location class and provides a basic setup
 * for the initial decision point—blending in or drawing attention.
 *
 * NOTE: Currently hardcoded for level 1. Will need to be adjusted to support
 * level-specific descriptions and behavior in the future.
 */

public class StartLocation extends Location {                                                       // Create a Location object using the StartLocation criteria
    @Override
    public void describe() {                                                                        // Print the location description
        System.out.println("You wake up in a dim alley. You hear voices and footsteps nearby.");
    }

    @Override
    public void interact(Player player) {                                                           // *** Hardcoded interaction for the first location in a level.
        player.visitLocation("L1-Start");                                                  // *** This will need to be modified for future levels
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to (1) change your appearance or (2) boldly walk out?");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.println("You blend in with the crowd.");
        } else if (choice.equals("2")) {
            System.out.println("Your clothes draw attention...");
            player.increaseSuspicion();
        } else {
            System.out.println("You hesitate, drawing even more attention...");
            player.increaseSuspicion();
        }
    }
}
