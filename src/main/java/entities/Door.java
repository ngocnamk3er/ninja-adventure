package entities;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import map.MapInteractionManager;

public class Door extends Enity {
    private Image[][] animationImages;
    private int aniDoorIndex;
    private int aniTick;
    private int aniSpeed = 15;
    private float yHitBox;
    public static final int CLOSED = 0;
    private static final int MOVING = 1;
    private final int UP = 1;
    private final int DOWN = -1;
    private final int DONTMOVE = 0;
    private final int prepareUp = 2;
    private int doorAction = CLOSED;
    private int direction = DONTMOVE;
    private ArrayList<Button> buttons;

    public Door() {
        this.gc = new Canvas().getGraphicsContext2D();
        buttons = new ArrayList<>();
        animationImages = new Image[2][15];
    }

    public void setProperties(float x, float y, Image[][] animationImages,
            MapInteractionManager mapInteractionManager) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 192;
        this.gc = mapInteractionManager.getGc();
        this.buttons = mapInteractionManager.getButtons();
        this.animationImages = animationImages;
    }

    @Override
    public void update() {
        handleCollision();
        setAnimation();
        updateAnimationTick();
    }

    @Override
    protected void handleCollision() {
        checkButtons();
    }

    private void checkButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (button.isPressed() == false) {
                if (direction == prepareUp) {
                    direction = UP;
                    return;
                } else if (direction == DOWN || direction == DONTMOVE) {
                    return;
                } else {
                    return;
                }
            }
        }
        if (direction != prepareUp) {// direction == UP || direction == DONTMOVE
            direction = DOWN;
        }
    }

    @Override
    protected void setAnimation() {
        int startAni = doorAction;
        if (direction != 0) {
            doorAction = MOVING;
        } else {
            doorAction = CLOSED;
        }
        if (startAni != doorAction) {
            aniDoorIndex = 0;
        }
    }

    @Override
    protected void updateAnimationTick() {
        if (direction == DOWN) {
            if (aniDoorIndex < getAmountSpritesOfDoor(doorAction) - 1) {
                aniTick++;
                if (aniTick >= aniSpeed) {
                    aniTick = 0;
                    aniDoorIndex++;
                    if (aniDoorIndex == getAmountSpritesOfDoor(doorAction) - 1) {
                        direction = prepareUp;
                    }
                }
            }
        } else if (direction == UP) {
            if (aniDoorIndex > 0) {
                aniTick++;
                if (aniTick >= aniSpeed) {
                    aniTick = 0;
                    aniDoorIndex--;
                    if (aniDoorIndex == 0) {
                        doorAction = CLOSED;
                        direction = DONTMOVE;
                        aniDoorIndex = 0;
                    }
                }
            }
        } else if (direction == DONTMOVE) {
            aniTick++;
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniDoorIndex++;
                if (aniDoorIndex >= getAmountSpritesOfDoor(doorAction)) {
                    aniDoorIndex = 0;
                }

            }
        }
        if (direction == DONTMOVE) {
            yHitBox = y;
        } else {
            if (aniDoorIndex <= 12) {
                yHitBox = y + aniDoorIndex * 192f / 12;
            } else {
                yHitBox = y + 192;
            }
        }
    }

    @Override
    public void render() {
        gc.drawImage(animationImages[doorAction][aniDoorIndex], x, y, width, height);
    }

    public static int getAmountSpritesOfDoor(int action) {
        if (action == CLOSED) {
            return 10;
        } else if (action == MOVING) {
            return 15;
        } else {
            return 0;
        }
    }

    public float getyHitBox() {
        return yHitBox;
    }

    public void setyHitBox(float yHitBox) {
        this.yHitBox = yHitBox;
    }

    @Override
    protected void updatePos() {

    }

}
