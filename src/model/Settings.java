/* Settings.java
 *
 * Contains general settings for the program.
 *
 * Note that this class should only store settings, not any kind of actual data.
 */

package com.github.synthsgw.model;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

import javafx.application.Platform;
import javafx.scene.control.ToggleGroup;

public class Settings {
	// Metronome settings
	public static float bpm;

	public static final String METRONOME_FXML  = "/fxml/metronome.fxml";
	public static final String METRONOME_TITLE = "Metronome";

	public static final int MIN_SLIDE_BPM = 40;
	public static final int MAX_SLIDE_BPM = 170;
	public static final int DEFAULT_BPM   = 90;

	// Instrument (gui) settings
	public static final String INSTRUMENT_FXML = "/fxml/instrument.fxml";
	public static final String INST_ICON_FXML  = "/fxml/instrumentIcon.fxml";

	public static final short MIN_SLIDE_PAN = 0;
	public static final short MAX_SLIDE_PAN = 127;

	public static final String INSTPANE_DEF_COLOR
			= "-fx-background-color: lightblue";
	public static final String INSTPANE_ACC_COLOR
			= "-fx-background-color: palegreen";

	public static final ToggleGroup SoloGroup = new ToggleGroup();

	// Settings settings (hehehe)
	public static final String SETTINGS_FXML  = "/fxml/settings.fxml";
	public static final String SETTINGS_TITLE = "Settings";

	// BeatMaker settings
	//TODO evaluate whether this even needs to be here
	public static final String BEATMAKER_FXML  = "/fxml/BeatMaker.fxml";
	public static final String BEATMAKER_TITLE = "Beat Maker";

	// BetaMaker settings
	public static final Sequencer MyMixtape = retrieveSequencer();

	// Static initialization
	// (init the default values for your settings here, this block will run when
	//  the class is loaded)
	static {
		bpm = DEFAULT_BPM;

		SoloGroup.selectedToggleProperty().addListener((obs, old, newTog) -> {
			if(newTog == null)
				BeatArray.Beats.setSolo(null);
			else
				BeatArray.Beats.setSolo((Instrument)newTog.getUserData());
		});
	}

	private static Sequencer retrieveSequencer() {
		Sequencer s = null;

		try {
			s = MidiSystem.getSequencer();
		} catch(Exception e) {
			e.printStackTrace();
			Platform.exit();
			System.exit(-1);
		}

		return s;
	}
}

