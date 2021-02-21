package controller;

import model.Position;

import java.awt.*;

public class AttackEvents {
    private Position attackFrom;
    private Position attackTo;
    private Color attackColor;
    private ObjectTypes attackType;
    private int attackDecay;
    private double attackVelocity;

    public AttackEvents(Position attackFrom, Position attackTo, ObjectTypes attackType){
        this.attackFrom = attackFrom;
        this.attackTo = attackTo;
        this.attackType = attackType;
    }

    public Position getAttackFrom() {
        return attackFrom;
    }

    public void setAttackFrom(Position attackFrom) {
        this.attackFrom = attackFrom;
    }

    public Position getAttackTo() {
        return attackTo;
    }

    public void setAttackTo(Position attackTo) {
        this.attackTo = attackTo;
    }

    public Color getAttackColor() {
        return attackColor;
    }

    public void setAttackColor(Color attackColor) {
        this.attackColor = attackColor;
    }

    public ObjectTypes getAttackType() {
        return attackType;
    }

    public void setAttackType(ObjectTypes attackType) {
        this.attackType = attackType;
    }

    public int getAttackDecay() {
        return attackDecay;
    }

    public void setAttackDecay(int attackDecay) {
        this.attackDecay = attackDecay;
    }

    public double getAttackVelocity() {
        return attackVelocity;
    }

    public void setAttackVelocity(double attackVelocity) {
        this.attackVelocity = attackVelocity;
    }
}
