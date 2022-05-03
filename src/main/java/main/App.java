package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Scene menuScene;
    private Scene gameScene;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        menuScene=new MenuScene(stage);
        stage.setScene(menuScene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }

}