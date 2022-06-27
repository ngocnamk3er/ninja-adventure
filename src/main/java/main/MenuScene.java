package main;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import subScene.SubMenuScene;
import buttons.MenuButton;
import data.Data;
import javafx.event.EventHandler;
import javafx.scene.Group;
public class MenuScene extends Scene{
    private Pane pane;
    private Background menuButtonBackground;
    private Background background;
    private SubMenuScene subMenuScene;
    public MenuScene(MainStage mainStage) {
        super(new Group(),1344,768);
        pane = new Pane();
        menuButtonBackground = new Background(new BackgroundImage(new Image(MenuButton.class.getResourceAsStream("menuButton.png")),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        background = new Background(new BackgroundImage(new Image(GameScene.class.getResourceAsStream("menuBG.png")),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        MenuButton startButton =  new MenuButton("Continute",menuButtonBackground);
        startButton.setLayoutY(200);
        //
        MenuButton newGameButton =  new MenuButton("New Game",menuButtonBackground);
        newGameButton.setLayoutY(300);
        //
        MenuButton skinButton =  new MenuButton("Skin",menuButtonBackground);
        skinButton.setLayoutY(400);
        //
        MenuButton settingButton =  new MenuButton("Setting",menuButtonBackground);
        settingButton.setLayoutY(500);
        //
        MenuButton exitButton =  new MenuButton("Exit",menuButtonBackground);
        exitButton.setLayoutY(600);
        //
        subMenuScene = new SubMenuScene();
        subMenuScene.getConfirmButton().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                subMenuScene.setVisible(false);
                Data.resetData();
                Data.loadData();
                SelectLevelScene.tickLevel();
                mainStage.nextScene(-1);
			}
		});
        //
        startButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.nextScene(-1);
			}
		});
        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.backScene();
			}
		});
        newGameButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                subMenuScene.setVisible(true);
			}
		});
        pane.setBackground(background);
        pane.getChildren().addAll(startButton,exitButton,newGameButton,skinButton,settingButton);
        pane.getChildren().add(subMenuScene);
        
        setRoot(pane);
    }
}
