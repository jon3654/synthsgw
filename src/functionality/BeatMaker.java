package userInterface;

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
			sequencer.setTempoInBPM(120/*this needs to be some value 
									     read from the Metronome class*/);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

}