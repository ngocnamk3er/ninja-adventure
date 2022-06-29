package buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuButton extends Button {
    private int width = 200;
    private int height = 50;

    public MenuButton(String text, Background menuButtonBackground) {
        setText(text);
        setPrefHeight(height);
        setPrefWidth(width);
        setBackground(menuButtonBackground);
        setLayoutX(1344 / 2 - width / 2);
        setFont(Font.loadFont(LevelButton.class.getResourceAsStream("m6x11.ttf"), 25));
        setTextFill(Color.WHITE);
    }
}
