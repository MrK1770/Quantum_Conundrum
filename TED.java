import java.util.Scanner;
/**
 *  Use TED to check if user had guessed the write time and place.
 *
 */

public class TED {
    public void activate(Player player, Scanner scanner, Level level) {
        System.out.println("Enter your guess for the year:");
        String year = scanner.nextLine();
        System.out.println("Enter your guess for the place:");
        String place = scanner.nextLine();

        if (level.isCorrectGuess(year, place)) {
            System.out.println("Correct! You're advancing to the next level.");
            player.setWon(true);
        } else {
            System.out.println("Incorrect guess. The device draws attention...");
            player.increaseSuspicion();
        }
    }
}
