package com.tictactoe;

import com.tictactoe.fileservice.FileService;
import com.tictactoe.gui.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;
import java.util.logging.Logger;


public class TicTacToeApplication extends Application{
    private final FileService file = new FileService();
    protected final Logger log = Logger.getLogger(getClass().getName());

    private Board board;
    private int allNumberOfWinPlayer = 0;
    private int allNumberOfWinComputer = 0;
    private int allNumberOfStartGames = 0;
    private int allNumberOfEndGames = 0;
    private int allNumberOfDraws = 0;

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ImageRotator");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        try {
            board = new Board(stage);
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

    @Override
    public void stop() throws Exception {
            log.info("Start to save statistic...");

            try {
                file.fileReader();
                allNumberOfStartGames = file.getAllNumberOfStartGames();
                allNumberOfEndGames = file.getAllNumberOfEndGames();
                allNumberOfWinPlayer = file.getAllNumberOfWinPlayer();
                allNumberOfWinComputer = file.getAllNumberOfWinComputer();
                allNumberOfDraws = file.getAllNumberOfDraws();

                allNumberOfWinPlayer += board.getMove().getWinGame();
                allNumberOfWinComputer += board.getMove().getLoseGame();
                allNumberOfStartGames += board.getMove().getStartGame();
                allNumberOfEndGames += board.getMove().getEndGame();
                allNumberOfDraws += board.getMove().getDrawGame();

                file.setStatistic(allNumberOfWinPlayer, allNumberOfWinComputer, allNumberOfStartGames,
                        allNumberOfEndGames, allNumberOfDraws);
            } catch (IOException exception) {
                log.warning("There is an exception: " + exception);
            } finally {
            log.info("The stop application");
        }
    }
}
