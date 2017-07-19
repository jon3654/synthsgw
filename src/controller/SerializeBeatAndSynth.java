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
    
    public void serializeBeatAndSynth(BeatMaker beat, Synth synth) throws IOException{
        serializeBeat(beat);
        serializeSynth(synth);
    }
    
    public void serializeBeat(BeatMaker beat) throws FileNotFoundException, IOException{
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(beat);
    }
    
    public void serializeSynth(Synth synth) throws IOException{
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(synth);
    }
        
    public BeatMaker deserializeBeat() throws IOException, ClassNotFoundException{
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        return (BeatMaker)objectInput.readObject();
    }
    
    public Synth deserializeSynth() throws IOException, ClassNotFoundException{
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        return (Synth)objectInput.readObject();
    }
}
