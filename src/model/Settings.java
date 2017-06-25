/* Settings.java
 *
 * Contains general settings for the program.
 *
 * Note that this class should only store settings, not any kind of actual data.
 */

package com.github.synthsgw.model;

public class Settings {
	// Metronome settings
	public static int bpm;

	public static final String METRONOME_FXML = "/fxml/metronome.fxml";
	public static final String METRONOME_TITLE = "Metronome";

	public static final int MIN_SLIDE_BPM = 40;
	public static final int MAX_SLIDE_BPM = 170;
	public static final int DEFAULT_BPM   = 90;

	// Static initialization
	// (init the default values for your settings here, this block will run when
	//  the class is loaded)
	static {
		bpm = DEFAULT_BPM;
	}
}

