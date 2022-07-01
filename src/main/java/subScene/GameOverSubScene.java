package subScene;

import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.App;

public class GameOverSubScene extends SubScene {
    private Pane pane;
    private Background background;
    private Button confirmButton;
    public GameOverSubScene() {
        super(new Group(),554,400);
        background = new Background(new BackgroundImage(new Image(ConfirmSubScene.class.getResourceAsStream("subMenu.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        pane = new Pane();
        pane.setBackground(background);
        setRoot(pane);
        setLayoutY(200);
        setLayoutX(400);
        setVisible(false);
        Background backgroundConfirmBtn = new Background(new BackgroundImage(new Image(ConfirmSubScene.class.getResourceAsStream("confirmButton.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        confirmButton = new Button();
        confirmButton.setBackground(backgroundConfirmBtn);
        confirmButton.setPrefWidth(100);
		confirmButton.setPrefHeight(100);
        confirmButton.setLayoutX(227);
        confirmButton.setLayoutY(260);
        Text text = new Text();
        text.setX(130);  
        text.setY(180);  
        text.setWrappingWidth(400);
        text.setFont(Font.loadFont(App.class.getResourceAsStream("m6x11.ttf"), 80));
        text.setFill(Color.WHITE);
        text.setText("GAME OVER");  
        pane.getChildren().addAll(text,confirmButton);
    }
    public Button getConfirmButton() {
        return confirmButton;
    }
    public void setConfirmButton(Button confirmButton) {
        this.confirmButton = confirmButton;
    }
    
}
