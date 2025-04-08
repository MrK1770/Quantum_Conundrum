/**
 * Abstract superclass for all locations in the game.
 * Subclasses:
 * - StartLocation
 * - SimpleLocation
 */
public abstract class Location {
    public abstract void describe();                // Print the description of the current location
    public abstract void interact(Player player);   // Provide the user with options
}
