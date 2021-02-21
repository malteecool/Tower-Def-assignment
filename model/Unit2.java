package model;

/**
 * A a bit stronger but more expensive unit.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 */
public class Unit2 implements GroundUnit {

    private boolean isAlive;

    private Integer hp;

    private Position position;

    private Integer creditCost;

    private Integer pathsLeft;

    private double movementSpeed;

    private Directions lastPosition;

    public Unit2(Position position) {
        isAlive = true;
        hp = 800;
        this.position = position;
        creditCost = 100;
        pathsLeft = 100;
        movementSpeed = 0.5;
        lastPosition = Directions.NORTH;
    }

    /**
     * Increments the health of the unit.
     */
    public void incrementHealth() {
        ++hp;
    }

    /**
     * Decrements the health of the unit.
     */
    public void decrementHealth() {
        --hp;
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

        //System.out.println(this.lastPosition);

        if (direction.getDirection() == Directions.WEST) {
            lastPosition = Directions.EAST;
        } else if (direction.getDirection() == Directions.NORTH) {
            lastPosition = Directions.SOUTH;
        } else if (direction.getDirection() == Directions.EAST) {
            lastPosition = Directions.WEST;
        } else if (direction.getDirection() == Directions.SOUTH) {
            lastPosition = Directions.NORTH;
        }
    }

    public void moveAgain() {
        //System.out.println(this.lastPosition);

        if (lastPosition.getDirection() == Directions.EAST) {
            this.position.setXPosition(this.position.getXPosition() - movementSpeed);
        } else if (lastPosition.getDirection() == Directions.SOUTH) {
            this.position.setYPosition(this.position.getYPosition() - movementSpeed);
        } else if (lastPosition.getDirection() == Directions.WEST) {
            this.position.setXPosition(this.position.getXPosition() + movementSpeed);
        } else if (lastPosition.getDirection() == Directions.NORTH) {
            this.position.setYPosition(this.position.getYPosition() + movementSpeed);
        }
    }

    public boolean isGoal(Position goalPosition) {

        if (this.position.getXPosition() == goalPosition.getXPosition()*32) {

            if (this.position.getYPosition() == goalPosition.getYPosition()*32) {
                return true;
            }
        }
        return false;
    }

    public Directions getLastDirection() {
        return this.lastPosition;
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
    }

    /**
     * Checks if the unit is alive.
     *
     * @return false if dead otherwise true.
     */
    public boolean isAlive() {
        return isAlive;
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

