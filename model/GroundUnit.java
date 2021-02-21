package model;

/**
 * Interface for the standard GroundUnit.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 */
public interface GroundUnit {

    /**
     * Decrements the health of the unit.
     */
    public void incrementHealth();

    /**
     * Increments the health of the unit.
     */
    public void decrementHealth();

    /**
     * Gets the current HP of the unit.
     * @return a int of the current health of the unit.
     */
    public Integer getHealth();

    /**
     * Moves the unit one step forward on the map.
     */
    public void move(Directions direction);

    public void moveAgain();

    public boolean isGoal(Position goalPosition);

    public Directions getLastDirection();


    /**
     * Gets the cost of the unit.
     * @return an int representing the cost of the unit.
     */
    public Integer getCreditCost();

    /**
     * Sets the unit to Alive.
     */
    public void setAlive();

    /**
     * Sets the unit to dead.
     */
    public void setDead();

    /**
     * Checks if the unit is Alive.
     * @return true if it's alive, otherwise false.
     */
    public boolean isAlive();

    /**
     * Gets the x coordinate of the unit.
     *
     * @return x coordinate of the unit.
     */
    public double getXPosition();

    /**
     * Gets the y coordinate of the unit.
     *
     * @return y coordinate of the unit.
     */
    public double getYPosition();

    /**
     * Gets position of the unit.
     *
     * @return the position of the unit.
     */
    public Position getPosition();

    /**
     * Sets how many path tiles are left to the goal.
     *
     * @param pathsLeftNew the number of paths left.
     */
    public void setPathLeft(Integer pathsLeftNew);

    /**
     * Gets how many path tiles are left to the goal.
     */
    public double getPathLeft();

    /**
     * Takes given number in damage on the unit.
     *
     * @param damage the damage taken.
     */
    public void damageUnit(Integer damage);
}
