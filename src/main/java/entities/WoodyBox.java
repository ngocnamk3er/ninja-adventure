package entities;

import javafx.scene.image.Image;
import map.MapInteractionManager;

public class WoodyBox extends Enity {
    private Image[] animationImages;
    private boolean bigSize = true;
    private int[][] mapData;
    private int indexX;
    private int indexY;

    public WoodyBox(float x, float y, Image[] animationImages, MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        indexX = (int) x / 64;
        indexY = (int) y / 64;
        this.gc = mapInteractionManager.getGc();
        this.mapData = mapInteractionManager.getMapData();
        width = 64;
        height = 64;
        this.animationImages = animationImages;
        if (bigSize) {
            mapData[indexY][indexX] = 22;
        } else {
            mapData[indexY][indexX] = 16;
        }
    }

    @Override
    public void render() {
        if (bigSize) {
            gc.drawImage(animationImages[0], x, y, width, height);
        } else {
            gc.drawImage(animationImages[1], x, y, width, height);
        }
    }

    @Override
    public void update() {

    }

    public void changeSize() {
        bigSize = !bigSize;
        if (bigSize) {
            mapData[indexY][indexX] = 22;
        } else {
            mapData[indexY][indexX] = 16;
        }
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

    public void setBigSize(boolean bigSize) {
        this.bigSize = bigSize;
    }
    
}
