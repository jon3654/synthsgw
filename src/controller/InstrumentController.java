/* InstrumentController.java
 *
 * Controls the little instrument boxes that appear when you add an instrument
 * to the thing.
 */

package com.github.synthsgw.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
//import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import com.github.synthsgw.model.BeatArray;
import com.github.synthsgw.model.Instrument;
import com.github.synthsgw.model.InstrumentData;
import com.github.synthsgw.model.Settings;

public class InstrumentController {
	private InstrumentData track;
	private Instrument inst;

	@FXML private BorderPane rootPane;
	@FXML private AnchorPane instrumentSettingsPane;
	@FXML private Label instrumentName;
	@FXML private ToggleButton muteButton;
	@FXML private ToggleButton soloButton;
	@FXML private Button deleteButton;
	@FXML private Slider panSlider;

	// Note buttons
	@FXML private ToggleButton note0;
	@FXML private ToggleButton note1;
	@FXML private ToggleButton note2;
	@FXML private ToggleButton note3;
	@FXML private ToggleButton note4;
	@FXML private ToggleButton note5;
	@FXML private ToggleButton note6;
	@FXML private ToggleButton note7;
	@FXML private ToggleButton note8;
	@FXML private ToggleButton note9;
	@FXML private ToggleButton note10;
	@FXML private ToggleButton note11;
	@FXML private ToggleButton note12;
	@FXML private ToggleButton note13;
	@FXML private ToggleButton note14;
	@FXML private ToggleButton note15;
	// Array provided for your convenience
	private ToggleButton [] notes;

	@FXML
	protected void initialize() {
		track = new InstrumentData();

		notes = new ToggleButton []
				{ note0,  note1,  note2,  note3,
				  note4,  note5,  note6,  note7,
				  note8,  note9,  note10, note11,
				  note12, note13, note14, note15 };

		for(int i = 0; i < notes.length; i++) {
			final int index = i;
			notes[i].selectedProperty().addListener((obs, oldVal, newVal) -> {
				BeatArray.Beats.setBeat(inst, index, newVal);
			});
		}

		// Mute and Solo buttons
		muteButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
			BeatArray.Beats.setMute(inst, newVal);
		});

		soloButton.setToggleGroup(Settings.SoloGroup);
		// Storing what instrument this Toggle represents
		soloButton.setUserData(inst);

		// Initialize panSlider
		panSlider.setMin(Settings.MIN_SLIDE_PAN);
		panSlider.setMax(Settings.MAX_SLIDE_PAN);
		// *Should* set slider to halfway
		panSlider.setValue(Settings.MAX_SLIDE_PAN/2 + Settings.MIN_SLIDE_PAN/2);
	}

	@FXML
	public void renameInstrument() {
		TextField renamer = new TextField();

		AnchorPane.setTopAnchor(renamer,
		                        AnchorPane.getTopAnchor(instrumentName));
		AnchorPane.setLeftAnchor(renamer,
		                         AnchorPane.getLeftAnchor(instrumentName));

		instrumentSettingsPane.getChildren().addAll(renamer);

		// Put focus on the new field
		renamer.requestFocus();

		// When the user hits Enter when on this text field, rename label and
		// remove text field
		
		// I think TextFields only fire ActionEvents when you hit enter...
		renamer.setOnAction(e -> {
			instrumentName.setText(renamer.getCharacters().toString());
			instrumentSettingsPane.getChildren().remove(renamer);
		});
		/* ...but if that's not the case then this might be necessary someday.
		// (don't forget to uncomment the KeyCode import!)
		renamer.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)) {
				instrumentName.setText(renamer.getCharacters());
				instrumentSettingsPane.getChildren().remove(renamer);
			}
		});
		*/

		// If the renamer loses focus, remove it
		renamer.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal == false)
				instrumentSettingsPane.getChildren().remove(renamer);
		});
	}

	@FXML
	public void removeInstrument() {
		//TODO remove the associated Sequence from the Sequencer

		((Pane)rootPane.getParent()).getChildren().remove(rootPane);
		// And now let GC do its job
	}

	public void setInstName(String name) {
		instrumentName.setText(name);
	}

	public void setInst(Instrument inst) {
		this.inst = inst;
	}
}

