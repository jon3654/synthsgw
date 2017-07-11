/* App.java
 *
 * Entry point for the application using JavaFX
 */

package com.github.synthsgw;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private static final String SCENE_FILE = "/fxml/mainScene.fxml"; 
    private static final String WINDOW_TITLE = "SynthsGW - COP 4331 Project";
    
    @FXML private SplitPane main_split_pane;
    @FXML private AnchorPane left_split_pane; 
    @FXML private AnchorPane right_split_pane;
    @FXML private ToolBar audio_tool_bar;
    @FXML private TitledPane mp3Pane;
    @FXML private VBox main_vBox;
    

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
        
	stage.setOnCloseRequest((event) -> {
		Platform.exit();
		System.exit(0);
	});

	stage.setTitle(WINDOW_TITLE);
	stage.show();
    }
}

