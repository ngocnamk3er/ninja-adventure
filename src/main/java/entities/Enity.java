package entities;

public abstract class Enity {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    public Enity(){};
    public Enity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    public abstract void render();
    protected abstract void loadAnimations();
}
