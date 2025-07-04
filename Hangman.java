import java.util.*;

/**
 * CST338 HW01: Hangman Game
 *  Author: Mahmoud Oraby
 * Date: July 2025
 * Represents a game of Hangman.
 *
 */
public class Hangman {

    private final List<String> allWords;
    private final String secretWord;
    private final Set<Character> guessedLetters;
    private final StringBuilder guessedWord;
    private int remainingGuesses;
    private int remainingHints;

    public Hangman(List<String> words) {
        this.allWords = new ArrayList<>(words);
        this.secretWord = chooseWord(allWords).toUpperCase();
        this.guessedLetters = new HashSet<>();
        this.remainingGuesses = 5;
        this.remainingHints = 2;
        this.guessedWord = new StringBuilder("_".repeat(secretWord.length()));
    }

    private String chooseWord(List<String> words) {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    public boolean guessLetter(char letter) {
        letter = Character.toUpperCase(letter);
        if (guessedLetters.contains(letter)) return false;

        guessedLetters.add(letter);
        boolean found = false;

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedWord.setCharAt(i, letter);
                found = true;
            }
        }

        if (!found) remainingGuesses--;
        return found;
    }

    public void useHint() {
        if (remainingHints == 0) return;

        for (int i = 0; i < secretWord.length(); i++) {
            if (guessedWord.charAt(i) == '_') {
                char hint = secretWord.charAt(i);
                guessLetter(hint);
                remainingHints--;
                break;
            }
        }
    }

    public boolean hasWon() {
        return guessedWord.toString().equals(secretWord);
    }

    public boolean hasLost() {
        return remainingGuesses <= 0;
    }

    public String getGuessedWord() {
        return guessedWord.toString();
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public int getRemainingHints() {
        return remainingHints;
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public List<String> getAllWords() {
        return allWords;
    }

    public String getSecretWord() {
        return secretWord;
    }
}
