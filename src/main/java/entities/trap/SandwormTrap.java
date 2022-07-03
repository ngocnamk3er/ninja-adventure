package entities.trap;

import javafx.scene.image.Image;
import map.MapInteractionManager;

public class SandwormTrap extends Trap {
    public SandwormTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 128, 128, x, y, 128, 128, 11, animationImages, mapInteractionManager);
        aniSpeed = 10;
    }
    @Override
    protected void updateDangerHitbox() {
        widthDangerHitbox = 0;
        heightDangerHitbox = 0;
        switch (aniIndex) {
            case 4:
                widthDangerHitbox = 128;
                heightDangerHitbox = 64;
                yDangerHitbox = y + 64;
                break;
            case 5:
                widthDangerHitbox = 128;
                heightDangerHitbox = 128;
                yDangerHitbox = y;
                break;
            default:
                break;
        }
    }
    
}
