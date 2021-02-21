package model;

import java.util.ArrayList;

/**
 * The main tower class in the game.
 *
 * @author Max Malmer c17mmr@cs.umu.se, Peter Johansson dv18pjn@cs.umu.se
 */
public class Tower1 implements Tower {

    private double range;
    private Integer damage;
    private Position placement; // name is up for debate.

    private GroundUnit target;

    private boolean shootsFired;

    public Tower1(Position placement) {
        this.range = 50;
        this.damage = 1;
        this.placement = placement;
        this.target = null;
        this.shootsFired = false;
    }

    /**
     * runs tower behaviour, such as checking
     * if a unit is in the area, which unit is in
     * range and of those units in range which is
     * closest to the start (which towers prioritise)
     *
     * @param nearbyUnits in the same area
     */
    //TODO Null case must be improved.
    public Attack runTower(ArrayList<GroundUnit> nearbyUnits) {

        if (target != null) {

            if (target.getHealth() <= 0) {
                target = null;
            }
        }

        if (nearbyUnits.size() == 0) {// no unit exist
            setTarget(null);

        } else if (target != null) {//engage current target

            if(rangeFinder(target.getPosition())) {
                //System.out.println("Vi skjuter");
                shootTarget();
                shootsFired = true;
            } else {
                setTarget(null);
            }
        }

        if (target == null) {// if no target, select new target

            for (GroundUnit nearbyUnit : nearbyUnits) {

                if (nearbyUnit != null) {

                    if (rangeFinder(nearbyUnit.getPosition())) {
                        //System.out.println("Hit kommer vi iaf.");
                        //setTarget(nearbyUnit);
                        target = nearbyUnit;

                        //TODO We can't check this if target is null(NullpointerException)
                        if (nearbyUnit.getPathLeft() >= target.getPathLeft()) {
                            System.out.println("Vi väljer target");
                            setTarget(nearbyUnit);
                        }
                    }

                    if (this.target != null) {
                        //System.out.println("Vi skjuter");
                        shootTarget();
                        shootsFired = true;
                    }
                }
            }
        }

        if (shootsFired) {
            Attack newAttack = new Attack(this.getPosition(), this.target.getPosition());
            shootsFired = false;
            return newAttack;
        }
        return null;
    }

    /**
     * calculates the distance to a position
     * @param groundUnitPosition to be measured
     */
    //TODO Never finds a unit.
    public boolean rangeFinder(Position groundUnitPosition) {
        double x1 = placement.getXPosition();
        double y1 = placement.getXPosition();

        double x2 = groundUnitPosition.getXPosition();
        double y2 = groundUnitPosition.getYPosition();

        return range >= Math.hypot(Math.abs(x1 - x2), Math.abs(y1 -y2));
        //return true;
    }

    /**
     * Set the next target the tower will shoot
     * @param target the chosen target to be shot
     */
    public void setTarget(GroundUnit target) { this.target = target; }

    /**
     * returns the towers current target.
     * @return Groundunit that is the current target.
     */
    public GroundUnit getTarget() { return this.target; }

    /**
     * Tower shoots at the target
     */
    public void shootTarget() {
        target.damageUnit(damage);
    }

    //------------------ test methods ----------------//
    /**
     * Sets the attack of the tower.
     */
    public void setAttack(Integer damage) { this.damage = damage; }

    /**
     * Gets the attack of the Tower.
     *
     * @return the attack of the tower.
     */
    public Integer getAttack() { return this.damage; }

    /**
     * Sets the range of the tower.
     */
    public void setRange(double range) { this.range = range; }

    /**
     * Gets the range of the tower.
     *
     * @return the range of the tower.
     */
    public double getRange() { return this.range; }

    /**
     * Sets the position of the Tower.
     */
    public void setPosition(Position position) { this.placement = position; }

    /**
     * Gets the position of the Tower.
     */
    public Position getPosition() { return this.placement; }
}
