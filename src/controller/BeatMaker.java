/* BeatMaker.java
 *
 * Handles beats
 * TODO Make a better description of this file
 */

package com.github.synthsgw.controller;

import com.github.synthsgw.model.Settings;
import javax.sound.midi.*;

public class BeatMaker{
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	
	String[] instrumentsNames = {"Bass Drum", "Closed Hi-Hat", 
		"Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap", 
		"High Tom", "Low-mid Tom"};
	
	int [] instruments = {35, 42, 46, 38, 49, 39, 50, 47};
	
	public BeatMaker(){
		startUp();
	}
	
	public void startUp(){
		setUpMidi();
	}
	
	public void setUpMidi(){
		try{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(Settings.bpm/*this needs to be some value 
									     read from the Metronome class*/);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
