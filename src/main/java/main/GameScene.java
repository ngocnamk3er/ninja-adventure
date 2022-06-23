package main;

import buttons.CloseGameButton;
import data.Data;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import map.MapManager;
import mapinteraction.MapInteractionManager;

public class GameScene extends Scene{
    private Canvas canvas;
    private Canvas canvasbg;
    private GameLoop gameLoop;
    private GraphicsContext gc;
    private GraphicsContext gcbg;
    private MapManager mapManager;
    private Text transcript;
    private CloseGameButton closeGameButton;
    private MapInteractionManager mapInteractionManager;
    private ImageView hudCoin;
    private ImageView[] hudHearts;
    private Image heartiImage;
    private Image noHeartiImage;
    private int oldHearts;
    public GameScene(MainStage mainStage) {
        super(new Group(),1344,768);
        canvas=new Canvas(1344,768);
        canvasbg=new Canvas(1344,768);
        
        gc=canvas.getGraphicsContext2D();
        gcbg=canvasbg.getGraphicsContext2D();
        
        mapManager=new MapManager(gcbg);
        mapInteractionManager = new MapInteractionManager(gc,mapManager.getMapData(),this);
        //
        closeGameButton = new CloseGameButton(mainStage);
        closeGameButton.setLayoutX(1344-58);
        closeGameButton.setLayoutY(10);
        //
        transcript = new Text();  
        transcript.setFont(Font.loadFont(GameScene.class.getResourceAsStream("m6x11.ttf"), 40));
		transcript.setFill(Color.YELLOW);
        transcript.setLayoutX(80);
        transcript.setLayoutY(120);
        //
        hudCoin = new ImageView(new Image(GameScene.class.getResourceAsStream("hudCoin.png")));
        hudCoin.setLayoutX(16);
        hudCoin.setLayoutY(80);
        hudCoin.setFitWidth(48);
        hudCoin.setFitHeight(48);
        //
        Pane pane=new Pane();
        setRoot(pane);
        pane.getChildren().add(canvasbg);
        pane.getChildren().add(canvas);
        pane.getChildren().add(closeGameButton);
        pane.getChildren().add(transcript);
        pane.getChildren().add(hudCoin);
        //
        hudHearts = new ImageView[15];
        heartiImage = new Image(GameScene.class.getResourceAsStream("hearts_hud.png"));
        noHeartiImage = new Image(GameScene.class.getResourceAsStream("no_hearts_hud.png"));
        oldHearts = Data.getHeart();
        for(int i = 0; i < oldHearts; i++){
            hudHearts[i] = new ImageView(heartiImage);
        }
        for(int i = 0; i < oldHearts; i++){
            hudHearts[i].setFitWidth(48);
            hudHearts[i].setFitHeight(48);
            hudHearts[i].setLayoutY(16);
            hudHearts[i].setLayoutX(16+64*i);
            pane.getChildren().add(hudHearts[i]);
        }
        //
        setCursor(new ImageCursor(new Image(SelectLevelScene.class.getResourceAsStream("cursorImage.png"))));
    }
    public void MakeGameLevel(int levelValue){
        mapManager.loadDataMap(levelValue);//level
        mapManager.render();//level
        mapInteractionManager.setInitialState(levelValue);//level
        gameLoop = new GameLoop(mapInteractionManager);
        gameLoop.start();
    }
    public void MakeGameNextLevel(int levelValue){
        mapManager.loadDataMap(levelValue);//level
        mapManager.render();//level
        mapInteractionManager.setInitialState(levelValue);//level
    }
    public GameLoop getGameLoop() {
        return gameLoop;
    }
    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }
    public void setTranscript(int point){
        this.transcript.setText(String.valueOf(point));
    }
    public void setHudHeart(int hearts){
        for(int i = Data.getHeart(); i < oldHearts; i++){
            hudHearts[i].setVisible(false);
        }
        for(int i = 0; i < Data.getHeart(); i++){
            hudHearts[i].setVisible(true);
            hudHearts[i].setImage(heartiImage);
        }
    }
}
