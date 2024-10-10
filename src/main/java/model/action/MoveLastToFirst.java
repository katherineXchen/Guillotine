package model.action;

import model.Card;
import model.Game;
import model.Player;
import util.LLNode;

/**
 * Move last card to first action implementation
 * @author Katherine Chen
 */
public class MoveLastToFirst extends AbstractAction {
    /**
     * Create the action
     */
    public MoveLastToFirst() {
        super("Move Last to First");
    }

    /**
     * Execute the action
     * @param player current player
     * @param game current game
     */
    @Override
    public void execute(Player player, Game game) {
        LLNode<Card> head = game.getHead();
        if (head == null || head.getNext() == null || game.getCurrCardNum() <= 1) {
            return;
        }
        LLNode<Card> newHead = head.moveLastToFirst();
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
