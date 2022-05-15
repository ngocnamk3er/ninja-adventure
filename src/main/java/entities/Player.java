package entities;


import javafx.scene.image.Image;
import main.MakeMainScene;
import mapinteraction.MapInteractionManager;

import static help.HelpMethods.*;

import java.util.ArrayList;

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
    private MapInteractionManager mapInteractionManager;
    private MakeMainScene makeMainScene;
    private Door door;
    private StrangeDoor strangeDoor;
    //handle Collision
    private int standOnStone = 1000;
    private int pushStone = 1000;
    private float brakingSpeed = 0;
    private boolean standOnDoor = false;
    private int standOnMushRoom = 1000;

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
        this.makeMainScene = mapInteractionManager.getMakeMainScene();
        this.strangeDoor = mapInteractionManager.getStrangeDoor();
        this.enemies = mapInteractionManager.getEnemies();
        this.animationImages = animationImages;
        this.mapInteractionManager = mapInteractionManager;
    }

    public void update() {
        updateAnimationTick();
        updatePos();
        handleCollision();
        setAnimation();
    }
    private void updateAnimationTick() {
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
        if((!isSolid(x+16, y+66, mapData))&&(!isSolid(x+48, y+66, mapData))&&standOnStone==1000&&standOnDoor==false&&standOnMushRoom==1000){
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
    private void checkStones(){
        for(int i=0;i<stones.size();i++){
            Stone stone = stones.get(i);
            if(Math.abs(x+xSpeed-stone.getX())<=132&&Math.abs(y+ySpeed-stone.getY())<=132){
                if(!up&&this.y+ySpeed-stone.getY()>=-64 &&this.y+ySpeed/4-stone.getY()<=-48&&Math.abs(this.x+xSpeed-stone.getX())<=48){
                    standOnStone = i;
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
                }else if(Math.abs(this.y+ySpeed-stone.getY())<=48&&Math.abs(this.x+xSpeed-stone.getX())<=58){
                    if(pushStone == 1000||pushStone == i){
                        pushStone = i;
                        if(this.x>stone.getX()&&!right){
                            push = true;
                            x = stone.getX() + 58;
                            if(stone.isCanPush()){
                                stone.setxSpeed(-2f);
                                brakingSpeed = 6f;
                            }else{
                                brakingSpeed = 8f;
                                stone.setxSpeed(0);
                            }
                        }
                        if(this.x<stone.getX()&&right){
                            push = true;
                            x = stone.getX() - 58;
                            if(stone.isCanPush()){
                                stone.setxSpeed(2f);
                                brakingSpeed = -6f;
                            }else{
                                brakingSpeed = -8f;
                                stone.setxSpeed(0);
                            }
                        }
                    }
                }else{
                    if(pushStone==i){
                        pushStone = 1000;
                        push = false;
                        stone.setxSpeed(0);
                        brakingSpeed = 0;
                    }
                    if(standOnStone == i){
                        standOnStone = 1000;
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
                }
            }
        }  	
    }
    private void checkDoor() {
        if(!up&&this.y+ySpeed-door.getyHitBox()>=-64 &&this.y+ySpeed/4-door.getyHitBox()<=-48&&Math.abs(this.x+xSpeed-door.getX())<48){
            standOnDoor = true;
            y = door.getyHitBox() - 64;
        }else if(y+64>door.getyHitBox()&&y<door.getY()+192&&Math.abs(this.x+xSpeed-door.getX())<=48){
            if(right&&x<door.getX()){
                x = door.getX() - 48;
                brakingSpeed = -8;
            }else if(!right&&x>door.getX()){
                x = door.getX() + 48;
                brakingSpeed = 8;
            }
        }else{
            standOnDoor = false;
            brakingSpeed = 0;
        }
    }
    private void checkStrangeDoor() {
        if(Math.abs((x+32)-(strangeDoor.getX()+64))<48&&Math.abs((y+32)-(strangeDoor.getY()+64))<48){
            nextLevel = true;
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
                        if(Math.abs(x+xSpeed-enemy.getX())<=48&&Math.abs(y+ySpeed-enemy.getY())<height){
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
                        if(Math.abs(x+xSpeed-enemy.getX())<=48&&Math.abs(y-enemy.getY())<=height){
                            if(ySpeed>=0&&y<enemy.getY()-height/2){
                                System.out.println(y);
                                System.out.println(enemy.getY());
                                y = enemy.getY() - height;
                                if(ySpeed<5){
                                    standOnMushRoom = i;
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
                                standOnMushRoom = 1000;
                            }
                        }
                    }
                }else if(enemy instanceof Enemy4){
                    if(Math.abs(x+xSpeed-enemy.getX())<=48&&Math.abs(y-enemy.getY())<=32){
                        y=enemy.getY()-32;
                        if(ySpeed>0&&ySpeed<4){
                            standOnMushRoom = i;
                        }else{
                            if(standOnMushRoom==1000){
                                y = y - 1;
                                ySpeed = -20;
                            }
                        }
                    }else{
                        if(standOnMushRoom == i){
                            standOnMushRoom = 1000;
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

    private void handleCollision(){
        checkEnemies();
        checkCoins();
        checkStones();
        checkDoor();
        checkStrangeDoor();
    }
    private void updatePos() {
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
            playerSpeedX = 8;
            if (right) {
                xSpeed = playerSpeedX+brakingSpeed;
            } else {
                xSpeed =- playerSpeedX+brakingSpeed;
            }
        }
        if (canMove((x+xSpeed+16),(y+2),32,61, mapData) == true) {
            x += xSpeed;
        }
        if (canMove((x+16),(y+ySpeed+2),32,61, mapData) == true) {
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
    
    private void setAnimation() {
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
            if(death==true&&aniIndex==7){
                playAgain();
            }
            if(nextLevel==true&&aniIndex==7){
                playNextLevel();
            }
        }
    }

    private void playAgain() {
        mapInteractionManager.setInitialState(mapInteractionManager.getLevelValue());
    }
    private void playNextLevel() {
        makeMainScene.MakeGameNextLevel(mapInteractionManager.getLevelValue()+1);
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
