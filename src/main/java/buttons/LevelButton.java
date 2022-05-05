package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.MainStage;

public class LevelButton extends Button {
    public LevelButton(String text,MainStage mainStage){
        setText(text);
		setPrefWidth(100);
		setPrefHeight(120);
        setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.setMakeMainScene(1);
			}
		});
    }
}
