package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Enemy4 extends Enemy {
    public static int IDLE = 0;
    public static int DEATH = 1;
    protected Image[][] animationImages;
    public Enemy4( float x, float y,Image[][] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
    }
    
    @Override
    public void render() {
        if(!disappear){
            gc.drawImage(animationImages[enemyAction][aniIndex], x, y, width, height);   
        }   
    }
    @Override
    protected void setAnimation() {
        int startAni = enemyAction;
        if(death){
            enemyAction = DEATH;
        }else{
            enemyAction = IDLE;
        }
        if(startAni!=enemyAction){
            aniIndex = 0;
        }
    }
    @Override
    protected void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getAmountSpritesOfEnimy4Action(enemyAction)) {
				aniIndex = 0;
                if(death){
                    disappear = true;
                }
			}
		}
    }
    @Override
    protected void handleCollision(){};
    @Override
    protected void updatePos(){};
    public static int getAmountSpritesOfEnimy4Action(int x) {
        if(x == IDLE){
            return 6;
        }else if(x == DEATH){
            return 6;
        }else{
            return 0;
        }
    }
    
}
