package model;

/**
 * A test unit to the test the function of the GroundUnit class.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 */
public class TestUnit implements GroundUnit {

    private boolean isAlive;

    private Integer hp;

    private Position position;

    private Integer creditCost;

    private double pathsLeft;

    private double movementSpeed;

    private Directions lastPosition;

    //Implement constructor to avoid instability
    public TestUnit(Position position) {
        isAlive = true;
        hp = 10;
        this.position = position;
        creditCost = 10;
        pathsLeft = 100;
        movementSpeed = 0.01;
        lastPosition = Directions.NORTH;
    }

    /**
     * Increments the health of the unit.
     */
    public void incrementHealth() {
        hp++;
    }

    /**
     * Decrements the health of the unit.
     */
    public void decrementHealth() {
        hp--;
    }

    /**
     * Takes given number in damage on the unit.
     *
     * @param damage the damage taken.
     */
    public void damageUnit(Integer damage) {

        for (int i = 0; i < damage; i++) {
            this.decrementHealth();
        }
    }

    /**
     * Gets the current HP of the unit.
     * @return a int of the current health of the unit.
     */
    public Integer getHealth() {
        return hp;
    }

    /**
     * Moves the unit one step forward on the map.
     */
    public void move(Directions direction) {

        if (direction.getDirection() == Directions.WEST) {
            this.position.setXPosition(this.position.getXPosition() - movementSpeed);
            lastPosition = Directions.WEST;
        } else if (direction.getDirection() == Directions.NORTH) {
            this.position.setYPosition(this.position.getYPosition() - movementSpeed);
            lastPosition = Directions.NORTH;
        } else if (direction.getDirection() == Directions.EAST) {
            this.position.setXPosition(this.position.getXPosition() + movementSpeed);
            lastPosition = Directions.EAST;
        } else if (direction.getDirection() == Directions.SOUTH) {
            this.position.setYPosition(this.position.getYPosition() + movementSpeed);
            lastPosition = Directions.SOUTH;
        }
    }

    public void moveAgain() {

        if (lastPosition.getDirection() == Directions.EAST) {
            this.position.setXPosition(this.position.getXPosition() - movementSpeed);
            lastPosition = Directions.EAST;
        } else if (lastPosition.getDirection() == Directions.SOUTH) {
            this.position.setYPosition(this.position.getYPosition() - movementSpeed);
            lastPosition = Directions.SOUTH;
        } else if (lastPosition.getDirection() == Directions.WEST) {
            this.position.setXPosition(this.position.getXPosition() + movementSpeed);
            lastPosition = Directions.WEST;
        } else if (lastPosition.getDirection() == Directions.NORTH) {
            this.position.setYPosition(this.position.getYPosition() + movementSpeed);
            lastPosition = Directions.NORTH;
        }
    }

    public boolean isGoal(Position goalPosition) {

        if (this.position.getXPosition() == goalPosition.getXPosition()) {

            if (this.position.getYPosition() == goalPosition.getYPosition()) {
                return true;
            }
        }
        return false;
    }

    public Directions getLastDirection() {
        return this.lastPosition;
    }

    private void setXYPosition(Integer newXPosition, Integer newYPosition) {
        position.setPosition(newXPosition, newYPosition);
    }

    /**
     * Gets the cost of the unit.
     * @return an int representing the cost of the unit.
     */
    public Integer getCreditCost() {
        return creditCost;
    }

    /**
     * Sets the unit to Alive.
     */
    public void setAlive() {
        isAlive = true;
    }

    /**
     * Sets the unit to Dead.
     */
    public void setDead() {
        isAlive = false;
    }// TODO call removeUnit.

    /**
     * Checks if the unit is Alive.
     * @return true if it's alive, otherwise false.
     */
    public boolean isAlive() {

        if (isAlive) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the x coordinate of the unit.
     *
     * @return x coordinate of the unit.
     */
    public double getXPosition() {
        return position.getXPosition();
    }

    /**
     * Gets the y coordinate of the unit.
     *
     * @return y coordinate of the unit.
     */
    public double getYPosition() {
        return position.getYPosition();
    }

    public void setXPosition(double XPos) {
        position.setXPosition(XPos);
    }

    public void setYPosition(double YPos) {
        position.setYPosition(YPos);
    }

    /**
     * Gets position of the unit.
     *
     * @return the position of the unit.
     */
    public Position getPosition() {
        return position;
    }


    /**
     * Sets how many path tiles are left to the goal.
     *
     * @param pathsLeftNew the number of paths left.
     */
    public void setPathLeft(Integer pathsLeftNew) {
        pathsLeft = pathsLeftNew;
    }

    /**
     * Gets how many path tiles are left until the goal.
     *
     * @return how many path tiles are left to the goal.
     */
    public double getPathLeft() {
        return pathsLeft;
    }
}
