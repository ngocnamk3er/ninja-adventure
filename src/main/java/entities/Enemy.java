package entities;

import java.util.ArrayList;

import javafx.scene.image.Image;
import static help.HelpMethods.*;
public abstract class Enemy extends Enity {
    protected Image[][] animationImages;
    protected boolean run = true;//death,hit,right,disappear=false;
    protected boolean death = false;
    protected boolean hit = false;
    protected boolean right;
    protected boolean disappear = false;
    protected int aniTick = 0;
    protected int aniIndex = 0;
    protected int aniSpeed = 6;
    protected int enemyAction = 0;
    protected int healthPoints = 2;
    protected ArrayList<Stone> stones;
    protected Door door;
    protected int [][] mapData;
    protected float SpeedX;
    protected float xSpeed;
    
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
    private void checkDoor(){
        if(Math.abs(x+xSpeed-door.getX())<width&&y+height>door.getyHitBox()&&y<door.getyHitBox()+door.getHeight()-(door.getY()-door.getyHitBox())){
            right=!right;
        }
    }
    private void checkStones(){
        for(int i=0;i<stones.size();i++){
            Stone stone = stones.get(i);
            if(Math.abs(x+xSpeed-stone.getX())<width&&y+height>stone.getY()&&y<stone.getY()+stone.getHeight()){
                right=!right;
            }
        }
    }
    protected void handleCollision() {
        if(!death){
            checkDoor();
            checkStones();
        }
    }   
    protected void updatePos() {
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
            if(isSolid(x+xSpeed, y+height+1, mapData)==false||isSolid(x+xSpeed+width, y+height+1, mapData)==false){
                right=!right;
            }else{
                x += xSpeed;
            }
        }else{
            right = !right;
        }
        
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }
}
