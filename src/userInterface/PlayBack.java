/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class PlayBack {
    Media song;
    MediaPlayer player;
    public PlayBack(File inFile){
        // instantiates Media class
        song = new Media(inFile.toURI().toString());
        // instantiates MediaPlayer class
        player = new MediaPlayer(song);
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
