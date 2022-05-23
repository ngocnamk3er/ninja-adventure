package main;

import buttons.CloseGameButton;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import map.MapManager;
import mapinteraction.MapInteractionManager;

public class MakeMainScene extends Scene{
    private Canvas canvas;
    private Canvas canvasbg;
    private GraphicsContext gc;
    private GraphicsContext gcbg;
    private MapManager mapManager;
    private GameLoop gameLoop;
    private MapInteractionManager mapInteractionManager;
    private CloseGameButton closeGameButton;
    public MakeMainScene(MainStage mainStage) {
        super(new Group(),1344,768);
        canvas=new Canvas(1344,768);
        canvasbg=new Canvas(1344,768);

        gc=canvas.getGraphicsContext2D();
        gcbg=canvasbg.getGraphicsContext2D();
        
        mapManager=new MapManager(gcbg);
        mapInteractionManager = new MapInteractionManager(gc,mapManager.getMapData(),this);

        Pane stackPane=new Pane();
        stackPane.getChildren().add(canvasbg);
        stackPane.getChildren().add(canvas);
        setRoot(stackPane);
        closeGameButton = new CloseGameButton(mainStage);
        stackPane.getChildren().add(closeGameButton);
        closeGameButton.setLayoutX(1344-58);
        closeGameButton.setLayoutY(10);
        setCursor(new ImageCursor(new Image(MenuScene.class.getResourceAsStream("cursorImage.png"))));
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
    
}
