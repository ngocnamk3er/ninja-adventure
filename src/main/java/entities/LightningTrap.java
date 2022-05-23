package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class LightningTrap extends Trap{
    public LightningTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 128;
        xDangerHitbox = x;
        yDangerHitbox = y;
        widthDangerHitbox = width;
        heightDangerHitbox = height;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
        amountSprites = 22;
    }
    @Override
    protected void updateDangerHitbox() {
        
    }
}
