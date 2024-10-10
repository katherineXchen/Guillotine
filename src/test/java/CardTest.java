import model.Card;
import model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test card
 * @author Katherine Chen
 */
public class CardTest {
    // test player
    private Player player;

    /**
     * Setup for the test
     */
    @BeforeEach
    void setUp() {
        player = new Player(1);
        player.addCard(Card.createKingLouisXVI());
        player.addCard(Card.createMarieAntoinette());
        player.addCard(Card.createRegent());
        player.addCard(Card.createDuke());
        player.addCard(Card.createBaron());
        player.addCard(Card.createCount());
        player.addCard(Card.createCountess());
        player.addCard(Card.createLord());
        player.addCard(Card.createLady());
        player.addCard(Card.createCardinal());
        player.addCard(Card.createArchbishop());
        player.addCard(Card.createNun());
        player.addCard(Card.createBishop());
        player.addCard(Card.createPriest());
        player.addCard(Card.createHeretic());
        player.addCard(Card.createGovernor());
        player.addCard(Card.createMayor());
        player.addCard(Card.createCouncilman());
        player.addCard(Card.createJudge());
        player.addCard(Card.createTaxCollector());
        player.addCard(Card.createSheriff());
        player.addCard(Card.createPalaceGuard());
        player.addCard(Card.createGeneral());
        player.addCard(Card.createColonel());
        player.addCard(Card.createCaptain());
        player.addCard(Card.createLieutenant());
        player.addCard(Card.createTragicFigure());
        player.addCard(Card.createHeroicFigure());
        player.addCard(Card.createStudent());
    }

    /**
     * Test get point of card
     */
    @Test
    public void testCardPoint() {
        Assertions.assertEquals(5, Card.createKingLouisXVI().getPoint(player));
        Assertions.assertEquals(5, Card.createMarieAntoinette().getPoint(player));
        Assertions.assertEquals(4, Card.createRegent().getPoint(player));
        Assertions.assertEquals(3, Card.createDuke().getPoint(player));
        Assertions.assertEquals(3, Card.createBaron().getPoint(player));
        Assertions.assertEquals(4, Card.createCount().getPoint(player));
        Assertions.assertEquals(4, Card.createCountess().getPoint(player));
        Assertions.assertEquals(4, Card.createLord().getPoint(player));
        Assertions.assertEquals(4, Card.createLady().getPoint(player));
        Assertions.assertEquals(5, Card.createCardinal().getPoint(player));
        Assertions.assertEquals(4, Card.createArchbishop().getPoint(player));
        Assertions.assertEquals(3, Card.createNun().getPoint(player));
        Assertions.assertEquals(2, Card.createBishop().getPoint(player));
        Assertions.assertEquals(1, Card.createPriest().getPoint(player));
        Assertions.assertEquals(6, Card.createHeretic().getPoint(player));
        Assertions.assertEquals(4, Card.createGovernor().getPoint(player));
        Assertions.assertEquals(3, Card.createMayor().getPoint(player));
        Assertions.assertEquals(3, Card.createCouncilman().getPoint(player));
        Assertions.assertEquals(2, Card.createJudge().getPoint(player));
        Assertions.assertEquals(6, Card.createTaxCollector().getPoint(player));
        Assertions.assertEquals(1, Card.createSheriff().getPoint(player));
        Assertions.assertEquals(1, Card.createPalaceGuard().getPoint(player));
        Assertions.assertEquals(4, Card.createGeneral().getPoint(player));
        Assertions.assertEquals(3, Card.createColonel().getPoint(player));
        Assertions.assertEquals(2, Card.createCaptain().getPoint(player));
        Assertions.assertEquals(1, Card.createLieutenant().getPoint(player));
        Assertions.assertEquals(-3, Card.createTragicFigure().getPoint(player));
        Assertions.assertEquals(-3, Card.createHeroicFigure().getPoint(player));
        Assertions.assertEquals(-1, Card.createStudent().getPoint(player));
    }
}
