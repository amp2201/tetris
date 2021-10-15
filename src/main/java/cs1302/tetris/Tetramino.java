package cs1302.tetris;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.Node;

public class Tetramino {

    private Rectangle[] blocks;     // the 4 blocks that make up a Tetramino

    private String shape;           // a Tetramino can be one of 7 shapes
    private int intShape;       
    private int nextShape;          // represents the next shape to be created
    private int size;               // length and width of one block
    private int[][] blockLocations; // keeps track of this Tetramino's blocks on the board


    private Random randy = new Random();

    /**
     * Constructs a Tetramino, whose shape is determined by @param next. A Tetramino consists of four blocks
     * arranged into different shapes.
     */
    public Tetramino(int next) {

        blockLocations = new int[4][2];
        blocks = new Rectangle[4];          // a Tetramino has 4 blocks
        size = 25;
        
        // create the 4 blocks that make up this Tetramino
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Rectangle();
            blocks[i].setWidth(size);
            blocks[i].setHeight(size);
            blocks[i].setStroke(Color.BLACK);
        }

        if (next == -1) {       // if this is the first Tetramino of the game
            intShape = randy.nextInt(7);
            nextShape = randy.nextInt(7);
        } else {                // else the shape of the Tetramino is already determined
            intShape = next;
            nextShape = randy.nextInt(7);
        }

        
        if (intShape == 0) {    // arranges the 4 blocks into a square shape

            shape = "yellow";
        
            // the first block starts at row 0, column 4
            blockLocations[0][0] = 0;
            blockLocations[0][1] = 4;

            // the second block starts at row 0, column 5
            blockLocations[1][0] = 0;
            blockLocations[1][1] = 5;

            // the third block starts at row 1, column 4
            blockLocations[2][0] = 1;
            blockLocations[2][1] = 4;

            // the fourth block starts at row 1, column 5
            blockLocations[3][0] = 1;
            blockLocations[3][1] = 5;

            for (int j = 0; j < 4; j++) {
                blocks[j].setFill(Color.YELLOW);
            }

        } else if (intShape == 1) { // arrange the four blocks in a horizontal line

            shape = "cyan";

            for (int i = 0; i < 4; i++) {
                blocks[i].setFill(Color.TURQUOISE);
            }

            // first block starts at row 0, column 3
            blockLocations[0][0] = 0;
            blockLocations[0][1] = 3;

            // second block starts at row 0, column 4
            blockLocations[1][0] = 0;
            blockLocations[1][1] = 4;

            // third block starts at row 0, column 5
            blockLocations[2][0] = 0;
            blockLocations[2][1] = 5;

            // fourth block starts at row 0, column 6
            blockLocations[3][0] = 0;
            blockLocations[3][1] = 6;

        } else if (intShape == 2) { // arrange the four blocks into a t-shape

            shape = "purple";

            for (int i = 0; i < 3; i++) {
                blocks[i].setFill(Color.DARKVIOLET);
            }

            blocks[3].setFill(Color.DARKVIOLET);

            // first block starts at row 1, column 3
            blockLocations[0][0] = 1;
            blockLocations[0][1] = 3;

            // second block starts at row 1, column 4
            blockLocations[1][0] = 1;
            blockLocations[1][1] = 4;

            // third block starts at row 1, column 5
            blockLocations[2][0] = 1;
            blockLocations[2][1] = 5;

            // fourth block starts at row 0, column 4
            blockLocations[3][0] = 0;
            blockLocations[3][1] = 4;

        } else if (intShape == 3) { // arrange blocks into an L shape

            shape = "blue";

            for (int i = 0; i < 3; i++) {

                blocks[i].setFill(Color.BLUE);

            }

            blocks[3].setFill(Color.BLUE);

            // set the starting positions of the blocks to be in an L shape
            blockLocations[0][0] = 0;
            blockLocations[0][1] = 3;

            blockLocations[1][0] = 1;
            blockLocations[1][1] = 3;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 4;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 5;

        } else if (intShape == 4) { // arrange the blocks into a mirrored L shape

            shape = "orange";

            for (int i = 0; i < 3; i++) {

                blocks[i].setFill(Color.DARKORANGE);
            }


            blocks[3].setFill(Color.DARKORANGE);

            // set the starting position of the blocks to be in a mirrored L shape
            blockLocations[0][0] = 0;
            blockLocations[0][1] = 5;

            blockLocations[1][0] = 1;
            blockLocations[1][1] = 3;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 4;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 5;

        } else if (intShape == 5) { // arrage the blocks into an S shape

            shape = "green";

            for (int i = 0; i < 4; i++) {
                blocks[i].setFill(Color.LIME);
            }

            blockLocations[0][0] = 0;
            blockLocations[0][1] = 4;

            blockLocations[1][0] = 0;
            blockLocations[1][1] = 5;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 3;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 4;

        } else if (intShape == 6) { // arrange the blocks into a Z shape

            shape = "red";

            for (int i = 0; i < 4; i++) {
                blocks[i].setFill(Color.RED);
            }

            blockLocations[0][0] = 0;
            blockLocations[0][1] = 4;

            blockLocations[1][0] = 0;
            blockLocations[1][1] = 5;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 5;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 6;
        }
    }

    /**
     * Constructs a Tetramino knowing what the shape to come after it will be, so that the next shape can
     * be displayed in the sidebar.
     */
    public Tetramino(int next, int next2) {

        blockLocations = new int[20][10];
        blocks = new Rectangle[4];
        size = 25;
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Rectangle();
            blocks[i].setWidth(size);
            blocks[i].setHeight(size);
            blocks[i].setStroke(Color.BLACK);
        }

        if (next == -1) {
            intShape = randy.nextInt(7);
            nextShape = randy.nextInt(7);
        } else {
            intShape = next;
            nextShape = next2;
        }

        //intShape = 6;
        if (intShape == 0) {

            shape = "yellow";

            //(0, 4)
            blockLocations[0][0] = 0;
            blockLocations[0][1] = 4;

            blockLocations[1][0] = 0;
            blockLocations[1][1] = 5;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 4;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 5;

            for (int j = 0; j < 4; j++) {
                blocks[j].setFill(Color.YELLOW);
            }


        } else if (intShape == 1) {

            shape = "cyan";

            for (int i = 0; i < 4; i++) {

                blocks[i].setFill(Color.TURQUOISE);
            }

            blockLocations[0][0] = 0;
            blockLocations[0][1] = 3;

            blockLocations[1][0] = 0;
            blockLocations[1][1] = 4;

            blockLocations[2][0] = 0;
            blockLocations[2][1] = 5;

            blockLocations[3][0] = 0;
            blockLocations[3][1] = 6;

        } else if (intShape == 2) {

            shape = "purple";

            for (int i = 0; i < 3; i++) {
                blocks[i].setFill(Color.DARKVIOLET);
            }

            blocks[3].setFill(Color.DARKVIOLET);

            blockLocations[0][0] = 1;
            blockLocations[0][1] = 3;

            blockLocations[1][0] = 1;
            blockLocations[1][1] = 4;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 5;

            blockLocations[3][0] = 0;
            blockLocations[3][1] = 4;

        } else if (intShape == 3) {

            shape = "blue";

            for (int i = 0; i < 3; i++) {

                blocks[i].setFill(Color.BLUE);

            }

            blocks[3].setFill(Color.BLUE);

            blockLocations[0][0] = 0;
            blockLocations[0][1] = 3;

            blockLocations[1][0] = 1;
            blockLocations[1][1] = 3;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 4;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 5;

        } else if (intShape == 4) {

            shape = "orange";

            for (int i = 0; i < 3; i++) {

                blocks[i].setFill(Color.DARKORANGE);
            }


            blocks[3].setFill(Color.DARKORANGE);

            blockLocations[0][0] = 0;
            blockLocations[0][1] = 5;

            blockLocations[1][0] = 1;
            blockLocations[1][1] = 3;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 4;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 5;

        } else if (intShape == 5) {

            shape = "green";

            for (int i = 0; i < 4; i++) {
                blocks[i].setFill(Color.LIME);
            }

            blockLocations[0][0] = 0;
            blockLocations[0][1] = 4;

            blockLocations[1][0] = 0;
            blockLocations[1][1] = 5;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 3;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 4;

        } else if (intShape == 6) {

            shape = "red";

            for (int i = 0; i < 4; i++) {
                blocks[i].setFill(Color.RED);
            }

            blockLocations[0][0] = 0;
            blockLocations[0][1] = 4;

            blockLocations[1][0] = 0;
            blockLocations[1][1] = 5;

            blockLocations[2][0] = 1;
            blockLocations[2][1] = 5;

            blockLocations[3][0] = 1;
            blockLocations[3][1] = 6;
        }
    }

    public Rectangle[] getBlocks() {

        return blocks;
    }

    public int[][] getInitialLocation() {

        return blockLocations;
    }

    public String getColor() {
        return shape;
    }

    public int getNextShape() {
        return nextShape;
    }

    public int getIntShape() {
        return intShape;
    }
}
