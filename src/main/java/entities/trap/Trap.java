package entities.trap;

import entities.Enity;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mapinteraction.MapInteractionManager;

public abstract class Trap extends Enity{
    protected Image []animationImages;
    protected float xDangerHitbox;
    protected float yDangerHitbox;
    protected float widthDangerHitbox;
    protected float heightDangerHitbox;
    private int aniTick = 0;
    protected int aniSpeed = 4;
    protected int aniIndex = 0;
    protected  int amountSprites;
    public Trap( float x, float y,float width,float height,float xDangerHitbox,float yDangerHitbox,float widthDangerHitbox,float heightDangerHitbox,int amountSprites,Image[] animationImages,MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xDangerHitbox = xDangerHitbox;
        this.yDangerHitbox = yDangerHitbox;
        this.widthDangerHitbox = widthDangerHitbox;
        this.heightDangerHitbox = heightDangerHitbox;
        this.gc = mapInteractionManager.getGc();
        this.animationImages = animationImages;
        this.amountSprites = amountSprites;
    }
    private void renderDangerHitbox(){
        gc.setFill(Color.RED);
        gc.fillRect(xDangerHitbox, yDangerHitbox, widthDangerHitbox, heightDangerHitbox);
        // gc.fill(xDangerHitbox, yDangerHitbox, widthDangerHitbox, heightDangerHitbox);

    }
    @Override
    public void render() {
        // renderDangerHitbox();
        gc.drawImage(animationImages[aniIndex], x, y, width, height); 
    }

    @Override
    public void update() {
        updateAnimationTick();
        updateDangerHitbox();
    }

    @Override
    protected void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= amountSprites) {
				aniIndex = 0;
			}
		}
    }
    protected abstract void updateDangerHitbox();
    public float getxDangerHitbox() {
        return xDangerHitbox;
    }
    public void setxDangerHitbox(float xDangerHitbox) {
        this.xDangerHitbox = xDangerHitbox;
    }
    public float getyDangerHitbox() {
        return yDangerHitbox;
    }
    public void setyDangerHitbox(float yDangerHitbox) {
        this.yDangerHitbox = yDangerHitbox;
    }
    public float getWidthDangerHitbox() {
        return widthDangerHitbox;
    }
    public void setWidthDangerHitbox(float widthDangerHitbox) {
        this.widthDangerHitbox = widthDangerHitbox;
    }
    public float getHeightDangerHitbox() {
        return heightDangerHitbox;
    }
    public void setHeightDangerHitbox(float heightDangerHitbox) {
        this.heightDangerHitbox = heightDangerHitbox;
    }
    
    public int getAmountSprites() {
        return amountSprites;
    }

    public void setAmountSprites(int amountSprites) {
        this.amountSprites = amountSprites;
    }

    @Override
    protected void handleCollision() {
        
    }

    @Override
    protected void setAnimation() {
        
    }

    @Override
    protected void updatePos() {
        
    }
    
}
