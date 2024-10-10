package model.action;

import model.Game;
import model.Player;
import java.util.*;

/**
 * An abstract action class that is the parent class of all the possible actions of the game.
 * @author Katherine Chen
 */
public abstract class AbstractAction {
    // action name
    private final String name;
    // whether this action can be executed multiple times
    private boolean multiTimeEnabled;

    /**
     * Constructor for an action which can be executed only once
     * @param name action name
     */
    public AbstractAction(String name) {
        this.name = name;
    }

    /**
     * Constructor for an action which can be executed multiple times
     * @param name action name
     * @param multiTimeEnabled true if action can be executed multiple times
     */
    public AbstractAction(String name, boolean multiTimeEnabled) {
        this.name = name;
        this.multiTimeEnabled = multiTimeEnabled;
    }

    /**
     * Get the action name
     * @return action name
     */
    public String getName() {
        return name;
    }

    /**
     * Run the action
     * @param player current player
     * @param game current game
     */
    public void run(Player player, Game game) {
        checkRunnable(player, game);
        execute(player, game);
        if (!multiTimeEnabled) {
            player.removeAction(name);
        }
        game.changePlayer();
    }

    /**
     * Execute the action
     * @param player current player
     * @param game current game
     */
    public abstract void execute(Player player, Game game);

    /**
     * Check if equals
     * @param o another object
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        AbstractAction that = (AbstractAction) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Count hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Check if the action is runnable
     * @param player current player
     * @param game current game
     * @throws IllegalStateException will be thrown if not runnable
     */
    protected abstract void checkRunnable(Player player, Game game) throws IllegalStateException;
}
