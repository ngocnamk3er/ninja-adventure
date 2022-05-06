package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
// import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import main.MainStage;

public class LevelButton extends Button {
	private int levelValue;
	private Background background;
    public LevelButton(String text,MainStage mainStage){
        setText(text);
		levelValue = Integer.parseInt(text)-1;
		setPrefWidth(80);
		setPrefHeight(80);
		background = new Background(new BackgroundImage(new Image(LevelButton.class.getResourceAsStream("levelButton.png")),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
		setBackground(background);
		setFont(Font.loadFont(LevelButton.class.getResourceAsStream("m6x11.ttf"), 25));
        setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.setMakeMainScene(levelValue);
			}
		});
    }
}
