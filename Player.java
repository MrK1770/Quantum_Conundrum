
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class Player {                                               // Player constructor
    private int suspicion = 0;                                      // Set suspicion meter to 0
    private boolean caught = false;                                 // Set caught condition to false
    private boolean won = false;                                    // Set win condition to false
    private Set<String> visitedLocations = new HashSet<>();         // HashSet that keeps track of visited locations
    private Set<String> actionsTaken = new HashSet<>();             // HashSet that keeps track of conversation
    private ArrayList<String> collectedItems = new ArrayList<>();   // ArrayList of collected items

    public void increaseSuspicion() {                               // Increases Suspicion Meter
        suspicion++;                                                // Increase suspicion variable by 1
        System.out.println("Suspicion increased to: " + suspicion); // Inform user of their current suspicion level
        if (suspicion >= 5) {                                       // If suspicion level is greater than 5, then player is caught
            caught = true;                                          // Change caught from false to true
        }
    }

    public void visitLocation(String locationName) {                                            // Check if player has been to a location already
        if (visitedLocations.contains(locationName)) {                                          // If the location is already in the location HashSet
            System.out.println("The townspeople are getting suspicious of your wandering...");  // Warn player they are acting strangely, returning to the same location
            increaseSuspicion();                                                                // Increase suspicion meter
        } else {                                                                                // Otherwise
            visitedLocations.add(locationName);                                                 // Add the location to the HashSet
        }
    }

    public boolean hasRepeated(String actionId) {                                           // Check if player has already taken an action (primarily conversation)
        if (actionsTaken.contains(actionId)) {                                              // If the action is in the actionsTaken HashSet
            System.out.println("Haven't we already had this conversation? Are you ok?");    // Warn player they are having repetitive conversations
            increaseSuspicion();                                                            // Increase suspicion meter
            return true;                                                                    // This action has been taken
        } else {                                                                            // Otherwise
            actionsTaken.add(actionId);                                                     // Add the action ID to the list (ID codes to come later)
            return false;                                                                   // This action has not been taken
        }
    }

    public void collectItem(String itemName) {                      // Add collectable items to the player's inventory
        if (!collectedItems.contains(itemName)) {                   // If the item is not in the ArrayList
            collectedItems.add(itemName);                           // Add it to the list
            System.out.println("Collected item: " + itemName);      // Inform player that the item has been collected
        }
    }

    public boolean hasAllItems(List<String> allItems) {             // Check if the player has collected all items
        return collectedItems.containsAll(allItems);                // Returns true if the player has collect all items.
    }

    public boolean isCaught() { return caught; }                    // Returns true if player has bas been caught
    public boolean hasWon() { return won; }                         // Returns true if player has won
    public void setWon(boolean value) { won = value; }              // Setter for changing the players won value

}
