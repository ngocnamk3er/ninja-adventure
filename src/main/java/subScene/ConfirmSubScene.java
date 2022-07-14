package subScene;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.App;

public class ConfirmSubScene extends SubScene {
    private Pane pane;
    private Background background;
    private Button cancelButton;
    private Button confirmButton;
    public ConfirmSubScene() {
        super(new Group(),554,400);
        background = new Background(new BackgroundImage(new Image(ConfirmSubScene.class.getResourceAsStream("subMenu.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        pane = new Pane();
        pane.setBackground(background);
        setRoot(pane);
        setLayoutY(200);
        setLayoutX(400);
        setVisible(false);
        Background backgroundCancelBtn = new Background(new BackgroundImage(new Image(ConfirmSubScene.class.getResourceAsStream("cancelButton.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        cancelButton = new Button();
        cancelButton.setBackground(backgroundCancelBtn);
        cancelButton.setPrefWidth(100);
		cancelButton.setPrefHeight(100);
        cancelButton.setLayoutX(458);
        cancelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                setVisible(false);
			}
		});
        Background backgroundConfirmBtn = new Background(new BackgroundImage(new Image(ConfirmSubScene.class.getResourceAsStream("confirmButton.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        confirmButton = new Button();
        confirmButton.setBackground(backgroundConfirmBtn);
        confirmButton.setPrefWidth(100);
		confirmButton.setPrefHeight(100);
        confirmButton.setLayoutX(227);
        confirmButton.setLayoutY(260);
        Text text = new Text();
        text.setX(50);  
        text.setY(120);  
        text.setWrappingWidth(400);
        text.setFont(Font.loadFont(App.class.getResourceAsStream("m6x11.ttf"), 40));
        text.setFill(Color.WHITE);
        text.setText("Starting a new game will lose all old game data, do you want to continue ?");  
        pane.getChildren().addAll(cancelButton,text,confirmButton);
    }
    public Button getConfirmButton() {
        return confirmButton;
    }
    public void setConfirmButton(Button confirmButton) {
        this.confirmButton = confirmButton;
    }
    
}