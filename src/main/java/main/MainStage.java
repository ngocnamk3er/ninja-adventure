package main;


import javafx.stage.Stage;

public class MainStage extends Stage {
    private MakeMainScene makeMainScene;
    private MenuScene menuScene;
    public MainStage(){
        makeMainScene = new MakeMainScene(this);
        menuScene = new MenuScene(this);
        setScene(menuScene);
    }   
    public void setMenuScene(){
        setScene(menuScene);
        makeMainScene.getGameLoop().interrupt();
    }
    public void setMakeMainScene(int level){
        setScene(makeMainScene);
        makeMainScene.MakeGameLevel(level);
    }
}
