/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package synthgw;

import userInterface.UIRunnable;


public class SynthGW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UIRunnable runner = new UIRunnable();
        Thread thread = new Thread(runner);
        thread.start();
    }
    
}