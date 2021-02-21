package model;

import java.util.ArrayList;

public class TestTower implements Tower {

    private double range;
    private Integer damage;
    private Position placement; // name is up for debate.

    private GroundUnit target;

    public TestTower(Position placement) {
        this.range = 3;
        this.damage = 2;
        this.placement = placement;
    }


    /**
     * runs tower behaviour, such as checking
     * if a unit is in the area, which unit is in
     * range and of those units in range which is
     * closest to the start (which towers prioritise)
     *
     * @param nearbyUnits in the same area
     */
    public Attack runTower(ArrayList<GroundUnit> nearbyUnits) {

        if (nearbyUnits.size() == 0) {// no unit exist
            setTarget(null);

        } else if (target != null) {//engage current target

            if(rangeFinder(target.getPosition())) {
                shootTarget();
            } else {
                setTarget(null);
            }
        }

        if (target == null) {// if no target, select new target

            for (GroundUnit unit : nearbyUnits) {
                if (rangeFinder(unit.getPosition())){
                    if (unit.getPathLeft() > target.getPathLeft()) {
                        setTarget(unit);
                    }
                }
            }
            shootTarget();
        }
        return null;
    }

    /**
     * calculates the distance to a position
     * @param groundUnitPosition to be measured
     */
    public boolean rangeFinder(Position groundUnitPosition) {
        double x1 = placement.getXPosition();
        double y1 = placement.getXPosition();

        double x2 = groundUnitPosition.getXPosition();
        double y2 = groundUnitPosition.getYPosition();

        return range >= Math.hypot(Math.abs(x1 - x2), Math.abs(y1 -y2));
    }

    /**
     * Set the next target the tower will shoot
     * @param target the chosen target to be shot
     */
    public void setTarget(GroundUnit target) { this.target = target; }


    /**
     * Tower shoots at the target
     */
    public void shootTarget() {
        target.damageUnit(damage);
    }

    //------------------ test methods ----------------//

    /**
     * returns the towers current target.
     * @return Groundunit that is the current target.
     */
    public GroundUnit getTarget() { return this.target; }

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
