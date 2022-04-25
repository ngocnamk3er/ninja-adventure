package inputs;

import entities.Player;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.MakeMainScene;

public class SetKeyBoardInputs {
    private Player player;
    private Scene scene;
    public SetKeyBoardInputs(MakeMainScene makeMainScene){
        this.player=makeMainScene.getMapInteractionManager().getPlayer();
        this.scene=makeMainScene.getMainScene();
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()==KeyCode.A||event.getCode()==KeyCode.D){
                    //nếu đang giữ A rồi D rồi thả D thì A không chạy nx;
                    player.setRun(false);
                }
                if(event.getCode()==KeyCode.W){
                    player.setJump(false);
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
                        break;
                    case D:
                        player.setRun(true);
                        player.setRight(true);
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
