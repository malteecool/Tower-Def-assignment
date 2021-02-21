package view;

import controller.ObjectTypes;
import model.Position;

import java.awt.*;

/**
 * Struct-like class used in the views animator to keep track of all attacks happening in the world
 * Keeps track of shooter, target and color of the attack. Also has an counter for how many more frames the attack
 * should be visible for
 */
class InternalAttackEvent {
    private Position fromPos;
    private Position targetPos;
    private Position attackCurrentPosition;

    private Color attackColor;
    private int attackDecayLeft;

    private boolean reachedTarget;
    private ObjectTypes attackType;
    private double attackVelocity;

    /**
     * Constructor that also takes all the settings needed for easier construction of new objects
     * @param fromPos Position from which the the attack is "shot"
     * @param toPos Position to which the attack is "shot"
     * @param attackColor Color of the attack
     * @param attackDecayLeft Remaning frames before the attack disappears from view
     */
    InternalAttackEvent(Position fromPos, Position targetPos){
        this.fromPos = fromPos;
        this.targetPos = targetPos;
        this.attackCurrentPosition = fromPos;
        this.reachedTarget = false;
    }

    /**
     * Returns position from where the shot is shot
     * @return Origin of shot
     */
    Position getFromPos() {
        return fromPos;
    }

    /**
     * Sets the position from where the shot is shot
     * @param fromPos Origin of shot
     */
    void setFromPos(Position fromPos) {
        this.fromPos = fromPos;
    }

    /**
     * Returns position to where the shot is shot
     * @return Destination of shot
     */
    Position getTargetPos() {
        return targetPos;
    }

    /**
     * Sets the position where the shot lands
     * @param targetPos Destination of the shot
     */
    void setTargetPos(Position targetPos) {
        this.targetPos = targetPos;
    }

    /**
     * Returns the color of the attack
     * @return Color of the shot
     */
    Color getAttackColor() {
        return attackColor;
    }

    /**
     * Sets the color of the attack
     * @param attackColor Color of the shot
     */
    void setAttackColor(Color attackColor) {
        this.attackColor = attackColor;
    }

    /**
     * Returns the remaining frames for which the shot should be shown
     * @return Number of frames before shot disappears
     */
    int getAttackDecayLeft() {
        return attackDecayLeft;
    }

    /**
     * Sets the remaining frames before the shot disappears
     * @param attackDecayLeft Number of frames before shot disappears
     */
    void setAttackDecayLeft(int attackDecayLeft) {
        this.attackDecayLeft = attackDecayLeft;
    }

    public Position getCurrentPosition() {
        return attackCurrentPosition;
    }

    public void setCurrentPosition(Position attackCurrentPosition) {
        this.attackCurrentPosition = attackCurrentPosition;
    }


    public boolean hasReachedTarget() {
        return reachedTarget;
    }

    public void reachedTarget(boolean reachedTarget) {
        this.reachedTarget = reachedTarget;
    }

    public ObjectTypes getAttackType() {
        return attackType;
    }

    public void setAttackType(ObjectTypes attackType) {
        this.attackType = attackType;
    }

    public double getAttackVelocity() {
        return attackVelocity;
    }

    public void setAttackVelocity(double attackVelocity) {
        this.attackVelocity = attackVelocity;
    }
}
