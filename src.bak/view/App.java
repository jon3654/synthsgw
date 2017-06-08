/* App.java
 *
 * Entry point for the application.
 */

package com.github.synthsgw.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static final String SCENE_FILE = "/fxml/mainScene.fxml"; 
    private static final String WINDOW_TITLE = "Window Title";
    public static void main(String [] args) {
	launch(args);
}
	
@Override
public void start(Stage stage) {
	//FIXME
	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	System.out.println(System.getProperty("user.dir"));
	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	//FIXME
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(SCENE_FILE)); 
    try {
	Parent root = loader.load();
	Scene scene = new Scene(root);
	stage.setScene(scene);
    } 
    catch(IOException e) {
	e.printStackTrace();
	System.exit(-1);
    }

    stage.setTitle(WINDOW_TITLE);
	stage.show();
    }
}

