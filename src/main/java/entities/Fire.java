package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Fire extends Enity {
    private int aniTick;
    private int aniSpeed = 10;
    private int aniIndex;
    private int fireAction;
    private Image[][] animationImages;
    public static final int OFF = 0;
    public static final int ON = 1;
    private boolean on = false;
    public Fire( float x, float y,Image[][] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 132;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
    }   
    
    @Override
    public void render() {
        gc.drawImage(animationImages[fireAction][aniIndex], x, y, width, height);
    }

    @Override
    public void update() {
        setAnimation();
        updateAnimationTick();
    }
    @Override
    protected void setAnimation() {
        int startAni = fireAction;
        if(on==false){
            fireAction = OFF;
        }else{
            fireAction = ON;
        }
        if(startAni != fireAction){
            aniIndex = 0;
        }
    }

    @Override
    protected void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
            if (aniIndex >= getAmountSpritesOfFireAction(fireAction)) {
				aniIndex = 0;
			}

		}
    }

    public static int getAmountSpritesOfFireAction(int fireAction) {
        if(fireAction==OFF){
            return 4;
        }else if(fireAction==ON){
            return 3;
        }else{
            return 0;
        }
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    protected void updatePos() {
        
    }

    @Override
    protected void handleCollision() {
        
    }
    
}
