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

import java.awt.Desktop;
import java.net.URI;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import /*com.github.synthsgw.*/controller.BeatMaker;
import /*com.github.synthsgw.*/controller.OpenFile;
import com.github.synthsgw.model.Settings;
import controller.SerializeBeatAndSynth;
import controller.Synth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;

public class SceneController {
    OpenFile openFile[] = new OpenFile[10];
    int openFileIndex = 0;
    public static BeatMaker beat;
    Duration duration;
    public static Synth synth;
    
    @FXML private SplitPane main_split_pane;
    @FXML private AnchorPane left_split_pane; 
    @FXML private AnchorPane right_split_pane;
    @FXML private ToolBar audio_tool_bar;
    @FXML private TitledPane mp3Pane;
    @FXML private VBox main_vBox;
    private Slider audio_slider;
    private Slider volume_slider;
    private Label volume_label;
    private Label audio_label;
    Window stage = null;
    
    
	@FXML
	protected void initialize() {
	}

    @FXML
	public void openMetronome() {
		displayScene(Settings.METRONOME_FXML, Settings.METRONOME_TITLE);
	}

	@FXML
	public void openSettings() {
		displayScene(Settings.SETTINGS_FXML, Settings.SETTINGS_TITLE);
	}

	public void displayScene(String fxml, String title) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fxml));

		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(-2);
		} 
		stage.setTitle(title);
		stage.show();
	}
    
    public void openMP3(ActionEvent e){
        // calls constructor for OpenFile
        // lets the class know that it should be opening an mp3
        openFile[openFileIndex++] = new OpenFile("mp3");
        // opens the File and places it in file array
        openFile[openFileIndex-1].openMP3();
        
        //Actions to show the Panel for the song name
        String songName = openFile[openFileIndex-1].songName;
        
        addmp3ToOpenFiles(songName);
    }

    //This method will add a TitledPane to the VBox with the 
    //info for the mp3 playing
    public void addmp3ToOpenFiles(String songName)
    {
        //Create a new TitledPane
        TitledPane mp3Pane = new TitledPane();
        mp3Pane.setText(songName);
        VBox mp3_vbox = new VBox();
        
        //Close button for the mp3 song open
        Button close_button = new Button("X");
        close_button.setPrefSize(10, 10);
        close_button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e){
                //Remove the Mp3 Pane and close the mp3
                main_vBox.getChildren().remove(mp3Pane);
                close();
            }
        }); 
        
        //Labels
        audio_label = new Label("TimeLine");
        volume_label = new Label("Volume");
        
        audio_slider = new Slider();
        //Audio Slider for the timeline of the song and Action Listener
        audio_slider.valueProperty().addListener(new InvalidationListener(){
            public void invalidated(Observable ov){
                if(audio_slider.isValueChanging()){
                    OpenFile.getPlayer().seek(duration.multiply(audio_slider.getValue() / 100.0));
                }
            }

            @Override
            public void invalidated(javafx.beans.Observable observable) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        volume_slider = new Slider();
        volume_slider.setPrefWidth(70);
        volume_slider.setMaxWidth(150);
        volume_slider.setMinWidth(30);
        //Control the volume of the mp3 with this audio slider  
        volume_slider.valueProperty().addListener(new InvalidationListener(){
            public void invalidated(Observable ov){
                if(volume_slider.isValueChanging()){
                    OpenFile.getPlayer().setVolume(volume_slider.getValue() / 100.0);
                }
            }

            @Override
            public void invalidated(javafx.beans.Observable observable) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
         //Add everything to the window
        
        
        
    }
    
    //This function adds Midi files to the GUI
    public void addMidiToOpenFiles(String midi_name)
    {
        TitledPane midi_pane = new TitledPane();
        midi_pane.setText(midi_name);
        VBox midi_vbox = new VBox();
        
        //Close Button
        //Close button for the mp3 song open
        Button close_button = new Button("X");
        close_button.setPrefSize(10, 10);
        close_button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e){
                //Remove the Mp3 Pane and close the mp3
                main_vBox.getChildren().remove(midi_pane);
                close();
            }
        });
        
        //Add everything to the main pane
        midi_vbox.getChildren().addAll(close_button);
        midi_pane.setContent(midi_vbox);
        main_vBox.getChildren().add(midi_pane);
        left_split_pane.getChildren().add(main_vBox);
        
    }
    
    //Updates volume, and time values for mp3
    private void updateValues()
    {
        if(audio_slider != null && volume_slider!= null)
        {
            Platform.runLater(new Runnable(){
                public void run(){
                    Duration current_time = OpenFile.getPlayer().getCurrentTime();
                    //This is where play_time will go for the audio slider
                    audio_slider.setDisable(duration.isUnknown());
                    if(!audio_slider.isDisable() && duration.greaterThan(Duration.ZERO) && !audio_slider.isValueChanging())
                    {
                        audio_slider.setValue(current_time.divide(duration).toMillis() * 100.0);
                    }
                    if(!volume_slider.isValueChanging())
                    {
                        volume_slider.setValue((int)Math.round(OpenFile.getPlayer().getVolume() * 100.0));
                    }
                }
            });
        }
    }

    public void goToGithub() throws Exception
    {
        //Hyperlink to go to GitHub Page
        Desktop link = Desktop.getDesktop();
        link.browse(new URI("https://github.com/jon3654/synthsgw"));
    }
    
    public void play(){
        int ret = openFile[openFileIndex-1].play();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void pause(){
        int ret = openFile[openFileIndex-1].pause();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void stop(){
        int ret = openFile[openFileIndex-1].stop();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void close(){
        if(openFile == null)
            OpenFile.noFileOpen();
        else{
            openFile[openFileIndex-1].stop();
            openFile[openFileIndex-1].close();
            openFileIndex--;
        }
    }
    
    // opens up the beatmaker
    public int newBeat(){
        beat = new BeatMaker();
        if(beat != null)
            return 0;
        else 
            return -1;
    }    
    
    public int openMIDI(){
        openFile[openFileIndex++] = new OpenFile("mid");
        try {
            return openFile[openFileIndex-1].openMIDI();
        } catch (IOException ex) {
            Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addMidiToOpenFiles(openFile[openFileIndex++].songName);
        return -1;
    }
    
    public void editBeat(){
        if(openFile[openFileIndex-1].getFileExtension().equals("mid")){
            openFile[openFileIndex-1].editMIDI();
        }
        else
            OpenFile.noFileOpen();
    }
    
    public void editSynth(){}
    
    public void newSynth(){
        synth = new Synth();
    }
    
    public void saveProject() throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Project");
        File file = fileChooser.showSaveDialog(stage);
        if(file != null){
            SerializeBeatAndSynth serialize = new SerializeBeatAndSynth(file);
        }
    }
}
