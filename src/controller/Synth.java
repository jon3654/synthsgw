package controller;
// Java Virtual Keyboard 
import com.github.synthsgw.model.Settings;
import javax.swing.*;
import javax.sound.midi.*;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class Synth{
        Window stage;
        Track track;
        Sequencer sequencer;
        Sequencer tempoSequencer;
        Sequence sequence;
        long time;
        
	public static void main (String[] args){
		new Synth();
	}
	
	public Synth(){
		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

				// this is the JFrame which contains the 8 buttons 
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }			
		});
	}
	public class TestPane extends JPanel{
        
		// Need a place for the buttons 
		public TestPane() {
            setLayout(new GridLayout(2, 8));
            add(createButton("C", KeyEvent.VK_1));
            add(createButton("D", KeyEvent.VK_2));
            add(createButton("E", KeyEvent.VK_3));
            add(createButton("F", KeyEvent.VK_4));
            add(createButton("G", KeyEvent.VK_5));
            add(createButton("A", KeyEvent.VK_6));
            add(createButton("B", KeyEvent.VK_7));
            add(createButton("C", KeyEvent.VK_8));
            add(createRecordButton("Record", KeyEvent.VK_9));
            add(createStartButton("Start", KeyEvent.VK_0));
            add(createSaveButton("Save", KeyEvent.VK_A));
            
        }

        protected JButton createButton(String name, int virtualKey) {
            JButton btn = new JButton(name);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // we need to get user input for the octave 
                    int octave = 12*3;
                    playNote(octave + (virtualKey - '1'), 1);
                    
                }
            });
            
            btn.setMargin(new Insets(8, 8, 8, 8));
            
            InputMap im = btn.getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = btn.getActionMap();
            
            im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
            am.put("clickMe", new AbstractAction() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.doClick();
            }
            });
            return btn;
        }
        protected JButton createRecordButton(String name, int virtualKey){
            JButton btn = new JButton(name);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(sequencer == null || !sequencer.isRecording()){
                        try {
                            tempoSequencer = MidiSystem.getSequencer();
                            tempoSequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
                            Sequence tempoSequence = new Sequence(Sequence.PPQ,4);
                            Track tempoTrack = tempoSequence.createTrack();
                            ShortMessage tempoMessage = new ShortMessage(ShortMessage.PROGRAM_CHANGE, 9, 1, 0);
                            MidiEvent tempoEvent = new MidiEvent(tempoMessage, 0);
                            tempoTrack.add(tempoEvent);
                            tempoSequencer.setSequence(tempoSequence);
                            tempoMessage = new ShortMessage(ShortMessage.NOTE_ON, 9, 37, 100);
                            int i;
                            for(i = 0; i < 1000; i ++){
                                tempoEvent = new MidiEvent(tempoMessage, i);
                                tempoTrack.add(tempoEvent);
                            }
                            tempoSequencer.setTrackMute(0, true);
                            tempoSequencer.setTempoInBPM(Settings.bpm);
                            tempoSequencer.open();
                            tempoSequencer.start();
                            
                            sequencer = MidiSystem.getSequencer();         
                            sequencer.setMasterSyncMode(Sequencer.SyncMode.INTERNAL_CLOCK);
                            
                            sequencer.open();  
                            sequence = new Sequence(Sequence.PPQ,4);
                            sequencer.setTempoInBPM(Settings.bpm);
                            track = sequence.createTrack();                          
                            sequencer.setSequence(sequence);  
                            
                            sequencer.recordEnable(track, HEIGHT);
                            sequencer.start();
                            time = System.currentTimeMillis();
                            sequencer.startRecording();
                            } catch (MidiUnavailableException ex) {
                            Logger.getLogger(Synth.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvalidMidiDataException ex) {
                            Logger.getLogger(Synth.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    
                        }
                    else if (sequencer.isRecording()){
                        tempoSequencer.stop();
                        sequencer.stop();
                    }
                }
            });
            btn.setMargin(new Insets(8, 8, 8, 8));
            
            InputMap im = btn.getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = btn.getActionMap();
            
            im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
            am.put("clickMe", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource(); 
                    btn.doClick();
                }
            });
            return btn;
        }
        protected JButton createStartButton(String name, int virtualKey){
            JButton btn = new JButton(name);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sequencer.stop();
                    sequencer.start();
                }
            });
            btn.setMargin(new Insets(8, 8, 8, 8));
            
            InputMap im = btn.getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = btn.getActionMap();
            
            im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
            am.put("clickMe", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.doClick();
                }
                
            });
            return btn;
        }
        protected JButton createSaveButton(String name, int virtualKey){
        JButton btn = new JButton(name);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save MIDI");
                        File file = fileChooser.showSaveDialog(stage);
                        if (file != null) {
                            try {
                                // serialize here
                                MidiSystem.write(sequence,1,file);
                            } catch (IOException ex) {
                                Logger.getLogger(BeatMaker.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }   
                });
            }
        });
        btn.setMargin(new Insets(8, 8, 8, 8));
        
        InputMap im = btn.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = btn.getActionMap();
        
        im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
        am.put("clickMe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.doClick();
            }
            
        });
        return btn;
        }
    }
	
	
    public void playNote(int finalNote, int finalInstrument) {
            
        try{
            Sequencer temp_sequencer = MidiSystem.getSequencer();
            Sequence temp_sequence;
            Track temp_track;
            temp_sequence = new Sequence(Sequence.PPQ,4);
            temp_track = temp_sequence.createTrack();
            temp_sequencer.open();
            
            temp_sequencer.setTempoInBPM(Settings.bpm);
            
            MidiEvent event = null;
            
            //int currentTick = (int)((System.currentTimeMillis()-time)*Settings.bpm)/6000;

            ShortMessage first = new ShortMessage();
            first.setMessage(192,1,finalInstrument,0);
            MidiEvent changeInstrument;
            if(sequencer != null && sequencer.isRecording()){
                changeInstrument = new MidiEvent(first, tempoSequencer.getTickPosition());
                track.add(changeInstrument);
            }
            changeInstrument = new MidiEvent(first, 1);
            temp_track.add(changeInstrument);
            
            

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,finalNote,100);
            MidiEvent noteOn;
            if(sequencer != null && sequencer.isRecording()){
                noteOn = new MidiEvent(a, tempoSequencer.getTickPosition());
                track.add(noteOn);
            }
            noteOn = new MidiEvent(a, 1);
            temp_track.add(noteOn);
            

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,finalNote,100);
            MidiEvent noteOff;
            if(sequencer != null && sequencer.isRecording()){
                noteOff = new MidiEvent(b, tempoSequencer.getTickPosition()+16);
                track.add(noteOff);
            }
            noteOff = new MidiEvent(b, 16);
            temp_track.add(noteOff);
            
            
            temp_sequencer.setSequence(temp_sequence);
            temp_sequencer.start();
            
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}