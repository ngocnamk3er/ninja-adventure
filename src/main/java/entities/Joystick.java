package entities;

import java.util.ArrayList;

import javafx.scene.image.Image;
import map.MapInteractionManager;

public class Joystick extends Entity {
    private Image[] animationImages;
    private ArrayList<WoodyBox> woodyBoxs;
    private int countClick = 0;
    private boolean left = true;

    public Joystick(float x, float y, Image[] animationImages, MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        this.gc = mapInteractionManager.getGc();
        width = 64;
        height = 64;
        this.animationImages = animationImages;
        this.woodyBoxs = mapInteractionManager.getWoodyBoxs();
    }

    @Override
    public void render() {
        if (left) {
            gc.drawImage(animationImages[0], x, y, width, height);
        } else {
            gc.drawImage(animationImages[1], x, y, width, height);
        }
    }

    @Override
    public void update() {

    }

    public void click() {
        left = !left;
        for (WoodyBox woodyBox : woodyBoxs) {
            woodyBox.changeSize();
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

    public int getCountClick() {
        return countClick;
    }

    public void setCountClick(int countClick) {
        this.countClick = countClick;
    }

}
