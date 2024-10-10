package model.action;

import model.Card;
import model.Game;
import model.Player;
import util.LLNode;

/**
 * Reverse the entire line of cards action implementation
 * @author Katherine Chen
 */
public class ReverseLine extends AbstractAction {
    /**
     * Create the action
     */
    public ReverseLine() {
        super("Reverse Line");
    }

    /**
     * Execute the action
     * @param player current player
     * @param game current game
     */
    @Override
    public void execute(Player player, Game game) {
        LLNode<Card> head = game.getHead();
        LLNode<Card> newHead = head.reverseList();
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
        if (game.getCurrCardNum() == 0) {
            throw new IllegalStateException("No card");
        }
    }
}
