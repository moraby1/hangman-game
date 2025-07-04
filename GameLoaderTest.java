

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
/**
 *  * Hangman.java
 *  * CST338 HW01: Hangman Game
 *  * Author: Mahmoud Oraby
 *  * Date: July 2025
 *  *

 */
public class GameLoaderTest {

    @Test
    public void testWordLoading() {
        List<String> words = List.of("CIRCLE", "SQUARE", "CAKE");
        Hangman game = new Hangman(words);
        assertTrue(words.contains(game.getSecretWord()));
    }
}
