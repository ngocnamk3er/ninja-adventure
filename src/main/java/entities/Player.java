package entities;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

import static help.HelpMethods.*;
// import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player extends Enity {
    private Image[][] animationImages;
    private GraphicsContext gc;
    //right
    private final int IDLE_R = 0;
    private final int RUN_R = 1;
    private final int ATTACK1_R = 2;
    private final int ATTACK2_R = 3;
    private final int PUSH_R = 4;
    private final int JUMPDOWN_R = 5;
    private final int JUMPUP_R = 6;
    //left
    private final int IDLE_L = 7;
    private final int RUN_L = 8;
    private final int ATTACK1_L = 9;
    private final int ATTACK2_L = 10;
    private final int PUSH_L = 11;
    private final int JUMPDOWN_L = 12;
    private final int JUMPUP_L = 13;
    
    private int playerAction = IDLE_L;
    private int aniTick = 0;
    private int aniIndex = 0;
    private int aniSpeed = 4;
    private float xSpeed;
    private float ySpeed = 0;
    private float playerSpeedX = 0;
    private boolean right = true;
    private boolean moving = false;
    private boolean run,up,down,push,attacking1,attacking2;

    //Mapdata
    private int [][] mapData;
    //jump and gravity
    private float gravity = 0.7f;
    private boolean jump = false;
    private boolean inAir = false;
    private boolean jumpInAir = false;
    //other objects
    private ArrayList<Enity> removedEnities;
    private ArrayList<Coin> coins;
    private ArrayList<Stone> stones;
    //handle Collision
    private int standOnStone = 1000;
    private int pushStone = 1000;
    private float brakingSpeed = 0;
    
    public final int getAmountSpritesOfPlayerAction(int x) {
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
        }else{
            return 0;
        }
    }

    public Player( float x, float y, float width, float height,MapInteractionManager mapInteractionManager) {
        super(x, y, width, height);
        loadAnimations();
        this.gc = mapInteractionManager.getGc();
        this.mapData = mapInteractionManager.getMapData();
        this.coins = mapInteractionManager.getCoins();
        this.removedEnities = mapInteractionManager.getRemovedEnities();
        this.stones = mapInteractionManager.getStones();
    }

    public void update() {
        handleCollision();
        updateAnimationTick();
        updatePos();
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
        if((!isSolid(x+16, y+66, mapData))&&(!isSolid(x+48, y+66, mapData))){
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
                if(Math.abs(this.y+ySpeed-stone.getY())<60&&Math.abs(this.x+xSpeed-stone.getX())<=59){
                    pushStone = i;
                    push = true;
                    if(this.x>stone.getX()&&!right){
                        if(stone.isCanPush()){
                            stone.setxSpeed(-2f);
                            brakingSpeed = 6f;
                        }else{
                            brakingSpeed = 8f;
                            stone.setxSpeed(0);
                        }
                    }
                    if(this.x<stone.getX()&&right){
                        if(stone.isCanPush()){
                            stone.setxSpeed(2f);
                            brakingSpeed = -6f;
                        }else{
                            brakingSpeed = -8f;
                            stone.setxSpeed(0);
                        }
                    }
                }else{
                    if(i==pushStone){
                        push = false;
                        stone.setxSpeed(0);
                        brakingSpeed = 0;
                    }
                }
            }
        }
        // return false;
    }
    private void checkCoins(){
        double distance;
        for (int i=0;i<coins.size();i++) {
            Coin coin= coins.get(i);
            distance = Math.sqrt(Math.pow(x-coin.getX(), 2.0) + Math.pow(y-coin.getY(), 2.0));
            if(distance<=48){
                removedEnities.add(new CoinPickUp(coin.getX(), coin.getY(), 32, 64, gc));
                coins.remove(coin);
            }
        }  	
    }
    private void handleCollision(){
        checkCoins();
        checkStones();
    }
    private void updatePos() {
        moving = false;
        xSpeed = 0;
        setInAir(this.x, this.y, mapData);
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
        if (canMove((x+xSpeed+16),(y+1),32,62, mapData) == true) {
            x += xSpeed;
        }
        if (canMove((x+16),(y+ySpeed+1),32,62, mapData) == true) {
		    y += ySpeed;
        }else{
            if(up){
                ySpeed = 0;
            }else{
                int rowBrick = (int)(y+ySpeed)/64;//this is bug 
                y = rowBrick*64;
            }
        }
        if(!inAir && !run && !attacking2){
            return;
        }
        moving = true;
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
        }
        if (startAni != playerAction) {
            aniIndex=0;
            aniTick=0;
        }
    }
    
    public void render() {
        if (attacking2 == true) {
            if(right){
                gc.drawImage(animationImages[playerAction][aniIndex], x, y, 128, 64);
            }else{
                gc.drawImage(animationImages[playerAction][aniIndex], x-64, y, 128, 64);
            }
        }else{
            gc.drawImage(animationImages[playerAction][aniIndex], x, y, 64, 64);
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

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    // public boolean isPush() {
    //     return push;
    // }

    // public void setPush(boolean push) {
    //     this.push = push;
    // }

    public void loadAnimations() {//load 1 bộ ảnh để tạo mảng 2 chiều để tạo animation
        animationImages = new Image[14][7];
        for(int i = 0 ; i < 14 ; i++){
            if (i == RUN_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("RUN_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == RUN_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("RUN_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == IDLE_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("IDLE_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == IDLE_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("IDLE_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK1_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("ATTACK1_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK1_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("ATTACK1_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == PUSH_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("PUSH_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == PUSH_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("PUSH_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPUP_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("JUMPUP_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPUP_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("JUMPUP_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPDOWN_L) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("JUMPDOWN_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == JUMPDOWN_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("JUMPDOWN_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK2_R) {
                try {
                    for(int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("ATTACK2_R"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == ATTACK2_L) {
                try {
                    for (int j=0;j<getAmountSpritesOfPlayerAction(i);j++) {
                        animationImages[i][j]=new Image(Player.class.getResourceAsStream("ATTACK2_L"+j+".png"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
        }
    } 
    
}
