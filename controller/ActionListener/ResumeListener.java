package controller.ActionListener;

import controller.GameHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener to resume a paused game.
 */

public class ResumeListener implements ActionListener {

    private GameHandler gameHandler;

    public ResumeListener(GameHandler gameHandler) { this.gameHandler = gameHandler; }

    /**
     * Resumes execution in gameHandler
     * @param event does nothing.
     */
    public void actionPerformed(ActionEvent event){
        gameHandler.setResume();
    }
}
