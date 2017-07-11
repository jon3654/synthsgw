/* MetronomeController.java
 *
 * Handles changing BPM and the like
 */

package com.github.synthsgw.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import com.github.synthsgw.model.Settings;

public class MetronomeController {
	@FXML
	private Label bpmLabel;
	@FXML
	private TextField bpmField;
	@FXML
	private Slider bpmSlider;

	// This will be run by the FXMLLoader when it has finished loading
	@FXML
	protected void initialize() {
		bpmSlider.setMin(Settings.MIN_SLIDE_BPM);
		bpmSlider.setMax(Settings.MAX_SLIDE_BPM);

		// Registering a ChangeListener for slider's value
		bpmSlider.valueProperty().addListener(
			(observable, oldValue, newValue) -> {
				Settings.bpm = newValue.intValue();
				updateGuiBpm();
			}
		);

		updateGuiBpm();
	}

	@FXML
	public void handleInputText(ActionEvent event) {
		String input = bpmField.getText();
		int inputbpm = validate(input);

		// validate returns a number <= 0 if the input was invalid
		if(inputbpm > 0) {
			Settings.bpm = inputbpm;
			updateGuiBpm();
		}
	}

	// Helper Functions

	private void updateGuiBpm() {
		if(Settings.bpm >= Settings.MIN_SLIDE_BPM
				&& Settings.bpm <= Settings.MAX_SLIDE_BPM)
			bpmSlider.setValue(Settings.bpm);

		bpmLabel.setText(Integer.toString(Settings.bpm));
	}

	// Returns an integer <= 0 if the input string is not a valid integer
	private int validate(String input) {
		int value = -1;

		try {
			value = Integer.parseInt(input);
		} catch(Exception e) {
			; // Do nothing, we have an invalid input
		}

		return value;
	}
}

