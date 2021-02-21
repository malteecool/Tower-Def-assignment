package controller;

import java.util.List;

/**
 * Struct used to pass whole game worlds around project
 * Contains worlds size (x/y dimensions) aswell as list of
 * all tiles in world and their respective TileType tags
 * @author Adam Bonnedahl (c13abl)
 */
public class GuiMapStruct {
    private int dimensionX;
    private int dimensionY;
    private volatile Object[][] worldTiles;

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

    public Object[][] getWorldTiles() {
        return worldTiles;
    }

    public void setWorldTiles(Object[][] worldTiles) {
        this.worldTiles = worldTiles;
    }
}
