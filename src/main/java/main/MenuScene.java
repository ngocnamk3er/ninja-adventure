package main;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import subScene.ConfirmSubScene;
import buttons.MenuButton;
import data.Data;
import javafx.event.EventHandler;
import javafx.scene.Group;

public class MenuScene extends Scene {
    private Pane pane;
    private Background menuButtonBackground;
    private Background background;
    private ConfirmSubScene confirmSubScene;

    public MenuScene(MainStage mainStage) {
        super(new Group(), 1344, 768);
        pane = new Pane();
        setRoot(pane);

        background = new Background(new BackgroundImage(new Image(
                GameScene.class.getResourceAsStream("menuBG.png")),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
        pane.setBackground(background);

        menuButtonBackground = new Background(new BackgroundImage(new Image(
                MenuButton.class.getResourceAsStream("menuButton.png")),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));

        MenuButton startButton = new MenuButton("Continute", menuButtonBackground);
        startButton.setLayoutY(250);

        MenuButton newGameButton = new MenuButton("New Game", menuButtonBackground);
        newGameButton.setLayoutY(350);

        MenuButton exitButton = new MenuButton("Exit", menuButtonBackground);
        exitButton.setLayoutY(450);

        confirmSubScene = new ConfirmSubScene();
        confirmSubScene.getConfirmButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                confirmSubScene.setVisible(false);
                Data.resetData();
                Data.loadData();
                SelectLevelScene.tickLevel();
                mainStage.setSelectLevelScene();
            }
        });

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
                confirmSubScene.setVisible(true);
            }
        });
        pane.getChildren().addAll(startButton, exitButton, newGameButton, confirmSubScene);
    }
}
