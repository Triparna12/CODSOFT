import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random r = new Random();

        int minNumber = 1;
        int maxNumber = 100;
        int maxAttempts = 10;
        int rounds = 0;
        int totalScore = 0;

        System.out.println("Welcome to the number guessing game : ");

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = r.nextInt(maxNumber - minNumber + 1) + minNumber;
            int attempts = 0;
            int score = 0;
            boolean guessedCorrectly = false;

            System.out.println("Selected number between " + minNumber + " and " + maxNumber);

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guessed number : ");
                int userGuessNumber = scanner.nextInt();
                attempts++;

                if (userGuessNumber < targetNumber) {
                    System.out.println("It's Too low! Please try again.");
                } else if (userGuessNumber > targetNumber) {
                    System.out.println("It's Too high! Please try again.");
                } else {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score = maxAttempts - attempts + 1;
                    guessedCorrectly = true;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
            }
            totalScore += score;
            rounds++;

            System.out.print("Do you want to play this number guessing game? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
        }

        System.out.println("Game over! You played " + rounds + " rounds with a total score of :  " + totalScore);
    }
}
