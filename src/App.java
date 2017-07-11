/* App.java
 *
 * Entry point for the application using JavaFX
 */

package com.github.synthsgw;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class App extends Application {
    private static final String SCENE_FILE = "/fxml/mainScene.fxml"; 
    private static final String WINDOW_TITLE = "SynthsGW - COP 4331 Project";
    

    public static void main(String [] args) {
		launch(args);
	}
	
    @Override
    public void start(Stage stage) {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource(SCENE_FILE)); 

	try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
	} catch(IOException e) {
            e.printStackTrace();
            System.exit(-1);
	}

	stage.setTitle(WINDOW_TITLE);
	stage.show();
        
    }
}

