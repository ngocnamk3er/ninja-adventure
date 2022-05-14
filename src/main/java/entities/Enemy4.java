package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class Enemy4 extends Enemy {
    protected Image[] animationImages;
    public Enemy4( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
    }
    
    @Override
    public void render() {
        gc.drawImage(animationImages[aniIndex], x, y, width, height);      
    }

    @Override
    public void update() {
        updateAnimationTick();    
    }
    private void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getAmountSpritesOfEnimy4Action()) {
				aniIndex = 0;
                hit = false;
                if(death){
                    disappear = true;
                }
			}
		}
    }

    public static int getAmountSpritesOfEnimy4Action() {
        return 6;
    }
    
}
