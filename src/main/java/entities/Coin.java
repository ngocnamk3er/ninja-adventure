package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin extends Enity {
    private Image[][] animationImages;
    private GraphicsContext gc;
    private int aniTick=0;
    private int aniSpeed=5;
    private int aniIndex=0;
    private boolean pickedUp = false;
    private int coinAction;
    private final int IDLE = 0;
    private final int PICKEDUP = 1;
    public Coin(float x, float y, float width, float height, GraphicsContext gc, Image[][] animationImages) {
        super(x, y, width, height);
        this.gc = gc;
        this.animationImages = animationImages;
    }
    private void setAnimation(){
        int startAni = coinAction;
        if(pickedUp==false){
            coinAction = IDLE;
        }else{
            coinAction = PICKEDUP;
        }
        if(startAni != coinAction){
            aniIndex = 0;
        }
    }
    private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 6) {
				aniIndex = 0;
                if(coinAction == PICKEDUP){
                    aniIndex = 6;
                }
			}

		}
    }
    public void update(){
        setAnimation();
        updateAnimationTick();
    }
    public void render() {
        if(pickedUp==false){
            gc.drawImage(animationImages[0][aniIndex], x+16, y+16, width,height);
        }else{
            if(aniIndex>=6){
                return;
            }else{
                gc.drawImage(animationImages[1][aniIndex], x+16, y, width,height*2);
            }
        }
    }
    public boolean isPickedUp() {
        return pickedUp;
    }
    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
    
}
