import java.util.Scanner;
import java.util.List;

/**
 * SimpleLocation represents a basic game location that the player can visit during a level.
 * This class extends the abstract Location class and provides a framework for displaying a
 * description and (eventually) offering interaction choices like talking to characters
 * or examining objects.
 */

public class SimpleLocation extends Location {
    private String name;
    private String description;
    private List<Interactable> interactables;

    public SimpleLocation(String name, String description, List<Interactable> interactables) {  // This constructor initializes location
        this.name = name;                                                                       // With the location name
        this.description = description;                                                         // And the description
        this.interactables = interactables;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    /**
    * Interact needs more development. It will provide the user with choices on whom or what to interact with.
    */

    @Override
    public void interact(Player player) {
        player.visitLocation(name);
        Scanner scanner = new Scanner(System.in);
        boolean stayHere = true;

        while (stayHere) {
            System.out.println("\nYou see the following options:");

            for (int i = 0; i < interactables.size(); i++) {
                System.out.println((i + 1) + ". " + interactables.get(i).getName());
            }
            System.out.println((interactables.size() + 1) + ". Leave this location");

            System.out.print("What would you like to do? ");
            String input = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (choice == interactables.size() + 1) {
                System.out.println("You leave the " + name + ".");
                stayHere = false;
            } else if (choice > 0 && choice <= interactables.size()) {
                Interactable selected = interactables.get(choice - 1);
                selected.interact(player);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}