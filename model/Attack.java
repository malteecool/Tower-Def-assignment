package model;

import java.awt.*;

public class Attack {

    private Position attackFrom;
    private Position attackTo;
    private Color attackColor;

    public Attack(Position attackFrom, Position attackTo){
        this.attackFrom = attackFrom;
        this.attackTo = attackTo;
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
}
