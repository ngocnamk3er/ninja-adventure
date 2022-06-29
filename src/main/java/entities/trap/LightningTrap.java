package entities.trap;

import javafx.scene.image.Image;
import map.MapInteractionManager;

public class LightningTrap extends Trap{
    public LightningTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 64, 128, x, y, 32, 128, 22, animationImages, mapInteractionManager);
        aniSpeed = 10;
    }
    @Override
    protected void updateDangerHitbox() {
        // System.out.println(aniIndex);
        widthDangerHitbox = 32;
        heightDangerHitbox = 128;
        switch (aniIndex) {
            case 0:
            case 1:
            case 2:
               widthDangerHitbox = 0;
               heightDangerHitbox = 0; 
            break;
            case 3:
                xDangerHitbox = x + 0;
            break;
            case 4:
                xDangerHitbox = x + 8;
            break;
            case 5:
                xDangerHitbox = x + 16;
            break;
            case 6:
                xDangerHitbox = x + 24;
            break;
            case 7:
                xDangerHitbox = x + 32;
            break;
            case 8:
                xDangerHitbox = x + 32;
            break;
            case 9:
                xDangerHitbox = x + 32;
            break;
            case 10:
                xDangerHitbox = x + 32;
            break;
            case 11:
                xDangerHitbox = x + 32;
            break;
            case 12:
                xDangerHitbox = x + 32;
            break;
            case 13:
                xDangerHitbox = x + 32;
            break;
            case 14:
                xDangerHitbox = x + 32;
            break;
            case 15:
                xDangerHitbox = x + 16;
            break;
            case 16:
                xDangerHitbox = x + 16;
            break;
            case 17:
                xDangerHitbox = x + 16;
            break;
            case 18:
            case 19:
            case 20:
            case 21:
                widthDangerHitbox = 0;
                heightDangerHitbox = 0;
            break;
            default:
                break;
        }
    }
}
