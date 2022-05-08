package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;
import static help.HelpMethods.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Stone extends Enity {

    private Image animationImage;
    private GraphicsContext gc;
    private int[][] mapData;
    private float gravity = 0.7f;
    private float ySpeed = 0;
    private float xSpeed = 0;
    private boolean inAir;
    private boolean canPush = true;
    private ArrayList<Stone> stones;
    private Player player;
    private HashSet<Integer> nextStones = new HashSet<>();
    private HashSet<Integer> underStones = new HashSet<>();
    public Stone(float x, float y, float width, float height,Image animationImage,MapInteractionManager mapInteractionManager) {
        super(x, y, width, height);
        this.mapData = mapInteractionManager.getMapData();
        this.gc=mapInteractionManager.getGc();
        this.stones = mapInteractionManager.getStones();
        this.player = mapInteractionManager.getPlayer();
        this.animationImage = animationImage;
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
    private void handleCollision(){
        checkPlayer();
        checkStones();
    }
    private void updatePos(){
        setInAir();
        if(inAir){
            ySpeed = ySpeed + gravity;
        }else{
            ySpeed = 0;
        }
        if (canMove(x + xSpeed,y,63,63, mapData) == true && nextStones.isEmpty()) {
		    x += xSpeed;
            canPush = true;
        }else{
            canPush = false;
        }
        if (canMove(x,y+ySpeed,63,63, mapData) == true&&underStones.isEmpty()) {
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
