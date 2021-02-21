package controller.main;

import controller.*;
import controller.ActionListener.*;
import model.MapParser;
import model.ModelMapStruct;
import view.*;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class main {
    final static ArrayList<ImageResource> resourceList = new ArrayList<>();
    final static Integer fps = 60;

    public static void main(String[] args) {

        MapParser parser = new MapParser("map1", 8,8);
        parser.readXML();

        ModelMapStruct modelMap = parser.getModelMap();
        Gui gui = new Gui();
        Clock clock = new Clock(1000/fps, parser.getModelMap(), gui);

        resourceList.add(new ImageResource(ObjectTypes.GRASS, "img/Grass.png"));
        resourceList.add(new ImageResource(ObjectTypes.ROAD, "img/Dirt.png"));
        resourceList.add(new ImageResource(ObjectTypes.WATER,"img/Water.png"));
        resourceList.add(new ImageResource(ObjectTypes.UNIT_1,"img/Unit_1.png"));
        resourceList.add(new ImageResource(ObjectTypes.UNIT_TELEPORT,"img/Unit-teleport.png"));
        resourceList.add(new ImageResource(ObjectTypes.TOWER_1,"img/Tower_1.png"));
        resourceList.add(new ImageResource(ObjectTypes.ATTACK_CANNONBALL, "img/Shot.png"));


        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    //Init Modell ->

                    GuiMapStruct worldInfo = parser.getGuiMap();
                    //Load from XML ->
                    gui.setImageResourceList(resourceList);
                    gui.setGameWorld(worldInfo);
                    gui.guiInit();

                    //Set all listeners ->
                    gui.setAboutButtonListener(new AboutListener(gui));
                    gui.setBuyUnitListener(new BuyUnitListener(clock.getGameHandler().getModel()));
                    gui.setExitButtonListener(new ExitListener(clock, clock.getGameHandler().getModel()));
                    gui.setHelpButtonListener(new HelpListener(gui));
                    gui.setNewGameListener(new NewGameListener());
                    gui.setPauseButtonListener(new PauseListener(clock.getGameHandler()));
                    gui.setResumeButtonListener(new ResumeListener(clock.getGameHandler()));


                    //When everything is ready ->
                    gui.showGui();
                }
            });
        }catch (InvocationTargetException | InterruptedException e){
            e.printStackTrace();
        }
        clock.start();
    }

    public static Object[][] buildWorld(int x, int y){
        Object[][] world = new Object[x][y];

        for(int i=0; i<x; i++){
            for (int j=0; j<y; j++){
                world[i][j] = new Tile(ObjectTypes.GRASS, i, j);
            }
        }

        world[1][0] = new Tile(ObjectTypes.ROAD,0,0);
        world[1][1] = new Tile(ObjectTypes.ROAD,0,0);
        world[1][2] = new Tile(ObjectTypes.ROAD,0,0);
        world[1][3] = new Tile(ObjectTypes.ROAD,0,0);


        return world;
    }
}
