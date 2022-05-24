package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class LightningTrap extends Trap{
    public LightningTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 64, 128, x, y, 64, 128, 22, animationImages, mapInteractionManager);
    }
    @Override
    protected void updateDangerHitbox() {
        
    }
}
