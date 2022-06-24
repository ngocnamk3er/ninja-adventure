package entities;


import javafx.scene.image.Image;
import main.GameScene;
import mapinteraction.MapInteractionManager;

import static help.HelpMethods.*;

import java.util.ArrayList;

import data.Data;
import entities.enemy.Enemy;
import entities.enemy.Enemy1;
import entities.enemy.Enemy2;
import entities.enemy.Enemy3;
import entities.enemy.Enemy4;
import entities.trap.Trap;

public class Player extends Enity {
    private Image[][] animationImages;
    private float width = 64;
    private float height = 64;
    //right
    public static final int IDLE_R = 0;
    public static final int RUN_R = 1;
    public static final int ATTACK1_R = 2;
    public static final int ATTACK2_R = 3;
    public static final int PUSH_R = 4;
    public static final int JUMPDOWN_R = 5;
    public static final int JUMPUP_R = 6;
    public static final int DEATH_R = 7;
    //left
    public static final int IDLE_L = 8;
    public static final int RUN_L = 9;
    public static final int ATTACK1_L = 10;
    public static final int ATTACK2_L = 11;
    public static final int PUSH_L = 12;
    public static final int JUMPDOWN_L = 13;
    public static final int JUMPUP_L = 14;
    public static final int DEATH_L = 15;
    
    private float xSword;
    private float ySword;
    private int playerAction;
    private int aniTick = 0;
    private int aniIndex = 0;
    private int aniSpeed = 4;
    private float xSpeed;
    private float ySpeed = 0;
    private float playerSpeedX = 0;
    private boolean right = true;
    private boolean moving = false;
    private boolean death = false;
    private boolean nextLevel = false;
    private boolean run,up,down,push,attacking1,attacking2;

    //Mapdata
    private int [][] mapData;
    //jump and gravity
    private float gravity = 0.7f;
    private boolean jump = false;
    private boolean inAir = false;
    private boolean jumpInAir = false;
    //other objects
    private ArrayList<Coin> coins;
    private ArrayList<Stone> stones;
    private ArrayList<Enemy> enemies;
    private ArrayList<Fire> fires;
    private ArrayList<Trap> traps;
    private ArrayList<Platform> platforms;
    private MapInteractionManager mapInteractionManager;
    private GameScene gameScene;
    private Door door;
    private StrangeDoor strangeDoor;
    //handle Collision
    private int standOnStone = -1;
    private int pushStone = -1;
    private float brakingSpeedByStone = 0;
    private float speedCarriedByEnemy = 0;
    private float speedCarriedByPlatform= 0;
    private boolean standOnDoor = false;
    private int standOnPlatform= -1;
    private int standOnMushRoom = -1;
    private int brakingSpeedByDoor = 0;
    private int point;
    private float speedCarriedByStone;
    public void setProperties( float x, float y,Image[][] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.gc = mapInteractionManager.getGc();
        this.mapData = mapInteractionManager.getMapData();
        this.coins = mapInteractionManager.getCoins();
        this.stones = mapInteractionManager.getStones();
        this.door = mapInteractionManager.getDoor();
        this.gameScene = mapInteractionManager.getGameScene();
        this.strangeDoor = mapInteractionManager.getStrangeDoor();
        this.enemies = mapInteractionManager.getEnemies();
        this.animationImages = animationImages;
        this.mapInteractionManager = mapInteractionManager;
        this.fires = mapInteractionManager.getFires();
        this.traps = mapInteractionManager.getTraps();
        this.platforms = mapInteractionManager.getPlatforms();
        point = Data.getPoint();
        gameScene.setTranscript(point);
        gameScene.setHudHeart(Data.getHeart());
    }

    public void update() {
        updateAnimationTick();
        updatePos();
        handleCollision();
        setAnimation();
        updateLevel();
    }
    private void updateLevel() {
        if(death==true&&aniIndex==7){
            if(Data.getHeart()==1){
                gameOver();
            }else{
                Data.setHeart(Data.getHeart()-1);
                playAgain();
            }
        }else if(nextLevel==true&&aniIndex==7){
            playNextLevel();
        }
    }

    private void gameOver() {
        // System.out.println("BAN DA THUA");
    }

    @Override
    protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getAmountSpritesOfPlayerAction(playerAction)) {
				aniIndex = 0;
				attacking2 = false;
			}

		}
    }

    private void setInAir(float x, float y, int [][]mapData) {
        if((!isSolid(x+16, y+64, mapData))&&(!isSolid(x+48, y+64, mapData))&&standOnStone==-1&&standOnDoor==false&&standOnMushRoom==-1&&standOnPlatform==-1){
            inAir = true;
        }else{
            inAir = false;
        }
    }
    private void turnUp() {
        ySpeed = -15;
        inAir = true;
        up = true;
    }
    private void checkPlatforms() {
        for(int i = 0 ;i < platforms.size(); i++){
            Platform platform = platforms.get(i);
            if(!up&&this.y+ySpeed-platform.getY()>=-64 &&this.y+ySpeed/4-platform.getY()<=-48&&x+8<platform.getX()+platform.getWidth()&&x+56>platform.getX()){
                standOnPlatform = i;
                y = platform.getY() - 64;
                speedCarriedByPlatform = platform.getxSpeed();
            }else{
                if(standOnPlatform==i){
                    speedCarriedByPlatform = 0;
                    standOnPlatform = -1;
                }
            }
        }
    }
    private void checkFires() {
        for(Fire fire:fires){
            if(Math.abs(x-fire.getX())<132&&Math.abs(y-fire.getY())<132){
                fire.setOn(true);
            }
            if(Math.abs(x-fire.getX())<48&&Math.abs(y-fire.getY())<48){
                death = true;
            }
        }
    }
    private void checkStones(){
        for(int i=0;i<stones.size();i++){
            Stone stone = stones.get(i);
            if(Math.abs(x+xSpeed-stone.getX())<=132&&Math.abs(y+ySpeed-stone.getY())<=132){
                if(!up&&this.y+ySpeed-stone.getY()>=-64 &&this.y+ySpeed/4-stone.getY()<=-48&&Math.abs(this.x+xSpeed-stone.getX())<=48){
                    standOnStone = i;
                    speedCarriedByStone = stone.getxSpeed();
                    stone.setySpeed(0);
                    y = stone.getY() - 64;
                }else if(this.y+ySpeed-stone.getY()<=64&&this.y+ySpeed-stone.getY()>0&&Math.abs(this.x+xSpeed-stone.getX())<=48){
                    if(standOnDoor){
                        death = true;
                    }else{
                        if(up){
                            jump = false;
                            ySpeed = 0;
                            y = stone.getY() + 64;
                        }
                    }
                }else if(Math.abs(this.y+ySpeed-stone.getY())<=48&&Math.abs(this.x-stone.getX())<=58){
                    if(pushStone == -1||pushStone == i){
                        pushStone = i;
                        if(this.x>stone.getX()&&!right){
                            push = true;
                            x = stone.getX() + 58;
                            if(stone.isCanPush()){
                                stone.setXSpeedByPush(-2f);
                                brakingSpeedByStone = 6f;
                                // System.out.println(xSpeed);
                            }else{
                                brakingSpeedByStone = 8f;
                                stone.setXSpeedByPush(0);
                                // System.out.println(xSpeed);
                            }
                        }
                        if(this.x<stone.getX()&&right){
                            push = true;
                            x = stone.getX() - 58;
                            if(stone.isCanPush()){
                                stone.setXSpeedByPush(2f);
                                brakingSpeedByStone = -6f;
                                // System.out.println(xSpeed);
                            }else{
                                brakingSpeedByStone = -8f;
                                stone.setXSpeedByPush(0);
                                // System.out.println(xSpeed);
                            }
                        }
                    }
                }else{
                    if(pushStone==i){
                        pushStone = -1;
                        push = false;
                        stone.setXSpeedByPush(0);
                        brakingSpeedByStone = 0;
                    }
                    if(standOnStone == i){
                        standOnStone = -1;
                        speedCarriedByStone = 0;
                    }
                }
            }
        }
    }
    private void checkCoins(){
        double distance;
        for (int i=0;i<coins.size();i++) {
            Coin coin= coins.get(i);
            if(coin.isPickedUp()==false){
                distance = Math.sqrt(Math.pow(x - coin.getX(), 2.0) + Math.pow(y-coin.getY(), 2.0));
                if(distance<=48){
                    coin.setPickedUp(true);
                    point++;
                    gameScene.setTranscript(point);
                    // Data.setHeart(Data.getHeart()+1);
                    // gameScene.setHudHeart(Data.getHeart());
                }
            }
        }  	
    }
    private void checkDoor() {
        if(!up&&this.y+ySpeed-door.getyHitBox()>=-64 &&this.y+ySpeed/4-door.getyHitBox()<=-48&&Math.abs(this.x+xSpeed-door.getX())<48){
            standOnDoor = true;
            y = door.getyHitBox() - 64;
        }else if(y+64>door.getyHitBox()&&y<door.getY()+door.getHeight()&&Math.abs(this.x+xSpeed-door.getX())<=48){
            if(right&&x<door.getX()){
                x = door.getX() - 48;
                brakingSpeedByDoor = -8;
            }else if(!right&&x>door.getX()){
                x = door.getX() + 48;
                brakingSpeedByDoor = 8;
            }
        }else{
            standOnDoor = false;
            brakingSpeedByDoor = 0;
        }
    }
    private void checkStrangeDoor() {
        if(Math.abs((x+32)-(strangeDoor.getX()+64))<48&&Math.abs((y+32)-(strangeDoor.getY()+64))<48){
            nextLevel = true;
        }
    }
    private void checkTraps() {
        for(Trap trap:traps){
            if(trap.getHeightDangerHitbox()>0&&trap.getWidthDangerHitbox()>0){
                float distanceOx;
                float distanceOy;
                distanceOx = Math.abs((x+width/2)-(trap.getxDangerHitbox()+trap.getWidthDangerHitbox()/2));
                distanceOy = Math.abs((y+height/2)-(trap.getyDangerHitbox()+trap.getHeightDangerHitbox()/2));
                if(distanceOx<(width+trap.getWidthDangerHitbox())/2&&distanceOy<(height+trap.getHeightDangerHitbox())/2){
                    death = true;
                }
            }
        }
    }
    private void checkEnemies() {
        for(int i=0;i<enemies.size();i++){
            Enemy enemy = enemies.get(i);
            if(enemy.isDeath()==false){
                if(enemy instanceof Enemy1 || enemy instanceof Enemy2){
                    if(attacking2==true){
                        setSwordPos();
                        if(Math.abs(xSword-enemy.getX())<32&&Math.abs((ySword+height/2)-(enemy.getY()+enemy.getHeight()/2))<32){
                            if(enemy.isHit()==false){
                                enemy.setHit(true);
                            }
                        }
                    }else {
                        if(Math.abs(x+xSpeed-enemy.getX())<=48&&y<enemy.getY()+enemy.getHeight()&&y+height>enemy.getY()){
                            if(ySpeed>0&&y<enemy.getY()){
                                enemy.setDeath(true);
                                y = enemy.getY() - height;
                                ySpeed = -ySpeed/2;        
                            }else{
                                death = true;
                            }
                        }
                    }
                }else if(enemy instanceof Enemy3){
                    if(attacking2==true){
                        setSwordPos();
                        if(Math.abs(xSword-enemy.getX())<32&&Math.abs((ySword+height/2)-(enemy.getY()+enemy.getHeight()/2))<32){
                            if(enemy.isHit()==false){
                                enemy.setHit(true);
                            }
                        }
                    }else {
                        if(Math.abs(x+xSpeed-enemy.getX())<=48&&y+ySpeed<enemy.getY()+enemy.getHeight()&&y+ySpeed+height>=enemy.getY()){
                            if(ySpeed>=0&&y<enemy.getY()-height/2){
                                y = enemy.getY() - height;
                                if(ySpeed<5){
                                    standOnMushRoom = i;
                                    speedCarriedByEnemy = enemy.getxSpeed();
                                }else{
                                    ySpeed = -20;  
                                }      
                            }else{
                                if(standOnMushRoom!=i){
                                    death = true;
                                }
                            }
                        }else{
                            if(standOnMushRoom == i){
                                standOnMushRoom = -1;
                                speedCarriedByEnemy = 0;
                            }
                        }
                    }
                }else if(enemy instanceof Enemy4){
                    if(Math.abs(x+xSpeed-enemy.getX())<=48&&Math.abs(y-enemy.getY())<=32){
                        setInAir(x, y, mapData);
                        if(inAir==false&&standOnMushRoom==-1){
                            y = y - 0.1f; 
                            ySpeed = -20;
                        }else{
                            if(standOnMushRoom == -1){
                                if(ySpeed>=0&&ySpeed<=0.5){
                                    standOnMushRoom = i;
                                    y = enemy.getY() - 32;
                                }else{
                                    ySpeed = -20;
                                }
                            }
                        }
                    }else{
                        if(standOnMushRoom==i){
                            standOnMushRoom = -1;
                        }
                    }
                }
            }
        }
    }
    private void setSwordPos() {
        if(right){
            xSword = x + 64;
        }else{
            xSword = x - 64;
        }
        ySword = y;
    }
    @Override
    protected void handleCollision(){
        // System.out.println(xSpeed);
        checkEnemies();
        checkCoins();
        checkStones();
        checkDoor();
        checkStrangeDoor();
        checkFires();
        checkTraps();
        checkPlatforms();
    }
    
    @Override
    protected void updatePos() {
        moving = false;
        xSpeed = 0;
        setInAir(this.x, this.y, mapData);
        if(death||nextLevel){
            attacking2 = false;
            return;
        }
        if(!inAir){
            ySpeed = 0;
            down = false;
            up = false;
            if(jump) {
                turnUp();
                jumpInAir = false;
            }
        }
        if (inAir) {
            ySpeed = ySpeed + gravity;
            if (ySpeed < 0) {
                up = true;
                down = false;
            } else {
                up = false;
                down = true;
            }
            if(down){
                if(jumpInAir == false){
                    if(jump){
                        turnUp();
                        jumpInAir=true;
                    }
                }
            }
        } 
        if (run) {
            if (right) {
                playerSpeedX = 8;
            } else {
                playerSpeedX = -8;
            }
        }else{
            playerSpeedX = 0;
        }
        xSpeed = playerSpeedX+brakingSpeedByStone+brakingSpeedByDoor+speedCarriedByEnemy+speedCarriedByPlatform+speedCarriedByStone;
        if (canMove((x+xSpeed+16),(y),32,63, mapData) == true) {
            x += xSpeed;
        }
        if (canMove((x+16),(y+ySpeed),32,63, mapData) == true) {
		    y += ySpeed;
        }else{
            if(standOnDoor){
                death = true;
            }else{
                if(up){
                    ySpeed = 0;
                }else{
                    // System.out.println(y+"-------------");
                    int rowBrick = (int)(y+ySpeed)/64;
                    y = rowBrick*64;
                }
            }
        }
        if(!inAir && !run && !attacking2){
            return;
        }
        moving = true;
        // System.out.println(y);
	}
    @Override
    protected void setAnimation() {
        int startAni = playerAction;
        if (right) {
            if (moving==false) {
                playerAction = IDLE_R;
            } else {
                if(run){
                    playerAction = RUN_R;
                }
                if (up&&!down) {
                    playerAction = JUMPUP_R;
                }else if(!up&&down){
                    playerAction = JUMPDOWN_R;
                }   
                if(push){
                    playerAction = PUSH_R;
                }
                if(attacking2){
                    playerAction = ATTACK2_R;
                }
            }
            if(death||nextLevel){
                playerAction = DEATH_R;
            }
        } else {
            if (moving == false) {
                playerAction = IDLE_L;
            } else {
                if (run) {
                    playerAction = RUN_L;
                }
                if (up&&!down) {
                    playerAction = JUMPUP_L;
                } else if (!up&&down) {
                    playerAction = JUMPDOWN_L;
                } 
                if(push){
                    playerAction = PUSH_L;
                }
                if (attacking2) {
                    playerAction = ATTACK2_L;
                }
            }
            if(death||nextLevel){
                playerAction = DEATH_L;
            }
        }
        if (startAni != playerAction) {
            aniIndex=0;
            aniTick=0;
        }
    }
    
    public void render() {
        if (attacking2 == true) {
            if(right){
                gc.drawImage(animationImages[playerAction][aniIndex], x, y, width*2, height);
            }else{
                gc.drawImage(animationImages[playerAction][aniIndex], x-width, y, width*2, height);
            }
        }else{
            gc.drawImage(animationImages[playerAction][aniIndex], x, y, width, height);
        }
    }

    private void playAgain() {
        mapInteractionManager.setInitialState(mapInteractionManager.getLevelValue());
    }
    private void playNextLevel() {
        if(mapInteractionManager.getLevelValue()==Data.getLevel()){
            Data.setLevel(Data.getLevel()+1);
        }
        Data.setPoint(point);
        gameScene.MakeGameNextLevel(mapInteractionManager.getLevelValue()+1);
    }
    public static int getAmountSpritesOfPlayerAction(int x) {
        if (x == IDLE_L || x == IDLE_R) {
            return 4;
        }
        else if (x == RUN_L || x == RUN_R) {
            return 6;
        }
        else if (x == ATTACK1_L || x == ATTACK1_R) {
            return 4;
        }
        else if (x == ATTACK2_L || x == ATTACK2_R) {
            return 4;
        }
        else if (x == PUSH_L || x == PUSH_R) {
            return 6;
        }
        else if (x == JUMPUP_L || x == JUMPUP_R) {
            return 3;
        }
        else if (x == JUMPDOWN_L || x == JUMPDOWN_R) {
            return 3;
        }else if (x == DEATH_L || x == DEATH_R) {
            return 8;
        }else{
            return 0;
        }
    }
    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isAttacking1() {
        return attacking1;
    }

    public void setAttacking1(boolean attacking1) {
        this.attacking1 = attacking1;
    }

    public boolean isAttacking2() {
        return attacking2;
    }

    public void setAttacking2(boolean attacking2) {
        this.attacking2 = attacking2;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }
    
}
