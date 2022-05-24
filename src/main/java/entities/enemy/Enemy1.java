package entities.enemy;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Enemy1 extends Enemy {
    //right
    public static final int IDLE_R = 0;
    public static final int RUN_R = 1;
    public static final int DEATH_R = 2;
    public static final int HIT_R = 3;
    //left
    public static final int IDLE_L = 4;
    public static final int RUN_L = 5;;
    public static final int DEATH_L = 6;
    public static final int HIT_L = 7;
    
    private float deltaY;

    public Enemy1( float x, float y,Image[][] animationImages,MapInteractionManager mapInteractionManager) {
        super(x,y,64,64,animationImages,mapInteractionManager.getGc());
        this.mapData = mapInteractionManager.getMapData();
        this.stones = mapInteractionManager.getStones();
        this.door = mapInteractionManager.getDoor();
        deltaY = 32;
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
			if (aniIndex >= getAmountSpritesOfEnimy1Action(enemyAction)) {
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
            if(run){
                gc.drawImage(animationImages[enemyAction][aniIndex], x, y-deltaY, width, height+deltaY);
            }else{
                gc.drawImage(animationImages[enemyAction][aniIndex], x, y, width, height);
            }
        }
    }
    public static int getAmountSpritesOfEnimy1Action(int x) {
        if (x == IDLE_L || x == IDLE_R) {
            return 5;
        } else if (x == RUN_L || x == RUN_R) {
            return 15;
        }else if (x == DEATH_L || x == DEATH_R) {
            return 6;
        }else if(x == HIT_L || x == HIT_R){
            return 3;
        }else{
            return 0;
        }
    }
        
}
