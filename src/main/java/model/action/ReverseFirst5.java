package model.action;

import model.Card;
import model.Game;
import model.Player;
import util.LLNode;

/**
 * Reverse the first 5 cards action implementation
 * @author Katherine Chen
 */
public class ReverseFirst5 extends AbstractAction {
    /**
     * Create the action
     */
    public ReverseFirst5() {
        super("Reverse First 5");
    }

    /**
     * Execute the action
     * @param player current player
     * @param game current game
     */
    @Override
    public void execute(Player player, Game game) {
        if (game.getCurrCardNum() < 5) {
            return;
        }

        LLNode<Card> head = game.getHead();
        LLNode<Card> newHead = head.reverseFirstK(5);
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
        if (game.getCurrCardNum() < 5) {
            throw new IllegalStateException("Card number is less than 5");
        }
    }
}
