package main;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import buttons.MenuButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
public class MenuScene extends Scene{
    private Pane pane;
    public MenuScene(MainStage mainStage) {
        super(new Group(),1344,768);
        pane = new Pane();
        MenuButton startButton =  new MenuButton("Start");
        startButton.setLayoutY(200);
        //
        MenuButton creditButton =  new MenuButton("Credit");
        creditButton.setLayoutY(300);
        //
        MenuButton exitButton =  new MenuButton("Exit");
        exitButton.setLayoutY(400);
        //
        startButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.nextScene(-1);
			}
		});
        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.backScene();
			}
		});
        pane.getChildren().addAll(startButton,exitButton,creditButton);
        setRoot(pane);
    }
}
