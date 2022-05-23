package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Coin extends Enity {
    private Image[][] animationImages;
    private int aniTick=0;
    private int aniSpeed=5;
    private int aniIndex=0;
    private boolean pickedUp = false;
    private int coinAction;
    private final int IDLE = 0;
    private final int PICKEDUP = 1;
    private boolean disappear = false;
    public Coin(float x, float y, MapInteractionManager mapInteractionManager, Image[][] animationImages) {
        super(x, y);
        width = 32;
        height = 32;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
    }
    @Override
    protected void setAnimation(){
        int startAni = coinAction;
        if(pickedUp==false){
            coinAction = IDLE;
        }else{
            coinAction = PICKEDUP;
        }
        if(startAni != coinAction){
            aniIndex = 0;
        }
    }
    @Override
    protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 6) {
				aniIndex = 0;
                if(coinAction == PICKEDUP){
                    disappear = true;
                }
			}

		}
    }
    @Override
    public void update(){
        if(!disappear){
            setAnimation();
            updateAnimationTick();
        }
    }
    @Override
    public void render() {
        if(!disappear){
            if(pickedUp==false){
                gc.drawImage(animationImages[0][aniIndex], x+16, y+16, width,height);
            }else{
                gc.drawImage(animationImages[1][aniIndex], x+16, y, width,height*2);
            }
        }
    }
    public boolean isPickedUp() {
        return pickedUp;
    }
    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
    @Override
    protected void updatePos() {
        
    }
    @Override
    protected void handleCollision() {
        
    }
    
}
