package help;


public class HelpMethods {
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
}