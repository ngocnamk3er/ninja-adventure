package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CoinPickUp extends Enity{
    private Image[] animationImages;
    private GraphicsContext gc;
    private int aniTick=0;
    private int aniSpeed=5;
    private int aniIndex=0;
    public CoinPickUp(float x, float y, float width, float height, GraphicsContext gc) {
        super(x, y, width, height);
        this.gc = gc;
        loadAnimations();
    }
    private void loadAnimations() {
        animationImages =  new Image[6];
        for(int i = 0 ; i < 6 ; i++) {
            try {
                animationImages[i]=new Image(CoinPickUp.class.getResourceAsStream("coin_pickup"+i+".png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void render() {
        if(aniIndex<6){
            aniTick++;
		    if (aniTick >= aniSpeed) {
			    aniTick = 0;
                gc.drawImage(animationImages[aniIndex], x+16, y+16-32, width,height);
			    aniIndex++;
		    }
        }else{
            return;
        }
    }
}
