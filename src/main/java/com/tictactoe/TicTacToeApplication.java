package com.tictactoe;

import com.tictactoe.fileservice.FileService;
import com.tictactoe.gui.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;


public class TicTacToeApplication extends Application{
    private final FileService file = new FileService();
    private Board board;
    private int numberOfWinPlayer = 0;
    private int numberOfWinComputer = 0;
    private int numberOfStartGames = 0;
    private int numberOfEndGames = 0;
    private int numberOfDraws = 0;
    private int allNumberOfWinPlayer = 0;
    private int allNumberOfWinComputer = 0;
    private int allNumberOfStartGames = 0;
    private int allNumberOfEndGames = 0;
    private int allNumberOfDraws = 0;

    public static void main(String[] args) {
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
        try {
            System.out.println("Start to save statistic...");

            try {
                file.fileReader();
                allNumberOfStartGames = file.getAllNumberOfStartGames();
                allNumberOfEndGames = file.getAllNumberOfEndGames();
                allNumberOfWinPlayer = file.getAllNumberOfWinPlayer();
                allNumberOfWinComputer = file.getAllNumberOfWinComputer();
                allNumberOfDraws = file.getAllNumberOfDraws();
            } catch (IOException exception) {
                System.out.println(exception);
            }

            System.out.println("numberOfWin = " + numberOfWinPlayer +
                    "\nnumberOfLost = " + numberOfWinComputer +
                    "\nnumberOfDarw = " + numberOfDraws +
                    "\nstarts = " + numberOfStartGames +
                    "\nends = " + numberOfEndGames +
                    "\n********************************" +
                    "\nnumberOfWin = " + allNumberOfWinPlayer +
                    "\nnumberOfLost = " + allNumberOfWinComputer +
                    "\nnumberOfDarw = " + allNumberOfDraws +
                    "\nstarts = " + allNumberOfStartGames +
                    "\nends = " + allNumberOfEndGames);
            System.out.println();

            numberOfWinPlayer = board.getMove().getWinGame();
            numberOfWinComputer = board.getMove().getLoseGame();
            numberOfStartGames = board.getMove().getStartGame();
            numberOfEndGames = board.getMove().getEndGame();
            numberOfDraws = board.getMove().getDrawGame();
            allNumberOfWinPlayer += numberOfWinPlayer;
            allNumberOfWinComputer += numberOfWinComputer;
            allNumberOfStartGames += numberOfStartGames;
            allNumberOfEndGames += numberOfEndGames;
            allNumberOfDraws += numberOfDraws;

            System.out.println("numberOfWin = " + numberOfWinPlayer +
                    "\nnumberOfLost = " + numberOfWinComputer +
                    "\nnumberOfDarw = " + numberOfDraws +
                    "\nstarts = " + numberOfStartGames +
                    "\nends = " + numberOfEndGames +
                    "\n********************************" +
                    "\nnumberOfWin = " + allNumberOfWinPlayer +
                    "\nnumberOfLost = " + allNumberOfWinComputer +
                    "\nnumberOfDarw = " + allNumberOfDraws +
                    "\nstarts = " + allNumberOfStartGames +
                    "\nends = " + allNumberOfEndGames);

            file.setStatistic(allNumberOfWinPlayer, allNumberOfWinComputer, allNumberOfStartGames,
                    allNumberOfEndGames, allNumberOfDraws);

        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            System.out.println("The stop application");
        }
    }
}
