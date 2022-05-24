package mapinteraction;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Button;
import entities.CeilingTrap;
import entities.Coin;
import entities.Door;
import entities.Enemy;
import entities.Enemy1;
import entities.Enemy2;
import entities.Enemy3;
import entities.Enemy4;
import entities.Enity;
import entities.Fire;
import entities.LightningTrap;
import entities.Player;
import entities.SandwormTrap;
import entities.ShurikenTrap;
import entities.SpearTrap;
import entities.Stone;
import entities.StrangeDoor;
import entities.Trap;
import help.Constant.MapInteraction;
import inputs.SetKeyBoardInputs;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import main.MakeMainScene;
public class MapInteractionManager {
    private MakeMainScene makeMainScene;
    private ArrayList<Coin> coins;
    private ArrayList<Stone> stones;
    private ArrayList<Button> buttons;
    private ArrayList<Enemy> enemies;
    private ArrayList<Fire> fires;
    private ArrayList<Trap> traps;
    private Player player;
    private Door door;
    private StrangeDoor strangeDoor;
    private GraphicsContext gc;
    private int [][] mapData;
    private Image[][] animationImagesPlayer;
    private Image[][] animationImagesCoin;
    private Image animationImageStone;
    private Image[][] animationImagesDoor;
    private Image[] animationImagesButton;
    private BufferedImage bufferedImage;
    private Image animationImageStrangeDoor;
    private int levelValue;
    private Image[][] animationImagesEnimy1;
    private Image[][] animationImagesEnimy2;
    private Image[][] animationImagesEnimy3;
    private Image[][] animationImagesEnimy4;
    private Image[][] animationImagesFire;
    private Image[] animationImagesLightningTrap;
    private Image[] animationImagesShurikenTrap;
    private Image[] animationImagesSandWormTrap;
    private Image[] animationImagesCeilingTrap;
    private Image[] animationImagesSpearTrap;
    private void loadAnimations(){
        loadAnimationsPlayer();
        loadAnimationsCoins();
        loadAnimationsStone();
        loadAnimationsDoor();
        loadAnimationsButton();
        loadAnimationsStrangDoor();
        //--------------
        loadAnimationsEnimy1();
        loadAnimationsEnimy2();
        loadAnimationsEnimy3();
        loadAnimationsEnimy4();
        //---------------
        loadAnimationsFire();
        loadAnimationsLightningTrap();
        loadAnimationsShurikenTrap();
        loadAnimationsSandWormTrap();
        loadAnimationsCeilingTrap();
        loadAnimationsSpearTrap();
    }

    public MapInteractionManager(GraphicsContext gc,int [][]mapData, MakeMainScene makeMainScene){
        loadAnimations();
        this.makeMainScene = makeMainScene;
        coins = new ArrayList<>();
        stones = new ArrayList<>();
        buttons = new ArrayList<>();
        enemies = new ArrayList<>();
        player = new Player();
        door = new Door();
        strangeDoor = new StrangeDoor();
        this.gc = gc;
        this.mapData = mapData;
    }
    public void setInitialState(int levelValue){
        this.levelValue = levelValue;
        loadDataMapInteraction(levelValue);
        new SetKeyBoardInputs(this);
    }
    private void loadDataMapInteraction(int levelValue){
        coins = new ArrayList<>();
        stones = new ArrayList<>();
        enemies = new ArrayList<>();
        buttons = new ArrayList<>();
        fires = new ArrayList<>();
        traps = new ArrayList<>();
        player = new Player();
        door = new Door();
        for(int i=0;i<12;i++){
            for(int j=0;j<21;j++){
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'c'){
                    Coin coin = new Coin(j*64, i*64,this,animationImagesCoin);
                    coins.add(coin);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'p'){
                    player.setProperties(j*64, i*64,animationImagesPlayer,this);
                    
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 's'){
                    Stone stone = new Stone(j*64, i*64,animationImageStone ,this);
                    stones.add(stone);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'b'){
                    Button button = new Button(j*64, i*64,animationImagesButton,this);
                    buttons.add(button);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'd'){
                    door.setProperties(j*64, i*64,animationImagesDoor,this);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'D'){
                    strangeDoor.setProperties(j*64, i*64, gc,animationImageStrangeDoor);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == '1'){
                    Enemy1 enimy1 = new Enemy1(j*64, i*64,animationImagesEnimy1 ,this);
                    enemies.add(enimy1);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == '2'){
                    Enemy2 enimy2 = new Enemy2(j*64, i*64,animationImagesEnimy2 ,this);
                    enemies.add(enimy2);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == '3'){
                    Enemy3 enimy3 = new Enemy3(j*64, i*64,animationImagesEnimy3 ,this);
                    enemies.add(enimy3);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == '4'){
                    Enemy4 enimy4 = new Enemy4(j*64, i*64,animationImagesEnimy4 ,this);
                    enemies.add(enimy4);
                    enimy4 = new Enemy4(j*64, i*64-32,animationImagesEnimy4 ,this);
                    enemies.add(enimy4);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'f'){
                    Fire fire = new Fire(j*64, i*64,animationImagesFire ,this);
                    fires.add(fire);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'l'){
                    LightningTrap lightningTrap = new LightningTrap(j*64, i*64,animationImagesLightningTrap ,this);
                    traps.add(lightningTrap);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'S'){
                    ShurikenTrap shurikenTrap = new ShurikenTrap(j*64, i*64,animationImagesShurikenTrap ,this);
                    traps.add(shurikenTrap);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'w'){
                    SandwormTrap sandwormTrap = new SandwormTrap(j*64, i*64,animationImagesSandWormTrap ,this);
                    traps.add(sandwormTrap);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'C'){
                    CeilingTrap ceilingTrap = new CeilingTrap(j*64, i*64,animationImagesCeilingTrap ,this);
                    traps.add(ceilingTrap);
                }else if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'P'){
                    SpearTrap spearTrap = new SpearTrap(j*64, i*64,animationImagesSpearTrap ,this);
                    traps.add(spearTrap);
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
        for(Enemy enemy:enemies){
            enemy.update();
        }
        for(Fire fire:fires){
            fire.update();
        }
        for(Trap trap:traps){
            trap.update();
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
        strangeDoor.render();
        for(Stone stone:stones){
            stone.render();
        }

        for (Enity coin : coins) {
            coin.render();
        }
        
        for(Button button:buttons){
            button.render();
        }
        for(Enemy enemy:enemies){
            enemy.render();
        }
        for(Fire fire:fires){
            fire.render();
        }
        for(Trap trap:traps){
            trap.render();
        }
        door.render();
        player.render();  
    }
    private void loadAnimationsSpearTrap(){
        animationImagesSpearTrap = new Image[12];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("SpearTrap.png"));
            int AmountSprites =  12;
            for(int j=0;j<AmountSprites;j++) {
                animationImagesSpearTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(4+j*16, 0, 9, 64), null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsCeilingTrap() {
        animationImagesCeilingTrap = new Image[14];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("CeilingTrap.png"));
            int AmountSprites = 14;
            for(int j=0;j<AmountSprites;j++) {
                animationImagesCeilingTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(16+j*64, 0, 32, 64), null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsSandWormTrap() {
        animationImagesSandWormTrap = new Image[11];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("SandWorm.png"));
            int AmountSprites =  11;
            for(int j=0;j<AmountSprites;j++) {
                animationImagesSandWormTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*32, 0, 32, 32), null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsShurikenTrap() {
        animationImagesShurikenTrap = new Image[8];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("Suriken.png"));
            int AmountSprites =  8;
            for(int j=0;j<AmountSprites;j++) {
                animationImagesShurikenTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*32, 0, 32, 32), null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void loadAnimationsLightningTrap() {
        animationImagesLightningTrap = new Image[25];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("LightningTrap.png"));
            int AmountSprites = 22;
            for(int j=0;j<AmountSprites;j++) {
                animationImagesLightningTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(24+j*96, 0, 48, 96), null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsFire() {
        animationImagesFire = new Image[2][4];
        for(int i=0;i<2;i++){
            if(i==Fire.ON){
                try {
                    bufferedImage = ImageIO.read(Fire.class.getResourceAsStream("fireOn.png"));
                    int AmountSprites = Fire.getAmountSpritesOfFireAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesFire[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 32), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else if(i==Fire.OFF){
                try {
                    bufferedImage = ImageIO.read(Fire.class.getResourceAsStream("fireOff.png"));
                    int AmountSprites = Fire.getAmountSpritesOfFireAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesFire[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 32), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    private void loadAnimationsEnimy4() {
        animationImagesEnimy4 = new Image[2][6];
        for(int i=0;i<2;i++){
            if(i==Enemy4.IDLE){
                try {
                    bufferedImage = ImageIO.read(Enemy4.class.getResourceAsStream("mushroomIdle.png"));
                    int AmountSprites = Enemy4.getAmountSpritesOfEnimy4Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy4[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else if(i==Enemy4.DEATH){
                try {
                    bufferedImage = ImageIO.read(Enemy4.class.getResourceAsStream("mushroomDeathR.png"));
                    int AmountSprites = Enemy4.getAmountSpritesOfEnimy4Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy4[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    private void loadAnimationsEnimy3() {
        animationImagesEnimy3 = new Image[6][8];
        for(int i=0;i<6;i++){
            if(i==Enemy3.RUN_L){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomRunL.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimy3Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy3[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }else if(i==Enemy3.DEATH_L){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomDeathL.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimy3Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy3[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }else if(i==Enemy3.HIT_L){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomHitL.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimy3Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy3[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }else if(i==Enemy3.RUN_R){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomRunR.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimy3Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy3[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }else if(i==Enemy3.DEATH_R){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomDeathR.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimy3Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy3[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }else if(i==Enemy3.HIT_R){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomHitR.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimy3Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy3[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
        }

    }
    private void loadAnimationsEnimy2() {
        animationImagesEnimy2 = new Image[3][6];
        for(int i=0;i<3;i++){
            if(i==Enemy2.RUN){
                try {
                    bufferedImage = ImageIO.read(Enemy2.class.getResourceAsStream("wormRun.png"));
                    int AmountSprites = Enemy2.getAmountSpritesOfEnimy2Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy2[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 8), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if(i==Enemy2.HIT){
                try {
                    bufferedImage = ImageIO.read(Enemy2.class.getResourceAsStream("wormHit.png"));
                    int AmountSprites = Enemy2.getAmountSpritesOfEnimy2Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy2[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 8), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if(i==Enemy2.DEATH){
                try {
                    bufferedImage = ImageIO.read(Enemy2.class.getResourceAsStream("wormDeath.png"));
                    int AmountSprites = Enemy2.getAmountSpritesOfEnimy2Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy2[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 8), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
        }
    }
    private void loadAnimationsEnimy1() {
        animationImagesEnimy1 = new Image[8][15];
        for(int i=0;i<8;i++){
            if(i==Enemy1.IDLE_R){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeIdleR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if(i==Enemy1.RUN_R){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeRunR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 24), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }           
            }
            else if(i==Enemy1.DEATH_R){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeDeathR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }    
            }
            else if(i==Enemy1.HIT_R){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeHitR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }   
            }
            else if(i==Enemy1.IDLE_L){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeIdleL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if(i==Enemy1.RUN_L){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeRunL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 24), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }           
            }
            else if(i==Enemy1.DEATH_L){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeDeathL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }    
            }
            else if(i==Enemy1.HIT_L){
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeHitL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimy1Action(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy1[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }   
            }
        }
    }
    private void loadAnimationsStrangDoor() {
        try {
            animationImageStrangeDoor =new Image(Player.class.getResourceAsStream("StrangeDoor.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsStone(){
        try {
            animationImageStone =new Image(Stone.class.getResourceAsStream("stone.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsCoins() {
        animationImagesCoin =  new Image[2][6];
        try {
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin.png"));
            for(int i = 0 ; i < 6 ; i++) {
                try {
                    animationImagesCoin[0][i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i*8, 0, 8, 8), null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin_pickup.png"));
            for(int i = 0 ; i < 6 ; i++) {
                try {
                    animationImagesCoin[1][i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i*8, 0, 8, 16), null);
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
            for(int j=0;j<Door.getAmountSpritesOfDoor(i);j++){
                if(i == Door.CLOSED){
                    try {
                        bufferedImage = ImageIO.read(Door.class.getResourceAsStream("door_closed.png"));
                        animationImagesDoor[i][j] =  SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 48), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        bufferedImage = ImageIO.read(Door.class.getResourceAsStream("door_openning.png"));
                        animationImagesDoor[i][j] =  SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 48), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            if (i == Player.RUN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.RUN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.IDLE_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.IDLE_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.ATTACK1_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1L.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.ATTACK1_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1R.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.PUSH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.PUSH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.JUMPUP_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.JUMPUP_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.JUMPDOWN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.JUMPDOWN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.ATTACK2_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2L.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.ATTACK2_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2R.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i ==Player. DEATH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            else if (i == Player.DEATH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
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
    public StrangeDoor getStrangeDoor() {
        return strangeDoor;
    }
    public void setStrangeDoor(StrangeDoor strangeDoor) {
        this.strangeDoor = strangeDoor;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    public ArrayList<Fire> getFires() {
        return fires;
    }
    public void setFires(ArrayList<Fire> fires) {
        this.fires = fires;
    }

    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public void setTraps(ArrayList<Trap> traps) {
        this.traps = traps;
    }
    
}

