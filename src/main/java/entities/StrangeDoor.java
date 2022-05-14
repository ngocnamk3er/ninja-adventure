package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StrangeDoor extends Enity {
    private Image animationImage;
    private GraphicsContext gc;
    private float width = 132;
    private float height = 132;
    public void setProperties(float x,float y,GraphicsContext gc, Image animationImage){
        this.x = x;
        this.y = y;
        this.gc=gc;
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
    
}
