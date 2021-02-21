package model;

/**
 * Interface to be used by tile-classes.
 */
public interface Tiles {

    /**
     * Returns the tile to get the direction for the unit.
     * @return the tile.
     */
    public Object landOn();

    /**
     * Checks if the tile can have a tower.
     *
     * @return true if it can have a tower, false otherwise.
     */
    public boolean canHaveTower();

    /**
     * Checks if the tile can be traversed.
     *
     * @return true if it can be traversed, false otherwise.
     */
    public boolean canBeTraversed();
}
