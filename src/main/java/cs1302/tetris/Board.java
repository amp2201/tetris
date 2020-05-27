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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Board extends TilePane {

    private Rectangle[][] blanks;
    private Boolean[][] state;
    private Rectangle[][] content;
    private Group[][] holders;

    private ImageView nextImage;
    private ImageView heldImage;
    private Tetramino active;
    private Rectangle[] activeBlocks;
    private int[][] aL;
    private Boolean canHold;

    private Timeline fallLine;
    private String orientation;

    private int nextInt;

    private KeyFrame fallFrame;

    private int held;
    private int clearedLines;
    private boolean speedUp;
    EventHandler<ActionEvent> faller;

    public Board(ImageView nextImg, ImageView heldImg) {

        super();
        clearedLines = 0;
        faller = event -> fall();
        nextImage = nextImg;
        heldImage = heldImg;
        held = -1;
        canHold = true;
        speedUp = true;
        this.setPrefColumns(10);
        aL = new int[4][2];
        blanks = new Rectangle[20][10];
        activeBlocks = new Rectangle[4];
        orientation = "";
        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 10; j++) {

                blanks[i][j] = new Rectangle();
                blanks[i][j].setFill(Color.WHITE);
                blanks[i][j].setStroke(Color.SLATEGRAY);
                blanks[i][j].setHeight(25);
                blanks[i][j].setWidth(25);
            }
        }
        /*
        blanks[0][3].setFill(Color.LIGHTCORAL);
        blanks[0][4].setFill(Color.LIGHTCORAL);
        blanks[0][5].setFill(Color.LIGHTCORAL);
        blanks[0][6].setFill(Color.LIGHTCORAL);
        blanks[1][3].setFill(Color.LIGHTCORAL);
        blanks[1][4].setFill(Color.LIGHTCORAL);
        blanks[1][5].setFill(Color.LIGHTCORAL);
        blanks[1][6].setFill(Color.LIGHTCORAL);
        */
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

    public void loadIn(int next) {

        active = new Tetramino(next);

        nextInt = active.getNextShape();
        if (nextInt == 0) {
            nextImage.setImage(new Image("file:resources/yellow.png"));
        } else if (nextInt == 1) {
            nextImage.setImage(new Image("file:resources/cyan.png"));
        } else if (nextInt == 2) {
            nextImage.setImage(new Image("file:resources/purple.png"));
        } else if (nextInt == 3) {
            nextImage.setImage(new Image("file:resources/blue.png"));
        } else if (nextInt == 4) {
           nextImage.setImage(new Image("file:resources/orange.png"));
        } else if (nextInt == 5) {
            nextImage.setImage(new Image("file:resources/green.png"));
        } else if (nextInt == 6) {
            nextImage.setImage(new Image("file:resources/red.png"));
        }
        aL = active.getInitialLocation();
        activeBlocks = active.getBlocks();
        orientation = "up";
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
            clearLines();
            canHold = true;
            loadIn(active.getNextShape());

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

        loadIn(-1);
        //moveLeft();


        fallFrame = new KeyFrame(Duration.seconds(1), faller);
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

    public void rotateLeftPurple() {

        int rowCenter = aL[1][0];
        int colCenter = aL[1][1];
        if (orientation.equals("up")) {

            if (aL[1][0] == 19 || state[rowCenter + 1][colCenter] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter;
                aL[2][0] = rowCenter - 1;
                aL[2][1] = colCenter;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter - 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "left";
            }
        } else if (orientation.equals("left")) {
            if (aL[1][1] == 9 || state[rowCenter][colCenter + 1] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter;
                aL[0][1] = colCenter + 1;
                aL[2][0] = rowCenter;
                aL[2][1] = colCenter - 1;
                aL[3][0] = rowCenter + 1;
                aL[3][1] = colCenter ;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "down";

            }
        } else if (orientation.equals("down")) {

            if (rowCenter == 0 || state[rowCenter - 1][colCenter] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter;
                aL[2][0] = rowCenter + 1;
                aL[2][1] = colCenter;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter + 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "right";
            }
        } else if (orientation.equals("right")) {
            if (colCenter == 0 || state[rowCenter][colCenter - 1] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter;
                aL[0][1] = colCenter - 1;
                aL[2][0] = rowCenter;
                aL[2][1] = colCenter + 1;
                aL[3][0] = rowCenter - 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "up";
            }
        }
    }

    public void rotateLeftCyan() {

        int rowCenter;
        int colCenter;
        if (orientation.equals("up")) {

            rowCenter = aL[2][0];
            colCenter = aL[2][1];
            if (rowCenter == 0 || state[rowCenter - 1][colCenter] == true ||
            state[rowCenter + 1][colCenter] == true || state[rowCenter + 2][colCenter] == true
                || rowCenter == 19 || rowCenter == 18) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 2;
                aL[0][1] = colCenter;
                aL[1][0] = rowCenter + 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter - 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "left";
            }
        } else if (orientation.equals("left")) {
            rowCenter = aL[1][0];
            colCenter = aL[1][1];

            if (colCenter == 9 || colCenter == 0 || colCenter == 1 ||
            state[rowCenter][colCenter - 2] == true || state[rowCenter][colCenter - 1] == true ||
            state[rowCenter][colCenter + 1] == true) {
                return;
            } else {
                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter;
                aL[0][1] = colCenter + 1;
                aL[2][0] = rowCenter;
                aL[2][1] = colCenter - 1;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter - 2;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "down";
            }
        } else if (orientation.equals("down")) {

            rowCenter = aL[2][0];
            colCenter = aL[2][1];

            if (rowCenter == 0 || rowCenter == 1 || rowCenter == 19 ||
            state[rowCenter - 2][colCenter] == true || state[rowCenter - 1][colCenter] == true ||
            state[rowCenter + 1][colCenter] == true) {

                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 2;
                aL[0][1] = colCenter;
                aL[1][0] = rowCenter - 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter + 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "right";
            }
        } else if (orientation.equals("right")) {

            rowCenter = aL[1][0];
            colCenter = aL[1][1];
            if (colCenter == 0 || colCenter == 9 || colCenter == 8 ||
                state[rowCenter][colCenter - 1] == true ||
                state[rowCenter][colCenter + 1] == true ||
                state[rowCenter][colCenter + 2] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter;
                aL[0][1] = colCenter - 1;
                aL[2][0] = rowCenter;
                aL[2][1] = colCenter + 1;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter + 2;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "up";
            }
        }
    }

    public void rotateLeftBlue() {

        int rowCenter = aL[2][0];
        int colCenter = aL[2][1];

        if (orientation.equals("up")) {

            if (rowCenter == 0 || rowCenter == 19 || state[rowCenter - 1][colCenter] == true
            || state[rowCenter + 1][colCenter] == true ||
            state[rowCenter + 1][colCenter - 1] == true) {
                return;
            } else {
                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter - 1;
                aL[1][0] = rowCenter + 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter - 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "left";
            }
        } else if (orientation.equals("left")) {

            if (colCenter == 0 || colCenter == 9 || state[rowCenter][colCenter - 1] == true ||
            state[rowCenter][colCenter + 1] == true || state[rowCenter + 1][colCenter + 1] == true){

                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter + 1;
                aL[1][0] = rowCenter;
                aL[1][1] = colCenter + 1;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter - 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "down";
            }
        } else if (orientation.equals("down")) {

            if (rowCenter == 0 || rowCenter == 19 || state[rowCenter - 1][colCenter] == true ||
            state[rowCenter - 1][colCenter + 1] == true || state[rowCenter + 1][colCenter] == true){

                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter + 1;
                aL[1][0] = rowCenter - 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter + 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "right";
            }
        } else if (orientation.equals("right")) {

            if (colCenter == 0 || state[rowCenter - 1][colCenter - 1] == true ||
            state[rowCenter][colCenter - 1] == true || state[rowCenter][colCenter + 1] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter - 1;
                aL[1][0] = rowCenter;
                aL[1][1] = colCenter - 1;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter + 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "up";
            }
        }
    }

    public void rotateLeftOrange() {

        int rowCenter = aL[2][0];
        int colCenter = aL[2][1];

        if (orientation.equals("up")) {

            if (rowCenter == 19 || state[rowCenter - 1][colCenter - 1] == true ||
            state[rowCenter - 1][colCenter] == true ||
            state[rowCenter + 1][colCenter] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter - 1;
                aL[1][0] = rowCenter + 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter - 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "left";
            }
        } else if (orientation.equals("left")) {

            if (colCenter == 9 || state[rowCenter + 1][colCenter - 1] == true ||
            state[rowCenter][colCenter - 1] || state[rowCenter][colCenter + 1]) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter - 1;
                aL[1][0] = rowCenter;
                aL[1][1] = colCenter + 1;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter - 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "down";
            }
        } else if (orientation.equals("down")) {

            if (rowCenter == 0 || state[rowCenter - 1][colCenter] == true ||
            state[rowCenter + 1][colCenter] || state[rowCenter + 1][colCenter + 1]) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter + 1;
                aL[1][0] = rowCenter - 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter + 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "right";
            }
        } else if (orientation.equals("right")) {

            if (colCenter == 0 || state[rowCenter][colCenter - 1] == true ||
            state[rowCenter][colCenter + 1] || state[rowCenter - 1][colCenter + 1]) {

                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter + 1;
                aL[1][0] = rowCenter;
                aL[1][1] = colCenter - 1;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter + 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "up";
            }
        }
    }

    public void rotateLeftGreen() {

        int rowCenter = aL[3][0];
        int colCenter = aL[3][1];

        if (orientation.equals("up")) {

            if (rowCenter == 19 || state[rowCenter - 1][colCenter - 1] == true ||
            state[rowCenter + 1][colCenter] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter;
                aL[0][1] = colCenter - 1;
                aL[1][0] = rowCenter - 1;
                aL[1][1] = colCenter - 1;
                aL[2][0] = rowCenter + 1;
                aL[2][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "left";
            }
        } else if (orientation.equals("left")) {

            if (colCenter == 9 || state[rowCenter][colCenter + 1] == true ||
            state[rowCenter + 1][colCenter - 1] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter;
                aL[1][0] = rowCenter + 1;
                aL[1][1] = colCenter - 1;
                aL[2][0] = rowCenter;
                aL[2][1] = colCenter + 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "down";
            }
        } else if (orientation.equals("down")) {

            if (rowCenter == 0 || state[rowCenter - 1][colCenter] == true ||
            state[rowCenter + 1][colCenter + 1] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter;
                aL[0][1] = colCenter + 1;
                aL[1][0] = rowCenter + 1;
                aL[1][1] = colCenter + 1;
                aL[2][0] = rowCenter - 1;
                aL[2][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "right";
            }
        } else if (orientation.equals("right")) {

            if (colCenter == 0 || state[rowCenter][colCenter - 1] == true ||
            state[rowCenter - 1][colCenter + 1] == true) {
                return;
            } else {
                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter;
                aL[1][0] = rowCenter - 1;
                aL[1][1] = colCenter + 1;
                aL[2][0] = rowCenter;
                aL[2][1] = colCenter - 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "up";
            }
        }
    }

    public void rotateLeftRed() {

        int rowCenter = aL[2][0];
        int colCenter = aL[2][1];
        if (orientation.equals("up")) {

            if (rowCenter == 19 || state[rowCenter][colCenter - 1] == true ||
            state[rowCenter + 1][colCenter - 1] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter - 1;
                aL[1][0] = rowCenter;
                aL[1][1] = colCenter - 1;
                aL[3][0] = rowCenter - 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "left";
            }
        } else if (orientation.equals("left")) {

            if (colCenter == 9 || state[rowCenter + 1][colCenter] == true ||
            state[rowCenter + 1][colCenter + 1] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter + 1;
                aL[0][1] = colCenter + 1;
                aL[1][0] = rowCenter + 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter - 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "down";
            }
        } else if (orientation.equals("down")) {

            if (rowCenter == 0 || state[rowCenter][colCenter + 1] == true ||
            state[rowCenter - 1][colCenter + 1] == true) {

                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter + 1;
                aL[1][0] = rowCenter;
                aL[1][1] = colCenter + 1;
                aL[3][0] = rowCenter + 1;
                aL[3][1] = colCenter;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "right";
            }
        } else if (orientation.equals("right")) {

            if (colCenter == 0 || state[rowCenter - 1][colCenter - 1] == true ||
            state[rowCenter - 1][colCenter] == true) {
                return;
            } else {

                content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
                content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
                content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
                content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
                aL[0][0] = rowCenter - 1;
                aL[0][1] = colCenter - 1;
                aL[1][0] = rowCenter - 1;
                aL[1][1] = colCenter;
                aL[3][0] = rowCenter;
                aL[3][1] = colCenter + 1;
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
                orientation = "up";
            }
        }
    } // rotateLeftRed()

    public String getActiveColor() {

        return active.getColor();
    }

    public void clearLines() {

        boolean clear = true;
        int tempClearedLines = clearedLines;
        for (int i = 0; i < 20; i++) {
            clear = true;
            for (int j = 0; j < 10; j++) {

                if (state[i][j] == false) {
                    clear = false;
                    break;
                }
            }
            if (clear) {
                clearedLines++;
                for (int k = i; k > 0; k--) {

                    for (int z = 0; z < 10; z++) {
                        content[k][z] = content[k - 1][z];
                        state[k][z] = state[k - 1][z];
                        blanks[k][z] = blanks[k - 1][z];
                    }
                }
                for (int h = 0; h < 10; h++) {
                    blanks[0][h] = new Rectangle();
                    blanks[0][h].setFill(Color.WHITE);
                    blanks[0][h].setStroke(Color.SLATEGRAY);
                    blanks[0][h].setHeight(25);
                    blanks[0][h].setWidth(25);
                    content[0][h] = blanks[0][h];
                    state[0][h] = false;
                }
                updateBoard();
                /*try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.print("");
                    }*/
                if (speedUp) {
                    if (tempClearedLines < 10 && clearedLines >= 10) {
                        fallFrame = new KeyFrame(Duration.seconds(.9), faller);
                    } else if (tempClearedLines < 20 && clearedLines >= 20) {
                        fallFrame = new KeyFrame(Duration.seconds(.8), faller);
                    } else if (tempClearedLines < 30 && clearedLines >= 30) {
                        fallFrame = new KeyFrame(Duration.seconds(.7), faller);
                    } else if (tempClearedLines < 40 && clearedLines >= 40) {
                        fallFrame = new KeyFrame(Duration.seconds(.6), faller);
                    } else if (tempClearedLines < 50 && clearedLines >= 50) {
                        fallFrame = new KeyFrame(Duration.seconds(.5), faller);
                    } else if (tempClearedLines < 60 && clearedLines >= 60) {
                        fallFrame = new KeyFrame(Duration.seconds(.4), faller);
                        speedUp = false;
                    }
                }
            }
        }
    }

    public Timeline getTimeline() {

        return fallLine;
    }

    public void hold() {

        if (canHold) {
            content[aL[0][0]][aL[0][1]] = blanks[aL[0][0]][aL[0][1]];
            content[aL[1][0]][aL[1][1]] = blanks[aL[1][0]][aL[1][1]];
            content[aL[2][0]][aL[2][1]] = blanks[aL[2][0]][aL[2][1]];
            content[aL[3][0]][aL[3][1]] = blanks[aL[3][0]][aL[3][1]];
            if (held == -1) {
                held = active.getIntShape();
                int tempNext = active.getNextShape();
                active = new Tetramino(tempNext);

                nextInt = active.getNextShape();
                if (nextInt == 0) {
                    nextImage.setImage(new Image("file:resources/yellow.png"));
                } else if (nextInt == 1) {
                    nextImage.setImage(new Image("file:resources/cyan.png"));
                } else if (nextInt == 2) {
                    nextImage.setImage(new Image("file:resources/purple.png"));
                } else if (nextInt == 3) {
                    nextImage.setImage(new Image("file:resources/blue.png"));
                } else if (nextInt == 4) {
                    nextImage.setImage(new Image("file:resources/orange.png"));
                } else if (nextInt == 5) {
                    nextImage.setImage(new Image("file:resources/green.png"));
                } else if (nextInt == 6) {
                    nextImage.setImage(new Image("file:resources/red.png"));
                }


                aL = active.getInitialLocation();
                activeBlocks = active.getBlocks();
                orientation = "up";
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
            } else {
                int tempHeld = active.getIntShape();
                active = new Tetramino(held, nextInt);
                held = tempHeld;
                aL = active.getInitialLocation();
                activeBlocks = active.getBlocks();
                orientation = "up";
                content[aL[0][0]][aL[0][1]] = activeBlocks[0];
                content[aL[1][0]][aL[1][1]] = activeBlocks[1];
                content[aL[2][0]][aL[2][1]] = activeBlocks[2];
                content[aL[3][0]][aL[3][1]] = activeBlocks[3];
                updateBoard();
            }
            canHold = false;
            if (held == 0) {
                heldImage.setImage(new Image("file:resources/yellow.png"));
            } else if (held == 1) {
                heldImage.setImage(new Image("file:resources/cyan.png"));
            } else if (held == 2) {
                heldImage.setImage(new Image("file:resources/purple.png"));
            } else if (held == 3) {
                heldImage.setImage(new Image("file:resources/blue.png"));
            } else if (held == 4) {
                heldImage.setImage(new Image("file:resources/orange.png"));
            } else if (held == 5) {
                heldImage.setImage(new Image("file:resources/green.png"));
            } else if (held == 6) {
                heldImage.setImage(new Image("file:resources/red.png"));
            }

        }
    }
} // Board
