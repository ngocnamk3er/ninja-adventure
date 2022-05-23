package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class SandwormTrap extends Trap {
    public SandwormTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 128;
        height = 128;
        xDangerHitbox = x;
        yDangerHitbox = y;
        widthDangerHitbox = width;
        heightDangerHitbox = height;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
        amountSprites = 11;
    }
    @Override
    protected void updateDangerHitbox() {
        
    }
    
}
