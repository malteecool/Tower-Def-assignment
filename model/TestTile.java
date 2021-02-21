package model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Example of a map-tile.
 */
public class TestTile implements Tiles{

    private BufferedImage image;
    Graphics2D graphics2D;
    private int width = 75;
    private int height = 75;
    private boolean canHaveTower;
    private boolean canBeTraversed;

    public TestTile(){

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        canHaveTower = true;
        canBeTraversed = true;

    }

    /**
     * Should return graphics?
     * @return the graphics to return.
     */
    public Object landOn() {
        return this;
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
