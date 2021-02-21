package controller;

import model.Position;

public class ObjectPosition {
    private int unitId;
    private ObjectTypes unitType;
    private Position position;

    public ObjectPosition(int unitId, ObjectTypes unitType, Position position) {
        this.unitId = unitId;
        this.unitType = unitType;
        this.position = position;
    }
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public ObjectTypes getUnitType() {
        return unitType;
    }

    public void setUnitType(ObjectTypes unitType) {
        this.unitType = unitType;
    }

    public double getPosX() {
        return position.getXPosition();
    }

    public void setPosX(double posX) {
        this.position.setXPosition(posX);
    }

    public double getPosY() {
        return position.getYPosition();
    }

    public void setPosY(double posY) {
        this.position.setYPosition(posY);
    }
    public Position getPosition(){
        return  this.position;
    }
}
