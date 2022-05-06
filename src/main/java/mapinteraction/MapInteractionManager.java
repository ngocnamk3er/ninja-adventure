package mapinteraction;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Button;
import entities.Coin;
import entities.Door;
import entities.Enity;
import entities.Player;
import entities.Stone;
import help.Constant.MapInteraction;
import inputs.SetKeyBoardInputs;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    private BufferedImage bufferedImage;
    private int levelValue;
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
    public void setInitialState(int levelValue){
        this.levelValue = levelValue;
        loadDataMapInteraction(levelValue);
        new SetKeyBoardInputs(this);
    }
    private void loadDataMapInteraction(int levelValue){
        removedEnities.clear();
        coins.clear();
        stones.clear();
        buttons.clear();
        player = new Player();
        door = new Door();
        for(int i=0;i<12;i++){
            for(int j=0;j<21;j++){
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'c'){
                    Coin coin = new Coin(j*64, i*64, 32, 32, gc);
                    coin.setAnimationsImages(animationImagesCoin);
                    coins.add(coin);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'p'){
                    player.setProperties(j*64, i*64, 64, 64,this);
                    player.setAnimationsImages(animationImagesPlayer);
                    
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 's'){
                    Stone stone = new Stone(j*64, i*64, 64, 64, this);
                    stone.setAnimationsImages(animationImageStone);
                    stones.add(stone);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'b'){
                    Button button = new Button(j*64, i*64, 64, 64, this);
                    button.setAnimationsImages(animationImagesButton);
                    buttons.add(button);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'd'){
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
    


    private void loadAnimationsStone(){
        try {
            animationImageStone =new Image(Player.class.getResourceAsStream("stone.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsCoins() {
        animationImagesCoin =  new Image[6];
        try {
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin.png"));
            for(int i = 0 ; i < 6 ; i++) {
                try {
                    animationImagesCoin[i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i*8, 0, 8, 8), null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    private void loadAnimationsDoor() {
        animationImagesDoor = new Image[2][15];
        for(int i=0;i<2;i++){
            for(int j=0;j<getAmountSpritesOfDoor(i);j++){
                if(i == CLOSED){
                    try {
                        bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("door_closed.png"));
                        animationImagesDoor[i][j] =  SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 48), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
        animationImagesPlayer = new Image[16][8];
        for(int i = 0 ; i < 16 ; i++){
            if (i == RUN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunL.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == RUN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunR.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == IDLE_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleL.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == IDLE_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleR.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK1_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1L.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK1_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1R.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == PUSH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingL.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == PUSH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingR.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPUP_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpL.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPUP_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpR.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPDOWN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownL.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPDOWN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownR.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK2_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2L.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK2_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2R.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == DEATH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathL.png"));
                    int AmountSprites = getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == DEATH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathR.png"));
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
        }
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
    public Door getDoor() {
        return door;
    }
    public void setDoor(Door door) {
        this.door = door;
    }
    public int getLevelValue() {
        return levelValue;
    }
    public void setLevelValue(int levelValue) {
        this.levelValue = levelValue;
    }
    
}

