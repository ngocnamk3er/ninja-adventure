package main;

import java.util.ArrayList;

import buttons.CloseGameButton;
import buttons.LevelButton;
import data.Data;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class SelectLevelScene extends Scene {
    private Pane pane;
    private Background background;
    private Background backgroundLevelButton;
    private CloseGameButton closeGameButton;
    private static ArrayList<LevelButton> levelButtons;
    public SelectLevelScene(MainStage mainStage) {
        super(new Group(),1344,768);
        levelButtons = new ArrayList<>();
        pane = new Pane();
        background = new Background(new BackgroundImage(new Image(GameScene.class.getResourceAsStream("menuBG.png")),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        backgroundLevelButton = new Background(new BackgroundImage(new Image(LevelButton.class.getResourceAsStream("levelButton.png")),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        pane.setBackground(background);
        for(int i=0;i<36;i++){
            LevelButton levelButton = new LevelButton(Integer.toString(i+1),mainStage,backgroundLevelButton);
            pane.getChildren().add(levelButton);
            levelButton.setLayoutX(72+i%12*100);
            levelButton.setLayoutY(300+i/12*120);
            levelButtons.add(levelButton);
        }
        closeGameButton = new CloseGameButton(mainStage);
        closeGameButton.setLayoutX(1344-58);
        closeGameButton.setLayoutY(10);
        pane.getChildren().add(closeGameButton);
        setRoot(pane);
        setCursor(new ImageCursor(new Image(SelectLevelScene.class.getResourceAsStream("cursorImage.png"))));
        tickLevel();
    }
    public static void tickLevel(){
        for(LevelButton levelButton:levelButtons){
            if(levelButton.getLevelValue()<=Data.getLevel()){
                levelButton.setTextFill(Color.WHITE);
                levelButton.setDisable(false);
            }else{
                levelButton.setDisable(true);
            }
        }
    }
}
