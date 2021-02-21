package controller.ActionListener;

import view.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener to open the "About"-window.
 */
public class AboutListener implements ActionListener {

    Gui view;

    public AboutListener(Gui view){
        this.view = view;
    }

    public void actionPerformed(ActionEvent event){

        view.showAboutPopup();

    }
}
