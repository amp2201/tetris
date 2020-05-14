package cs1302.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;

public class TetrisApp extends Application {

    public void start(Stage stage) {



        HBox g = new HBox();

        Board board = new Board();
        Button button = new Button("Start");
        g.getChildren().add(button);
        g.getChildren().add(board);


        EventHandler<ActionEvent> starter = event -> board.play();
        button.setOnAction(starter);
        Scene scene = new Scene(g);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                board.moveLeft();
            } else if (event.getCode() == KeyCode.D) {
                board.moveRight();
            } else if (event.getCode() == KeyCode.S) {
                board.fall();
            }
        });
        stage.setTitle("Tetris!");
        stage.setResizable(false);


        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
