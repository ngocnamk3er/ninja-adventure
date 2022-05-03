package main;

import buttons.LevelButton;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuScene extends Scene {
    private GridPane gridPane;
    public MenuScene(Stage stage) {
        super(new Group(),1344,768);
        gridPane = new GridPane();
        gridPane.add(new LevelButton("1",stage),0,1);
        gridPane.add(new LevelButton("2",stage),0,2);
        gridPane.add(new LevelButton("3",stage),0,3);
        gridPane.add(new LevelButton("4",stage),0,4);
        gridPane.add(new LevelButton("5",stage),0,5);
        gridPane.add(new LevelButton("6",stage),0,6);
        setRoot(gridPane);
    }
    
}
