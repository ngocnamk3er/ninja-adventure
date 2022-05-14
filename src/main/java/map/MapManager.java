package map;

import help.Constant.Map;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class MapManager {
    private GraphicsContext gc;
    private int[][] mapData;
    private Image[] allBrick;
    private Image maplayout0Image;
    private Image maplayout1Image;
    private BufferedImage bufferedImage;
    
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
        allBrick=new Image[48];
        try {
            bufferedImage = ImageIO.read(MapManager.class.getResourceAsStream("AllBricks.png"));
            for(int i=0;i<48;i++){
                allBrick[i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i%12*64, i/12*64, 64, 64), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void render(){
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
