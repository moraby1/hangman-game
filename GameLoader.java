// GameLoader.java
// Author: Mahmoud Oraby
// Description: Manages interaction between user and Hangman game logic

import java.util.Scanner;

public class GameLoader {
    public static void main(String[] args) {
        Hangman game = new Hangman();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");

        while (true) {
            System.out.println("Current score: " + game.getScore());
            System.out.println("Guessed Word: " + game.getGuessedWord());
            System.out.println("Remaining Guesses: " + game.getGuessesLeft());
            System.out.println("Remaining hints: " + game.getHintsLeft());
            System.out.println("Guessed Letters: " + game.getGuessedLetters());

            System.out.println("Your options are:\n\t1: Guess a letter\n\t2: Get a hint\n\t3: exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("What is your guess: ");
                char guess = scanner.next().charAt(0);
                boolean correct = game.playLetter(guess);
                if (correct) {
                    System.out.println(guess + " was present!");
                } else {
                    System.out.println(guess + " was not present!");
                }
            } else if (choice == 2) {
                game.giveHint();
            } else if (choice == 3) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (game.isWin()) {
                game.updateScore();
                System.out.println("You won! Final word was: " + game.getCurrentWord());
                game.chooseWord();
            } else if (game.isLose()) {
                System.out.println("You lost! Final word was: " + game.getCurrentWord());
                game.chooseWord();
            }
        }

        scanner.close();
    }
}
