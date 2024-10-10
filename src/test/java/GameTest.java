import model.Card;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.LLNode;

import java.util.List;

/**
 * Test for game handler
 * @author Katherine Chen
 */
public class GameTest {
    // game handler instance
    private Game game;

    // setup before each test
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    /**
     * Test game initialize
     */
    @Test
    public void testInit() {
        Assertions.assertEquals(40, Game.allCards.size());
        Assertions.assertEquals(20, game.getCurrCardNum());
        LLNode<Card> head = game.getHead();
        int cnt = 0;
        while (head != null) {
            head = head.getNext();
            cnt++;
        }
        Assertions.assertEquals(20, cnt);
        Assertions.assertFalse(game.isGameOver());
        Assertions.assertEquals("Player 1", game.getCurrentPlayer().getName());
    }

    /**
     * Test simple play
     */
    @Test
    public void testPlay() {
        LLNode<Card> next = game.getHead().getNext();
        List<Player> playerList = game.getPlayerList();
        Assertions.assertNull(game.getWinPlayer());
        game.executeAction("Take First Card");
        Assertions.assertEquals(19, game.getCurrCardNum());
        Assertions.assertEquals(next, game.getHead());
        Assertions.assertNotNull(game.getWinPlayer());
        Assertions.assertEquals(playerList.get(1), game.getCurrentPlayer());

        for (int i = 0; i < 19; i++) {
            game.executeAction("Take First Card");
        }
        Assertions.assertTrue(game.isGameOver());
    }

    /**
     * Test execute action multiple times for a player which can only be played once
     */
    @Test
    public void testDuplicateExecuteOnceAction() {
        game.executeAction("Move Lead Back 4");
        game.executeAction("Move Lead Back 4");
        Assertions.assertThrows(IllegalStateException.class, () -> game.executeAction("Move Lead Back 4"));
    }

    /**
     * Test other actions
     */
    @Test
    public void testOtherActions() {
        LLNode<Card> next = game.getHead().getNext();
        game.executeAction("Move Lead Back 4");
        Assertions.assertEquals(next, game.getHead());
        next = game.getHead().getNext();
        game.executeAction("Move First to Last");
        Assertions.assertEquals(next, game.getHead());
        LLNode<Card> curr = game.getHead();
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        game.executeAction("Move Last to First");
        Assertions.assertEquals(curr, game.getHead());
        LLNode<Card> head = game.getHead();
        game.executeAction("Reverse Line");
        Assertions.assertNotEquals(head, game.getHead());
        curr = game.getHead().getNext();
        for (int i = 0; i < 3; i++) {
            curr = curr.getNext();
        }
        game.executeAction("Reverse First 5");
        Assertions.assertEquals(curr, game.getHead());
        curr = game.getHead();
        game.executeAction("Skip Turn");
        Assertions.assertEquals(curr, game.getHead());
    }
}
