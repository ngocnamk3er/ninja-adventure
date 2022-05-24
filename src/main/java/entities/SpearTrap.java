package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class SpearTrap extends Trap {
    public SpearTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 16, 128, x, y, 16, 128, 12, animationImages, mapInteractionManager);
    }
    @Override
    protected void updateDangerHitbox() {
        
    }
    
}
