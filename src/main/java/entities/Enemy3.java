package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Enemy3 extends Enemy {

    public static final int RUN_R = 0;
    public static final int DEATH_R = 1;
    public static final int HIT_R = 2;
    public static final int RUN_L = 3;
    public static final int DEATH_L = 4;
    public static final int HIT_L = 5;
    
    public Enemy3( float x, float y,Image[][] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.gc = mapInteractionManager.getGc();
        this.mapData = mapInteractionManager.getMapData();
        this.stones = mapInteractionManager.getStones();
        this.door = mapInteractionManager.getDoor();
        this.animationImages = animationImages;
    }
    @Override
    protected void setAnimation() {
        int startAni = enemyAction;
        if(right){
            if(run){
                enemyAction = RUN_R;
            }
            if(hit){
                enemyAction = HIT_R;
            }
            if(death){
                enemyAction = DEATH_R;
            }    
            if(startAni!=enemyAction){
                aniIndex = 0;
            }
        }else{
            if(run){
                enemyAction = RUN_L;
            }
            if(hit){
                enemyAction = HIT_L;
            }
            if(death){
                enemyAction = DEATH_L;
            }    
            if(startAni!=enemyAction){
                aniIndex = 0;
            }
        }
    }
    @Override
    protected void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getAmountSpritesOfEnimy3Action(enemyAction)) {
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
            gc.drawImage(animationImages[enemyAction][aniIndex], x, y, width, height);
        }
    }
    public static int getAmountSpritesOfEnimy3Action(int x) {
        if (x == RUN_L||x == RUN_R) {
            return 8;
        }else if (x == DEATH_L|| x == DEATH_R) {
            return 6;
        }else if(x == HIT_L|| x == HIT_R){
            return 3;
        }else{
            return 0;
        }
    }
    
}
