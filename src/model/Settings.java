/* Settings.java
 *
 * Contains general settings for the program.
 *
 * Make your variable settings package-private static, that way we don't have to
 * write a getter and a setter for each one. Be warned that we aren't usng
 * getters and setters for these.
 *
 * Note that this class should only store settings, not any kind of actual data.
 */

package com.github.synthsgw.model;

public class Settings {
	// Metronome settings
	static bpm;

	public static final MIN_SLIDE_BPM = 40;
	public static final MAX_SLIDE_BPM = 170;
	public static final DEFAULT_BPM   = 90;

	// Static initialization
	// (init the default values for your settings here, this block will run when
	//  the class is loaded)
	static {
		bpm = DEFAULT_BPM;
	}
}

