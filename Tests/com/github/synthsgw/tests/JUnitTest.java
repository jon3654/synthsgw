package com.github.synthsgw.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.github.synthsgw.controller.MetronomeController;
import com.github.synthsgw.controller.SceneController;
import controller.OpenFile;
import java.io.File;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


public class JUnitTest extends ApplicationTest{
    
    private static final String SCENE_FILE = "/view/fxml/mainScene.fxml"; 
    private static final String WINDOW_TITLE = "Window Title";
    SceneController controller;
	
    @Override
    public void start(Stage stage) {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource(SCENE_FILE)); 

	try {
  		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
                controller = new SceneController();
	} catch(IOException e) {
			e.printStackTrace();
		System.exit(-1);
	}

	stage.setTitle(WINDOW_TITLE);
	stage.show();
    }

        
    public JUnitTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void checkExtWithCorrectExtension(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.checkExt("mp3"), 0);
    }
    
    @Test
    public void CheckExtWithoutCorrectExtension(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.checkExt("asd"),-1);
    }
    
    @Test
    public void attemptToCloseFileWhileNoneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.close(), -1);
    }
    
    @Test
    public void attemptToCloseFileWhileOneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        File newFile = new File("Tests/test.mp3");
        file.openPlayer(newFile);
        assertEquals(file.close(), 0);
    }
    
    @Test
    public void attemptToPlayFileWhileNoneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.play(), -1);
    }
    
    @Test
    public void attemptToPlayFileWhileOneIsOPen(){
        OpenFile file = new OpenFile("mp3");
        File newFile = new File("Tests/test.mp3");
        file.openPlayer(newFile);
        assertEquals(file.play(), 0);
    }
    
    @Test
    public void attemptToPauseFileWhileNoneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.pause(), -1);
    }
    
    @Test
    public void attemptToPauseFileWhileOneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        File newFile = new File("Tests/test.mp3");
        file.openPlayer(newFile);
        assertEquals(file.pause(), 0);
    }
    
    @Test
    public void attemptToStopFileWhileNoneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.stop(), -1);
    }
    
    @Test
    public void attemptToStopFileWhileOneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        File newFile = new File("Tests/test.mp3");
        OpenFile.openPlayer(newFile);
        assertEquals(file.stop(), 0);
    }
    
    @Test
    public void OpenMetronome(){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                SceneController controller = new SceneController();
                controller.openMetronome();
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }  
        });
    }
    
    @Test
    public void openBeatMaker(){
        assertEquals(controller.newBeat(), 0);
    }
    
    @Test
    public void goToGithub() throws Exception{
        controller.goToGithub();
    }
    
    @Test
    public void openMIDIFile(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() { 
                assertEquals(controller.openMIDI(),0);
            }
        });
    }
    
//    @Test
//    public void loadNewSoundPanel(){
//        OpenFile file = new OpenFile("mp3");
//        File newFile = new File("Tests/test.mp3");
//        OpenFile.numberOfOpenFiles++;
//        SceneController controller = new SceneController();
//        controller.loadNewSoundPanel(SCENE_FILE);
//    }
    
}
