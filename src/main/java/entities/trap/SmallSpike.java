package entities.trap;


import javafx.scene.image.Image;
import mapinteraction.MapInteractionManager;

public class SmallSpike extends Trap {
    private Image animationImage;
    public SmallSpike( float x, float y,Image animationImage,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.xDangerHitbox = x;
        this.yDangerHitbox = y+32;
        this.widthDangerHitbox = 64;
        this.heightDangerHitbox = 32;
        this.gc = mapInteractionManager.getGc();
        this.animationImage = animationImage;
    }
    
    @Override
    protected void updateDangerHitbox() {
        
    }

    @Override
    public void render() {
        renderDangerHitbox();
        gc.drawImage(animationImage, x, y, width, height); 
    }

    @Override
    public void update() {
        updateDangerHitbox();
    }
    
}
