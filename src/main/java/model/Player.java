package model;

import model.action.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Player representation
 * @author Katherine Chen
 */
public class Player {
    // current point
    private int point;
    // name to card list map
    private final Map<String, List<Card>> nameCardMap;
    // group to card list map
    private final Map<Card.Group, List<Card>> groupCardMap;
    // card list
    private final List<Card> cardList;
    // name to action map, only actions in this map are available for this player
    private final Map<String, AbstractAction> actionMap;
    // player name
    private final String name;

    /**
     * Create a new player
     * @param id current id
     */
    public Player(int id) {
        name = "Player " + id;
        //create a new HashMap to store the card's name
        nameCardMap = new HashMap<>();
        //create a new HashMap to store the card's group
        groupCardMap = new HashMap<>();
        //create a new HashMap to store the actions
        actionMap = new HashMap<>();
        //create an arraylist for the cards
        cardList = new ArrayList<>();

        initActionMap();
    }

    /**
     * Add all actions
     */
    private void initActionMap() {
        // add 4 move lead back actions with different places
        for (int i = 1; i <= 4; i++) {
            addAction(new MoveLeadBack(i));
        }

        addAction(new MoveFirstToLast());
        addAction(new MoveLastToFirst());
        addAction(new ReverseFirst5());
        addAction(new ReverseLine());
        addAction(new SkipTurn());
        addAction(new TakeFirstCard());
    }

    /**
     * Add one action
     * @param action action
     */
    private void addAction(AbstractAction action) {
        actionMap.put(action.getName(), action);
    }

    /**
     * Remove one action
     * @param actionName name of action
     */
    public void removeAction(String actionName) {
        actionMap.remove(actionName);
    }

    /**
     * Get action by name
     * @param name name of action
     * @return action object
     */
    public AbstractAction getAction(String name) {
        return actionMap.get(name);
    }

    /**
     * Get action map
     * @return actionMap
     */
    public Map<String, AbstractAction> getActionMap() {
        return actionMap;
    }

    /**
     * Check if player has card by name
     * @param name name of card
     * @return true if player has card, false otherwise
     */
    public boolean hasNameCard(String name) {
        return nameCardMap.containsKey(name);
    }

    /**
     * Get count of specified card by name
     * @param name name of card
     * @return number of cards with the name
     */
    public int getNameCardCount(String name) {
        return nameCardMap.containsKey(name) ? nameCardMap.get(name).size() : 0;
    }

    /**
     * Get count of specified card by group
     * @param group group name of card
     * @return number of cards with the group
     */
    public int getGroupCardCount(Card.Group group) {
        return groupCardMap.containsKey(group) ? groupCardMap.get(group).size() : 0;
    }

    /**
     * Add one card to player's list
     * @param card the card
     */
    public void addCard(Card card) {
        cardList.add(card);
        nameCardMap.compute(card.getName(), (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(card);

            return v;
        });

        groupCardMap.compute(card.getGroup(), (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(card);

            return v;
        });

        point += card.getPoint(this);
    }

    /**
     * Get card list
     * @return list of cards
     */
    public List<Card> getCardList() {
        return cardList;
    }

    /**
     * Get point of player
     * @return point of player
     */
    public int getPoint() {
        return point;
    }

    /**
     * Get name of player
     * @return name of player
     */
    public String getName() {
        return name;
    }
}
