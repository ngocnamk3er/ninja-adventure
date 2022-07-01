package entities;

import javafx.scene.image.Image;
import map.MapInteractionManager;

public class Heart extends Enity {
    private Image[] animationImages;
    private int aniTick = 0;
    private int aniSpeed = 10;
    private int aniIndex = 0;
    private boolean disappear = false;
    private boolean isPickedUp = false;
    public Heart(float x, float y, Image[] animationImages, MapInteractionManager mapInteractionManager) {
        super(x, y, 64, 64, mapInteractionManager.getGc());
        this.animationImages = animationImages;
    }
    @Override
    public void render() {
        if(!disappear){
            gc.drawImage(animationImages[aniIndex], x , y , width, height);
        }
    }

    @Override
    public void update() {
        setAnimation();
        updateAnimationTick();
    }

    @Override
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 3) {
                aniIndex = 0;
            }
        }
    }

    @Override
    protected void updatePos() {

    }

    @Override
    protected void handleCollision() {

    }

    @Override
    protected void setAnimation() {
        if(isPickedUp){
            disappear  = true;
        }
    }
    public boolean isPickedUp() {
        return isPickedUp;
    }
    public void setPickedUp(boolean isPickedUp) {
        this.isPickedUp = isPickedUp;
    }
    
}
