package mapinteraction;
import java.util.ArrayList;

import entities.Button;
import entities.Coin;
import entities.Door;
import entities.Enity;
import entities.Player;
import entities.Stone;
import help.Constant.MapInteraction;
import inputs.SetKeyBoardInputs;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.MakeMainScene;
import static help.HelpMethods.*;
public class MapInteractionManager {
    private MakeMainScene makeMainScene;
    private ArrayList<Enity> removedEnities;
    private ArrayList<Coin> coins;
    private ArrayList<Stone> stones;
    private ArrayList<Button> buttons;
    private Player player;
    private Door door;
    private GraphicsContext gc;
    private int [][] mapData;
    private Image[][] animationImagesPlayer;
    private Image[] animationImagesCoin;
    private Image animationImageStone;
    private Image[][] animationImagesDoor;
    private Image[] animationImagesButton;
    private void loadAnimations(){
        loadAnimationsPlayer();
        loadAnimationsCoins();
        loadAnimationsStone();
        loadAnimationsDoor();
        loadAnimationsButton();
    }
    public MapInteractionManager(GraphicsContext gc,int [][]mapData, MakeMainScene makeMainScene){
        loadAnimations();
        this.makeMainScene = makeMainScene;
        removedEnities = new ArrayList<>();
        coins = new ArrayList<>();
        stones = new ArrayList<>();
        buttons = new ArrayList<>();
        player = new Player();
        door = new Door();
        this.gc = gc;
        this.mapData = mapData;
    }
    public void setInitialState(){
        loadDataMapInteraction();
        new SetKeyBoardInputs(this);
    }
    private void loadDataMapInteraction(){
        removedEnities.clear();
        coins.clear();
        stones.clear();
        buttons.clear();
        for(int i=0;i<12;i++){
            for(int j=0;j<21;j++){
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'c'){
                    Coin coin = new Coin(j*64, i*64, 32, 32, gc);
                    coin.setAnimationsImages(animationImagesCoin);
                    coins.add(coin);
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'p'){
                    player.setProperties(j*64, i*64, 64, 64,this);
                    player.setAnimationsImages(animationImagesPlayer);
                    
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 's'){
                    Stone stone = new Stone(j*64, i*64, 64, 64, this);
                    stone.setAnimationsImages(animationImageStone);
                    stones.add(stone);
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'b'){
                    Button button = new Button(j*64, i*64, 64, 64, this);
                    button.setAnimationsImages(animationImagesButton);
                    buttons.add(button);
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'd'){
                    door.setProperties(j*64, i*64, 64, 64*3, this);
                    door.setAnimationsImages(animationImagesDoor);
                }
            }
        }
    }
    public void update(){
        player.update();
        for(Stone stone:stones){
            stone.update();
        }
        for (Coin coin : coins) {
            coin.update();
        }
        for(Button button:buttons){
            button.update();
        }
        door.update();
    }
    public void render(){
        try {
            gc.clearRect(0, 0, 21*64, 12*64);
            // player.render();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for(Stone stone:stones){
            stone.render();
        }
        for (Enity coin : coins) {
            coin.render();
        }
        for(Button button:buttons){
            button.render();
        }
        door.render();
        for(Enity removedEnities:removedEnities){
            removedEnities.render();
        }
        player.render();  
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }
    public ArrayList<Enity> getRemovedEnities() {
        return removedEnities;
    }
    public void setRemovedEnities(ArrayList<Enity> removedEnities) {
        this.removedEnities = removedEnities;
    }
    public int[][] getMapData() {
        return mapData;
    }
    public void setMapData(int[][] mapData) {
        this.mapData = mapData;
    }
    public GraphicsContext getGc() {
        return gc;
    }
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    public ArrayList<Stone> getStones() {
        return stones;
    }
    public void setStones(ArrayList<Stone> stones) {
        this.stones = stones;
    }
    public ArrayList<Button> getButtons() {
        return buttons;
    }
    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }
    public MakeMainScene getMakeMainScene() {
        return makeMainScene;
    }
    public void setMakeMainScene(MakeMainScene makeMainScene) {
        this.makeMainScene = makeMainScene;
    }
    private void loadAnimationsStone(){
        try {
            animationImageStone =new Image(Player.class.getResourceAsStream("stone.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsCoins() {
        animationImagesCoin =  new Image[6];
        for(int i = 0 ; i < 6 ; i++) {
            try {
                animationImagesCoin[i]=new Image(Coin.class.getResourceAsStream("coin"+i+".png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void loadAnimationsDoor() {
        animationImagesDoor = new Image[2][15];
        for(int i=0;i<2;i++){
            for(int j=0;j<getAmountSpritesOfDoor(i);j++){
                if(i == CLOSED){
                    animationImagesDoor[i][j] =  new Image(Door.class.getResourceAsStream("door_closed"+j+".png"));
                }else {
                    animationImagesDoor[i][j] =  new Image(Door.class.getResourceAsStream("door_openning"+j+".png"));
                }
            }
        } 
    }
    private void loadAnimationsButton() {
        animationImagesButton =  new Image[2];
        for(int i = 0 ; i < 2 ; i++) {
            try {
                animationImagesButton[i]=new Image(Button.class.getResourceAsStream("button"+i+".png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    protected void loadAnimationsPlayer() {//load 1 bộ ảnh để tạo mảng 2 chiều để tạo animation
        animationImagesPlayer = new Image[14][7];
        for(int i = 0 ; i < 14 ; i++){
            if (i == RUN_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("RUN_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == RUN_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("RUN_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == IDLE_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("IDLE_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == IDLE_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("IDLE_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK1_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("ATTACK1_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK1_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("ATTACK1_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == PUSH_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("PUSH_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == PUSH_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("PUSH_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPUP_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("JUMPUP_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPUP_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("JUMPUP_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPDOWN_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("JUMPDOWN_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPDOWN_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("JUMPDOWN_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK2_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("ATTACK2_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK2_L) {
                try {
                    for (int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]=new Image(Player.class.getResourceAsStream("ATTACK2_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
        }
    } 
}

