package model;

import controller.GuiMapStruct;
import controller.ObjectTypes;
import controller.Tile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.*;
import java.util.ArrayList;

/**
 * Class to read xml-file containing mapdata.
 * The data is set to the WorldRunner.
 * The Map needs to named correctly to be parsed correctly.
 * (case sensitive)
 * Root: levels
 *  Element: map
 *      Element: row
 *          Element: col
 *
 * TODO:    The parser could be changed to jaxb-parser
 *
 */

public class MapParser {

    private Position startPos;
    private ArrayList<Position> finishPos;

    private Integer x;
    private Integer y;
    private Tiles[][] map;

    private DocumentBuilder builder;
    private DocumentBuilderFactory factory;
    private Document doc;
    private NodeList rowList;
    private String mapName;



    /**
     * @param mapName  Name of XML-file.
     * @param x     width of map as number of tiles.
     * @param y    height of map as number of tiles.
     */
    public MapParser(String mapName, Integer x, Integer y){

        this.x = x;
        this.y = y;
        map = new Tiles[x][y];
        this.mapName = mapName;
        finishPos = new ArrayList<>();

        try{
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.parse("levels.xml");
        }
        catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }

    }

    /**
     * The read-method takes every column in each row of the xml-file
     * and adds the invoked class to the map-array.
     *
     * @return 0 on succes, negative value on faliure.
     */
    public int readXML(){

        if(!this.validateXML()){
            System.err.println("Xml-file not valid");
            return -1;
        }

        Element content = getTagContent();

        NodeList settings = content.getElementsByTagName("settings");

        //readSettings(settings);

        rowList = content.getElementsByTagName("row");

        int posy = 0;
        for(int i = 0; i < rowList.getLength(); i++){

            Node rowNode = rowList.item(i);

            if(rowNode.getNodeType() == Node.ELEMENT_NODE){

                Element row = (Element)rowNode;
                NodeList colList = row.getChildNodes();

                int posx = 0;
                for(int j = 0; j < colList.getLength(); j++){

                    Node colNode = colList.item(j);

                    if(colNode.getNodeType() == Node.ELEMENT_NODE){

                        Element tile = (Element)colNode;

                        Tiles tileObj;

                        if(tile.hasAttribute("left") && tile.hasAttribute("right")){

                            Directions left = getDirections(tile.getAttribute("left"));
                            Directions right = getDirections(tile.getAttribute("right"));

                            tileObj = invokeTile(tile, left, right);
                        }
                        else{
                            tileObj = invokeTile(tile);
                        }

                        if(tileObj == null){
                            return -1;
                        }

                        if(tile.getAttribute("tile").equals("start")){
                            startPos = new Position(posx, posy);
                        }

                        if(tile.getAttribute("tile").equals("finish")){
                            System.out.println(posx + ":" + posy);
                            finishPos.add(new Position(posx, posy));
                        }



                        setTile(tileObj, posx, posy);
                        posx++;
                    }
                }
                posy++;
            }
            else{
                return -1;
            }
        }

        return 0;
    }

    /**
     * Checks for the wanted map by attribute.
     * If there is not map in the xml with the same
     * name, the default map is used.
     * @return The wanted map, or the default map.
     */
    private Element getTagContent(){

        NodeList map = doc.getElementsByTagName("map");
        Element element = (Element)map.item(0);

        for(int i = 0; i < map.getLength(); i++){

            Element node = (Element)map.item(i);
            if(node.hasAttribute(mapName)){
                return node;
            }
        }
        return element;
    }

    /**
     * Compares the xml map-file against a xsd to make
     * sure the map is correctly structured.
     * @return true if ok, else false.
     */
    private boolean validateXML(){

        try {
            Source xmlFile = new StreamSource(new File("levels.xml"));

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("levelsSchema.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
        }
        catch (SAXException | IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Convert string to direction.
     * @param dir The string to convert, takes enum values.
     * @return The direction found.
     */
    private Directions getDirections(String dir) {

        if(dir.equals("0")){
            return Directions.WEST;
        }
        if(dir.equals("1")){
            return Directions.EAST;
        }
        if(dir.equals("2")){
            return Directions.NORTH;
        }
        if(dir.equals("3")){
            return Directions.SOUTH;
        }
        return null;
    }

    /**
     * Method to get specific tile.
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return Tile at given position.
     */
    public Tiles getTile(Integer x, Integer y){
        return map[x][y];
    }

    /**
     * Creating the tile using reflection.
     * The tile needs to be implementing the Tiles
     * interface, or the method will return null.
     * Using classloader to for the current path
     * to find tile-classes. If the tiles are in
     * another directory it needs to be included
     * map-xml file.
     *
     * @param tile Tile element gotten from xml-file.
     * @return The created tile-object.
     */
    private Tiles invokeTile(Element tile){

        Object o = null;
        try{
            File dir = new File(".");
            URLClassLoader classLoader = URLClassLoader.newInstance(
                    new URL[]{dir.toURI().toURL()});

            Class<?> c = Class.forName(tile.getTextContent(), true, classLoader);

            if(!Tiles.class.isAssignableFrom(c)){
                return null;
            }

            Constructor<?> cons = c.getConstructor();
            o = cons.newInstance();

        }
        catch (ClassNotFoundException | NoSuchMethodException e){
            System.err.println("Invalid class");
        }
        catch (Exception e){
            System.err.println("Could not read xml.");
        }
        return (Tiles)o;
    }

    /**
     * Special invoke method to invoke TCrossingTile.
     * @param tile  Tile to be invoked.
     * @param left  Left direction of the TCrossing.
     * @param right Right direction of the TCrossing.
     * @return The invoked tile.
     */
    private Tiles invokeTile(Element tile, Directions left, Directions right){

        Object o;
        try{
            File dir = new File(".");
            URLClassLoader classLoader = URLClassLoader.newInstance(
                    new URL[]{dir.toURI().toURL()});

            Class<?> c = Class.forName(tile.getTextContent(), true, classLoader);

            if(!Tiles.class.isAssignableFrom(c)){
                return null;
            }

            Constructor<?> cons = c.getConstructor(Directions.class, Directions.class);
            o = cons.newInstance(left, right);

        }
        catch (ClassNotFoundException | NoSuchMethodException e){
            System.err.println("Invalid class");
            return null;
        }
        catch (Exception e){
            System.err.println("Could not read xml.");
            return null;
        }
        return (Tiles)o;
    }

    /**
     * Sets the tile at a given position/index.
     * @param tile  Tile to be set.
     * @param x     X index to be set at.
     * @param y     Y index to be set at.
     */
    private void setTile(Tiles tile, Integer x, Integer y){
        map[x][y] = tile;
    }

    /**
     * Get the start tile.
     * @return Start tile.
     */
    public Position getStartPos() {
        return startPos;
    }

    /**
     * Gets the finish tile.
     * @return Finish tile.
     */
    public Position getFinishPos() {
        return finishPos.get(0);
    }

    /**
     * Gets the whole map ModelMapStruct.
     * @return The map.
     */
    public ModelMapStruct getModelMap(){

        ModelMapStruct mapStruct = new ModelMapStruct();

        mapStruct.setWorldTiles(map);

        if(startPos == null || finishPos == null){
            System.err.println("Start or finish-position is not set.");
        }

        mapStruct.setSpawnPosX((int)startPos.getXPosition());
        mapStruct.setSpawnPosY((int)startPos.getYPosition());

        mapStruct.setGoalPos(finishPos);

        mapStruct.setDimensionX(x);
        mapStruct.setDimensionY(y);

        return mapStruct;
    }

    /**
     * Gets a special map for the GUI.
     * @return Map for the gui.
     */
    public GuiMapStruct getGuiMap(){

        Object[][] guiMap = new Object[x][y];

        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){

                switch (map[i][j].getClass().getSimpleName()){

                    case "PathTile":
                        guiMap[i][j] = new Tile(ObjectTypes.ROAD, i, j);
                        break;

                    case "GrassTile":
                        guiMap[i][j] = new Tile(ObjectTypes.GRASS, i, j);
                        break;

                    case "TCrossingEndTile":
                        guiMap[i][j] = new Tile(ObjectTypes.ROAD, i, j);
                        break;

                    case "TCrossingTile":
                        guiMap[i][j] = new Tile(ObjectTypes.ROAD, i, j);
                        break;

                    case "TeleportTile":
                        guiMap[i][j] = new Tile(ObjectTypes.ROAD, i, j);
                        break;
                }

            }
        }

        GuiMapStruct mapStruct = new GuiMapStruct();

        mapStruct.setDimensionX(x);
        mapStruct.setDimensionY(y);
        mapStruct.setWorldTiles(guiMap);

        return mapStruct;
    }

    public Tiles[][] getWorldTiles(){
        return this.map;
    }

}
