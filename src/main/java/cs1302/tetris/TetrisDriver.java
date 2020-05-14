package cs1302.tetris;

import javafx.application.Application;

public class TetrisDriver {

    public static void main(String[] args) {

        try {
            Application.launch(TetrisApp.class, args);
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}
