package main;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.application.Platform;
public class MainStage extends Stage {
    private GameScene gameScene;
    private SelectLevelScene selectLevelScene;
    private MenuScene menuScene;
    private final int MENU_SCENE = 0;
    private final int SELECT_LEVEL_SCENE = 1;
    private final int GAME_SCENE = 2;
    private int indexScene = 0;
    public MainStage(){
        getIcons().add(new Image(MainStage.class.getResourceAsStream("logo.png")));
        setTitle("Ninja Adventures");
        setResizable(false);
        gameScene = new GameScene(this);
        selectLevelScene = new SelectLevelScene(this);
        menuScene = new MenuScene(this);
        setScene(menuScene);
    }
    public void nextScene(int levelValue){
        indexScene++;
        switch (indexScene) {
            case SELECT_LEVEL_SCENE:
                setSelectLevelScene();
                break;
            case GAME_SCENE:
                setgameScene(levelValue);
                break;
            default:
                break;
        }
    } 
    public void backScene(){
        indexScene--;
        switch (indexScene) {
            case -1:
                Data.saveData();
                Platform.exit();
                System.exit(0);
            case MENU_SCENE:
                setMenuScene();
                break;
            case SELECT_LEVEL_SCENE:
                setSelectLevelScene();
                break;
            default:
                break;
        }
    }
    public void setSelectLevelScene(){
        setScene(selectLevelScene);
        if(gameScene.getGameLoop()!=null){
            gameScene.getGameLoop().interrupt();
        }
    }
    public void setMenuScene(){
        setScene(menuScene);
    }
    public void setgameScene(int levelValue){
        setScene(gameScene);
        gameScene.MakeGameLevel(levelValue);
    }
}
