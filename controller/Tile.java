package controller;

/**
 * Struct used when passing tiles around the project
 * @author Adam Bonnedahl (c13abl)
 */
public class Tile {

    private ObjectTypes tileType;
    private int posX;
    private int posY;

    public Tile(ObjectTypes tileType, int posX, int posY){
        this.tileType = tileType;
        this.posX = posX;
        this.posY = posY;
    }

    public ObjectTypes getTileType() {
        return tileType;
    }

    public void setTileType(ObjectTypes tileType) {
        this.tileType = tileType;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }


}
