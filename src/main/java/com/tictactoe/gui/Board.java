package com.tictactoe.gui;

import com.tictactoe.display.DisplayTexts;
import com.tictactoe.display.Texts;
import com.tictactoe.fileservice.FileService;
import com.tictactoe.game.Coordinates;
import com.tictactoe.game.TicTacToeGame;
import com.tictactoe.buttons.CreateButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class Board extends BorderPane {

    private final static BackgroundView backgroundView = new BackgroundView();
    private int matrixSize = 3;
    private char winnerSign = 'n';
    private char sign = 'X';
    private int centreWidth = 500;
    private int bottomHight = 200;
    private int numberOfWinPlayer = 0;
    private int numberOfWinComputer = 0;
    private int numberOfStartGames = 0;
    private int numberOfEndGames = 0;
    private int numberOfDraws = 0;

    private Texts texts = new Texts();
    private DisplayTexts displayTexts = new DisplayTexts();
    private CreateButton createButton = new CreateButton();
    private Sign displaySign = new Sign();
    private TicTacToeGame game = new TicTacToeGame(matrixSize);
    private String difficulty = texts.easy();
    private final Background backgroundX = displaySign.matrixChoice('X', centreWidth, matrixSize);
    private final Background backgroundO = displaySign.matrixChoice('O', centreWidth, matrixSize);

    public Board (Stage stage) {
        super();
        this.setCenter(createCentre(stage));
        this.setBottom(createBottom(stage));
        this.setTop(createTop(stage));
    }

    public int getNumberOfWinPlayer() {
        return numberOfWinPlayer;
    }

    public int getNumberOfWinComputer() {
        return numberOfWinComputer;
    }

    public int getNumberOfStartGames() {
        return numberOfStartGames;
    }

    public int getNumberOfEndGames() {
        return numberOfEndGames;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public int getCentreWidth() {
        return centreWidth;
    }

    public int getBottomHight() {
        return bottomHight;
    }

    private GridPane createCentre(Stage stage) {
        GridPane grid = new GridPane();
        double signSize = 0.8 * centreWidth;
        signSize = signSize/matrixSize;

        grid.setBackground(backgroundView.getBackgroundCentre());
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0.1 * signSize);
        grid.setVgap(0.1 * signSize);

        Button [][] buttonTab = createButton.matrixButton(centreWidth, matrixSize);

        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                grid.add(buttonTab[i][j], j, i);
            }
        }

        AtomicInteger moveNumber = new AtomicInteger();

        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                int finalI = i;
                int finalJ = j;
                buttonTab[i][j].setOnAction(action -> {
                    do {
                        if (buttonTab[finalI][finalJ].getBackground().equals(backgroundX) ||
                                buttonTab[finalI][finalJ].getBackground().equals(backgroundO) ||
                                game.getSignTab()[finalI][finalJ] == 'X' ||
                                game.getSignTab()[finalI][finalJ] == 'O') {
                            System.out.println("Incorrect move! This area has chosen before.");
                            try {
                                System.out.println("I am waiting for correct move");
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        } else {
                            buttonTab[finalI][finalJ].setBackground(
                                    displaySign.matrixChoice(sign, centreWidth, matrixSize));
                            game.addMove(sign, finalI, finalJ);
                            if (moveNumber.get() == 0) {
                                this.numberOfStartGames++;
                                moveNumber.getAndIncrement();
                            }
                            break;
                        }
                    } while (true);

                    winnerSign = game.whoWin();
                    if ('n' != winnerSign) {
                        setStatisticOfGame();
                        displayEndingWindow(stage);
                    } else {
                        do {
                            System.out.println("Computer move");
                            Coordinates coordinates = new Coordinates();
                            Background computerBackgroundPlace =
                                    game.computerMove(sign, centreWidth, matrixSize,
                                            coordinates, difficulty);
                            //System.out.println("ComputerRow = " + coordinates.getRow() +
                                    //" computerColumn = " + coordinates.getColumn());
                            if (buttonTab[coordinates.getRow()][coordinates.getColumn()].equals(backgroundX)
                                || buttonTab[coordinates.getRow()][coordinates.getColumn()].equals(backgroundO)) {
                                //System.out.println("In Board. Incorrect move! This area has chosen before.");
                                continue;
                            } else {
                                buttonTab[coordinates.getRow()][coordinates.getColumn()]
                                        .setBackground(computerBackgroundPlace);
                                //game.displayGameTable();
                                break;
                            }
                        } while (true);
                    }

                    winnerSign = game.whoWin();
                    setStatisticOfGame();
                    displayEndingWindow(stage);
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

        Text welcomeText = displayTexts.textDisplayArial20WithUnderline(texts.startGame());
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

        Text howManyPlaces = displayTexts.textDisplayTimesNewRoman16(texts.askMatrixSize());
        Text whatSign = displayTexts.textDisplayTimesNewRoman16(texts.whatSign());
        Text difficulty = displayTexts.textDisplayTimesNewRoman16(texts.difficultyLevel());

        ToggleGroup matrixButtons = new ToggleGroup();
        ToggleGroup signChoice = new ToggleGroup();
        ToggleGroup difficultyLevelGroup = new ToggleGroup();

        RadioButton matrix3x3 = createButton.radioButtonTimesNewRoman16(" 3 ", true);
        RadioButton matrix4x4 = createButton.radioButtonTimesNewRoman16(" 4 ", false);
        RadioButton matrix5x5 = createButton.radioButtonTimesNewRoman16(" 5 ", false);

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

        RadioButton signX = createButton.radioButtonTimesNewRoman16(" X ", true);
        RadioButton signO = createButton.radioButtonTimesNewRoman16(" O ", false);

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

        RadioButton easy = createButton.radioButtonTimesNewRoman16(texts.easy(), true);
        RadioButton middle = createButton.radioButtonTimesNewRoman16(texts.middle(), false);
        //RadioButton difficult = createButton.radioButtonTimesNewRoman16(texts.difficult(), false);

        easy.setToggleGroup(difficultyLevelGroup);
        middle.setToggleGroup(difficultyLevelGroup);
        //difficult.setToggleGroup(difficultyLevelGroup);

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

        /*difficult.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.difficulty = texts.middle();
            this.game = new TicTacToeGame(matrixSize);
            this.setCenter(createCentre(stage));
        });*/

        difficultyLevel.getChildren().addAll(difficulty, easy, middle);//, difficult);

        top.getChildren().add(difficultyLevel);

        return top;
    }

    private HBox createBottom(Stage stage) {

        HBox bottom = new HBox();
        bottom.setBackground(backgroundView.getBackgroundTopOrBottom());
        bottom.setAlignment(Pos.CENTER);
        bottom.setPrefWidth(bottomHight);
        Button statistic = createButton.statistic();
        Button endGame = createButton.endGame();
        Button playAgain = createButton.playAgain();

        endGame.setOnAction((action) -> {
            stage.close();
        });

        playAgain.setOnAction((action) -> {
            this.game.cleanTab(matrixSize);
            this.setCenter(createCentre(stage));
            System.out.println("Start new game");
        });

        statistic.setOnAction((action) -> {
            displayStatistic(stage);
        });

        bottom.getChildren().addAll(statistic, playAgain, endGame);

        return bottom;
    }

    private Scene endGameWindow (Stage thisStage, Stage mainStage){
        VBox displayWinner = new VBox();
        displayWinner.setBackground(backgroundView.getBackgroundTopOrBottom());
        displayWinner.setAlignment(Pos.CENTER);
        displayWinner.setPrefWidth(centreWidth);

        Text winner = displayTexts.textDisplayArial20(game.displayWinner(game, sign));
        Text whatIsNext = displayTexts.textDisplayArial20(texts.whatNext());

        HBox buttonsDown = new HBox();
        Button playAgain = createButton.playAgain();
        Button endGame = createButton.endGame();
        buttonsDown.setAlignment(Pos.CENTER);

        endGame.setOnAction((action) -> {
            thisStage.close();
            mainStage.close();
        });

        playAgain.setOnAction((action) -> {
            this.game.cleanTab(matrixSize);
            this.setCenter(createCentre(mainStage));
            System.out.println("Start new game");
            thisStage.close();
        });

        buttonsDown.getChildren().addAll(playAgain, endGame);
        displayWinner.getChildren().addAll(winner, whatIsNext, buttonsDown);

        Scene scene = new Scene(displayWinner);

        return scene;
    }

    public void displayEndingWindow(Stage stage){
        if ('n' != winnerSign) {
            winnerSign = 'n';
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
        Scene scene = new Scene(statisticLayout(thisStage, stage));
        return scene;
    }

    public VBox statisticLayout(Stage thisStage, Stage stage){
        VBox statisticVBox = new VBox();
        statisticVBox.setBackground(backgroundView.getBackgroundTopOrBottom());
        statisticVBox.setAlignment(Pos.CENTER);
        statisticVBox.setPrefWidth(centreWidth);
        FileService file = new FileService();

        try {
            file.fileReader();
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            System.out.println("Read statistic");
        }
        Text displayStatistic = displayTexts.textDisplayTimesNewRoman16(
                texts.setAllStatistic(this.numberOfWinPlayer, this.numberOfWinComputer,
                        this.numberOfStartGames, this.numberOfEndGames, this.numberOfDraws,
                        file.getAllNumberOfWinPlayer() + this.numberOfWinPlayer,
                        file.getAllNumberOfWinComputer() + this.numberOfWinComputer,
                        file.getAllNumberOfStartGames() + this.numberOfStartGames,
                        file.getAllNumberOfEndGames() + this.numberOfEndGames,
                        file.getAllNumberOfDraws() + this.numberOfDraws));

        HBox buttonPart = new HBox();
        buttonPart.setAlignment(Pos.CENTER);

        Button closeWindow = createButton.closeStatisticWindow();
        Button clearStatistic = createButton.clearStatistic();

        clearStatistic.setOnAction((action) -> {
            try {
                file.createClearStatistic();
                this.numberOfStartGames = 0;
                this.numberOfEndGames = 0;
                this.numberOfWinPlayer = 0;
                this.numberOfWinComputer = 0;
                this.numberOfDraws = 0;
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                displayStatistic(stage);
                thisStage.close();
            }
        });

        closeWindow.setOnAction((action) -> {
            thisStage.close();
        });

        buttonPart.getChildren().addAll(clearStatistic, closeWindow);
        statisticVBox.getChildren().addAll(displayStatistic, buttonPart);

        return statisticVBox;
    }

    private void setStatisticOfGame(){
        if ('n' != winnerSign) {
            this.numberOfEndGames++;
            System.out.println("Current endGames = " + numberOfEndGames);
            if ('d' == winnerSign) {
                this.numberOfDraws++;
                System.out.println("Current draws = " + numberOfDraws);
            } else if (sign == winnerSign) {
                this.numberOfWinPlayer++;
                System.out.println("Current won = " + numberOfWinPlayer);
            } else {
                this.numberOfWinComputer++;
                System.out.println("Current lost = " + numberOfWinComputer);
            }
        }
    }
}
