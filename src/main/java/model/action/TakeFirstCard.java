package model.action;

import model.Card;
import model.Game;
import model.Player;
import util.LLNode;

/**
 * Take the first card action implementation
 * @author Katherine Chen
 */
public class TakeFirstCard extends AbstractAction {
    /**
     * Create the  action
     */
    public TakeFirstCard() {
        super("Take First Card", true);
    }

    /**
     * Execute the action
     * @param player current player
     * @param game current game
     */
    @Override
    public void execute(Player player, Game game) {
        LLNode<Card> head = game.getHead();
        player.addCard(head.getElement());

        game.setHead(head.getNext());
        game.subCardNumber();
    }

    /**
     * Check if the actiona is runnable
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
