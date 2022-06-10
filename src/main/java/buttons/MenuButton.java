package buttons;

import javafx.scene.control.Button;

public class MenuButton extends Button{
    private int width = 50;
    private int height = 200;
    public MenuButton(String text){
        super(text);
        setPrefHeight(width);
        setPrefWidth(height);
        setLayoutX(1344/2-height/2);
    }
}
