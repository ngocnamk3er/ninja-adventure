package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;
import static help.HelpMethods.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Stone extends Enity {

    private Image animationImage;
    private int[][] mapData;
    private float gravity = 0.7f;
    private float ySpeed = 0;
    private float xSpeed = 0;
    private boolean inAir;
    private boolean canPush = true;
    private ArrayList<Stone> stones;
    private ArrayList<Enemy> enemies;
    private Player player;
    private Door door;
    private boolean nextDoor = false;
    private HashSet<Integer> nextStones = new HashSet<>();
    private HashSet<Integer> underStones = new HashSet<>();
    public Stone(float x, float y,Image animationImage,MapInteractionManager mapInteractionManager) {
        super(x, y);
        this.mapData = mapInteractionManager.getMapData();
        this.gc=mapInteractionManager.getGc();
        this.stones = mapInteractionManager.getStones();
        this.player = mapInteractionManager.getPlayer();
        this.enemies = mapInteractionManager.getEnemies();
        this.animationImage = animationImage;
        this.door = mapInteractionManager.getDoor();
        width = 64;
        height = 64;
    }
    public void checkPlayer(){
        if(ySpeed>0){
            if(Math.abs(x-player.getX())<=48&&Math.abs(y-player.getY())<64&&y<player.getY()){
                ySpeed = 0;
                player.setDeath(true);
            } 
        }
    }
    public void checkStones(){
        for(int i=0;i<stones.size();i++){
            Stone stone = stones.get(i);
            if(stone.equals(this)==false){
                if(Math.abs(x-stone.getX())<=96&&Math.abs(y-stone.getY())<=96){
                    if(Math.abs(x-stone.getX())<64&&Math.abs(y-stone.getY())<=64&&y-stone.getY()<0){
                        underStones.add(i);
                    }else{
                        underStones.remove(i);
                    }
                    if(Math.abs(x-stone.getX())<=64&&Math.abs(y-stone.getY())<64){
                        nextStones.add(i);
                    }else{
                        nextStones.remove(i);
                    }
                }
            }
        }
    }
    private void checkEnimies() {
        for(int i=0;i<enemies.size();i++){
            Enemy enemy = enemies.get(i);
            if(ySpeed>0&&Math.abs(x-enemy.getX())<=64&y<enemy.getY()&&Math.abs(y-enemy.getY())<height){
                enemy.setDeath(true);
            }
        }
    }
    private void checkDoor() {
        // System.out.println(door.getX());
        if(Math.abs(x-door.getX())<=64&&y+height>door.getyHitBox()&&y<door.getY()+door.getHeight()){
            nextDoor = true;
        }else{
            nextDoor = false;
        }
    }
    private void handleCollision(){
        checkEnimies();
        checkPlayer();
        checkDoor();
        checkStones();
    }
    private void updatePos(){
        setInAir();
        if(inAir){
            ySpeed = ySpeed + gravity;
        }else{
            ySpeed = 0;
        }
        if (canMove(x + xSpeed,y,63,63, mapData) == true && nextStones.isEmpty() && nextDoor == false) {
		    x += xSpeed;
            canPush = true;
        }else{
            canPush = false;
        }
        if (canMove(x,y+ySpeed,63,63, mapData) == true && underStones.isEmpty()) {
		    y += ySpeed;
        }else{
            int rowBrick = (int)(y+ySpeed)/64;
            y = rowBrick*64;
        }
        // System.out.println(y);
        
    }
    private void setInAir() {
        if((!isSolid(x, y + 64, mapData))&&(!isSolid(x + 63, y + 64, mapData))&&underStones.isEmpty()){
            inAir = true;
        }else{
            inAir = false;
        }
    }
    public void update(){
        handleCollision();
        updatePos();
    }
    @Override
    public void render() {
        gc.drawImage(animationImage,x, y, width, height);
    }
    public float getxSpeed() {
        return xSpeed;
    }
    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }
    public boolean isCanPush() {
        return canPush;
    }
    public void setCanPush(boolean canPush) {
        this.canPush = canPush;
    }
    
}
