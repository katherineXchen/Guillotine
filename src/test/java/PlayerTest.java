import model.Card;
import model.Player;
import model.action.ReverseLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * test for player
 * @author Katherine Chen
 */
public class PlayerTest {
    /**
     * test player methods
     */
    @Test
    public void testPlayer() {
        Player player = new Player(1);
        Assertions.assertEquals("Player 1", player.getName());
        Assertions.assertEquals(10, player.getActionMap().size());
        Assertions.assertTrue(player.getAction("Reverse Line") instanceof ReverseLine);
        player.removeAction("Reverse Line");
        Assertions.assertNull(player.getAction("Reverse Line"));

        player.addCard(Card.createKingLouisXVI());
        Assertions.assertEquals(5, player.getPoint());
        Assertions.assertEquals(1, player.getCardList().size());
    }
}
