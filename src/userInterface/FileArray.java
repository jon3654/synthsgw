/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.io.File;

// class that stores open files
public class FileArray {
    File file[];
    int i;  // var that keeps track of how many files are open
    
    // class constructor
    public FileArray(){
        file = new File[10];
        i = 0;
    }
    
    // adds a file to the array
    public void addFile(File in){
        file[i] = in;
        i++;
    }
    
    // removes a file from the array
    public void removeFile(int n){
        for(int j = n; n < i; n++){
            file[n] = file[n+1];
        }
        file[i] = null;
        i--;
    }
    
    // returns the most recently added file
    public File getFile(){
        return file[i-1];
    }
}
