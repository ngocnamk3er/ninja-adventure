package main;

import buttons.LevelButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;

public class MenuScene extends Scene {
    private TilePane tilePane;
    public MenuScene(MainStage mainStage) {
        super(new Group(),1344,768);
        tilePane = new TilePane();
        tilePane.getChildren().add(new LevelButton("1",mainStage));
        tilePane.getChildren().add(new LevelButton("2",mainStage));
        tilePane.getChildren().add(new LevelButton("3",mainStage));
        tilePane.getChildren().add(new LevelButton("4",mainStage));
        tilePane.getChildren().add(new LevelButton("5",mainStage));
        tilePane.getChildren().add(new LevelButton("6",mainStage));
        setRoot(tilePane);
    }
    
}
