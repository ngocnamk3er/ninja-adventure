package entities;

import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Button extends Enity {
    private boolean pressed;
    private Image[] animationImages;
    private GraphicsContext gc;
    private Player player;
    private ArrayList<Stone> stones;
    public Button(float x, float y, float width, float height, MapInteractionManager mapInteractionManager) {
        super(x, y, width, height);
        loadAnimations();
        this.gc=mapInteractionManager.getGc();
        this.stones = mapInteractionManager.getStones();
        this.player = mapInteractionManager.getPlayer();
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
    private void handleCollision(){
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
    public void update(){
        handleCollision();
    }
    @Override
    public void render() {
        if(!pressed){
            gc.drawImage(animationImages[0], x, y + 58, 64, 16);
        }else{
            gc.drawImage(animationImages[1], x, y + 58, 64, 16);
        }
        
    }

    @Override
    protected void loadAnimations() {
        animationImages =  new Image[2];
        for(int i = 0 ; i < 2 ; i++) {
            try {
                animationImages[i]=new Image(Button.class.getResourceAsStream("button"+i+".png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public boolean isPressed() {
        return pressed;
    }
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    
}
