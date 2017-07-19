/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 *
 * @author jon
 */
public class SerializeBeatAndSynth implements Serializable{
    FileOutputStream fileOutput;
    
    public SerializeBeatAndSynth(File file) throws FileNotFoundException{
        fileOutput = new FileOutputStream(file);
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
    
    public void serialize(){
        
    }
    
    public void deserializeBeat(){
        
    }
    
    public void deserializeSynth(){
        
    }
    
    public void deserialize(){
        
    }
}
