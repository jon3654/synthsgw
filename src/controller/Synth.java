// Java Virtual Keyboard 
import javax.swing.*;
import javax.sound.midi.*;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.ArrayList;

public class Synth{

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
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }			
		});
	}
	public class TestPane extends JPanel {
        
		// Need a place for the buttons 
		public TestPane() {
            setLayout(new GridLayout(1, 8));
            add(createButton("C", KeyEvent.VK_1));
            add(createButton("D", KeyEvent.VK_2));
            add(createButton("E", KeyEvent.VK_3));
            add(createButton("F", KeyEvent.VK_4));
            add(createButton("G", KeyEvent.VK_5));
            add(createButton("A", KeyEvent.VK_6));
            add(createButton("B", KeyEvent.VK_7));
            add(createButton("C", KeyEvent.VK_8));
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
    }
	
	
    public void playNote(int finalNote, int finalInstrument) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ,4);
            Track track = sequence.createTrack();

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192,1,finalInstrument,0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,finalNote,100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,finalNote,100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}