package entities;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Door extends Enity {
    private Image[][] animationImagesDoor;
    private int aniDoorIndex;
    private int aniTick;
    private int aniSpeed = 15;
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
    // public Door(float x, float y, float width, float height, MapInteractionManager mapInteractionManager) {
    //     super(x, y, width, height);
    //     this.gc =  mapInteractionManager.getGc();
    //     this.buttons = mapInteractionManager.getButtons();
    // }
    public Door() {
    }
    public void setProperties(float x, float y, float width, float height, MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width=width;
        this.gc =  mapInteractionManager.getGc();
        this.buttons = mapInteractionManager.getButtons();
        loadAnimations();
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
        for(Button button:buttons){
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
        }else{
            return;
        }
    }
    
    @Override
    public void render() {
        gc.drawImage(animationImagesDoor[doorAction][aniDoorIndex], x, y, width, height);
    }

    @Override
    protected void loadAnimations() {
        animationImagesDoor = new Image[2][15];
        for(int i=0;i<2;i++){
            for(int j=0;j<getAmountSpritesOfDoor(i);j++){
                if(i == CLOSED){
                    animationImagesDoor[i][j] =  new Image(Door.class.getResourceAsStream("door_closed"+j+".png"));
                }else {
                    animationImagesDoor[i][j] =  new Image(Door.class.getResourceAsStream("door_openning"+j+".png"));
                }
            }
        }        
    }

    private int getAmountSpritesOfDoor(int action) {
        if(action == CLOSED){
            return 10;
        }else if (action == MOVING){
            return 15;
        }else{
            return 0;
        }
    }
    
}
