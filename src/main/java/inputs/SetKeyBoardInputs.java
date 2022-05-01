package inputs;

import entities.Player;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mapinteraction.MapInteractionManager;

public class SetKeyBoardInputs {
    private Player player;
    private Scene scene;
    private boolean pressedA;
    private boolean pressedD;
    public SetKeyBoardInputs(MapInteractionManager mapInteractionManager){
        this.player=mapInteractionManager.getPlayer();
        this.scene=mapInteractionManager.getMakeMainScene().getMainScene();
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()==KeyCode.A){
                    pressedA = false;
                    if(pressedD == true){
                        player.setRight(true);
                    }
                }
                if(event.getCode()==KeyCode.D){
                    pressedD = false;
                    if(pressedA == true){
                        player.setRight(false);
                    }
                }
                if(event.getCode()==KeyCode.W){
                    player.setJump(false);
                }
                if(pressedA==false&&pressedD==false){
                    player.setRun(false);
                }
            }   
            
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        player.setJump(true);
                        // System.out.println(event.getCode());
                        break;
                    case A:
                        player.setRun(true);
                        player.setRight(false);
                        pressedA = true;
                        break;
                    case D:
                        player.setRun(true);
                        player.setRight(true);
                        pressedD = true;
                        break;
                    case J:
                        player.setAttacking2(true);
                        break;
                    default:
                        break;
                }
            }
            
        });
    }
}
