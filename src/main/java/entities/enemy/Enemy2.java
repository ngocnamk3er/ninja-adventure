package entities.enemy;

import javafx.scene.image.Image;
import map.MapInteractionManager;

public class Enemy2 extends Enemy {

    public static final int RUN = 0;
    public static final int DEATH = 1;
    public static final int HIT = 2;

    public Enemy2(float x, float y, Image[][] animationImages, MapInteractionManager mapInteractionManager) {
        super(x, y + 32, 64, 32, animationImages, mapInteractionManager.getGc());
        this.mapData = mapInteractionManager.getMapData();
        this.stones = mapInteractionManager.getStones();
        this.door = mapInteractionManager.getDoor();
    }

    @Override
    protected void setAnimation() {
        int startAni = enemyAction;
        if (run) {
            enemyAction = RUN;
        }
        if (hit) {
            enemyAction = HIT;
        }
        if (death) {
            enemyAction = DEATH;
        }
        if (startAni != enemyAction) {
            aniIndex = 0;
        }
    }

    @Override
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getAmountSpritesOfEnimyAction(enemyAction)) {
                aniIndex = 0;
                hit = false;
                if (death) {
                    disappear = true;
                }
            }
        }
    }

    @Override
    public void render() {
        if (!disappear) {
            gc.drawImage(animationImages[enemyAction][aniIndex], x, y, width, height);
        }
    }

    public static int getAmountSpritesOfEnimyAction(int x) {
        if (x == RUN) {
            return 6;
        } else if (x == DEATH) {
            return 6;
        } else if (x == HIT) {
            return 3;
        } else {
            return 0;
        }
    }
}
