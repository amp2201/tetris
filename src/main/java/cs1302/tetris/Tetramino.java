package cs1302.tetris;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.Node;

public class Tetramino {

    private Rectangle[] blocks;

    private String shape;
    private int intShape;
    private int nextShape;
    private int size;
    private int[][] blockLocations;


    private Random randy = new Random();

    public Tetramino(int next) {

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
            nextShape = randy.nextInt(7);
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
}
