package model;

import model.action.AbstractAction;
import util.LLNode;

import java.util.*;

/**
 * Game handler implementation
 * @author Katherine Chen
 */
public class Game {
    // total number of cards in the game
    private static final int CARD_NUM = 20;
    // all cards, game need select 20 from the list
    public static final List<Card> allCards = new ArrayList<>();
    // head in the linked list of game cards
    private LLNode<Card> head;
    // player list, two players in total
    private final List<Player> playerList;
    // current number of cards in the game
    private int currCardNum;
    // current index of player, use this to get current player
    private int currPlayerIndex;

    /**
     * Constructor to build a new game
     */
    public Game() {
        initCards();
        //create an array list for the players
        playerList = new ArrayList<>();
        playerList.add(new Player(1));
        playerList.add(new Player(2));
    }

    /**
     * Initialize cards by randomly select 20 cards from all cards
     */
    private void initCards() {
        //create a Set which prevents from containing duplicated elements
        Set<Integer> selectedIndexes = new HashSet<>();
        Random random = new Random();
        int size = allCards.size();
        LLNode<Card> curr = null;
        // random get 20 cards from all cards
        while (selectedIndexes.size() != CARD_NUM) {
            int index = random.nextInt(size);
            if (!selectedIndexes.contains(index)) {
                selectedIndexes.add(index);
                LLNode<Card> node = new LLNode<>(allCards.get(index), null);
                if (head == null) {
                    head = node;
                    curr = head;
                } else {
                    curr.setNext(node);
                    curr = node;
                }
            }
        }

        currCardNum = CARD_NUM;
    }

    /**
     * Execute an action by action name
     * @param actionName target action
     */
    public void executeAction(String actionName) {
        Player player = getCurrentPlayer();
        AbstractAction action = player.getAction(actionName);
        if (action == null) {
            throw new IllegalStateException("Action not found: " + actionName);
        } else {
            try {
                action.run(player, this);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Subtract card number when player take card
     */
    public void subCardNumber() {
        currCardNum--;
    }

    /**
     * Get the card list head
     * @return head node
     */
    public LLNode<Card> getHead() {
        return head;
    }

    /**
     * Set the head of card linked list
     * @param head new head node
     */
    public void setHead(LLNode<Card> head) {
        this.head = head;
    }

    /**
     * Get the current number of cards in the game
     * @return number of cards in the game
     */
    public int getCurrCardNum() {
        return currCardNum;
    }

    /**
     * Check if game is over, no cards left
     * @return true if game is over
     */
    public boolean isGameOver() {
        return currCardNum == 0;
    }

    /**
     * Get the player who is the winner
     * @return winner player, null if tied
     */
    public Player getWinPlayer() {
        Player player1 = playerList.get(0);
        Player player2 = playerList.get(1);
        if (player1.getPoint() == player2.getPoint()) {
            return null;
        } else if (player1.getPoint() > player2.getPoint()) {
            return player1;
        } else {
            return player2;
        }
    }

    /**
     * Get player list
     * @return list of players
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Get current player
     * @return current player
     */
    public Player getCurrentPlayer() {
        return playerList.get(currPlayerIndex);
    }

    /**
     * Change player index
     */
    public void changePlayer() {
        currPlayerIndex = currPlayerIndex == 0 ? 1 : 0;
    }

    static {
        // init all cards
        initAllCards();
    }

    /**
     * Initialize all cards
     */
    public static void initAllCards() {
        allCards.addAll(Arrays.asList(Card.createKingLouisXVI(), Card.createMarieAntoinette(), Card.createRegent(),
                Card.createDuke(), Card.createBaron(), Card.createCount(), Card.createCountess(), Card.createLord(),
                Card.createLady(), Card.createCardinal(), Card.createArchbishop(), Card.createNun(), Card.createBishop(),
                Card.createPriest(), Card.createPriest(), Card.createHeretic(), Card.createGovernor(), Card.createMayor(),
                Card.createCouncilman(), Card.createJudge(), Card.createJudge(), Card.createTaxCollector(), Card.createSheriff(),
                Card.createSheriff(), Card.createSheriff(), Card.createGeneral(), Card.createColonel(), Card.createCaptain(),
                Card.createLieutenant(), Card.createTragicFigure(), Card.createHeroicFigure()));
        // add 4 student cards
        for (int i = 0; i < 4; i++) {
            allCards.add(Card.createStudent());
        }
        // add 5 palace guard cards
        for (int i = 0; i < 5; i++) {
            allCards.add(Card.createPalaceGuard());
        }
    }
}
