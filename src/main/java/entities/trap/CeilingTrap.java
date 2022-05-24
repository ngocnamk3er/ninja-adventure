package entities.trap;

import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class CeilingTrap  extends Trap{
    public CeilingTrap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        super(x, y, 64, 128, x, y, 64, 8,15,animationImages, mapInteractionManager);
        aniSpeed = 10;
    }
    @Override
    protected void updateDangerHitbox() {
        // System.out.println(aniIndex);
        switch (aniIndex) {
            case 0:
                yDangerHitbox = y + 16;
            break;
            case 1:
                yDangerHitbox = y + 76;
            break;
            case 2:  
            case 3:
            case 4:
                yDangerHitbox = y + 128;
                break;
            case 5:
                yDangerHitbox = y + 120;
                break;
            case 6:
                yDangerHitbox = y + 96;
            break;
            case 7:
                yDangerHitbox = y + 76;
            break;
            case 8:
                yDangerHitbox = y + 60;
            break;
            case 9:
                yDangerHitbox = y + 46;
            break;
            case 10:
                yDangerHitbox = y + 32;
            break;
            case 11:
                yDangerHitbox = y + 20;
            break;
            case 12:
            case 13:
            case 14:
                yDangerHitbox = y + 16;
            default:
                break;
        }
    }
    
}
