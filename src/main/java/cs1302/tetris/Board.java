package cs1302.tetris;

import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class Board extends TilePane {

    private Rectangle[][] blanks;
    private Boolean[][] state;
    private Rectangle[][] content;
    private Group[][] holders;

    private Tetramino active;
    private Rectangle[] activeBlocks;
    private int[][] aL;

    private Timeline fallLine;

    public Board() {

        super();
        this.setPrefColumns(10);
        aL = new int[4][2];
        blanks = new Rectangle[20][10];
        activeBlocks = new Rectangle[4];
        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 10; j++) {

                blanks[i][j] = new Rectangle();
                blanks[i][j].setFill(Color.WHITE);
                blanks[i][j].setStroke(Color.SLATEGRAY);
                blanks[i][j].setHeight(25);
                blanks[i][j].setWidth(25);
            }
        }

        state = new Boolean[20][10];
        content = new Rectangle[20][10];
        holders = new Group[20][10];

        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 10; j++) {

                state[i][j] = false;
                holders[i][j] = new Group();
                content[i][j] = blanks[i][j];
                holders[i][j].getChildren().add(content[i][j]);
                this.getChildren().add(holders[i][j]);
            }
        }
    } // constructor

    public void loadIn() {

        active = new Tetramino();
        aL = active.getInitialLocation();
        activeBlocks = active.getBlocks();
        // checks for gameover
        if (state[aL[0][0]][aL[0][1]] == true ||
        state[aL[1][0]][aL[1][1]] == true ||
        state[aL[2][0]][aL[2][1]] == true ||
        state[aL[3][0]][aL[3][1]]) {
            System.out.println("Game over");
            fallLine.stop();
            return;
        }
        content[aL[0][0]][aL[0][1]] = activeBlocks[0];
        content[aL[1][0]][aL[1][1]] = activeBlocks[1];
        content[aL[2][0]][aL[2][1]] = activeBlocks[2];
        content[aL[3][0]][aL[3][1]] = activeBlocks[3];
        updateBoard();
    }

    public void updateBoard() {

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {

                holders[i][j].getChildren().clear();
                holders[i][j].getChildren().add(content[i][j]);
            }
        }
    }


    public void fall() {

        if (aL[0][0] == 19 || aL[1][0] == 19 || aL[2][0] == 19 || aL[3][0] == 19 ||
        state[aL[0][0] + 1][aL[0][1]] == true || // if there is a piece below a block, don't fa
        state[aL[1][0] + 1][aL[1][1]] == true ||
        state[aL[2][0] + 1][aL[2][1]] == true ||
        state[aL[3][0] + 1][aL[3][1]] == true) {
            //fallLine.stop()
//            moveLeft();
            state[aL[0][0]][aL[0][1]] = true;
            state[aL[1][0]][aL[1][1]] = true;
            state[aL[2][0]][aL[2][1]] = true;
            state[aL[3][0]][aL[3][1]] = true;
            loadIn();
            //fallLine.play();
            return;
        }
        content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
        content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
        content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
        content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
        aL[0][0]++;
        aL[1][0]++;
        aL[2][0]++;
        aL[3][0]++;
        content[aL[0][0]][aL[0][1]] = activeBlocks[0];
        content[aL[1][0]][aL[1][1]] = activeBlocks[1];
        content[aL[2][0]][aL[2][1]] = activeBlocks[2];
        content[aL[3][0]][aL[3][1]] = activeBlocks[3];
        updateBoard();
    }

    public void play() {

        loadIn();
        //moveLeft();

        EventHandler<ActionEvent> faller = event -> fall();
        KeyFrame fallFrame = new KeyFrame(Duration.seconds(1), faller);
        fallLine = new Timeline();
        fallLine.setCycleCount(Timeline.INDEFINITE);
        fallLine.getKeyFrames().add(fallFrame);
        runNow(() -> fallLine.play());
        //fallLine.play();
    }

    public void moveLeft() {

        if (aL[0][1] == 0 || aL[1][1] == 0 || aL[2][1] == 0 || aL[3][1] == 0 ||
        state[aL[0][0]][aL[0][1] - 1] == true ||
        state[aL[1][0]][aL[1][1] - 1] == true ||
        state[aL[2][0]][aL[2][1] - 1] == true ||
        state[aL[3][0]][aL[3][1] - 1] == true) {

            return;
        }

        content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
        content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
        content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
        content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
        aL[0][1]--;
        aL[1][1]--;
        aL[2][1]--;
        aL[3][1]--;
        content[aL[0][0]][aL[0][1]] = activeBlocks[0];
        content[aL[1][0]][aL[1][1]] = activeBlocks[1];
        content[aL[2][0]][aL[2][1]] = activeBlocks[2];
        content[aL[3][0]][aL[3][1]] = activeBlocks[3];
        updateBoard();
    }

    public void moveRight() {

        if (aL[0][1] == 9 || aL[1][1] == 9 || aL[2][1] == 9 || aL[3][1] == 9 ||
        state[aL[0][0]][aL[0][1] + 1] == true ||
        state[aL[1][0]][aL[1][1] + 1] == true ||
        state[aL[2][0]][aL[2][1] + 1] == true ||
        state[aL[3][0]][aL[3][1] + 1] == true) {

            return;
        }

        content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
        content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
        content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
        content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
        aL[0][1]++;
        aL[1][1]++;
        aL[2][1]++;
        aL[3][1]++;
        content[aL[0][0]][aL[0][1]] = activeBlocks[0];
        content[aL[1][0]][aL[1][1]] = activeBlocks[1];
        content[aL[2][0]][aL[2][1]] = activeBlocks[2];
        content[aL[3][0]][aL[3][1]] = activeBlocks[3];
        updateBoard();
    }

    public static void runNow(Runnable target) {

        Thread t = new Thread(target);
        t.setDaemon(true);
        t.start();
    }
} // Board
