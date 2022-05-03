package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.MakeMainScene;

public class LevelButton extends Button {
    private MakeMainScene makeMainScene;
    public LevelButton(String text,Stage stage){
        setText(text);
		setPrefWidth(190);
		setPrefHeight(49);
        setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(text);
                makeMainScene = new MakeMainScene(stage);
                stage.setScene(makeMainScene);
			}
		});
    }
}
