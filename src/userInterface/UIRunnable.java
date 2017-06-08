/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jon
 */
public class UIRunnable implements Runnable{ 
    @Override
    public void run() {
        mainWindow ui;
        try {
            ui = new mainWindow();
            ui.setVisible(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(UIRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
}
