package model;

public class PathTile implements Tiles {

    private boolean canHaveTower;

    private boolean canBeTraversed;

    public PathTile() {
        canHaveTower = false;
        canBeTraversed = true;
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
