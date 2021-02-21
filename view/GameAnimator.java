package view;

import controller.*;
import model.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles the drawing of objects and the game world
 * Extends JPanel to give a "surface" to draw on.
 * Implements MouseListener to enable on-click interactivity
 * @author Adam Bonnedahl
 */
public class GameAnimator extends JPanel implements MouseListener{
    //Defines
    private int GAME_WIDTH_TILES = 9;
    private int GAME_HEIGHT_TILES = 6;
    int IMG_WIDTH = 32;
    int IMG_HEIGHT = 32;
    private int ATTACK_DECAY = 40;
    private int ATTACK_TIME_ANIMATED = 500; //500ms
    private int GAME_WIDTH_PIX ;//IMG_WIDTH*GAME_WIDTH_TILES;
    private int GAME_HEIGHT_PIX ;//IMG_HEIGHT*GAME_HEIGHT_TILES;

    //
    private Gui view;
    private GuiMapStruct worldInfo;
    private ArrayList<ObjectPosition> objectPositions;
    private HashMap<Integer, Rectangle> activeUnitShapes;
    private ArrayList<AttackEvents> attackEvents;
    private HashMap<ObjectTypes, BufferedImage> loadedImages;
    private ArrayList<InternalAttackEvent> internalAttackEvents;
    private JButton teleportUnitButton;

    /** Constructor of the class, sets some internal variables and initiates internal variables
     *
     * @param view
     */
    GameAnimator(Gui view){
        this.view = view;
        this.worldInfo = view.currentGameWorld;
        this.addMouseListener(this);
        internalAttackEvents = new ArrayList<>();
        teleportUnitButton = new JButton();
    }

    /** Method that loads all resources into BufferedImages for later use,
     * takes a ImageResource list and parses it.
     * @param resourceList List of ImageResources to be loaded
     */
    void loadResources(List<ImageResource> resourceList) {
        loadedImages = new HashMap<>();
        if(resourceList != null) {
            for (ImageResource imgRes : resourceList) {
                try {
                    BufferedImage tmpImg = ImageIO.read(new File(imgRes.getPath()));
                    loadedImages.put(imgRes.getType(), tmpImg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            System.err.println("Empty resource list, unable to continue");
        }
    }

    /**
     * Method needed since class implments MouseListener.
     * Functionality not used since program doesn't need it
     * @param mouseEvent Ignored
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {}
    /**
     * Method needed since class implments MouseListener.
     * Functionality not used since program doesn't need it
     * @param mouseEvent Ignored
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}
    /**
     * Method needed since class implments MouseListener.
     * Functionality not used since program doesn't need it
     * @param mouseEvent Ignored
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
    /**
     * Method needed since class implments MouseListener.
     * Functionality not used since program doesn't need it
     * @param mouseEvent Ignored
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent){}

    /**
     * Catches mouse-clicks and checks if the click was on a teleporter-unit.
     * Clicks and imaginary button to notify controller if that is the case.
     * @param e MouseClicked event
     */
    @Override
    public void mouseClicked(MouseEvent e){
        SwingWorker clickedWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() {
            Point p = e.getPoint();
            Integer tmpId = -1;
            ObjectTypes tmpType = null;
            for (Map.Entry<Integer, Rectangle> entry: activeUnitShapes.entrySet()){
                if (entry.getValue().contains(p)){
                    for (ObjectPosition unitPosition : objectPositions) {
                        if (entry.getKey() == unitPosition.getUnitId()) {
                            if(unitPosition.getUnitType() == ObjectTypes.UNIT_TELEPORT) {
                                tmpId = unitPosition.getUnitId();
                                tmpType = unitPosition.getUnitType();
                                System.out.println("Clicked on ID: "+ tmpId + " : " + tmpType);
                                teleportUnitButton.setActionCommand(tmpId.toString());
                                teleportUnitButton.doClick();
                            }
                        }
                    }
                }
            }
            return null;
            }
        };
        clickedWorker.execute();
    }

    /**
     * Sets the internal ButtonListener used when the user clicks on a teleport-unit
     * @param listener Listener from controller that responds to teleport-unit clicks
     */
    void setTeleportUnitButtonListener(ActionListener listener){
        teleportUnitButton.addActionListener(listener);
    }

    /**
     * Method that draws the game world onto a BufferedImage using the game world defined in the worldInfo-struct.
     * Draws each tile using the ObjectType to define which sprite to use
     * @return the resulting BufferedImage
     */
    private BufferedImage drawGameWorld(){
        GAME_WIDTH_TILES = this.worldInfo.getDimensionX();
        GAME_HEIGHT_TILES = this.worldInfo.getDimensionY();
        Object[][] world = this.worldInfo.getWorldTiles();
        BufferedImage img = new BufferedImage(this.worldInfo.getDimensionX() * IMG_WIDTH,
                worldInfo.getDimensionY() * IMG_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = (Graphics2D) img.getGraphics();

        for(int i=0; i<this.worldInfo.getDimensionX(); i++){
            for (int j=0; j<this.worldInfo.getDimensionY(); j++){
                Tile currentTile = (Tile)world[i][j];
                g2d.drawImage(loadedImages.get(currentTile.getTileType()), i*IMG_WIDTH, j*IMG_HEIGHT, this);
            }
        }
        g2d.dispose();
        /*int tmpW, tmpH;
        if (this.worldInfo.getDimensionX() * IMG_WIDTH < 700 &&
                this.worldInfo.getDimensionY() * IMG_HEIGHT < 500){
            tmpW = 700;
            tmpH = 500;
        }else{
            tmpW = this.worldInfo.getDimensionX() * IMG_WIDTH;
            tmpH = this.worldInfo.getDimensionY() * IMG_HEIGHT;
        }
        setPreferredSize(new Dimension(tmpW,tmpH));*/
        return img;
    }

    /**
     * Sets new UnitPositions for the next draw-cycle
     * @param newUnits List of UnitPositions
     */
    void setNewObjectPositions(ArrayList<ObjectPosition> newUnits){
        this.objectPositions = newUnits;
    }

    /**
     * Method to call when the gui should redraw the world with current information
     */
    void update(){
        repaint();
    }

    /**
     * Sets new AttackEvents for next draw cycle.
     * AttackEvents represent each new attack the current cycle
     * @param attackEvents AttackEvents list used for next draw cycle
     */
    void setAttackEvents(ArrayList<AttackEvents> attackEvents){
        this.attackEvents = attackEvents;
    }

    /**
     * Sets the game world to be used in next draw cycle. Can be changed at any time.
     * @param worldInfo GameWorldStruct containing new world
     */
    void setGameWorld(GuiMapStruct worldInfo){
        this.worldInfo = worldInfo;
    }

    /**
     * Draws all objects in the game world using previously set objectPosition.
     * Also draws invisible shape underneath sprite to allow click-on detection
     * @param workImage BufferedImage to draw upon
     */
    private void drawObjects(BufferedImage workImage){

        Graphics2D g2d = (Graphics2D) workImage.getGraphics();

        if(objectPositions != null) {
            activeUnitShapes = new HashMap<>();
            for (ObjectPosition tmpUnit : objectPositions) {

                //Draw image
                g2d.drawImage(loadedImages.get(tmpUnit.getUnitType()),
                        (int)tmpUnit.getPosX(), (int)tmpUnit.getPosY(), this);
                //Draw empty Shape on top for to detect click
                Rectangle tmpRect = new Rectangle((int)tmpUnit.getPosX(),
                        (int)tmpUnit.getPosY(),
                        IMG_WIDTH,
                        IMG_HEIGHT);
                Color transpColor = new Color(255,255,244,0);
                g2d.setPaint(transpColor);
                //g2d.setPaint(Color.MAGENTA);//DEBUGGING ONLY
                activeUnitShapes.put(tmpUnit.getUnitId(),tmpRect);
                g2d.draw(tmpRect);
            }
        }
        g2d.dispose();
    }


    private void drawAttacks(BufferedImage workImage){
        Graphics2D g2d = (Graphics2D)workImage.getGraphics();
        ViewHelper helper = new ViewHelper();

        //Adds new events to internal attack list
        if(attackEvents != null) {
            for (AttackEvents atk : attackEvents) {
                InternalAttackEvent e = new InternalAttackEvent(
                        atk.getAttackFrom(),
                        atk.getAttackTo());
                if(atk.getAttackType() == ObjectTypes.ATTACK_BEAM) {
                    e.setAttackType(atk.getAttackType());
                    e.setAttackColor(atk.getAttackColor());
                    e.setAttackDecayLeft(atk.getAttackDecay());
                }else if(atk.getAttackType() == ObjectTypes.ATTACK_CANNONBALL){
                    e.setAttackType(atk.getAttackType());
                    e.setAttackVelocity(atk.getAttackVelocity());
                }
                internalAttackEvents.add(e);
            }
        }
        //Draws internal attack list
        for (InternalAttackEvent intAtk: internalAttackEvents){
            if(intAtk.getAttackType() == ObjectTypes.ATTACK_CANNONBALL) {
                Position attackPos = helper.calculateTrajectory(intAtk.getCurrentPosition(), intAtk.getTargetPos(), intAtk.getAttackVelocity(), IMG_WIDTH);
                if (attackPos == null) {
                    intAtk.reachedTarget(true);
                } else {
                    intAtk.setCurrentPosition(attackPos);
                    g2d.drawImage(loadedImages.get(ObjectTypes.ATTACK_CANNONBALL), (int) attackPos.getXPosition() + 16, (int) attackPos.getYPosition() + 16, this);
                }
            }else if(intAtk.getAttackType() == ObjectTypes.ATTACK_BEAM) {
                if(intAtk.getAttackDecayLeft() != 0){
                    g2d.setColor(intAtk.getAttackColor());
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
                    g2d.drawLine(
                            (int)intAtk.getFromPos().getXPosition()+(IMG_WIDTH/2),
                            (int)intAtk.getFromPos().getYPosition()+(IMG_HEIGHT/2),
                            (int)intAtk.getTargetPos().getXPosition()+(IMG_WIDTH/2),
                            (int)intAtk.getTargetPos().getYPosition()+(IMG_HEIGHT/2)
                    );
                    intAtk.setAttackDecayLeft(intAtk.getAttackDecayLeft() - 1);
                    if(intAtk.getAttackDecayLeft() == 0){
                        intAtk.reachedTarget(true);
                    }
                }
            }
        }
        internalAttackEvents.removeIf(InternalAttackEvent::hasReachedTarget);
        g2d.dispose();
    }

    /**
     * Clears all objects from draw-lists and redraws world
     */
    void clearObjects(){
        internalAttackEvents.clear();
        objectPositions.clear();
        repaint();
    }

    /**
     * Method that is called when repaint is called.
     * Draws new image and redraws visible image with new image
     * @param g @link{paintComponent}
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        BufferedImage img  = drawGameWorld();
        drawObjects(img);
        drawAttacks(img);
        g.drawImage(img, 0,0,this);
    }
}
