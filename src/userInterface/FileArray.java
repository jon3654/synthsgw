/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.io.File;

// class that stores open files
public class FileArray {
    static int MAX = 10;    // max num of files allowed to be open at the same time
    static File file[];    // file array for storing files
    
    static int i;  // var that keeps track of how many files are open
    
    // class constructor
    public FileArray(){
        file = new File[MAX];
        i = 0;
    }
    
    // adds a file to the array
    public void addFile(File in){
        if(i < MAX){
            file[i] = in;
            i++;
        }
    }
    
    // closes the most recently opened file
    public int close(){
        if(i > 0){
            removeFile(i-1);
            return 0;
        }
        return -1;
    }
    
    // removes a file from the array
    private void removeFile(int n){
        for(int j = n; n < i; n++){
            file[n] = file[n+1];
        }
        file[i] = null;
        i--;
    }
    
    // returns the most recently added file
    public static File getFile(){
        if (i == 0)
            return null;
        return file[i-1];
    }
}
