package model;

import java.util.ArrayList;

/**
 * Interface for the Towers that the AI generates.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 */
public interface Tower {

    public Attack runTower(ArrayList<GroundUnit> nearbyUnits);

    /**
     * calculates the distance between
     * a tower and a given position
     *
     * @param position
     * @return true if target is in range.
     */
    public boolean rangeFinder(Position position);

    /**
     * Sets the attack of the tower.
     */
    public void setAttack(Integer damage);

    /**
     * Gets the attack of the Tower.
     *
     * @return the attack of the tower.
     */
    public Integer getAttack();

    /**
     * Sets the range of the tower.
     */
    public void setRange(double range);

    /**
     * Gets the range of the tower.
     *
     * @return the range of the tower.
     */
    public double getRange();

    public GroundUnit getTarget();

    /**
     * Attacks the unit currently choosen.
     */
    public void shootTarget();

    /**
     * Chooses a unit to attack.
     */
    public void setTarget(GroundUnit target);

    /**
     * Sets the position of the Tower.
     */
    public void setPosition(Position position);

    /**
     * Gets the position of the Tower.
     */
    public Position getPosition();
}
