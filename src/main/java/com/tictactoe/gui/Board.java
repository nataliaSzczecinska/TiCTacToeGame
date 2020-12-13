package com.tictactoe.gui;

import com.tictactoe.display.DisplayTexts;
import com.tictactoe.display.Texts;
import com.tictactoe.game.Coordinates;
import com.tictactoe.game.TicTacToeGame;
import com.tictactoe.buttons.CreateButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Board extends BorderPane {

    private final static BackgroundView backgroundView = new BackgroundView();
    private int matrixSize = 3;
    private char winnerSign = 'n';
    private char sign = 'X';
    private int centreWidth = 500;
    private int bottomHight = 200;

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
                            break;
                        }
                    } while (true);

                    winnerSign = game.whoWin();
                    if ('n' != winnerSign) {
                        winnerSign = 'n';

                        Stage newWindow = new Stage();
                        Scene newScene = endGameWindow(newWindow, stage);

                        newWindow.setScene(newScene);
                        newWindow.setTitle("Tic-Tac-Toe");
                        newWindow.centerOnScreen();
                        newWindow.show();

                        game.cleanTab(matrixSize);
                    } else {
                        do {
                            System.out.println("Computer move");
                            Coordinates coordinates = new Coordinates();
                            Background computerBackgroundPlace =
                                    game.computerMove(sign, centreWidth, matrixSize,
                                            coordinates, difficulty);
                            System.out.println("ComputerRow = " + coordinates.getRow() +
                                    " computerColumn = " + coordinates.getColumn());
                            if (buttonTab[coordinates.getRow()][coordinates.getColumn()].equals(backgroundX)
                                || buttonTab[coordinates.getRow()][coordinates.getColumn()].equals(backgroundO)) {
                                System.out.println("In Board. Incorrect move! This area has chosen before.");
                                continue;
                            } else {
                                buttonTab[coordinates.getRow()][coordinates.getColumn()]
                                        .setBackground(computerBackgroundPlace);
                                game.displayGameTable();
                                break;
                            }
                        } while (true);
                    }

                    winnerSign = game.whoWin();
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

        bottom.getChildren().addAll(playAgain, endGame);

        return bottom;
    }

    private Scene endGameWindow (Stage thisStage, Stage mainStage){
        VBox displayWinner = new VBox();
        displayWinner.setBackground(backgroundView.getBackgroundTopOrBottom());
        displayWinner.setAlignment(Pos.CENTER);
        displayWinner.setPrefWidth(centreWidth);

        Text winner = displayTexts.textDisplayArial20(game.displayWinner(game, sign));
        Text whatIsNext = displayTexts.textDisplayArial20("What will you do next?");

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
            Scene newScene = endGameWindow(newWindow, stage);

            newWindow.setScene(newScene);
            newWindow.setTitle("Tic-Tac-Toe");
            newWindow.centerOnScreen();
            newWindow.show();

            game.cleanTab(matrixSize);
        }
    }
}
