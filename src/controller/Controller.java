/* Controller.java
 *
 * Put short description of class here
 */

package controller;

import controller.OpenFile;
import javafx.event.ActionEvent;

public class Controller {
    OpenFile openFile;
    
    public void openMP3(ActionEvent e){
        // calls constructor for OpenFile
        // lets the class know that it should be opening an mp3
        openFile = new OpenFile("mp3");
        // opens the File and places it in file array
        openFile.openFile();
        
        //Actions to show the Panel for the song name
        String songName = openFile.songName;
    }
    
    public void play(){
        OpenFile.play();
    }
    
    public void pause(){
        OpenFile.pause();
    }
    
    public void stop(){
        OpenFile.stop();
    }
    
    public void close(){
        int ret = openFile.close();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
}
