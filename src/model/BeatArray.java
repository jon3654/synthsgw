/* BeatArray.java
 *
 * Translates buttons pressed by the user into instrument beats
 */

package com.github.synthsgw.model;

import java.util.EnumMap;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import javafx.application.Platform;

public class BeatArray {
	public static BeatArray Beats = new BeatArray();

	private EnumMap<Instrument, boolean []> beats;
	private EnumMap<Instrument, Boolean>    muted;
	private Instrument soloed;

	public BeatArray() {
		beats = new EnumMap<Instrument, boolean []>(Instrument.class);
		muted = new EnumMap<Instrument, Boolean>(Instrument.class);
		soloed = null;
	}

	public void setBeat(Instrument inst, int beat, boolean value) {
		if(!beats.containsKey(inst)) {
			beats.put(inst, new boolean[16]);
		}

		beats.get(inst)[beat] = value;
	}

	public void setMute(Instrument inst, boolean value) {
		muted.put(inst, value);
	}

	public void setSolo(Instrument inst) {
		soloed = inst;
	}

	public Sequence makeSequence() {
		Sequence seq = null;

		try {
			// 4 beats per quarter note
			seq = new Sequence(Sequence.PPQ, 4);
		} catch(Exception e) {
			e.printStackTrace();
			Platform.exit();
			System.exit(-1);
		}

		for(Instrument ins : Instrument.values()) {
			Track track = seq.createTrack();
			boolean [] instTrack = beats.get(ins);

			// Make sure that a track is not muted or soloed
			if((soloed == null || soloed == ins) && !muted.get(ins)) {
				for(int i = 0; i < instTrack.length; i++) {
					// if this beat is enabled
					if(instTrack[i]) {
						// Add the on and off events to the track for this note
						track.add(makeEvent(144, 9, ins.code, 100, i));
						track.add(makeEvent(128, 9, ins.code, 100, i+1));
					}
				}
			}

			// I think this is the end of track symbol, but who really knows
			track.add(makeEvent(192,9,1,0,15));
		}

		return seq;
	}

	private MidiEvent makeEvent(int com, int chan, int one, int two, int tick) {
		MidiEvent event = null;

		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(com, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch(Exception e) {
			e.printStackTrace();
			Platform.exit();
			System.exit(-1);
		}

		return event;
	}
}

