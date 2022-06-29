package entities.trap;

import javafx.scene.image.Image;
import map.MapInteractionManager;

public class SpearTrap extends Trap {
    public SpearTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 16, 128, x, y, 16, 16, 13, animationImages, mapInteractionManager);
        aniSpeed = 5;
    }
    @Override
    protected void updateDangerHitbox() {
        switch (aniIndex) {
            case 0:
            case 1:
                yDangerHitbox = y + 96;
                break;
            case 2:
                yDangerHitbox = y + 40;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                yDangerHitbox = y;
                break;
            case 7:
                yDangerHitbox = y + 8;
                break;
            case 8:
                yDangerHitbox = y + 40;
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                yDangerHitbox = y;
                break;
            default:
                break;
        }
    }
    
}
