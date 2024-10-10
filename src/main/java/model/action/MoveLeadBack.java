package model.action;

import model.Card;
import model.Game;
import model.Player;
import util.LLNode;

/**
 * Move lead card back action implementation
 * @author Katherine Chen
 */
public class MoveLeadBack extends AbstractAction {
    // places to move lead
    private final int places;

    /**
     * Create the action
     */
    public MoveLeadBack(int places) {
        super("Move Lead Back " + places);

        this.places = places;
    }

    /**
     * Execute the action
     * @param player current player
     * @param game current game
     */
    @Override
    public void execute(Player player, Game game) {
        LLNode<Card> head = game.getHead();
        if (head == null || head.getNext() == null || game.getCurrCardNum() <= places) {
            return;
        }
        LLNode<Card> newHead = head.moveBack(places);
        game.setHead(newHead);
    }

    /**
     * Check if the action is runnable
     * @param player current player
     * @param game current game
     * @throws IllegalStateException will be thrown if not runnable
     */
    @Override
    protected void checkRunnable(Player player, Game game) throws IllegalStateException {
        if (game.getCurrCardNum() <= places) {
            throw new IllegalStateException("Card is not enough to move");
        }
    }
}
