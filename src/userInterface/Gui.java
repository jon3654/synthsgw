/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author jon
 */
public class Gui{
    private static JFrame frame;
    private static JMenuBar menuBar;
    
    // Constructor
    public Gui() {
        this.initComponents();
    }
    
    private void initComponents(){
        initFrame();
        frame.setVisible(true);
    }
    
    private void initFrame(){
        frame = new JFrame();
        frame.setTitle("SynthGW");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 650));
        frame.setPreferredSize(new Dimension(800, 650));
        
        initMenu();
        
        frame.add(menuBar);
    }
    
    private void initMenu(){
        menuBar = new JMenuBar();
    }
}
