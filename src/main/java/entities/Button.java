package entities;

import java.util.ArrayList;


import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Button extends Enity {
    private boolean pressed;
    private Image[] animationImages;
    private Player player;
    private ArrayList<Stone> stones;
    public Button(float x, float y,Image[] animationImages, MapInteractionManager mapInteractionManager) {
        super(x, y , 64, 64 , mapInteractionManager.getGc());
        this.stones = mapInteractionManager.getStones();
        this.player = mapInteractionManager.getPlayer();
        this.animationImages = animationImages;
    }
    private boolean checkStones(){
        for(Stone stone:stones){
            if(Math.abs(stone.getY()-y)<=16&&Math.abs(stone.getX()-x)<=48){
                return true;
            }
        }
        return false;
    }
    private boolean checkPlayer(){
        if(Math.abs(player.getY()-y)<=16&&Math.abs(player.getX()-x)<=48){
            return true;
        }
        return false;
    }
    @Override
    protected void handleCollision(){
        if(checkPlayer()){
            pressed = true;
            return;
        }
        if(checkStones()){
            pressed =true;
            return;
        }
        pressed = false;

    }
    @Override
    public void update(){
        handleCollision();
    }
    @Override
    public void render() {
        if(!pressed){
            gc.drawImage(animationImages[0], x, y + 56, width, height/4);
        }else{
            gc.drawImage(animationImages[1], x, y + 56, width, height/4);
        }
        
    }
    public boolean isPressed() {
        return pressed;
    }
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    @Override
    protected void updateAnimationTick() {
                
    }
    @Override
    protected void updatePos() {
                
    }
    @Override
    protected void setAnimation() {
                
    }
    
}
