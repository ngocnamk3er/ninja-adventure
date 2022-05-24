package entities.trap;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class ShurikenTrap extends Trap{
    public ShurikenTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 128, 128, x, y, 128, 128, 8, animationImages, mapInteractionManager);
    }

    @Override
    protected void updateDangerHitbox() {
        
    }
    
}
