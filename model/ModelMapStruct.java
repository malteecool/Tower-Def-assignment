package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Struct used to pass whole game worlds around project
 * Contains worlds size (x/y dimensions) aswell as list of
 * all tiles in world and their respective TileType tags
 * @author Adam Bonnedahl (c13abl)
 */
public class ModelMapStruct {
    private int dimensionX;
    private int dimensionY;
    private int spawnPosX;
    private int spawnPosY;
    private ArrayList<Position> goalPos;
    private volatile Tiles[][] worldTiles;

    public int getDimensionX() {
        return dimensionX;
    }

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public int getSpawnPosX() {
        return spawnPosX;
    }

    public void setSpawnPosX(int spawnPosX) {
        this.spawnPosX = spawnPosX;
    }

    public int getSpawnPosY() {
        return spawnPosY;
    }

    public void setSpawnPosY(int spawnPosY) {
        this.spawnPosY = spawnPosY;
    }

    public ArrayList<Position> getGoalPos(){return this.goalPos;}

    public void setGoalPos(ArrayList<Position> goalPos) {
        this.goalPos = goalPos;
    }

    public Tiles[][] getWorldTiles() {
        return worldTiles;
    }

    public void setWorldTiles(Tiles[][] worldTiles) {
        this.worldTiles = worldTiles;
    }
}
