package entities;

import java.util.ArrayList;
import static help.HelpMethods.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Enimy extends Enity {
    private Image[][] animationImages;
    private GraphicsContext gc;
    private float width = 64;
    private float height = 64;
    private float deltaY = 32;
    //right
    public static final int IDLE_R = 0;
    public static final int RUN_R = 1;
    public static final int DEATH_R = 2;
    public static final int HIT_R = 3;
    //left
    public static final int IDLE_L = 4;
    public static final int RUN_L = 5;;
    public static final int DEATH_L = 6;
    public static final int HIT_L = 7;
    //
    private int aniTick = 0;
    private int aniIndex = 0;
    private int aniSpeed = 6;
    private int enimyAction = 0;
    private int healthPoints = 3;
    private boolean run = true,death,hit,right,disappear=false;
    //other objects
    private ArrayList<Stone> stones;
    private Door door;
    private int [][] mapData;
    private float SpeedX;
    private float xSpeed;
    private float brakingSpeed;

    public Enimy( float x, float y,Image[][] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        this.gc = mapInteractionManager.getGc();
        this.mapData = mapInteractionManager.getMapData();
        this.stones = mapInteractionManager.getStones();
        this.door = mapInteractionManager.getDoor();
        this.animationImages = animationImages;
    }

    public void update() {
        if(!disappear){
            handleCollision();
            updateAnimationTick();
            updatePos();
            setAnimation();
        }
    }
    private void setAnimation() {
        int startAni = enimyAction;
        if(right){
            if(run){
                enimyAction = RUN_R;
            }
            if(hit){
                enimyAction = HIT_R;
            }
            if(death){
                enimyAction = DEATH_R;
            }
        }else{
            if(run){
                enimyAction = RUN_L;
            }
            if(hit){
                enimyAction = HIT_L;
            }
            if(death){
                enimyAction = DEATH_L;
            }
        }
        if(startAni!=enimyAction){
            aniIndex = 0;
        }
    }
    private void updatePos() {
        xSpeed = 0;
        if(death||hit){
            return;
        }
        if (run) {
            SpeedX = 2;
            if (right) {
                xSpeed = SpeedX;
            } else {
                xSpeed =- SpeedX;
            }
        }
        if (canMove(x+xSpeed,y,width,height-1, mapData) == true) {
            if(isSolid(x+xSpeed, y+65, mapData)==false||isSolid(x+xSpeed+64, y+65, mapData)==false){
                right=!right;
            }else{
                x += xSpeed;
            }
        }else{
            right = !right;
        }
    }
    private void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getAmountSpritesOfEnimyAction(enimyAction)) {
				aniIndex = 0;
                hit = false;
                if(death){
                    disappear = true;
                }
			}
		}
    }
    private void handleCollision() {
    }
    @Override
    public void render() {
        if(!disappear){
            if(run){
                gc.drawImage(animationImages[enimyAction][aniIndex], x, y-deltaY, width, height+deltaY);
            }else{
                gc.drawImage(animationImages[enimyAction][aniIndex], x, y, width, height);
            }
        }
    }
    public static int getAmountSpritesOfEnimyAction(int x) {
        if (x == IDLE_L || x == IDLE_R) {
            return 5;
        } else if (x == RUN_L || x == RUN_R) {
            return 15;
        }else if (x == DEATH_L || x == DEATH_R) {
            return 6;
        }else if(x == HIT_L || x == HIT_R){
            return 3;
        }else{
            return 0;
        }
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
        healthPoints--;
        if(healthPoints==0){
            death = true;
        }
    }
        
}
