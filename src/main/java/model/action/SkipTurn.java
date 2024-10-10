package model.action;

import model.Game;
import model.Player;

/**
 * Skip turn action implementation
 * @author Katherine Chen
 */
public class SkipTurn extends AbstractAction {
    /**
     * Create the action
     */
    public SkipTurn() {
        super("Skip Turn");
    }

    /**
     * Execute the action
     * @param player current player
     * @param game current game
     */
    @Override
    public void execute(Player player, Game game) {
    }

    /**
     * Check if the action is runnable
     * @param player current player
     * @param game current game
     * @throws IllegalStateException will be thrown if not runnable
     */
    @Override
    protected void checkRunnable(Player player, Game game) throws IllegalStateException {
    }
}
