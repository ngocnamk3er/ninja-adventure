package map;

import help.Constant.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MapManager {
    private GraphicsContext gc;
    private int[][] mapData;
    private Image[] allBrick;
    private Image maplayout0Image;
    private Image maplayout1Image;
    
    public MapManager(GraphicsContext gc) {
        mapData=new int[12][21];
        this.gc = gc;
        loadMaplayout0Image();
        loadMaplayout1Image();
        loadAllBrick();
        // loadDataMap();
    }
    private void loadMaplayout1Image() {
        maplayout1Image=new Image(MapManager.class.getResourceAsStream("layout1.png"));
    }
    private void loadMaplayout0Image() {
        maplayout0Image=new Image(MapManager.class.getResourceAsStream("layout0.png"));
    }
    public void loadDataMap(int levelValue){
        for(int i=0;i<12;i++){
            for(int j=0;j<21;j++){
                mapData[i][j]=Map.MAPDATA[levelValue][i][j];
            }
        }
    }
    private void loadAllBrick(){
        allBrick=new Image[43];
        for(int i=0;i<43;i++){
            allBrick[i]=new Image(MapManager.class.getResourceAsStream("brick"+i+".png"));
        }
    }
    public void render(){
        // gc.clearRect(0, 0, 21*64, 12*64);
        gc.drawImage(maplayout0Image,0,0 ,21*64, 12*64);
        gc.drawImage(maplayout1Image,0,0, 21*64, 12*64);
        for(int i=0;i<12;i++){
            for(int j=0;j<21;j++){
                gc.drawImage(allBrick[mapData[i][j]], j*64, i*64);
            }
        }
    }
    public int[][] getMapData() {
        return mapData;
    }
    public void setMapData(int[][] mapData) {
        this.mapData = mapData;
    }
    
}
