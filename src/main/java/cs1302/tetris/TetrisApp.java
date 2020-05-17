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
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;

public class TetrisApp extends Application {

    public void start(Stage stage) {



        HBox g = new HBox();

        ImageView imageNext = new ImageView("file:resources/yellow.png");
        imageNext.setFitWidth(100);
        imageNext.setPreserveRatio(true);
        Board board = new Board(imageNext);
        Button button = new Button("Start");
        g.getChildren().add(button);
        g.getChildren().add(board);

        VBox rightCol = new VBox();
        Text nextText = new Text("  Next");
        // ImageView imageNext = new ImageView("file:resources/yellow.png");
        nextText.setTextAlignment(TextAlignment.CENTER);
        nextText.setFont(new Font (25));
        rightCol.getChildren().addAll(nextText, imageNext);
        g.getChildren().add(rightCol);

        //Timeline timeline = board.getTimeline();
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

                //timeline.stop();
                board.getTimeline().jumpTo(Duration.seconds(.1));
                board.getTimeline().play();
            } else if (event.getCode() == KeyCode.W) {
                if (board.getActiveColor().equals("cyan")) {
                    board.rotateLeftCyan();
                } else if (board.getActiveColor().equals("purple")) {
                    board.rotateLeftPurple();
                } else if (board.getActiveColor().equals("blue")) {
                    board.rotateLeftBlue();
                } else if (board.getActiveColor().equals("orange")) {
                    board.rotateLeftOrange();
                } else if (board.getActiveColor().equals("green")) {
                    board.rotateLeftGreen();
                } else if (board.getActiveColor().equals("red")) {
                    board.rotateLeftRed();
                }
            }
        });
        stage.setTitle("Tetris!");
        stage.setResizable(true);


        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
