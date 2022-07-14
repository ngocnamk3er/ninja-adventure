package main;

import buttons.BackButton;
import data.Data;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import map.MapInteractionManager;
import map.MapManager;
import subScene.GameOverSubScene;

public class GameScene extends Scene {
    private Canvas canvas;
    private Canvas canvasbg;
    private GameLoop gameLoop;
    private GraphicsContext gc;
    private GraphicsContext gcbg;
    private MapManager mapManager;
    private Text transcript;
    private BackButton backButton;
    private MapInteractionManager mapInteractionManager;
    private ImageView hudCoin;
    private ImageView[] hudHearts;
    private Image heartiImage;
    private int oldHearts;
    private Pane pane;
    private GameOverSubScene gameOverSubScene;

    public GameScene(MainStage mainStage) {
        super(new Group(), 1344, 768);
        canvas = new Canvas(1344, 768);
        canvasbg = new Canvas(1344, 768);
        pane = new Pane();
        setRoot(pane);

        gc = canvas.getGraphicsContext2D();
        gcbg = canvasbg.getGraphicsContext2D();

        mapManager = new MapManager(gcbg);
        mapInteractionManager = new MapInteractionManager(gc, mapManager.getMapData(), this);

        backButton = new BackButton(mainStage);
        backButton.setLayoutX(1344 - 58);
        backButton.setLayoutY(10);

        transcript = new Text();
        transcript.setFont(Font.loadFont(GameScene.class.getResourceAsStream("m6x11.ttf"), 40));
        transcript.setFill(Color.YELLOW);
        transcript.setLayoutX(80);
        transcript.setLayoutY(120);

        hudCoin = new ImageView(new Image(GameScene.class.getResourceAsStream("hudCoin.png")));
        hudCoin.setLayoutX(16);
        hudCoin.setLayoutY(80);
        hudCoin.setFitWidth(48);
        hudCoin.setFitHeight(48);

        gameOverSubScene = new GameOverSubScene();
        gameOverSubScene.getConfirmButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameOverSubScene.setVisible(false);
                mainStage.setMenuScene();
            }
        });

        pane.getChildren().add(canvasbg);
        pane.getChildren().add(canvas);
        pane.getChildren().add(backButton);
        pane.getChildren().add(transcript);
        pane.getChildren().add(hudCoin);
        pane.getChildren().add(gameOverSubScene);
        hudHearts = new ImageView[15];
        heartiImage = new Image(getClass().getResource("hearts_hud.png").toString());
        System.out.println(getClass().getResource("hearts_hud.png").toString());
        oldHearts = Data.getHeart();

        for (int i = 0; i < 15; i++) {
            hudHearts[i] = new ImageView();
            hudHearts[i].setFitWidth(48);
            hudHearts[i].setFitHeight(48);
            hudHearts[i].setLayoutY(16);
            hudHearts[i].setLayoutX(16 + 64 * i);
            pane.getChildren().add(hudHearts[i]);
        }
        for (int i = 0; i < oldHearts; i++) {
            hudHearts[i].setImage(heartiImage);
        }
        setCursor(new ImageCursor(new Image(SelectLevelScene.class.getResourceAsStream("cursorImage.png"))));
    }

    public void MakeGameLevel(int levelValue) {
        mapManager.loadDataMap(levelValue);
        mapManager.render();
        mapInteractionManager.setInitialState(levelValue);
        gameLoop = new GameLoop(mapInteractionManager);
        gameLoop.start();
    }

    public void MakeGameNextLevel(int levelValue) {
        mapManager.loadDataMap(levelValue);
        mapManager.render();
        mapInteractionManager.setInitialState(levelValue);
    }

    public void setHudHeart(int hearts) {
        for (int i = hearts; i < oldHearts; i++) {
            try {
                hudHearts[i].setVisible(false);
            } catch (Exception e) {
                System.out.println(i);
            }
        }
        for (int i = 0; i < hearts; i++) {
            hudHearts[i].setVisible(true);
            hudHearts[i].setImage(heartiImage);
        }
        oldHearts = hearts;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setTranscript(int point) {
        this.transcript.setText(String.valueOf(point));
    }

    public GameOverSubScene getGameOverSubScene() {
        return gameOverSubScene;
    }

    public void setGameOverSubScene(GameOverSubScene gameOverSubScene) {
        this.gameOverSubScene = gameOverSubScene;
    }

    public BackButton getBackButton() {
        return backButton;
    }
    
}
