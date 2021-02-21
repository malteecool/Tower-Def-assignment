package view;

import controller.ObjectTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 *
 * Builder for the main gui window. Builds the gui into a Jframe
 * @author Adam Bonnedahl (c13abl)
 */
class MainWindowBuilder {
    //Dimensions are later to be made variables and moved to controller as static defines
    private Dimension minDim = new Dimension(10,25);
    private Dimension prefDim = new Dimension(15,40);
    private Dimension maxDim = new Dimension(20,60);
    private Dimension fill = new Dimension(10, 10);
    private Dimension gameSize = new Dimension(800,600);
    private Dimension bPrefDim = new Dimension(120,40);
    private Dimension bMinDim = new Dimension(20,30);
    private Dimension bPanelPrefDim = new Dimension(150,500);


    /**
     * Main method that builds combines different components of the gui
     * Adds all components to main window using GridBag layout
     */
     void buildMainWindow(Gui gui){
        gui.mainWindow = new JFrame("Anti-TD Grupp 19");
        gui.mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE/**/);
        gui.mainWindow.addWindowListener(createCloseWindowListener(gui));
        gui.mainWindow.setLayout(new GridBagLayout());
        ViewHelper helper = new ViewHelper();
        //Menu bar
        gui.mainWindow.setJMenuBar(buildMenuBar(gui));
        //Button Panel
        gui.mainWindow.add(buildButtonPanel(gui), helper.setGridBagConstraints(
                GridBagConstraints.NONE,
                1,0, 1,1,1,
                0,0,5
        ));
        //Info panel
        gui.mainWindow.add(buildInfoPanel(gui), helper.setGridBagConstraints(
                GridBagConstraints.NONE,
                0,0,1,0,1,
                0, 0, 5
        ));
         //Game Panel
         gui.mainWindow.add(buildGamePanel(gui),helper.setGridBagConstraints(
                 GridBagConstraints.BOTH, 1,1,0,1,
                 1,5, 5,5
         ));
         //gui.mainWindow.add(gui.animator);
         Dimension mainWindowSize = new Dimension((
                 gui.currentGameWorld.getDimensionX()*gui.animator.IMG_WIDTH) + gui.buttonPanel.getPreferredSize().width,
                 (gui.currentGameWorld.getDimensionY()*gui.animator.IMG_HEIGHT)+150);
         System.out.println("x:" + mainWindowSize.width + " y:" + mainWindowSize.height);
         //Dimension tmpM = new Dimension(600, 500);
        gui.mainWindow.setPreferredSize(mainWindowSize);
        //gui.mainWindow.setPreferredSize(gameSize);
        gui.mainWindow.pack();
        gui.mainWindow.setLocationRelativeTo(null);
        gui.mainWindow.setVisible(false);
     }

    /**
     * Builds the top-right info-panel which displays time and the users credit amount
     * @param gui Reference to the main gui class so it later can access certain components
     * @return the finished JPanel
     */
    private JPanel buildInfoPanel(Gui gui){
        gui.infoPanel = new JPanel();
        gui.infoPanel.setLayout(new BoxLayout(gui.infoPanel, BoxLayout.Y_AXIS));
        Font arialBold = new Font("Arial", Font.BOLD, 16);

        gui.showTimeArea = new JTextArea("<Time: 10:10 >");
        gui.showTimeArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        gui.showTimeArea.setFont(arialBold);
        gui.showTimeArea.setEditable(false);
        gui.showTimeArea.setOpaque(false);

        gui.showCreditArea = new JTextArea("<Credit: 100000 >");
        gui.showCreditArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        gui.showCreditArea.setFont(arialBold);
        gui.showCreditArea.setEditable(false);
        gui.showCreditArea.setOpaque(false);

        gui.infoPanel.add(gui.showTimeArea);
        gui.infoPanel.add(gui.showCreditArea);
        gui.infoPanel.setPreferredSize(new Dimension(150,50));
        gui.infoPanel.setBackground(Color.green); //DEBUGGING
        return gui.infoPanel;
    }

    /**
     * Builds the right hand side button panel
     * @param gui Reference to the main gui class so it later can access certain components
     * @return the finished JPanel
     */
    private JPanel buildButtonPanel(Gui gui){
        ViewHelper helper = new ViewHelper();
        gui.buttonPanel = new JPanel();
        gui.buttonPanel.setLayout(new GridBagLayout());

        //Temp Setting options for buttons
        gui.buyUnitButtonList = new ArrayList<>();
        for (int i=0;i<gui.numberOfBuyUnitButtons+1;i++){
            GridBagConstraints buyUnitButtonConstraints = helper.setGridBagConstraints(
                    GridBagConstraints.NONE,
                    0,0,0, i, 0, 5,10,5
            );
            //If after last button to be added (+1 in loop above)
            //Create an invisible box.filler object with y-axis weighting
            //too absorb all extra space underneath and push buttons upwards
            if(i == gui.numberOfBuyUnitButtons){
                buyUnitButtonConstraints.weighty = 1;
                Box.Filler filler = new Box.Filler(bPrefDim,bPrefDim,bPrefDim);
                gui.buttonPanel.add(filler, buyUnitButtonConstraints);
            }else {
                gui.buyUnitButtonList.add(new JButton("Unit " + i));
                gui.buyUnitButtonList.get(i).setPreferredSize(bPrefDim);
                gui.buttonPanel.add(gui.buyUnitButtonList.get(i), buyUnitButtonConstraints);
                switch (i) {
                    case 0:
                        gui.buyUnitButtonList.get(i).setActionCommand(ObjectTypes.UNIT_1.toString());
                        break;
                    case 1:
                        gui.buyUnitButtonList.get(i).setActionCommand(ObjectTypes.UNIT_TELEPORT.toString());
                        break;
                    default:
                }
            }
        }

        gui.buttonPanel.setPreferredSize(bPanelPrefDim);
        gui.buttonPanel.setBackground(Color.magenta); //DEBUGGING
        return gui.buttonPanel;
    }

    /**
     * Builds the top menu bar
     * @param gui Reference to the main gui class so it later can access certain components
     * @return the finished menu bar
     */
    private JMenuBar buildMenuBar(Gui gui){
        gui.menuBar = new JMenuBar();
        gui.mainMenu = new JMenu("Menu");
        gui.extraMenu = new JMenu("About/Help");
        gui.exitButton = new JMenuItem("Quit");
        gui.pauseButton = new JMenuItem("Pause");
        gui.resumeButton = new JMenuItem("Resume");
        gui.aboutButton = new JMenuItem("About");
        gui.helpButton = new JMenuItem("Help");
        gui.newGameButton = new JMenuItem("New Game");
        gui.replayLevelButton = new JMenuItem("Restart Game");

        gui.mainMenu.add(gui.newGameButton);
        gui.mainMenu.add(gui.replayLevelButton);
        gui.mainMenu.add(gui.pauseButton);
        gui.mainMenu.add(gui.resumeButton);
        gui.mainMenu.add(gui.exitButton);

        gui.extraMenu.add(gui.aboutButton);
        gui.extraMenu.add(gui.helpButton);
        //resumeButton.setVisible(false);

        gui.menuBar.add(gui.mainMenu);
        gui.menuBar.add(gui.extraMenu);
        return gui.menuBar;
    }

    /**
     * Initiates the game animator
     * @param gui Reference to the main gui class so it later can access certain components
     * @return the finished game animator which extends JPanel
     */
    private GameAnimator buildGamePanel(Gui gui){
        gui.animator = new GameAnimator(gui);
        gui.animator.setBackground(Color.cyan); //DEBUGGING
        return gui.animator;
    }

    /**
     * Creates a custom window listener that only responds to the window being disposed (closed)
     * @param gui the main gui class
     * @return the custom window listener
     */
    private WindowListener createCloseWindowListener(Gui gui){
        return (new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                gui.exitButton.doClick();
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {
            }
        });
    }
}


/*gui.gamePanel = new JPanel();
        //gui.gamePanel.setPreferredSize(gameSize);
        //gui.gamePanel.setBackground(Color.LIGHT_GRAY);
        //Border fancyBorder = BorderFactory.createCompoundBorder(
        //                    BorderFactory.createRaisedBevelBorder(),
        //                    BorderFactory.createLoweredBevelBorder());
        //Border paddingBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        //Border comboBorder = BorderFactory.createCompoundBorder(paddingBorder, fancyBorder);
        //gui.gamePanel.setBorder(comboBorder);

        //gui.gamePanel.add(new DrawStuff());
        //gui.gamePanel.revalidate();
        //gui.gamePanel.repaint();
        //gui.gamePanel.setVisible(true);
        //Add everything else needed to be able to draw the game later on...
        //gui.animator.loadResources(gui.resourceList);
        //gui.animator.setGameWorld(gui.currentGameWorld);
        //gui.animator.revalidate();
        //gui.animator.update();
        //System.out.println("W: " + gui.animator.getPrefferedSize().width + " H: " + gui.animator.getPrefferedSize().width);
        //gui.animator.setSize(gui.animator.getPrefferedSize());
        //gui.animator.setSize(new Dimension(800, 600));
* */