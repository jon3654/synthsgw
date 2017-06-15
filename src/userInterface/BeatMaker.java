package userInterface;

import javax.sound.midi.*;

public class BeatMaker{
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	
	public BeatMaker(){
		System.out.println("this is just here for testing");
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
			sequencer.setTempoInBPM(120);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}