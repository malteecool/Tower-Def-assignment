package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TeleportTile implements Tiles {

    private boolean canHaveTower;

    private boolean canBeTraversed;

    private boolean isTeleport;

    public TeleportTile(){
        canHaveTower = false;
        canBeTraversed = true;
        isTeleport = true;
    }

    /**
     * Returns the tile to get the direction for the unit.
     * @return the tile.
     */
    public Object landOn() {
        return this;
    }

    /**
     * Method that confirms if the tile is a teleport tile.
     *
     * @return a boolean is the tile is a teleport tile.
     */
    public boolean isTeleport() {
        return isTeleport;
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
