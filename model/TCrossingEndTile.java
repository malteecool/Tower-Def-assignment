package model;

public class TCrossingEndTile implements Tiles {

    private Directions direction;
    private boolean canHaveTower;
    private boolean canBeTraversed;

    public TCrossingEndTile(Directions directionNext){

        direction = directionNext;
        canHaveTower = false;
        canBeTraversed = true;
    }

    /**
     * Returns the tile to get the direction for the unit.
     * @return the tile.
     */
    public Object landOn() {
        return this;
    }

    /**
     * returns the directtion to the next tile.
     *
     * @return direction to the next tile
     */
    public Directions getDirection() { return direction; }

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
