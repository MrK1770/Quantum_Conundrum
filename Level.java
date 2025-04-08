import java.util.List;

/** Creates the current level
 * Stores correct Year and place
 * Stores all available locations
 */

public class Level {
    private String correctYear;
    private String correctPlace;
    private List<Location> locations;

    public Level(String correctYear, String correctPlace, List<Location> locations) {
        this.correctYear = correctYear;
        this.correctPlace = correctPlace;
        this.locations = locations;
    }

    public boolean isCorrectGuess(String year, String place) {
        return year.equals(correctYear) && place.equalsIgnoreCase(correctPlace);
    }

    public List<Location> getLocations() {
        return locations;
    }
}