/* SceneController.java
 *
 * Loads other scenes
 */

package com.github.synthsgw.controller;

// Exception imports
import java.io.IOException;

// Java/Javax imports
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

// JavaFX imports
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

// SynthsGW imports
import /*com.github.synthsgw.*/controller.BeatMaker;
import /*com.github.synthsgw.*/controller.OpenFile;
import /*com.github.synthsgw.*/controller.Synth;
import com.github.synthsgw.model.Instrument;
import com.github.synthsgw.model.Settings;


public class SceneController {
    OpenFile openFile[] = new OpenFile[10];
    int openFileIndex = 0;
    File file;
    FileChooser fileChooser;

    public static BeatMaker beat;
    public static Synth synth;

    Duration duration;
    Window stage = null;
    SerializeBeatAndSynth serialize;
    
    @FXML private AnchorPane left_split_pane; 
    @FXML private AnchorPane right_split_pane;
    @FXML private Button     newMidiButton;
    @FXML private Button     newSampleButton;
    @FXML private HBox       newInstrumentButtons;
    @FXML private SplitPane  main_split_pane;
    @FXML private TitledPane mp3Pane;
    @FXML private ToolBar    audio_tool_bar;
    @FXML private VBox       instrumentPane;
    @FXML private VBox       main_vBox;
    @FXML private VBox       percussionEnumPane;
    @FXML private VBox       synthEnumPane;

    private Slider audio_slider;
    private Slider volume_slider;
    private Label volume_label;
    private Label audio_label;

	@FXML
	protected void initialize() {
		////////////////////
		// Event Handlers //
		////////////////////
		instrumentPane.setOnDragOver(e -> {
			if(e.getGestureSource() != instrumentPane &&
			   e.getDragboard().hasString()) {
				e.acceptTransferModes(TransferMode.LINK);
			}
			e.consume();
		});
		instrumentPane.setOnDragEntered(e -> {
			if(e.getGestureSource() != instrumentPane &&
			   e.getDragboard().hasString()) {
				instrumentPane.setStyle(Settings.INSTPANE_ACC_COLOR);
			}
			e.consume();
		});
		instrumentPane.setOnDragExited(e -> {
			instrumentPane.setStyle(Settings.INSTPANE_DEF_COLOR);
			e.consume();
		});
		instrumentPane.setOnDragDropped(e -> {
			e.acceptTransferModes(TransferMode.LINK);
			Dragboard db = e.getDragboard();
			boolean success = false;
			if(db.hasString()) {
				addInstrument(db.getString(), -1);
				success = true;
			}
			e.setDropCompleted(success);
			e.consume();
		});

		populateSynthPane();
	}

    @FXML
	public void openMetronome() {
		displayScene(Settings.METRONOME_FXML, Settings.METRONOME_TITLE);
	}

	@FXML
	public void openSettings() {
		displayScene(Settings.SETTINGS_FXML, Settings.SETTINGS_TITLE);
	}
        
        //This is to add a synth or a beat
	public void addInstrument(String name, int index) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Settings.INSTRUMENT_FXML));

		try {
			Parent instRoot = loader.load();

			//TODO tell instRoot what instrument it's representing somehow
			Instrument inst = Instrument.valueOf(name);
			//NOTE: inst.name() is the dirty behind-the-scenes no spaces string,
			//      inst.name is the user-friendly string
			((InstrumentController)loader.getController())
					.setInstName(inst.name);

			//*
			LinkedList<Node> lst =
					new LinkedList<>(instrumentPane.getChildren());

			if(index >= 0)
				lst.add(index, instRoot);
			else
				lst.add(instRoot);

			instrumentPane.getChildren().setAll(lst);
			//*/
			/*
			// Add new instrument gui to bottom of VBox
			instrumentPane.getChildren().addAll(instRoot);
			// Move the buttons to the bottom
			newInstrumentButtons.toFront();
			//*/
		} catch(IOException e) {
			e.printStackTrace();
			Platform.exit();
			System.exit(-2);
		}
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
			Platform.exit();
			System.exit(-2);
		} 
		stage.setTitle(title);
		stage.show();
	}

	private void populateSynthPane() {
		URL loc = getClass().getResource(Settings.INST_ICON_FXML);

		ArrayList<Pane> percs = makeInstPanes(Instrument.getPercussion(), loc);
		percussionEnumPane.getChildren().addAll(percs);

		ArrayList<Pane> synts = makeInstPanes(Instrument.getSynths(), loc);
		synthEnumPane.getChildren().addAll(synts);
	}

	private ArrayList<Pane> makeInstPanes(Collection<Instrument> insts,
	                                      URL location) {
		ArrayList<Pane> panes = new ArrayList<>(insts.size());

		for(Instrument i : insts) {
			try {
				final Pane pane = (Pane)FXMLLoader.load(location);

				// Set the drag and drop handler
				pane.setOnDragDetected(e -> {
					Dragboard db = pane.startDragAndDrop(TransferMode.LINK);

					// Put the Instrument in question on the dragboard
					ClipboardContent instrument = new ClipboardContent();
					//NOTE: i.name() gives the all-caps, no spaces version of
					//      the name, whereas i.name gives the user-friendly
					//      version.
					instrument.putString(i.name());
					db.setDragView(pane.snapshot(null, null),
					               pane.getWidth()/2,
								   pane.getHeight()/2);
					db.setContent(instrument);

					e.consume();
				});
				// Set the text of this thing's label
				((Label)pane.lookup("#instrumentNameLabel")).setText(i.name);
				panes.add(pane);
			} catch(IOException e) {
				e.printStackTrace();
				Platform.exit();
				System.exit(-2);
			}
		}

		return panes;
	}
    
    public void openMP3(ActionEvent e){
        // calls constructor for OpenFile
        // lets the class know that it should be opening an mp3
        openFile[openFileIndex++] = new OpenFile("mp3");
        // opens the File and places it in file array
        openFile[openFileIndex-1].openMP3();
        
        //Actions to show the Panel for the song name
        String songName = openFile[openFileIndex-1].songName;
        
        addMP3(songName);
    }

    //This method will add a TitledPane to the VBox with the 
    //info for the mp3 playing
    public void addMP3(String songName)
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
                instrumentPane.getChildren().remove(mp3Pane);
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
                throw new UnsupportedOperationException("Not supported yet.");
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
        mp3_vbox.getChildren().addAll(close_button, audio_label, audio_slider, volume_label, volume_slider);
        mp3Pane.setContent(mp3_vbox);
        instrumentPane.getChildren().add(mp3Pane);
    }
    
    public int openMIDI(){
        openFile[openFileIndex++] = new OpenFile("mid");
        try {
            return openFile[openFileIndex-1].openMIDI();
        } catch (IOException ex) {
            Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String songName = new String(openFile[openFileIndex - 1].songName);
        
        System.out.println("The song name for the MIDI is " + songName);
        addMidi(songName);
        return -1;
    }
    
    //This function adds Midi files to the GUI
    public void addMidi(String midi_name)
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
                instrumentPane.getChildren().remove(midi_pane);
                close();
            }
        });
        
        //Add everything to the Instrument Pane
        midi_vbox.getChildren().addAll(close_button);
        midi_pane.setContent(midi_vbox);
        instrumentPane.getChildren().add(midi_pane);
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
//        for(int i = openFileIndex; i > -1; --i)
//            openFile[openFileIndex-1].play();
//        if(openFileIndex == -1)
//            OpenFile.noFileOpen();
            
        int ret = openFile[openFileIndex-1].play();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void pause(){
//        for(int i = openFileIndex; i > -1; --i)
//            openFile[openFileIndex-1].pause();
//        if(openFileIndex == -1)
//            OpenFile.noFileOpen();
        int ret = openFile[openFileIndex-1].pause();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void stop(){
//        for(int i = openFileIndex; i > -1; --i)
//            openFile[openFileIndex-1].stop();
//        if(openFileIndex == -1)
//            OpenFile.noFileOpen();
        int ret = openFile[openFileIndex-1].stop();
        if(ret == -1)
            OpenFile.noFileOpen();
    }
    
    public void close(){
//        if(openFile == null)
//            OpenFile.noFileOpen();
//        else
//        {
//            while(openFileIndex > 0)
//                openFile[openFileIndex-1].stop();
//                openFile[openFileIndex-1].close();
//                --openFileIndex;
//        }
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
}
