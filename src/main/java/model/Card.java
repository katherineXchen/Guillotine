package model;

import java.util.Objects;
import java.util.function.Function;

/**
 * Card representation
 * @author Katherine Chen
 */
public class Card {
    /**
     * Group enum
     */
    public enum Group {
        CHURCH,
        MILITARY,
        ROYAL,
        CIVIC,
        COMMONER
    }

    // name of card
    private final String name;
    // group of card
    private final Group group;
    // point of card
    private int point;
    // method to calculate point of card for some special cards
    private Function<Player, Integer> pointFunc;

    /**
     * Create a new card with fixed point
     * @param name card name
     * @param group group of card
     * @param point point of card
     */
    public Card(String name, Group group, int point) {
        this.name = name;
        this.group = group;
        this.point = point;
    }

    /**
     * Create a new card with non-fixed point
     * @param name card name
     * @param group group of card
     * @param pointFunc function to calculate point
     */
    public Card(String name, Group group, Function<Player, Integer> pointFunc) {
        this.name = name;
        this.group = group;
        this.pointFunc = pointFunc;
    }

    /**
     * Get the name of card
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the group of card
     * @return group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Calculate the card hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Get the point of card
     * @param player current player
     * @return point of card
     */
    public int getPoint(Player player) {
        if (pointFunc != null) {
            return pointFunc.apply(player);
        }

        return getPoint();
    }

    /**
     * Get the fix point of card
     * @return point
     */
    public int getPoint() {
        return point;
    }

    /**
     * Show card with name
     * @return card string
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Create a king louis XVI card
     * @return card
     */
    public static Card createKingLouisXVI() {
        return new Card("King Louis XVI", Group.ROYAL, 5);
    }

    /**
     * Create a Marie Antoinette card
     * @return card
     */
    public static Card createMarieAntoinette() {
        return new Card("Marie Antoinette", Group.ROYAL, 5);
    }

    /**
     * Create a regent card
     * @return card
     */
    public static Card createRegent() {
        return new Card("Regent", Group.ROYAL, 4);
    }

    /**
     * Create a duke card
     * @return card
     */
    public static Card createDuke() {
        return new Card("Duke", Group.ROYAL, 3);
    }

    /**
     * Create a baron card
     * @return card
     */
    public static Card createBaron() {
        return new Card("Baron", Group.ROYAL, 3);
    }

    /**
     * Create a count card
     * @return card
     */
    public static Card createCount() {
        return new Card("Count", Group.ROYAL, player -> player.hasNameCard("Countess") ? 4 : 2);
    }

    /**
     * Create a countess card
     * @return card
     */
    public static Card createCountess() {
        return new Card("Countess", Group.ROYAL, player -> player.hasNameCard("Count") ? 4 : 2);
    }

    /**
     * Create a lord card
     * @return card
     */
    public static Card createLord() {
        return new Card("Lord", Group.ROYAL, player -> player.hasNameCard("Lady") ? 4 : 2);
    }

    /**
     * Create a lady card
     * @return card
     */
    public static Card createLady() {
        return new Card("Lady", Group.ROYAL, player -> player.hasNameCard("Lord") ? 4 : 2);
    }

    /**
     * Create a cardinal card
     * @return card
     */
    public static Card createCardinal() {
        return new Card("Cardinal", Group.CHURCH, 5);
    }

    /**
     * create an archbishop card
     * @return card
     */
    public static Card createArchbishop() {
        return new Card("Archbishop", Group.CHURCH, 4);
    }

    /**
     * Create a nun card
     * @return card
     */
    public static Card createNun() {
        return new Card("Nun", Group.CHURCH, 3);
    }

    /**
     * Create a bishop card
     * @return card
     */
    public static Card createBishop() {
        return new Card("Bishop", Group.CHURCH, 2);
    }

    /**
     * Create a priest card
     * @return card
     */
    public static Card createPriest() {
        return new Card("Priest", Group.CHURCH, 1);
    }

    /**
     * Create a heretic card
     * @return card
     */
    public static Card createHeretic() {
        return new Card("Heretic", Group.CHURCH, player -> player.getGroupCardCount(Group.CHURCH));
    }

    /**
     * Create a governor card
     * @return card
     */
    public static Card createGovernor() {
        return new Card("Governor", Group.CIVIC, 4);
    }

    /**
     * Create a mayor card
     * @return card
     */
    public static Card createMayor() {
        return new Card("Mayor", Group.CIVIC, 3);
    }

    /**
     * Create a councilman card
     * @return card
     */
    public static Card createCouncilman() {
        return new Card("Councilman", Group.CIVIC, 3);
    }

    /**
     * Create a judge card
     * @return card
     */
    public static Card createJudge() {
        return new Card("Judge", Group.CIVIC, 2);
    }

    /**
     * Create a tax collector card
     * @return card
     */
    public static Card createTaxCollector() {
        return new Card("Tax Collector", Group.CIVIC, player -> player.getGroupCardCount(Group.CIVIC));
    }

    /**
     * Create a sheriff card
     * @return card
     */
    public static Card createSheriff() {
        return new Card("Sheriff", Group.CIVIC, 1);
    }

    /**
     * Create a palace guard card
     * @return card
     */
    public static Card createPalaceGuard() {
        return new Card("Palace Guard", Group.MILITARY, player -> player.getNameCardCount("Palace Guard"));
    }

    /**
     * Create a general card
     * @return card
     */
    public static Card createGeneral() {
        return new Card("General", Group.MILITARY, 4);
    }

    /**
     * Create a colonel card
     * @return card
     */
    public static Card createColonel() {
        return new Card("Colonel", Group.MILITARY, 3);
    }

    /**
     * Create a captain card
     * @return card
     */
    public static Card createCaptain() {
        return new Card("Captain", Group.MILITARY, 2);
    }

    /**
     * Create a lieutenant card
     * @return card
     */
    public static Card createLieutenant() {
        return new Card("Lieutenant", Group.MILITARY, 1);
    }

    /**
     * Create a tragic figure card
     * @return card
     */
    public static Card createTragicFigure() {
        return new Card("Tragic Figure", Group.COMMONER, player -> player.getGroupCardCount(Group.COMMONER) * -1);
    }

    /**
     * Create a heroic figure card
     * @return card
     */
    public static Card createHeroicFigure() {
        return new Card("Heroic Figure", Group.COMMONER, -3);
    }

    /**
     * Create a student card
     * @return card
     */
    public static Card createStudent() {
        return new Card("Student", Group.COMMONER, -1);
    }
}
