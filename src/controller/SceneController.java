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
    
    public void play(){
        openFile.play();
    }
    
    public void pause(){
        openFile.pause();
    }
    
    public void stop(){
        openFile.stop();
    }
    
    public void close(){
        openFile.close();
    }
}
