package com.tictactoe;

import com.tictactoe.graphicinterface.Board;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.*;


public class TicTacToeApplication extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Board board = new Board(stage);
            int dimension = board.getBottomHight() + board.getCentreWidth();

            Scene scene = new Scene(board,
                    dimension,
                    dimension);

            stage.setScene(scene);
            stage.setTitle("Tic-Tac-Toe");
            stage.centerOnScreen();
            stage.show();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
