import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
/**
 *  *  * CST338 HW01: Hangman Game
 *  *  * Author: Mahmoud Oraby
 *  *  * Date: July 2025
 *  **/
public class HangmanTest {

    @Test
    public void testConstructorAndGetters() {
        Hangman h = new Hangman(List.of("FISH", "CAKE"));
        assertNotNull(h.getSecretWord());
        assertEquals(2, h.getAllWords().size());
    }

    @Test
    public void testGuessLetterCorrectly() {
        Hangman h = new Hangman(List.of("FISH"));
        assertTrue(h.guessLetter('F'));
        assertTrue(h.getGuessedWord().startsWith("F"));
    }

    @Test
    public void testGuessLetterWrong() {
        Hangman h = new Hangman(List.of("FISH"));
        int before = h.getRemainingGuesses();
        assertFalse(h.guessLetter('Z'));
        assertEquals(before - 1, h.getRemainingGuesses());
    }

    @Test
    public void testUseHint() {
        Hangman h = new Hangman(List.of("FISH"));
        int before = h.getRemainingHints();
        h.useHint();
        assertEquals(before - 1, h.getRemainingHints());
    }

    @Test
    public void testWinLose() {
        Hangman h = new Hangman(List.of("HI"));
        h.guessLetter('H');
        h.guessLetter('I');
        assertTrue(h.hasWon());

        Hangman l = new Hangman(List.of("HI"));
        l.guessLetter('A');
        l.guessLetter('B');
        l.guessLetter('C');
        l.guessLetter('D');
        l.guessLetter('E');
        assertTrue(l.hasLost());
    }
}
