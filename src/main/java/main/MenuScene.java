package main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.Group;
public class MenuScene extends Scene{
    private Pane pane;
    public MenuScene(MainStage mainStage) {
        super(new Group(),1344,768);
        pane = new Pane();
        Button startButton =  new Button("Start button");
        startButton.setPrefHeight(50);
        startButton.setPrefWidth(200);
        startButton.setLayoutY(200);
        startButton.setLayoutX(1344/2-100);
        //
        Button creditButton =  new Button("Credit Button");
        creditButton.setPrefHeight(50);
        creditButton.setPrefWidth(200);
        creditButton.setLayoutY(300);
        creditButton.setLayoutX(1344/2-100);
        //
        Button exitButton =  new Button("Exit Button");
        exitButton.setPrefHeight(50);
        exitButton.setPrefWidth(200);
        exitButton.setLayoutY(400);
        exitButton.setLayoutX(1344/2-100);
        //
        startButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                mainStage.nextScene(-1);
			}
		});
        pane.getChildren().addAll(startButton,exitButton,creditButton);
        setRoot(pane);
    }
}
