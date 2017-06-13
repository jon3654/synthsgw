/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Component;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JFileChooser;


public class OpenFile {
    final JFileChooser fc;
    private static Component comp;
    private static String fileExt;
    String songName;
    private static Media song;
    private static MediaPlayer player;

    
    // class constructor
    public OpenFile(String str){
        comp = null;
        fc = new JFileChooser();
        fileExt = str;
    }
    
    public static void openPlayer(File inFile){
        // instantiates Media class
        song = new Media(inFile.toURI().toString());
        // instantiates MediaPlayer class
        player = new MediaPlayer(song);
    }
    
    // method that opens the file
    public File openFile(){
        int ret = fc.showOpenDialog(comp);
        File file = null;
        
        if (ret == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
            songName = file.getName();
            // checks if file is of the correct type
            if(checkExt(file.getName().substring(file.getName().lastIndexOf('.') + 1)) == 0)
                return file;
        }
        
        return null;
    }
    
    // method that checks that the extension is correct
    // returns 0 if it is, -1 otherwise
    private int checkExt(String str){
        if(str.equals(fileExt)){
            return 0;
        }
        else return -1;
    }
    
    public static MediaPlayer getPlayer(){
        return player;
    }
    
    // plays loaded file
    public void play(){
        player.play();
    }
    
    public void pause(){
        player.pause();
    }
    
    public void stop(){
        player.stop();
    }

}
