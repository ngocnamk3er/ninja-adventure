package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import main.MainStage;

public class CloseGameButton extends Button {
	private Background background;
    public CloseGameButton(MainStage mainStage){
		setPrefWidth(48);
		setPrefHeight(48);
		background = new Background(
		new BackgroundImage(
		new Image(CloseGameButton.class.getResourceAsStream("back.png"))
		,BackgroundRepeat.REPEAT,
		BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT));
		setBackground(background);
        setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.setMenuScene();
			}
		});
    }
}
