/* SceneController.java
 *
 * Loads other scenes
 */

package com.github.synthsgw.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.github.synthsgw.model.Settings;
import controller.OpenFile;
import java.awt.Desktop;
import java.net.URI;
import javafx.scene.control.Hyperlink;

public class SceneController {
    OpenFile openFile;

	@FXML
        
    public void openMetronome() {
	Stage stage = new Stage();
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource(Settings.METRONOME_FXML));

	try {
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
	} catch(IOException e) {
		e.printStackTrace();
		System.exit(-2);
	}

	stage.setTitle(Settings.METRONOME_TITLE);
	stage.show();
    }
    
    public void openMP3(ActionEvent e){
        // calls constructor for OpenFile
        // lets the class know that it should be opening an mp3
        openFile = new OpenFile("mp3");
        // opens the File and places it in file array
        openFile.openFile();
        
        //Actions to show the Panel for the song name
        String songName = openFile.songName;
    }
    
    //This function displays a new panel for the type of sound that has been
    //opened/made
    public void loadNewSoundPanel()
    {
        //Use a vbox with javaFx, so that it hopefully fills in the spots when
        //something is closed?
        
    }
    
    public void goToGithub() throws Exception
    {
        //Hyperlink to go to GitHub Page
        Desktop link = Desktop.getDesktop();
        link.browse(new URI("https://github.com/jon3654/synthsgw"));
    }
    
        public void play(){
        int ret = OpenFile.play();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void pause(){
        int ret = OpenFile.pause();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void stop(){
        int ret = OpenFile.stop();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void close(){
        if(openFile == null)
            OpenFile.noFileOpen();
        else
            openFile.close();
    }

}
