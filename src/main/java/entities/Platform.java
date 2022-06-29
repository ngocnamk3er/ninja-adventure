package entities;

import javafx.scene.image.Image;
import map.MapInteractionManager;

import static help.HelpMethods.*;

import java.util.ArrayList;

public class Platform extends Enity {
    private Image[][] animationImages;
    public static final int RUN_R = 0;
    public static final int RUN_L = 1;
    private int platformAction = 0;
    private int xSpeed;
    private int aniTick;
    private int aniSpeed = 10;
    private int aniIndex;
    private boolean right = true;
    private int[][] mapData;

    private Door door;
    private ArrayList<Stone> stones;
    private ArrayList<Platform> platforms;

    public Platform(float x, float y, Image[][] animationImages, MapInteractionManager mapInteractionManager) {
        super(x, y, 128, 40, mapInteractionManager.getGc());
        this.animationImages = animationImages;
        this.mapData = mapInteractionManager.getMapData();
        this.door = mapInteractionManager.getDoor();
        this.stones = mapInteractionManager.getStones();
        this.platforms = mapInteractionManager.getPlatforms();
    }

    @Override
    public void render() {
        gc.drawImage(animationImages[platformAction][aniIndex], x, y, width, height);
    }

    @Override
    public void update() {
        updateAnimationTick();
        updatePos();
        handleCollision();
        setAnimation();
    }

    @Override
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getAmountSpritesOfPlatformAction(platformAction)) {
                aniIndex = 0;
            }

        }
    }

    @Override
    protected void updatePos() {
        if (isSolid(x + xSpeed, y, mapData) == true || isSolid(x + xSpeed + width, y, mapData) == true) {
            right = !right;
        }
        if (right) {
            xSpeed = 3;
        } else {
            xSpeed = -3;
        }
        x = x + xSpeed;
    }

    @Override
    protected void handleCollision() {
        checkStones();
        checkDoor();
        checkPlatforms();
    }

    private void checkPlatforms() {
        for (Platform platform : platforms) {
            if (!platform.equals(this)) {
                if (y + height > platform.getY() && y < platform.getY() + platform.getHeight()) {
                    if ((platform.getX() < x + xSpeed && x + xSpeed < platform.getX() + platform.getWidth())
                            || (platform.getX() < x + width + xSpeed
                                    && x + width + xSpeed < platform.getX() + platform.getWidth())) {
                        right = !right;
                    }
                }
            }
        }
    }

    private void checkDoor() {
        if (y + height > door.getyHitBox() && y < door.getY() + door.getHeight()) {
            if ((door.getX() < x + xSpeed && x + xSpeed < door.getX() + door.getWidth())
                    || (door.getX() < x + width + xSpeed && x + width + xSpeed < door.getX() + door.getWidth())) {
                right = !right;
            }
        }
    }

    private void checkStones() {
        for (Stone stone : stones) {
            if (y + height > stone.getY() && y < stone.getY() + stone.getHeight()) {
                if ((stone.getX() < x + xSpeed && x + xSpeed < stone.getX() + stone.getWidth())
                        || (stone.getX() < x + width + xSpeed
                                && x + width + xSpeed < stone.getX() + stone.getWidth())) {
                    right = !right;
                }
            }
        }
    }

    @Override
    protected void setAnimation() {
        if (right) {
            platformAction = RUN_R;
        } else {
            platformAction = RUN_L;
        }
    }

    public static int getAmountSpritesOfPlatformAction(int i) {
        return 4;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

}
