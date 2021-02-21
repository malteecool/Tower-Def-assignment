package controller;


import model.*;
import view.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.TimerTask;

/**
 * Sets actionlisteners and works in the middle of model
 * and view.
 */
public class GameHandler extends TimerTask {

    /*Public Variable only used for test*/
    private boolean run;
    private int iterations = 0;

    /* list of events */
    private LinkedBlockingDeque userEvents;

    /* view and model in MVC */
    private Gui view;
    private WorldRunner model;

    private Integer numSecondsPassed;

    /**
     * Creates a new Gui and sets all ActionsListeners in the Gui.
     * Creates a new WorldRunner with the map size, WorldRunner handles all background data.
     * The GameHandler extends TimerTask and implements a run method which is invoked in
     * every clock-iteration.
     * @param clock Clock that handles the updates of the controller.
     */
    public GameHandler(Clock clock, ModelMapStruct modelMap, Gui view){
        this.view = view;
        this.run = true;
        this.userEvents = new LinkedBlockingDeque< ActionEvent >();
        model = new WorldRunner(modelMap);
        model.addTowers(8);
        this.numSecondsPassed = 0;

        /*try {
            model.createUnit("Unit1");
        } catch (IllegalUnitName e) {
            System.out.format("You are totaly stupid man%n");
        }*/

    }

    /**
     * Pauses the game if it is currently running.
     */
    public void setPause() {this.run = false;}

    /**
     * Resumes the game if its currently paused.
     */
    public void setResume() {this.run = true;}


    public void run(){

        if (run) {

            if (iterations % 60 == 0) {
                model.decrementGameTime();
                model.increaseWallet(1);
                --numSecondsPassed;
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    updateObjectsForView();
                    view.update();
                    /*
                    send update if not first cycle

                    arrayOfShotTargets = mass runTower
                    arrayOfMovedUnits = mass runUnit

                    create update,
                    */

                }
            });
            model.moveAllUnits();
            model.removeUnits();
            ++iterations;
        }
    }

    /**
     * Method only used for testing of clock-frequency.
     * This can be removed in a later state.
     * @return Number of interations done by the clock.
     */
    public int getIterations(){
        return iterations;
    }

    public WorldRunner getModel() {
        return this.model;
    }

    //TODO: No model calls should be done here. We should move this to a separate method.
    public void updateObjectsForView() {

        if (iterations % 60 == 0) {
            view.setCurrentTime((numSecondsPassed + 600));
            System.out.println(numSecondsPassed);
        }

        if (model.hasWon()) {
            ArrayList<HighscoreEntry> highScores = new ArrayList<>();
            HighscoreEntry highScore = new HighscoreEntry("Hans", 1000);
            highScores.add(highScore);
            view.showWinPopup(highScores);
            run = false;
        } else if (model.hasLost()) {
            ArrayList<HighscoreEntry> highScores = new ArrayList<>();
            HighscoreEntry highScore = new HighscoreEntry("Hans", 1000);
            highScores.add(highScore);
            view.showLossPopup(highScores);
            run = false;
        }

        //TODO: These should be shared and thread safe.
        ArrayList<GroundUnit> units = model.getAllUnits();
        ArrayList<Tower> towers = model.getTowerList();
        ArrayList<Attack> attacks = model.runTowers();
        ArrayList<ObjectPosition> updatedObjects = new ArrayList<>();
        ArrayList<AttackEvents> attackEvents = new ArrayList<>();

        for (int i = 0; i < units.size(); i++) {

            if (units.get(i) != null) {

                if (units.get(i) instanceof Unit1) {
                    ObjectPosition newPosition = new ObjectPosition(i, ObjectTypes.UNIT_1, units.get(i).getPosition());
                    updatedObjects.add(newPosition);
                }
            }
        }

        for (int i = 0; i < towers.size(); i++) {

            if (towers.get(i) != null) {
                ObjectPosition newTower = new ObjectPosition(i, ObjectTypes.TOWER_1, towers.get(i).getPosition());
                updatedObjects.add(newTower);
            }
        }

        for (int i = 0; i < attacks.size(); i++) {

            if (attacks.get(i) != null) {
                AttackEvents newAttack =
                        new AttackEvents(attacks.get(i).getAttackFrom(), attacks.get(i).getAttackTo(), ObjectTypes.ATTACK_CANNONBALL);
                newAttack.setAttackVelocity(2);
                attackEvents.add(newAttack);
            }
        }
        view.updateObjectPositions(updatedObjects);
        view.setAttacks(attackEvents);
        view.setCurrentCredits(model.getCurrentCredits());
    }


}
