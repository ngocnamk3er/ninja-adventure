package main;


import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainStage extends Stage {
    private GameScene gameScene;
    private SelectLevelScene selectLevelScene;
    public MainStage(){
        getIcons().add(new Image(MainStage.class.getResourceAsStream("logo.png")));
        setTitle("Ninja Adventures");
        setResizable(false);
        gameScene = new GameScene(this);
        selectLevelScene = new SelectLevelScene(this);
        setScene(selectLevelScene);
    }   
    public void setSelectLevelScene(){
        setScene(selectLevelScene);
        gameScene.getGameLoop().interrupt();
    }
    public void setgameScene(int levelValue){
        setScene(gameScene);
        gameScene.MakeGameLevel(levelValue);
    }
}
