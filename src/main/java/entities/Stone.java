package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;
import static help.HelpMethods.*;

public class Stone extends Enity {

    private Image animationImage;
    private GraphicsContext gc;
    private int[][] mapData;
    private float gravity = 0.7f;
    private float ySpeed = 0;
    private float xSpeed = 0;
    private boolean inAir;
    private boolean canPush = true;
    public Stone(float x, float y, float width, float height, MapInteractionManager mapInteractionManager) {
        super(x, y, width, height);
        this.mapData = mapInteractionManager.getMapData();
        loadAnimations();
        this.gc=mapInteractionManager.getGc();
    }
    private void loadAnimations() {
        try {
            animationImage =new Image(Player.class.getResourceAsStream("stone.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // private void handleCollision(){
        
    // }
    private void updatePos(){
        setInAir();
        if(inAir){
            ySpeed = ySpeed + gravity;
        }else{
            ySpeed = 0;
        }
        if (canMove(x+xSpeed,y,63,63, mapData) == true) {
		    x += xSpeed;
            canPush = true;
        }else{
            canPush = false;
        }
        if (canMove(x,y+ySpeed,63,63, mapData) == true) {
		    y += ySpeed;
        }else{
            int rowBrick = (int)(y+ySpeed)/64;//this is bug 
            // ySpeed = 0;
            y = rowBrick*64;
        }
    }
    private void setInAir() {
        if((!isSolid(x, y+65, mapData))&&(!isSolid(x+63, y+65, mapData))){
            inAir = true;
        }else{
            inAir = false;
        }
    }
    public void update(){
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
