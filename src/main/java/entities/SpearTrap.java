package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class SpearTrap extends Trap {
    public SpearTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 16;
        height = 128;
        xDangerHitbox = x;
        yDangerHitbox = y;
        widthDangerHitbox = width;
        heightDangerHitbox = height;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
        amountSprites = 12;
    }
    @Override
    protected void updateDangerHitbox() {
        
    }
    
}
