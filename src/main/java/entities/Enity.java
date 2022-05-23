package entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Enity {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected GraphicsContext gc;
    public Enity(){};
    public Enity(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public abstract void render();
    public abstract void update();
    protected abstract void updateAnimationTick();
    protected abstract void updatePos();
    protected abstract void handleCollision();
    protected abstract void setAnimation();
}
