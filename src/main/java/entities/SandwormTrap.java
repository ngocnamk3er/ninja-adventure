package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class SandwormTrap extends Trap {
    public SandwormTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 128, 128, x, y, 128, 128, 11, animationImages, mapInteractionManager);
    }
    @Override
    protected void updateDangerHitbox() {
        
    }
    
}
