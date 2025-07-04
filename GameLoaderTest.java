// GameLoaderTest.java
// Author: Mahmoud Oraby
// Description: Basic tests for GameLoader logic (integration with Hangman)

import org.junit.Test;

import static org.junit.Assert.*;

public class GameLoaderTest {

    @Test
    public void fileCreated() {
        Hangman game = new Hangman("testWords.txt");
        assertFalse(game.getAllWords().isEmpty());
    }

    @Test
    public void gameLoaderTest() {
        Hangman game = new Hangman("testWords.txt");
        assertNotNull(game.getGuessedWord());
        assertNotNull(game.getGuessedLetters());
    }

    @Test
    public void gameWinTest() {
        Hangman game = new Hangman("testWords.txt");
        for (char c : game.getCurrentWord().toCharArray()) {
            game.playLetter(c);
        }
        assertTrue(game.isWin());
    }
}
