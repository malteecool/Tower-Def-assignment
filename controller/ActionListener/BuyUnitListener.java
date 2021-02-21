package controller.ActionListener;

import controller.ObjectTypes;
import model.GroundUnit;
import model.TestUnit;
import model.WorldRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * ActionListener to create a new unit.
 */
public class BuyUnitListener implements ActionListener {

    private WorldRunner model;

    public BuyUnitListener(WorldRunner model){

        this.model = model;

    }

    /**
     * When a buy unit button is pressed, that
     * information is sent to model through addUnit.
     * @param event Actionevent of button in Gui.
     */
    public void actionPerformed(ActionEvent event) {

        SwingWorker buyUnit;
        buyUnit = new SwingWorker <Void, Void> () {

            @Override
            protected Void doInBackground() throws Exception {

                model.createUnit(event.getActionCommand());
                return null;
            }
        };

        buyUnit.execute();
    }
}
