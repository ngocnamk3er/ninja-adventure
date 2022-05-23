package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StrangeDoor extends Enity {
    private Image animationImage;
    public void setProperties(float x,float y,GraphicsContext gc, Image animationImage){
        this.x = x;
        this.y = y;
        this.gc=gc;
        width = 132;
        height = 132;
        this.animationImage = animationImage;
    }
    @Override
    public void render() {
        gc.drawImage(animationImage, x, y, width, height);
    }

    public void setAnimationImage(Image animationImage) {
        this.animationImage = animationImage;
    }
    @Override
    public void update() {
        
    }
    @Override
    protected void updateAnimationTick() {
        
    }
    @Override
    protected void updatePos() {
        
    }
    @Override
    protected void handleCollision() {
        
    }
    @Override
    protected void setAnimation() {
        
    }  
}
