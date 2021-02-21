package controller.ActionListener;

import view.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * ActionListener to open the "Help"-window.
 */
public class HelpListener implements ActionListener {

    private Gui view;

    public HelpListener(Gui view){
        this.view = view;
    }

    public void actionPerformed(ActionEvent event){

        view.showHelpPopup();

    }
}
