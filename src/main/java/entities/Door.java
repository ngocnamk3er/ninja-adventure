package entities;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;
import static help.HelpMethods.*;
public class Door extends Enity {
    private Image[][] animationImages;
    private int aniDoorIndex;
    private int aniTick;
    private int aniSpeed = 15;
    private float yHitBox;
    private final int CLOSED = 0;
    private final int MOVING = 1;
    private final int UP = 1;
    private final int DOWN = -1;
    private final int DONTMOVE = 0;
    private final int prepareUp = 2;
    private int doorAction = CLOSED;
    private int direction = DONTMOVE;
    private GraphicsContext gc;
    private ArrayList<Button> buttons;
    public Door() {
    }
    public void setProperties(float x, float y, float width, float height,Image[][] animationImages, MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width=width;
        this.gc =  mapInteractionManager.getGc();
        this.buttons = mapInteractionManager.getButtons();
        this.animationImages = animationImages;
    }
    public void update(){
        handleCollision();
        setAnimation();
        updateDoorAniTick();
    }
    private void handleCollision() {
        checkButtons();
    }
    private void checkButtons() {
        for(int i=0;i<buttons.size();i++){
            Button button = buttons.get(i);
            if(button.isPressed()==false){
                if(direction == prepareUp){
                    direction = UP;
                    return;
                }else if(direction == DOWN||direction == DONTMOVE){
                    return;
                }else{
                    return;
                }
            }
        }
        if(direction != prepareUp){//direction == UP || direction ==  DONTMOVE
            direction = DOWN;   
        }
    }
    private void setAnimation() {
        int startAni = doorAction;
        if(direction!=0){
            doorAction = MOVING;
        }else{
            doorAction = CLOSED;
        }
        if(startAni != doorAction) {
            aniDoorIndex = 0;
        }
    }
    private void updateDoorAniTick() {
        if(direction == DOWN){
            if(aniDoorIndex < getAmountSpritesOfDoor(doorAction)-1){
                aniTick++;
                if (aniTick >= aniSpeed) {
                    aniTick = 0;
                    aniDoorIndex++;
                    if(aniDoorIndex == getAmountSpritesOfDoor(doorAction)-1){
                        direction = prepareUp;
                    }
                }
            }
        }else if(direction == UP){
            if(aniDoorIndex > 0){
                aniTick++;
                if (aniTick >= aniSpeed) {
                    aniTick = 0;
                    aniDoorIndex--;
                    if(aniDoorIndex==0){
                        doorAction = CLOSED;
                        direction = DONTMOVE;
                        aniDoorIndex = 0;
                    }
                }
            }
        }else if(direction == DONTMOVE){
            aniTick++;
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniDoorIndex++;
                if (aniDoorIndex >= getAmountSpritesOfDoor(doorAction)) {
                    aniDoorIndex = 0;
                }
    
            }          
        }
       if(direction == DONTMOVE){
           yHitBox = y;
        //    System.out.println(yHitBox);
       }else{
           if(aniDoorIndex<=12){
                yHitBox = y + aniDoorIndex * 192f/12;
           }else{
               yHitBox = y + 192;
           }
       }
    }
    
    @Override
    public void render() {
        gc.drawImage(animationImages[doorAction][aniDoorIndex], x, y, width, height);
    }
    public float getyHitBox() {
        return yHitBox;
    }
    public void setyHitBox(float yHitBox) {
        this.yHitBox = yHitBox;
    }
    
    
}
