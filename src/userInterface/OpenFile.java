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
    static File file[];
    static int MAX = 10;
    static int i;
    
    // class constructor
    public OpenFile(String str){
        comp = null;
        fc = new JFileChooser();
        fileExt = str;
        file = new File[10];
        i = 0;
    }
    
    public static void openPlayer(File inFile){
        // instantiates Media class
        song = new Media(inFile.toURI().toString());
        // instantiates MediaPlayer class
        player = new MediaPlayer(song);
    }
    
    // method that opens the file
    public int openFile(){
        int ret = fc.showOpenDialog(comp);
        File openFile = null;
        
        if (ret == JFileChooser.APPROVE_OPTION){
            openFile = fc.getSelectedFile();
            songName = openFile.getName();
            // checks if file is of the correct type
            if(checkExt(openFile.getName().substring(openFile.getName().lastIndexOf('.') + 1)) == 0){
                file[i] = openFile;
                i++;
                return 0;
            }
        }
        
        return -1;
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
    
    public static File getFile(){
        // returns null if no file is open
        if(i < 1) return null;
        return file[i-1];
    }
    
    public int close(){
        return removeFile(i-1);
    }
    
    // removes a file from the array
    private int removeFile(int n){
        // returns -1 if no file is open
        if(i < 1) return -1;
        
        for(int j = n; n < i; j++){
            file[j] = file[j+1];
        }
        file[i] = null;
        i--;
        return 0;
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
