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
    public MakeMainScene(Stage stage) {
        super(new Group(),1344,768);
        canvas=new Canvas(1344,768);
        canvasbg=new Canvas(1344,768);

        gc=canvas.getGraphicsContext2D();
        gcbg=canvasbg.getGraphicsContext2D();

        mapManager=new MapManager(gcbg);
        mapManager.render();

        StackPane stackPane=new StackPane();
        stackPane.getChildren().add(canvasbg);
        stackPane.getChildren().add(canvas);
        setRoot(stackPane);
        mapInteractionManager = new MapInteractionManager(gc,mapManager.getMapData(),this);
        gameLoop = new GameLoop(mapInteractionManager);
        gameLoop.start();
        stackPane.getChildren().add(new CloseGameButton("1",stage,gameLoop));
        // gameLoop.wait();
        // gameLoop.interrupt();
    }
    
}
