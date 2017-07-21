/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.sound.midi.Track;
import javax.swing.JCheckBox;
/**
 *
 * @author jon
 */
public class SerializeBeatAndSynth implements Serializable{
    FileOutputStream fileOutput;
    FileInputStream fileInput;
    
    public SerializeBeatAndSynth(File file) throws FileNotFoundException{
        fileOutput = new FileOutputStream(file);
        fileInput = new FileInputStream(file);
    }
    
    public void serializeBeatAndSynth(ArrayList<JCheckBox> checkboxList, Track synthTrack) throws IOException{
        if (checkboxList != null){
            serializeBeat(checkboxList);
            System.out.println("Test");
        }
        if (synthTrack != null)
            serializeSynth(synthTrack);
    }
    
    public void serializeBeat(ArrayList<JCheckBox> checkboxList) throws FileNotFoundException, IOException{
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(checkboxList);
    }
    
    public void serializeSynth(Track synthTrack) throws IOException{
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(synthTrack);
    }
        
    public ArrayList<JCheckBox> deserializeBeat() throws IOException, ClassNotFoundException{
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        return (ArrayList<JCheckBox>)objectInput.readObject();
    }
    
    public Track deserializeSynth() throws IOException, ClassNotFoundException{
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        return (Track)objectInput.readObject();
    }
}
