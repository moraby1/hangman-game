// HangmanTest.java
// Author: Mahmoud Oraby
// Description: Unit tests for Hangman.java

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class HangmanTest {

    @Test
    public void constructorTest() {
        Hangman game = new Hangman();
        assertNotNull(game.getGuessedWord());
        assertEquals(6, game.getGuessesLeft());
        assertEquals(3, game.getHintsLeft());
    }

    @Test
    public void hintTest() {
        Hangman game = new Hangman("testWords.txt");
        int hintsBefore = game.getHintsLeft();
        game.giveHint();
        assertEquals(hintsBefore - 1, game.getHintsLeft());
    }

    @Test
    public void testGetAllWords() {
        Hangman game = new Hangman("testWords.txt");
        List<String> words = game.getAllWords();
        assertTrue(words.contains("FISH"));
    }

    @Test
    public void checkChooseWord() {
        Hangman game = new Hangman("testWords.txt");
        String word = game.getCurrentWord();
        assertNotNull(word);
        assertTrue(game.getAllWords().contains(word));
    }

    @Test
    public void checkLose() {
        Hangman game = new Hangman("testWords.txt");

        // Use 6 incorrect guesses (non-overlapping, not in any word)
        char[] wrongGuesses = {'X', 'Y', 'Z', 'Q', 'V', 'B'};
        int wrongs = 0;
        for (char c : wrongGuesses) {
            if (!game.getCurrentWord().contains(Character.toString(c))) {
                game.playLetter(c);
                wrongs++;
            }
            if (wrongs == 6) break;
        }

        // Retry with alternative letters if needed to ensure 6 wrong guesses
        int backup = 0;
        while (game.getGuessesLeft() > 0 && backup < 26) {
            char c = (char) ('A' + backup);
            if (!game.getCurrentWord().contains(Character.toString(c))
                    && !game.getGuessedLetters().contains(c)) {
                game.playLetter(c);
            }
            backup++;
        }

        assertTrue("Expected game to be lost (0 guesses left), but it wasn't.", game.isLose());
    }


    @Test
    public void checkPlay() {
        Hangman game = new Hangman("testWords.txt");
        char firstLetter = game.getCurrentWord().charAt(0);
        boolean result = game.playLetter(firstLetter);
        assertTrue(result);
        assertTrue(game.getGuessedWord().contains(Character.toString(firstLetter)));
    }

    @Test
    public void checkWin() {
        Hangman game = new Hangman("testWords.txt");
        for (char c : game.getCurrentWord().toCharArray()) {
            game.playLetter(c);
        }
        assertTrue(game.isWin());
    }

    @Test
    public void testDisplayGameState() {
        Hangman game = new Hangman("testWords.txt");
        assertNotNull(game.getGuessedWord());
        assertTrue(game.getGuessedWord().contains("_"));
    }

    @Test
    public void readFileTest() {
        Hangman game = new Hangman("testWords.txt");
        assertFalse(game.getAllWords().isEmpty());
    }
}
