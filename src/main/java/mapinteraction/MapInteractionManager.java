package mapinteraction;
import java.util.ArrayList;

import entities.Button;
import entities.Coin;
import entities.Door;
import entities.Enity;
import entities.Player;
import entities.Stone;
import help.Constant.MapInteraction;
import javafx.scene.canvas.GraphicsContext;
public class MapInteractionManager {
    private ArrayList<Enity> removedEnities = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Stone> stones = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();
    private Player player = new Player();
    private Door door = new Door();
    private GraphicsContext gc;
    private int [][] mapData;
    public MapInteractionManager(GraphicsContext gc,int [][]mapData){
        // removedEnities = new ArrayList<>();
        // stones = new ArrayList<>();
        // coins = new ArrayList<>();
        this.gc = gc;
        this.mapData = mapData;
        loadDataMapInteraction();
    }
    private void loadDataMapInteraction(){
        
        for(int i=0;i<12;i++){
            for(int j=0;j<21;j++){
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'c'){
                    coins.add(new Coin(j*64, i*64, 32, 32, gc));
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'p'){
                    player.setProperties(j*64, i*64, 64, 64,this);
                    
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 's'){
                    stones.add(new Stone(j*64, i*64, 64, 64, this));
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'b'){
                    buttons.add(new Button(j*64, i*64, 64, 64, this));
                }
                if(MapInteraction.MAP_INTERAC_DATA1[i][j] == 'd'){
                    door.setProperties(j*64, i*64, 64, 64*3, this);
                }
            }
        }
    }
    public void update(){
        player.update();
        for(Stone stone:stones){
            stone.update();
        }
        for (Coin coin : coins) {
            coin.update();
        }
        for(Button button:buttons){
            button.update();
        }
        door.update();
    }
    public void render(){
        try {
            gc.clearRect(0, 0, 21*64, 12*64);
            // player.render();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for(Stone stone:stones){
            stone.render();
        }
        for (Enity coin : coins) {
            coin.render();
        }
        for(Button button:buttons){
            button.render();
        }
        door.render();
        for(Enity removedEnities:removedEnities){
            removedEnities.render();
        }
        player.render();  
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }
    public ArrayList<Enity> getRemovedEnities() {
        return removedEnities;
    }
    public void setRemovedEnities(ArrayList<Enity> removedEnities) {
        this.removedEnities = removedEnities;
    }
    public int[][] getMapData() {
        return mapData;
    }
    public void setMapData(int[][] mapData) {
        this.mapData = mapData;
    }
    public GraphicsContext getGc() {
        return gc;
    }
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    public ArrayList<Stone> getStones() {
        return stones;
    }
    public void setStones(ArrayList<Stone> stones) {
        this.stones = stones;
    }
    public ArrayList<Button> getButtons() {
        return buttons;
    }
    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }
    
}
