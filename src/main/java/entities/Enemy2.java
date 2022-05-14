package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;
public class Enemy2 extends Enemy {

    public static final int RUN = 0;
    public static final int DEATH = 1;
    public static final int HIT = 2;
    
    public Enemy2( float x, float y,Image[][] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y+32;
        width = 64;
        height = 32;
        this.gc = mapInteractionManager.getGc();
        this.mapData = mapInteractionManager.getMapData();
        this.stones = mapInteractionManager.getStones();
        this.door = mapInteractionManager.getDoor();
        this.animationImages = animationImages;
    }

    public void update() {
        if(!disappear){
            handleCollision();
            updatePos();
            setAnimation();
            updateAnimationTick();
        }
    }
    private void setAnimation() {
        int startAni = enimyAction;
        if(run){
            enimyAction = RUN;
        }
        if(hit){
            enimyAction = HIT;
        }
        if(death){
            enimyAction = DEATH;
        }    
        if(startAni!=enimyAction){
            aniIndex = 0;
        }
    }
    private void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getAmountSpritesOfEnimy2Action(enimyAction)) {
				aniIndex = 0;
                hit = false;
                if(death){
                    disappear = true;
                }
			}
		}
    }
    @Override
    public void render() {
        if(!disappear){
            gc.drawImage(animationImages[enimyAction][aniIndex], x, y, width, height);
        }
    }
    public static int getAmountSpritesOfEnimy2Action(int x) {
        if (x == RUN) {
            return 6;
        }else if (x == DEATH) {
            return 6;
        }else if(x == HIT){
            return 3;
        }else{
            return 0;
        }
    }
}
