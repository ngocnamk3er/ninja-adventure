package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StrangeDoor extends Enity {
    private Image animationImage;
    private GraphicsContext gc;
    public StrangeDoor(){
        width = 132;
        height = 132;
    }
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
    
}
