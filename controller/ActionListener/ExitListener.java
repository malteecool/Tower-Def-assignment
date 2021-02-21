package controller.ActionListener;

import controller.Clock;
import model.WorldRunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener to close the main gui and safely cancel all
 * working threads.
 */
public class ExitListener implements ActionListener {

    private Clock clock;
    private WorldRunner model;

    public ExitListener(Clock clock, WorldRunner model){

        this.clock = clock;
        this.model = model;

    }

    /**
     * Canceling the controller and model-threads before exiting the
     * gui.
     * @param event Event not used.
     */
    public void actionPerformed(ActionEvent event){

        //close gui
        //cancel all active threads.
        clock.cancel();
        model.stopGame();
        System.out.println("exiting...");
        System.exit(0);

    }
}
