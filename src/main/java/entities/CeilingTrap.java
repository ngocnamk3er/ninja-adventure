package entities;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class CeilingTrap  extends Trap{
    public CeilingTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 64, 128, x, y, 64, 128,14,animationImages, mapInteractionManager);
    }
    @Override
    protected void updateDangerHitbox() {
        
    }
    
}
