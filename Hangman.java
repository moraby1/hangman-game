// Hangman.java
// Author: Mahmoud Oraby
// Description: Fully working and testable Hangman implementation as per CSUMB CST338 assignment

import java.io.*;
import java.util.*;

public class Hangman {
    private List<String> words;
    private String currentWord;
    private char[] guessedWord;
    private List<Character> guessedLetters;
    private int guessesLeft;
    private int hintsLeft;
    private int score;

    // Default constructor
    public Hangman() {
        this("testWords.txt");
    }

    // Overloaded constructor for testing with custom file
    public Hangman(String fileName) {
        words = new ArrayList<>();
        guessedLetters = new ArrayList<>();
        guessesLeft = 6;
        hintsLeft = 3;
        score = 0;
        loadWords(fileName);
        chooseWord();
    }

    // Load words from the file
    public void loadWords(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word.trim().toUpperCase());
                System.out.println("adding: " + word.trim().toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("File not found. Using default words.");
            words = Arrays.asList("SQUARE", "CIRCLE", "FISH", "CAKE");
        }
    }

    // Choose a word randomly
    public void chooseWord() {
        Random rand = new Random();
        currentWord = words.get(rand.nextInt(words.size()));
        guessedWord = new char[currentWord.length()];
        Arrays.fill(guessedWord, '_');
        guessedLetters.clear();
        guessesLeft = 6;
        hintsLeft = 3;
    }

    // Play a letter guess
    public boolean playLetter(char letter) {
        letter = Character.toUpperCase(letter);
        if (guessedLetters.contains(letter)) return false;

        guessedLetters.add(letter);
        boolean correct = false;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == letter) {
                guessedWord[i] = letter;
                correct = true;
            }
        }
        if (!correct) guessesLeft--;
        return correct;
    }

    // Provide a hint by revealing a letter
    public void giveHint() {
        if (hintsLeft <= 0) return;
        for (int i = 0; i < currentWord.length(); i++) {
            if (guessedWord[i] == '_') {
                playLetter(currentWord.charAt(i));
                hintsLeft--;
                break;
            }
        }
    }

    // Check if player won
    public boolean isWin() {
        for (char c : guessedWord) {
            if (c == '_') return false;
        }
        return true;
    }

    // Check if player lost
    public boolean isLose() {
        return guessesLeft <= 0;
    }

    // Update score if won
    public void updateScore() {
        if (isWin()) score++;
    }

    // Get current guessed word as string
    public String getGuessedWord() {
        return new String(guessedWord);
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }

    public int getHintsLeft() {
        return hintsLeft;
    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public int getScore() {
        return score;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    // Return all words (for testing)
    public List<String> getAllWords() {
        return words;
    }
}
