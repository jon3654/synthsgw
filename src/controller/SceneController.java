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
import controller.BeatMaker;
import controller.OpenFile;
import java.awt.Desktop;
import java.net.URI;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SceneController {
    OpenFile openFile;
    BeatMaker beat;
    
    @FXML private SplitPane main_split_pane;
    @FXML private AnchorPane left_split_pane; 
    @FXML private AnchorPane right_split_pane;
    @FXML private ToolBar audio_tool_bar;
    @FXML private TitledPane mp3Pane;
    @FXML private VBox main_vBox;
    
            
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
        
        addmp3ToOpenFiles(songName);
        
    }
    
    //This method will add a TitledPane to the VBox with the 
    //info for the mp3 playing
    public void addmp3ToOpenFiles(String songName)
    {
        //Create a new TitledPane
        TitledPane mp3Pane = new TitledPane();
        mp3Pane.setText(songName);
        
        main_vBox.getChildren().add(mp3Pane);
        
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
    
    // opens up the beatmaker
    public void newBeat(){
        beat = new BeatMaker();
    }    
}
