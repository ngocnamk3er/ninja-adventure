package help;


public class HelpMethods {
    //right
    public static final int IDLE_R = 0;
    public static final int RUN_R = 1;
    public static final int ATTACK1_R = 2;
    public static final int ATTACK2_R = 3;
    public static final int PUSH_R = 4;
    public static final int JUMPDOWN_R = 5;
    public static final int JUMPUP_R = 6;
    public static final int DEATH_R = 7;
    //left
    public static final int IDLE_L = 8;
    public static final int RUN_L = 9;
    public static final int ATTACK1_L = 10;
    public static final int ATTACK2_L = 11;
    public static final int PUSH_L = 12;
    public static final int JUMPDOWN_L = 13;
    public static final int JUMPUP_L = 14;
    public static final int DEATH_L = 15;
    //door
    public static final int CLOSED = 0;
    public static final int MOVING = 1;

    public static boolean isSolid(float x,float y, int mapData[][]){
        int columnBrick = (int) x / 64;
        int rowBrick = (int) y / 64;
        if(rowBrick < 0 || rowBrick >= 12){
            return true;
        }
        if(columnBrick < 0  ||  columnBrick >= 21){
            return true;
        }
        if(mapData[rowBrick][columnBrick] < 0 || mapData[rowBrick][columnBrick] > 42 || mapData[rowBrick][columnBrick] != 16){
            return true;
        }
        return false;
    }

    public static boolean canMove(Float xHitbox,Float yHitbox,float widthHitbox,float heightHitbox, int[][] mapData){
        if((isSolid(xHitbox, yHitbox, mapData)) || (isSolid(xHitbox, yHitbox+heightHitbox,mapData)) || (isSolid(xHitbox+widthHitbox, yHitbox, mapData)) || (isSolid(xHitbox+widthHitbox, yHitbox+heightHitbox, mapData))){
            return false;
        }else{
            return true;
        }
    }
    public static int getAmountSpritesOfPlayerAction(int x) {
        if (x == IDLE_L || x == IDLE_R) {
            return 4;
        }
        else if (x == RUN_L || x == RUN_R) {
            return 6;
        }
        else if (x == ATTACK1_L || x == ATTACK1_R) {
            return 4;
        }
        else if (x == ATTACK2_L || x == ATTACK2_R) {
            return 4;
        }
        else if (x == PUSH_L || x == PUSH_R) {
            return 6;
        }
        else if (x == JUMPUP_L || x == JUMPUP_R) {
            return 3;
        }
        else if (x == JUMPDOWN_L || x == JUMPDOWN_R) {
            return 3;
        }else if (x == DEATH_L || x == DEATH_R) {
            return 8;
        }else{
            return 0;
        }
    }
    public static int getAmountSpritesOfDoor(int action) {
        if(action == CLOSED){
            return 10;
        }else if (action == MOVING){
            return 15;
        }else{
            return 0;
        }
    }
}