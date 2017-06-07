/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;


public class OpenFile {
    final JFileChooser fc;
    Component comp;
    String fileExt;
    
    public OpenFile(String str){
        comp = null;
        fc = new JFileChooser();
        fileExt = str;
    }
    
    public File openFile(){
        int returnVal = fc.showOpenDialog(comp);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
            if(checkExt(file.getName().substring(file.getName().lastIndexOf('.') + 1)) == 0)
                return file;
        }
        return null;
    }
    private int checkExt(String str){
        if(str.equals(fileExt)){
            System.out.println(fileExt);
            return 0;
        }
        else return -1;
    }
}
