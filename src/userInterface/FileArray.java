/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.io.File;

/**
 *
 * @author jon
 */
public class FileArray {
    File file[];
    int i;
    public FileArray(){
        file = new File[10];
        i = 0;
    }
    
    public void addFile(File in){
        file[i] = in;
        i++;
    }
    public void removeFile(int n){
        for(int j = n; n < i; n++){
            file[n] = file[n+1];
        }
        file[i] = null;
    }
}
