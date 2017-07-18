/* OpenFile.java
 *
 * Handles opening files
 */

package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class OpenFile {
    Window stage;
    FileChooser fileChooser;
    private static String fileExtension;
    public String songName;
    private static Media song;
    private static MediaPlayer player;
    static File file;
    Sequencer sequencer;
    InputStream input;
    BeatMaker beat;
    
    // class constructor
    public OpenFile(String str){
        fileChooser = new FileChooser();
        fileExtension = str;
        file = null;
    }
    
    public static void openPlayer(File inFile){
        file = inFile;
        // instantiates Media class
        song = new Media(inFile.toURI().toString());
        // instantiates MediaPlayer class
        player = new MediaPlayer(song);
    }
    
    // method that opens the file
    public int openMP3(){
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open MP3");
        file = fileChooser.showOpenDialog(stage);
                        
        songName = file.getName();
        // checks if file is of the correct type
        if(checkExt(file.getName().substring(
				file.getName().lastIndexOf('.') + 1)) == 0) {
            // intializes the player
            openPlayer(file);
            return 0;
        }
        else{
            wrongFileOpen();
            file = null;
        }
        
        return -1;
    }
    
    public int openMIDI() throws IOException{
        try {
            sequencer = MidiSystem.getSequencer();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(OpenFile.class.getName()).log(Level.SEVERE, null, ex);
            sequencer = null;
        }
        try {
            sequencer.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open MIDI");
        file = fileChooser.showOpenDialog(stage);
        if(checkExt(file.getName().substring(
				file.getName().lastIndexOf('.') + 1)) == 0){
            // read MIDI file to buffered input
            try {
                input = new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OpenFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            // add buffered input to sequencer
            try {
                sequencer.setSequence(input);
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(OpenFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
        else{
            wrongFileOpen();
            file = null;
        }
        return -1;
    }
    
    // method that checks that the extension is correct
    // returns 0 if it is, -1 otherwise
    public int checkExt(String str){
        if(str.equals(fileExtension)){
            return 0;
        }
        else return -1;
    }
    
    public static MediaPlayer getPlayer(){
        return player;
    }
    
    public static File getFile(){
        // returns null if no file is open
        return file;
    }
    
    // public method to close file
    public int close(){
        if(file == null) 
            return -1;
        file = null;
        return 0;
    }
    
    // removes a file from the array
//    private int removeFile(int n){
//        // returns -1 if no file is open
//        if(numberOfOpenFiles < 1) 
//            return -1;
//        
//        
//        for(int j = n; n < numberOfOpenFiles-1; j++){
//            file[j] = file[j+1];
//        }
//        file[numberOfOpenFiles] = null;
//        numberOfOpenFiles--;
//        return 0;
//    }

    
    // plays loaded file
    public int play(){
        if(file == null) {
            return -1;
        }
        else{
            if(fileExtension.equals("mp3"))
                playMP3();
            else if(fileExtension.equals("mid"))
                playMIDI();
            return 0;
        }
        
    }
    
    public void playMP3(){
        player.play();
    }
    
    public void playMIDI(){
        sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
        sequencer.start();
    }
    
    public int pause(){
        if(file == null){
            return -1;
        }
        else{
            if(fileExtension.equals("mp3"))
                pauseMP3();
            else if(fileExtension.equals("mid"))
                pauseMIDI();
            return 0;
        }
    }
    
    public void pauseMP3(){
        player.pause();
    }
    
    public void pauseMIDI(){
        sequencer.stop();
    }
    
    public int stop(){
        if(file == null){ 
            return -1;
        }
        else{ 
            if(fileExtension.equals("mp3"))
                stopMP3();
            else if(fileExtension.equals("mid"))
                stopMIDI();
            return 0;
        }
    }
    
    public void stopMP3(){
        player.stop();
    }
    
    public void stopMIDI(){
        sequencer.stop();
    }
    
    
    
    public static void noFileOpen(){
        Alert popup = new Alert(AlertType.INFORMATION);
        popup.setTitle("Error");
        popup.setHeaderText("No file open");
        popup.show();    
    }
    
    public static void wrongFileOpen(){
        Alert popup = new Alert(AlertType.INFORMATION);
        popup.setTitle("Error");
        popup.setHeaderText("Wrong file type");
        popup.show();
    }
    
    public String getFileExtension(){
        return fileExtension;
    }
    
    public int editMIDI(){
        if(fileExtension.equals("mid")){
            beat = new BeatMaker();
            beat.openForEdit(sequencer);
            return 0;
        }
        else return -1;
    }
}