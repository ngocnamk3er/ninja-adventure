package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.GameLoop;
import main.MenuScene;

public class CloseGameButton extends Button {
    private GameLoop gameLoop;
    public CloseGameButton(String text,Stage stage, GameLoop gameLoop){
        setText(text);
		setPrefWidth(190);
		setPrefHeight(49);
        this.gameLoop = gameLoop;
        setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(text);
                gameLoop.interrupt();
                stage.setScene(new MenuScene(stage));
			}
		});
    }
}
