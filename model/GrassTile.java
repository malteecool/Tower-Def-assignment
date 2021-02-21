package model;

/**
 * The class that represents the grass tile in the game.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 * @version 1.0
 */
public class GrassTile implements Tiles {

    private boolean canHaveTower;

    private boolean canBeTraversed;

    public GrassTile() {
        canHaveTower = true;
        canBeTraversed = false;
    }

    /**
     * Gets the image(?) from the tile-class.
     *
     * @return null
     */
    public Object landOn() {
        return null;
    }

    /**
     * Checks if the tile can have a tower.
     *
     * @return true if it can have a tower, false otherwise.
     */
    public boolean canHaveTower() {
        return canHaveTower;
    }

    /**
     * Checks if the tile can be traversed.
     *
     * @return true if it can be traversed, false otherwise.
     */
    public boolean canBeTraversed() {
        return canBeTraversed;
    }
}
