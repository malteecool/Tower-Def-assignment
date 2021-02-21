package controller;

import model.ModelMapStruct;
import view.*;

import java.util.TimerTask;
import java.util.Timer;

/**
 * Class to perform updates on the GameHandler. Clock works
 * as a timer and performs iterations based on a interval/delay.
 *
 */
public class Clock {

    private int interval;
    private Timer timer;
    private TimerTask task;
    private GameHandler gameHandler;

    /**
     * Initialized the clock with a gamehandler which should
     * be updated.
     * To start the clock, the start-method should be used.
     * @param interval Interval of which the clock should update.
     */
    public Clock(int interval, ModelMapStruct modelMap, Gui view) {

        this.interval = interval;
        timer = new Timer();
        gameHandler = new GameHandler(this, modelMap, view);
        task = gameHandler;

    }

    /**
     * Return the currently set interval.
     * @return The interval.
     */
    public int getInterval() {
        return interval;
    }

    /**
     * Sets a new interval.
     * The clock needs to be restarted after a new
     * interval is set.
     * @param interval The interval to be set.
     */
    public void setInterval(int interval){
        this.interval = interval;
    }

    /**
     * Starts the clock with the set interval and with 0 delay.
     */
    public void start(){
        System.out.format("Clock started%n");
        timer.schedule(task, 0, interval);
    }

    /**
     * Cancels the clock if it's running.
     */
    public void cancel(){
        timer.cancel();
    }

    /**
     * Method only used for testing of clock-frequency.
     * This can be removed in a later state.
     * @return Number of interations done by the clock.
     */
    public int getIterations(){
        return gameHandler.getIterations();
    }

    public GameHandler getGameHandler() {
        return this.gameHandler;
    }
}
