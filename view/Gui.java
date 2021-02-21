package view;

import controller.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/** The main GUI-class
 * As the main class its the primary Integererface between the view and controller in
 * the MVC program structure. Handles setting all listeners for various events in the gui
 * and builds the gui.
 * @author Adam Bonnedahl (c13abl)
 * @version 1.0
 *
 */

public class Gui {

    Integer numberOfBuyUnitButtons = 6;
    boolean gameIsPaused;

    ArrayList<JButton> buyUnitButtonList;
    JFrame mainWindow;
    //Main window JPanels
    JPanel infoPanel;
    JPanel buttonPanel;
    JPanel gamePanel;

    //Info panel
    JTextArea showTimeArea;
    JTextArea showCreditArea;

    //Main menu bar
    JMenuBar menuBar;
    JMenu mainMenu;
    JMenu extraMenu;
    JMenuItem exitButton;
    JMenuItem aboutButton;
    JMenuItem pauseButton;
    JMenuItem resumeButton;
    JMenuItem helpButton;
    JMenuItem replayLevelButton;
    JMenuItem newGameButton;


    //GameAnimator
    List<ImageResource> resourceList;
    GuiMapStruct currentGameWorld;
    ArrayList<ObjectPosition> unitPositions;
    ArrayList<AttackEvents> attackEvents;


    //Gui
    MainWindowBuilder builder;
    AboutDialog aboutDialog;
    HelpDialog helpDialog;
    GameAnimator animator;
    LossDialog lossDialog;
    WinDialog winDialog;

    public Gui(){


    }
    /**
     * A method for setting settings and values before initialization on the GUI
     */
    public void setBeforeInit (){
        //Things to be set before GUI initialization
    }

    /**
     * Initializes the GUI and builds the main window.
     * Uses settings set via setBeforeInit
     * The methods return nothing, instead presenting the gui to the user
     *
     */
    public void guiInit(){
        builder = new MainWindowBuilder();
        helpDialog = new HelpDialog();
        aboutDialog = new AboutDialog();
        lossDialog = new LossDialog();
        winDialog = new WinDialog();
        builder.buildMainWindow(this);
        animator.loadResources(resourceList);
        animator.setGameWorld(currentGameWorld);

        aboutDialog.setAboutText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Morbi eget dignissim arcu. Nulla rhoncus eros purus, et pretium lorem efficitur finibus. " +
                "Praesent rhoncus, neque volutpat ultricies interdum, diam leo vulputate sem, eget vulputate nibh ipsum et lacus.");

    }
    public void showGui(){
        mainWindow.setVisible(true);
    }

    /**
     * Receives the information about the game world, including world size
     *  and what tile type to display at each position.
     */
    public void setGameWorld(GuiMapStruct currentGameWorld){
        //Sets which game world the gui should render
        this.currentGameWorld = currentGameWorld;
        if(this.animator != null) {
            this.animator.setGameWorld(this.currentGameWorld);
        }
    }

    public void setAttacks(ArrayList<AttackEvents> attackEvents){
        this.attackEvents = attackEvents;
        if(this.animator != null){
            this.animator.setAttackEvents(this.attackEvents);
        }
    }

    /**
     * Recieves the updated hashmap of unit positions used to animate unit movement through the map.
     * The hashmap should contain structs defining unit-id, unit taf,x position and y position.
     * @param newObjectPositions the thread-safe hashmap consisting of structs
     */
    public void updateObjectPositions(ArrayList<ObjectPosition> newObjectPositions){
        //Sets new list of unit positions
        this.unitPositions = newObjectPositions;
        if(this.animator != null) {
            this.animator.setNewObjectPositions(this.unitPositions);
        }
    }

    /**
     * Recieves the list of image resources used to draw the world and different elements.
     * The list should contain structs defining resource path and resource tag
     * @param resourceList List of structs
     */
    public void setImageResourceList(List<ImageResource> resourceList){
        //Sets list for all image resources with their respective type-tags
        this.resourceList = resourceList;

    }

    //public void timeTick(List attackingTurrets){
    public void update(){
        //Triggered each time-tick (updates GUI, time and credits)
        //Contains list of turrets that fires on units
        animator.update();
    }

    /**
     * Method to set users current credit amount
     * @param newCredits users current credit amount
     */
    public void setCurrentCredits (int newCredits){
        //Updates currently displayed credits
        showCreditArea.setText("Credits: " + newCredits);
    }

    /**
     * Method to set current game time in seconds
     * @param seconds current game time in seconds
     */
    public void setCurrentTime (int seconds){
        //Updates currently displayed time

        int minutes = seconds / 60;
        seconds = seconds - (60* minutes);
        if ( minutes <= 0 && seconds <= 0){
            showTimeArea.setText("Time: 0:00");
        }else if (seconds < 10){
            showTimeArea.setText("Time: "+minutes+":0"+seconds);
        }else {
            showTimeArea.setText("Time: " + minutes + ":" + seconds);
        }
    }

    //Methods to show different GUI components in accordance with user action

    /**
     * Method to call when the "Win" popup is to be shown to the player
     * Opens the popup showing the highscore-list and prompts the user to
     * play next level, replay level or exit
     */
    public void showWinPopup(ArrayList<HighscoreEntry> highScore){
        //shows Win popup when called
        winDialog.setVisible();
    }

    /**
     * Method to call when the "Loss" popup is to be shown to the player.
     * Opens the popup showing the highscore-list and prompts the user to replay level or exit
     */
    public void showLossPopup(ArrayList<HighscoreEntry> highScore){
        //shows Loss popup when called
        lossDialog.setHighScoreList(highScore);
        lossDialog.setVisible();
    }

    /**
     * Method to call when the "About" page is to be shown to the user
     */
    public void showAboutPopup(){
        //shows About popup when called
        aboutDialog.setVisible();
    }

    /**
     * Method to call when the "Help" page is to be shown to the user
     */
    public void showHelpPopup(){
        //shows Help popup when called
        helpDialog.setVisible();
    }

    /**
     * When called, clears the game map of all non-tile
     * entities (towers and units)
     */
    public void clearWorldEntities(){
        animator.clearObjects();
    }
    /**
     * Called to mark the game as paused in the GUI
     */
    public void gameIsPaused(){
        gameIsPaused = true;
        pauseButton.setVisible(false);
        resumeButton.setVisible(true);
    }
    /**
     * Called to mark the game as running in the GUI
     */
    public void gameIsRunning(){
        gameIsPaused = false;
        pauseButton.setVisible(true);
        resumeButton.setVisible(false);
    }

    //Setters for all possible listeners

    /**
     * Sets the action listener for the new game button found in various views of the game
     * @param newGameListener the action listener for when the user presses "new game"
     */
    public void setNewGameListener(ActionListener newGameListener){
        newGameButton.addActionListener(newGameListener);
    }

    /**
     * Sets the action listener for the restart game button in the menu bar and loss/win popups
     * @param restartGameListener the action listener used when restarting the game
     */
    public void setReplayLevelListener(ActionListener restartGameListener){
        replayLevelButton.addActionListener(restartGameListener);
        lossDialog.setPlayAgainListener(restartGameListener);
        winDialog.setPlayAgainListener(restartGameListener);
    }

    /**
     * Sets the action listener used when the user press "quit/exit"
     * @param exitButtonListener action listener used to exit the game
     */
    public void setExitButtonListener (ActionListener exitButtonListener){
        exitButton.addActionListener(exitButtonListener);
        lossDialog.setExitButtonListener(exitButtonListener);
        winDialog.setExitButtonListener(exitButtonListener);
    }

    /**
     * Sets the action listener used when the users press the pause button
     * @param pauseButtonListener action listener used when pausing the game
     */
    public void setPauseButtonListener (ActionListener pauseButtonListener){
        pauseButton.addActionListener(pauseButtonListener);
    }

    /**
     * Sets the action listener used when the user press the resume button
     * @param resumeButtonListener action listener used when resuming the game
     */
    public void setResumeButtonListener (ActionListener resumeButtonListener){
        resumeButton.addActionListener(resumeButtonListener);
    }

    /**
     * Sets the action listener used when the user presses the About button
     * @param aboutButtonListener action listener used to show about popup
     */
    public void setAboutButtonListener(ActionListener aboutButtonListener){
        aboutButton.addActionListener(aboutButtonListener);
    }

    /**
     * Sets the action listener used when the user presses the Help button
     * @param helpButtonListener action listener used to show the help popup
     */
    public void setHelpButtonListener (ActionListener helpButtonListener){
        helpButton.addActionListener(helpButtonListener);
    }

    /**
     * Sets the action listener used when the user press the next level button on the win-popup
     * @param nextLevelListener action listener used for starting the next level
     */
    public void setNextLevelListener (ActionListener nextLevelListener){
        winDialog.setNextLevelButtonListener(nextLevelListener);
    }

    /**
     * Sets the action listener used when the user press any "buy unit" button.
     * The subsequent action event has its ActionCommand set to the unit type tag
     * corresponding to the buy-unit-type-button pressed
     * @param buyUnitListener action listener for when the users buys a unit
     */
    public void setBuyUnitListener (ActionListener buyUnitListener){
        //TODO
        //Also sends unit ID with call imply which unit was bought
        for (JButton button:buyUnitButtonList){
            button.addActionListener(buyUnitListener);
        }
    }

    /**
     * Sets the action listener used when the user orders a teleport-unit to place its portal
     * The subsequent action event has its ActionCommand set to the unit ID of the unit that
     * issued the command
     * @param placePortalListener action listener too be activated when the user clicks a teleporter unit
     */
    public void setPlacePortalListener (ActionListener placePortalListener){
        //TODO
        //Includes ID of portal unit that placed portal?
        animator.setTeleportUnitButtonListener(placePortalListener);
    }

    /**
     * Sets the action listener used when the users click on a crossing to change its direction
     * The subsequent action event has its ActionCommand set to the x/y coordinate of the
     * crossing that was clicked
     * @param crossingChangeListener action listener for when the user clicks an crossing
     */
    public void setCrossingChangeListener (ActionListener crossingChangeListener){
        //TODO
    }

}
