package model;

/**
 * A class that represents a 2-dimensional position.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 * @version 1.0
 */
public class Position {

    private double xPosition;

    private double yPosition;

    /**
     * Class constructor that initiates the position.
     *
     * @param XCord the x coordinate.
     * @param YCord the y coordinate.
     */
    public Position(double XCord, double YCord) {
        xPosition = XCord;
        yPosition = YCord;
    }

    /**
     * Updates the position.
     *
     * @param XCord the x coordinate.
     * @param YCord the y coordinate.
     */
    public void setPosition(double XCord, double YCord) {
        xPosition = XCord;
        yPosition = YCord;
    }
    public void setXPosition(double xPosition){
        this.xPosition = xPosition;
    }

    public void setYPosition(double yPosition){
        this.yPosition = yPosition;
    }

    /**
     * Get the current position.
     *
     * @return the current position.
     */
    public Position getPosition() {
        return this;
    }

    /**
     * Gets the x position of the position.
     *
     * @return the x coordinate.
     */
    public double getXPosition() {
        return xPosition;
    }

    /**
     * Get the y position of the position.
     *
     * @return the y position.
     */
    public double getYPosition() {
        return yPosition;
    }
}
