/* OpenFile.java
 *
 * Handles opening files
 */

package com.github.synthsgw.controller;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class OpenFile {
    Window stage;
    FileChooser fileChooser;
    private static String fileExt;
    String songName;
    private static Media song;
    private static MediaPlayer player;
    static File file[];
    static int MAX = 10;
    static int numberOfOpenFiles;
    
    // class constructor
    public OpenFile(String str){
        fileChooser = new FileChooser();
        fileExt = str;
        file = new File[10];
        numberOfOpenFiles = 0;
    }
    
    public static void openPlayer(File inFile){
        // instantiates Media class
        song = new Media(inFile.toURI().toString());
        // instantiates MediaPlayer class
        player = new MediaPlayer(song);
    }
    
    // method that opens the file
    public int openFile(){
        File openFile = null;
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        openFile = fileChooser.showOpenDialog(stage);
                        
        songName = openFile.getName();
        // checks if file is of the correct type
        if(checkExt(openFile.getName().substring(openFile.getName().lastIndexOf('.') + 1)) == 0){
            file[numberOfOpenFiles] = openFile;
            // intializes the player
            openPlayer(openFile);
            numberOfOpenFiles++;
            return 0;
        }
        else
            wrongFileOpen();
        
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
        if(numberOfOpenFiles < 1) return null;
        return file[numberOfOpenFiles-1];
    }
    
    public int close(){
        return removeFile(numberOfOpenFiles-1);
    }
    
    // removes a file from the array
    private int removeFile(int n){
        // returns -1 if no file is open
        if(numberOfOpenFiles < 1) return -1;
        
        for(int j = n; n < numberOfOpenFiles-1; j++){
            file[j] = file[j+1];
        }
        file[numberOfOpenFiles] = null;
        numberOfOpenFiles--;
        return 0;
    }

    
    // plays loaded file
    public static void play(){
        if(numberOfOpenFiles < 1) 
            noFileOpen();
        else
            player.play();
        
    }
    
    public static void pause(){
        if(numberOfOpenFiles < 1) noFileOpen();
        else player.pause();
    }
    
    public static void stop(){
        if(numberOfOpenFiles < 1) noFileOpen();
        else player.stop();
    }
    
    public static void noFileOpen(){
        Alert popup = new Alert(AlertType.INFORMATION);
        popup.setTitle("Error");
        popup.setHeaderText("No file open");
        popup.show();    
    }
    
    public static void wrongFileOpen(){
        Alert popup = new Alert(AlertType.INFORMATION);
        popup.setTitle("Error");
        popup.setHeaderText("Wrong file type");
        popup.show();
    }
}
