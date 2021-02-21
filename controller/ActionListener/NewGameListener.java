package controller.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for starting a new game
 */
public class NewGameListener implements ActionListener {

    public NewGameListener(){

    }

    public void actionPerformed(ActionEvent e) {

        SwingWorker newGame;
        newGame = new SwingWorker <Void, Void> () {

            @Override
            protected Void doInBackground() throws Exception {


                return null;
            }
        };

        newGame.execute();
    }

}
