package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TCrossingTile implements Tiles {

    private BufferedImage image;
    private Graphics2D graphics2D;
    private Integer width = 75;
    private Integer height = 75;
    private Directions leftDirection;
    private Directions rightDirection;
    private boolean canHaveTower;
    private boolean canBeTraversed;

    public TCrossingTile(Directions leftDirectionNew, Directions rightDirectionNew){

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        leftDirection = leftDirectionNew;
        rightDirection = rightDirectionNew;
        canHaveTower = false;
        canBeTraversed = true;
    }

    /**
     * Returns the tile to get the direction for the unit.
     * @return the tile.
     */
    public TCrossingTile landOn() {
        return this;
    }

    /**
     * Method that randomizes the direction that a unit takes in the TCrossing.
     *
     * @return a randomized direction.
     */
    public Directions getDirection() {
        Directions direction = leftDirection;
        final Random random = new Random();
        Integer randInt = 0;

        if (random.nextBoolean()) {
            randInt = 1;
        } else {
            randInt = 2;
        }

        if (randInt == 2) {
            direction = rightDirection;
        }
        return direction;
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
