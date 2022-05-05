package main;

import buttons.CloseGameButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
    public MakeMainScene(MainStage mainStage) {
        super(new Group(),1344,768);
        canvas=new Canvas(1344,768);
        canvasbg=new Canvas(1344,768);
        gc=canvas.getGraphicsContext2D();
        gcbg=canvasbg.getGraphicsContext2D();
        
        mapManager=new MapManager(gcbg);
        mapInteractionManager = new MapInteractionManager(gc,mapManager.getMapData(),this);

        StackPane stackPane=new StackPane();
        stackPane.getChildren().add(canvasbg);
        stackPane.getChildren().add(canvas);
        setRoot(stackPane);
        stackPane.getChildren().add(new CloseGameButton("1",mainStage));
        // MakeGameLevel(1);
    }
    public void MakeGameLevel(int level){
        mapManager.loadDataMap();//level
        mapManager.render();//level
        mapInteractionManager.setInitialState();//level
        gameLoop = new GameLoop(mapInteractionManager);
        gameLoop.start();
    }
    public GameLoop getGameLoop() {
        return gameLoop;
    }
    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }
    
}
