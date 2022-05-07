package main;


import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainStage extends Stage {
    private MakeMainScene makeMainScene;
    private MenuScene menuScene;
    public MainStage(){
        getIcons().add(new Image(MainStage.class.getResourceAsStream("logo.png")));
        setTitle("Ninja Adventures");
        setResizable(false);
        makeMainScene = new MakeMainScene(this);
        menuScene = new MenuScene(this);
        setScene(menuScene);
    }   
    public void setMenuScene(){
        setScene(menuScene);
        makeMainScene.getGameLoop().interrupt();
    }
    public void setMakeMainScene(int levelValue){
        setScene(makeMainScene);
        makeMainScene.MakeGameLevel(levelValue);
    }
}
