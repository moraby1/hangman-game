import java.io.*;
import java.util.*;

/**
 *  * CST338 HW01: Hangman Game
 *  * Author: Mahmoud Oraby
 *  * Date: July 2025
 *  *
 *  * Description:
 *  * This class represents the logic of a Hangman game including guessing letters,
 *  * checking for win/loss, and providing hints.
 * GameLoader loads word list and runs the Hangman game loop.
 */
public class GameLoader {

    public static void main(String[] args) {
        List<String> words = loadWordsFromFile("testWords.txt");

        if (words.isEmpty()) {
            System.out.println("Word list is empty or missing.");
            return;
        }

        Hangman game = new Hangman(words);
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("Welcome to Hangman!");

        while (!game.hasWon() && !game.hasLost()) {
            System.out.println("\nGuessed Word: " + game.getGuessedWord());
            System.out.println("Remaining Guesses: " + game.getRemainingGuesses());
            System.out.println("Remaining Hints: " + game.getRemainingHints());
            System.out.println("Guessed Letters: " + game.getGuessedLetters());

            System.out.println("\nChoose an option:");
            System.out.println("1: Guess a letter");
            if (game.getRemainingHints() > 0) {
                System.out.println("2: Use a hint");
            }
            System.out.println("3: Exit");

            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {
                    System.out.print("Your guess: ");
                    char letter = scanner.nextLine().trim().toUpperCase().charAt(0);
                    boolean correct = game.guessLetter(letter);
                    System.out.println(letter + (correct ? " was present!" : " was not present!"));
                    if (correct) score++;
                }
                case "2" -> {
                    if (game.getRemainingHints() > 0) {
                        game.useHint();
                        System.out.println("Hint used!");
                    } else {
                        System.out.println("No hints remaining.");
                    }
                }
                case "3" -> {
                    System.out.println("Thanks for playing!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        if (game.hasWon()) {
            System.out.println("\n🎉 You won! The word was: " + game.getSecretWord());
        } else {
            System.out.println("\n💀 You lost. The word was: " + game.getSecretWord());
        }

        System.out.println("Final score: " + score);
    }

    private static List<String> loadWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
        return words;
    }
}
