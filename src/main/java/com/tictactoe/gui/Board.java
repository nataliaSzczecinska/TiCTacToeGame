package com.tictactoe.gui;

import com.tictactoe.display.TextFactor;
import com.tictactoe.display.Texts;
import com.tictactoe.fileservice.FileService;
import com.tictactoe.game.Coordinates;
import com.tictactoe.game.EnumResults;
import com.tictactoe.game.StatisticAnalysis;
import com.tictactoe.game.TicTacToeGame;
import com.tictactoe.buttons.ButtonFactor;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;


public class Board extends BorderPane {

    private static final int CENTRE_WIDTH = 500;
    private static final int BOTTOM_HIGHT = 200;
    private final Texts texts = new Texts();
    private final TextFactor textFactor = new TextFactor();
    protected final Logger log = Logger.getLogger(getClass().getName());

    private int matrixSize = 3;

    private static  final BackgroundView backgroundView = new BackgroundView();

    private static StatisticAnalysis move = new StatisticAnalysis();
    private char sign = 'X';

    private ButtonFactor buttonFactor = new ButtonFactor();
    private TicTacToeGame game = new TicTacToeGame(matrixSize);
    private String difficulty = texts.easy();

    public Board (Stage stage) {
        super();
        this.setCenter(createCentre(stage));
        this.setBottom(createBottom(stage));
        this.setTop(createTop(stage));
    }

    private GridPane createCentre(Stage stage) {
        GridPane grid = new GridPane();
        double signSize = (0.8 * CENTRE_WIDTH) / matrixSize;
        move.setNumberOfMove(0);
        move.setPlayerMove(true);

        grid.setBackground(backgroundView.getBackgroundCentre());
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0.1 * signSize);
        grid.setVgap(0.1 * signSize);

        Button [][] buttonTab = buttonFactor.matrixButton(CENTRE_WIDTH, matrixSize);

        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                grid.add(buttonTab[i][j], j, i);
            }
        }

        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                buttonTab[i][j].setOnAction(action -> {
                    do {
                        //Real Player Move
                        game.playerMove(sign, CENTRE_WIDTH, matrixSize, coordinates, move,
                                buttonTab);
                    } while (move.isPlayerMove());
                    log.info("The realPlayer move is end");
                    if (!isGameEnd()) {
                        log.info("The computerPlayer move...");
                        game.computerMove(sign, CENTRE_WIDTH, matrixSize,
                                coordinates, difficulty, buttonTab, move);
                    }
                    displayEndingWindow(game.whoWin(), stage);
                });
            }
        }
        return grid;
    }

    private VBox createTop(Stage stage) {
        VBox top = new VBox();
        top.setBackground(backgroundView.getBackgroundTopOrBottom());
        top.setAlignment(Pos.CENTER);
        top.setPrefWidth(200);
        top.setSpacing(10);

        Text welcomeText = textFactor.textDisplayArial20WithUnderline(texts.startGame());
        top.getChildren().add(welcomeText);

        HBox matrixAsk = new HBox();
        matrixAsk.setAlignment(Pos.CENTER);
        matrixAsk.setSpacing(10);

        HBox signAsk = new HBox();
        signAsk.setAlignment(Pos.CENTER);
        signAsk.setSpacing(10);

        HBox difficultyLevel = new HBox();
        difficultyLevel.setAlignment(Pos.CENTER);
        difficultyLevel.setSpacing(10);

        Text howManyPlaces = textFactor.textDisplayTimesNewRoman16(texts.askMatrixSize());
        Text whatSign = textFactor.textDisplayTimesNewRoman16(texts.whatSign());
        Text difficultyChoice = textFactor.textDisplayTimesNewRoman16(texts.difficultyLevel());

        ToggleGroup matrixButtons = new ToggleGroup();
        ToggleGroup signChoice = new ToggleGroup();
        ToggleGroup difficultyLevelGroup = new ToggleGroup();

        RadioButton matrix3x3 = buttonFactor.radioButtonTimesNewRoman16(" 3 ", true);
        RadioButton matrix4x4 = buttonFactor.radioButtonTimesNewRoman16(" 4 ", false);
        RadioButton matrix5x5 = buttonFactor.radioButtonTimesNewRoman16(" 5 ", false);

        matrix3x3.setToggleGroup(matrixButtons);
        matrix4x4.setToggleGroup(matrixButtons);
        matrix5x5.setToggleGroup(matrixButtons);

        matrix3x3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.matrixSize = 3;
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });

        matrix4x4.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.matrixSize = 4;
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });

        matrix5x5.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.matrixSize = 5;
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });

        matrixAsk.getChildren().add(howManyPlaces);
        matrixAsk.getChildren().addAll(matrix3x3, matrix4x4, matrix5x5);

        top.getChildren().add(matrixAsk);

        RadioButton signX = buttonFactor.radioButtonTimesNewRoman16(" X ", true);
        RadioButton signO = buttonFactor.radioButtonTimesNewRoman16(" O ", false);

        signX.setToggleGroup(signChoice);
        signO.setToggleGroup(signChoice);

        signX.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.sign = 'X';
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });

        signO.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.sign = 'O';
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });

        signAsk.getChildren().add(whatSign);
        signAsk.getChildren().addAll(signX, signO);

        top.getChildren().add(signAsk);

        RadioButton easy = buttonFactor.radioButtonTimesNewRoman16(texts.easy(), true);
        RadioButton middle = buttonFactor.radioButtonTimesNewRoman16(texts.middle(), false);

        easy.setToggleGroup(difficultyLevelGroup);
        middle.setToggleGroup(difficultyLevelGroup);

        easy.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.difficulty = texts.easy();
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });

        middle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.difficulty = texts.middle();
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });

        difficultyLevel.getChildren().addAll(difficultyChoice, easy, middle);

        top.getChildren().add(difficultyLevel);

        return top;
    }

    private HBox createBottom(Stage stage) {

        HBox bottom = new HBox();
        bottom.setBackground(backgroundView.getBackgroundTopOrBottom());
        bottom.setAlignment(Pos.CENTER);
        bottom.setPrefWidth(BOTTOM_HIGHT);
        Button statistic = buttonFactor.statistic();
        Button endGame = buttonFactor.endGame();
        Button playAgain = buttonFactor.playAgain();

        endGame.setOnAction(action -> stage.close());

        playAgain.setOnAction(action -> {
            this.game.cleanTab(matrixSize);
            this.setCenter(createCentre(stage));
            log.info("Start new game");
        });

        statistic.setOnAction(action -> displayStatistic(stage));

        bottom.getChildren().addAll(statistic, playAgain, endGame);

        return bottom;
    }

    private Scene endGameWindow (Stage thisStage, Stage mainStage){
        VBox displayWinner = new VBox();
        displayWinner.setBackground(backgroundView.getBackgroundTopOrBottom());
        displayWinner.setAlignment(Pos.CENTER);
        displayWinner.setPrefWidth(CENTRE_WIDTH);

        Text winner = textFactor.textDisplayArial20(game.displayWinner(game, sign));
        Text whatIsNext = textFactor.textDisplayArial20(texts.whatNext());

        HBox buttonsDown = new HBox();
        Button playAgain = buttonFactor.playAgain();
        Button endGame = buttonFactor.endGame();
        buttonsDown.setAlignment(Pos.CENTER);

        endGame.setOnAction(action -> {
            thisStage.close();
            mainStage.close();
        });

        playAgain.setOnAction(action -> {
            this.game.cleanTab(matrixSize);
            this.setCenter(createCentre(mainStage));
            log.info("Start new game");
            thisStage.close();
        });

        buttonsDown.getChildren().addAll(playAgain, endGame);
        displayWinner.getChildren().addAll(winner, whatIsNext, buttonsDown);

        return new Scene(displayWinner);
    }

    public void displayEndingWindow(EnumResults winnerSign, Stage stage){
        if (EnumResults.n != winnerSign) {
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(stage);
            Scene newScene = endGameWindow(newWindow, stage);

            newWindow.setScene(newScene);
            newWindow.setTitle("Tic-Tac-Toe");
            newWindow.centerOnScreen();
            newWindow.show();

            game.cleanTab(matrixSize);
        }
    }

    public void displayStatistic(Stage stage){
        Stage statisticWindow = new Stage();
        statisticWindow.initModality(Modality.WINDOW_MODAL);
        statisticWindow.initOwner(stage);

        Scene statisticScene = displayStatisticWindow(statisticWindow,stage);

        statisticWindow.setScene(statisticScene);
        statisticWindow.show();
    }

    public Scene displayStatisticWindow(Stage thisStage, Stage stage){
        return new Scene(statisticLayout(thisStage, stage));
    }

    public VBox statisticLayout(Stage thisStage, Stage stage){
        VBox statisticVBox = new VBox();
        statisticVBox.setBackground(backgroundView.getBackgroundTopOrBottom());
        statisticVBox.setAlignment(Pos.CENTER);
        statisticVBox.setPrefWidth(CENTRE_WIDTH);
        FileService file = new FileService();

        try {
            file.fileReader();
        } catch (IOException exception) {
            log.warning("There is an exception: " + exception);
        } finally {
            log.info("Read statistic");
        }
        Text displayStatistic = textFactor.textDisplayTimesNewRoman16(
                texts.setAllStatistic(move.getWinGame(), move.getLoseGame(),
                        move.getStartGame(), move.getEndGame(), move.getDrawGame(),
                        file.getAllNumberOfWinPlayer() + move.getWinGame(),
                        file.getAllNumberOfWinComputer() + move.getLoseGame(),
                        file.getAllNumberOfStartGames() + move.getStartGame(),
                        file.getAllNumberOfEndGames() + move.getEndGame(),
                        file.getAllNumberOfDraws() + move.getDrawGame()));

        HBox buttonPart = new HBox();
        buttonPart.setAlignment(Pos.CENTER);

        Button closeWindow = buttonFactor.closeStatisticWindow();
        Button clearStatistic = buttonFactor.clearStatistic();

        clearStatistic.setOnAction(action -> {
            try {
                file.createClearStatistic();
                move.setDrawGame(0);
                move.setWinGame(0);
                move.setLoseGame(0);
                move.setEndGame(0);
                move.setStartGame(0);
                move.setNumberOfMove(0);
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                displayStatistic(stage);
                thisStage.close();
            }
        });

        closeWindow.setOnAction(action -> thisStage.close());

        buttonPart.getChildren().addAll(clearStatistic, closeWindow);
        statisticVBox.getChildren().addAll(displayStatistic, buttonPart);

        return statisticVBox;
    }

    private boolean isGameEnd() {
        return (EnumResults.n != game.whoWin());
    }

    public static StatisticAnalysis getMove() {
        return move;
    }

    public int getCentreWidth() {
        return CENTRE_WIDTH;
    }

    public int getBottomHight() {
        return BOTTOM_HIGHT;
    }
}
