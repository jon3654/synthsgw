/* Instrument.java
 *
 * Enumerates all the instruments.
 */

package com.github.synthsgw.model;

import java.util.ArrayList;

public enum Instrument {
	// Percussion
	BASS_DRUM("Bass Drum", 35),
	ACOUSTIC_SNARE("Acoustic Snare", 38),
	HAND_CLAP("Hand Clap", 39),
	CLOSED_HI_HAT("Closed Hi-Hat", 42),
	OPEN_HI_HAT("Open Hi-Hat", 46),
	LOW_MID_TOM("Low-Mid Tom", 47),
	CRASH_CYMBAL("Crash Cymbal", 49),
	HIGH_TOM("High Tom", 50),
	COWBELL("Cowbell", 56),
	VIBRASLAP("Vibraslap", 58),
	HI_BONGO("Hi Bongo", 60),
	OPEN_HI_CONGA("Open Hi Conga", 63),
	LOW_CONGA("Low Conga", 64),
	HIGH_AGOGO("High Agogo", 67),
	MARACAS("Maracas", 70),
	WHISTLE("Whistle", 72),
	SEPARATOR("", -1);

	// Synthesizers
	//SYNTH(???, ???);

	public final String name;
	public final int    code;

	private Instrument(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public static ArrayList<Instrument> getPercussion() {
		ArrayList<Instrument> perc = new ArrayList<>();

		for(Instrument i : Instrument.values()) {
			if(i.code == -1)
				break;

			perc.add(i);
		}

		return perc;
	}

	public static ArrayList<Instrument> getSynths() {
		boolean passedSeparator = false;
		ArrayList<Instrument> synt = new ArrayList<>();

		for(Instrument i : Instrument.values()) {
			if(passedSeparator) {
				synt.add(i);
				continue;
			}

			if(i.code == -1)
				passedSeparator = true;
		}

		return synt;
	}

	public static ArrayList<Instrument> getAll() {
		ArrayList<Instrument> insts = new ArrayList<>();

		for(Instrument i : Instrument.values())
			if(i.code != -1)
				insts.add(i);

		return insts;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

