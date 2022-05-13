package entities;

public abstract class Enity {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
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
    public abstract void render();
}
