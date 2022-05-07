package main;

import buttons.LevelButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class MenuScene extends Scene {
    private Pane pane;
    private Background background;
    private Background backgroundLevelButton;
    public MenuScene(MainStage mainStage) {
        super(new Group(),1344,768);
        pane = new Pane();
        background = new Background(new BackgroundImage(new Image(MakeMainScene.class.getResourceAsStream("menuBG.png")),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        backgroundLevelButton = new Background(new BackgroundImage(new Image(LevelButton.class.getResourceAsStream("levelButton.png")),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        pane.setBackground(background);
        for(int i=0;i<36;i++){
            LevelButton levelButton = new LevelButton(Integer.toString(i+1),mainStage,backgroundLevelButton);
            pane.getChildren().add(levelButton);
            levelButton.setLayoutX(72+i%12*100);
            levelButton.setLayoutY(300+i/12*120);
        }
        setRoot(pane);
        // setCursor();
    }
    
}
