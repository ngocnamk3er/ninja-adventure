package entities;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;

public class CoinPickUp extends Enity{
    private Image[] animationImages;
    private GraphicsContext gc;
    private int aniTick=0;
    private int aniSpeed=5;
    private int aniIndex=0;
    private BufferedImage bufferedImage;
    public CoinPickUp(float x, float y, float width, float height, GraphicsContext gc) {
        super(x, y, width, height);
        this.gc = gc;
        loadAnimations();
    }
    protected void loadAnimations() {
        animationImages =  new Image[6];
        try {
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin_pickup.png"));
            for(int i = 0 ; i < 6 ; i++) {
                try {
                    animationImages[i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i*8, 0, 8, 16), null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
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
