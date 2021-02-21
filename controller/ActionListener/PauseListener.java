package controller.ActionListener;

import controller.GameHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener to pause a running game.
 */

public class PauseListener implements ActionListener {

    private GameHandler gameHandler;

    public PauseListener(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    /**
     *  Stops execution in gameHandler.
     * @param event does nothing.
     */
    public void actionPerformed(ActionEvent event) {
        gameHandler.setPause();
    }
}
