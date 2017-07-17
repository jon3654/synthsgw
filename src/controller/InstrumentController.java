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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
//import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import com.github.synthsgw.model.Settings;

public class InstrumentController {
	@FXML private SplitPane rootPane;
	@FXML private AnchorPane instrumentSettingsPane;
	@FXML private Label instrumentName;
	@FXML private ToggleButton muteButton;
	@FXML private ToggleButton soloButton;
	@FXML private Button deleteButton;
	@FXML private Slider panSlider;

	@FXML
	protected void initialize() {
		panSlider.setMin(Settings.MIN_SLIDE_PAN);
		panSlider.setMax(Settings.MAX_SLIDE_PAN);
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
		/* ...but if that's not the case then this might be necessary.
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
}

