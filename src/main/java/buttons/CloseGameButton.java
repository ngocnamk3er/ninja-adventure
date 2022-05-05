package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.MainStage;

public class CloseGameButton extends Button {
    public CloseGameButton(String text,MainStage mainStage){
        setText(text);
		setPrefWidth(190);
		setPrefHeight(49);
        setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.setMenuScene();
			}
		});
    }
}
