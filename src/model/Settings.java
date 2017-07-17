/* Settings.java
 *
 * Contains general settings for the program.
 *
 * Note that this class should only store settings, not any kind of actual data.
 */

package com.github.synthsgw.model;

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

	public static final short MIN_SLIDE_PAN = 0;
	public static final short MAX_SLIDE_PAN = 127;

	// Settings settings (hehehe)
	public static final String SETTINGS_FXML  = "/fxml/settings.fxml";
	public static final String SETTINGS_TITLE = "Settings";

	// BeatMaker settings
	//TODO evaluate whether this even needs to be here
	public static final String BEATMAKER_FXML  = "/fxml/BeatMaker.fxml";
	public static final String BEATMAKER_TITLE = "Beat Maker";

	// Static initialization
	// (init the default values for your settings here, this block will run when
	//  the class is loaded)
	static {
		bpm = DEFAULT_BPM;
	}
}

