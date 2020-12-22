package com.tictactoe.game;

import com.tictactoe.gui.Sign;
import javafx.scene.control.Button;

public class TicTacToeGame {
    private final char x = 'X';
    private final char o = 'O';
    private final char empty = ' ';
    private char [][] signTab;
    private Player computer = new ComputerPlayer();
    private int size;
    private Sign displaySign = new Sign();


    public TicTacToeGame (int matrixSize) {
        this.size = matrixSize;
        signTab = new char [matrixSize][matrixSize];
        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                this.signTab[i][j] = empty;
            }
        }
    }

    public void addMove (char sign, int row, int col) {

        if (x == sign) {
            this.signTab[row][col] = x;
        } else {
            this.signTab[row][col] = o;
        }
    }

    public char whoWin(){
        if ('n' != checkColumns()){
            return checkColumns();
        }
        if ('n' != checkRows()) {
            return checkRows();
        }
        if ('n' != checkDiagonal()) {
            return checkDiagonal();
        }
        if ('n' != checkAntiDiagonal()) {
            return checkAntiDiagonal();
        }
        return checkNoEmptyAreas();
    }

    public void cleanTab(int matrixSize) {
        signTab = new char [matrixSize][matrixSize];
        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                this.signTab[i][j] = empty;
            }
        }
    }

    public void computerMove (char sign, double dimension, int matrixSize,
                                    Coordinates coordinates, String difficultyLevel,
                              Button [][]buttonTab, StatisticAnalysis statisticAnalysis) {
        char computerSign;
        if (x == sign) {
            computerSign = o;
        } else {
            computerSign = x;
        }

        System.out.println("There is a computer move");
        computer.getMove(sign, this.signTab, dimension, matrixSize,
                coordinates, difficultyLevel);
        buttonTab[coordinates.getRow()][coordinates.getColumn()]
                .setBackground(displaySign.matrixChoice(computerSign, dimension, matrixSize));

        statisticAnalysis.setPlayerMove(true);
        statisticAnalysis.setStatisticOfGame(sign, whoWin());
    }

    public void playerMove (char sign, double dimension, int matrixSize,
                                  Coordinates coordinates, StatisticAnalysis statisticAnalysis,
                                  Button [][] buttonTab) {
        if (x == this.signTab[coordinates.getRow()][coordinates.getColumn()]) {
            System.out.println("Incorrect move! This area has chosen before");

        } else if (o == this.signTab[coordinates.getRow()][coordinates.getColumn()]){
            System.out.println("Incorrect move! This area has chosen before.");

        } else {
            this.signTab[coordinates.getRow()][coordinates.getColumn()] = sign;
            buttonTab[coordinates.getRow()][coordinates.getColumn()]
                    .setBackground(displaySign.matrixChoice(sign, dimension, matrixSize));
            statisticAnalysis.setPlayerMove(false);
            System.out.println("Player move is " + statisticAnalysis.isPlayerMove());

            if (statisticAnalysis.getNumberOfMove() == 0) {
                statisticAnalysis.setNumberOfMove(1);
                statisticAnalysis.setStartGame(
                        statisticAnalysis.getStartGame() + 1);
            } else {
                statisticAnalysis.setNumberOfMove(2);
            }
        }

        statisticAnalysis.setStatisticOfGame(sign, whoWin());
    }

    public String displayWinner (TicTacToeGame ticTacToeGame, char sign){
        String text = new String();
        char opponentChar = 'n';

        if (x == sign) {
            opponentChar = o;
        } else if (o == sign) {
            opponentChar = x;
        } else {
            System.out.println("Given char is wrong");
        }

        if (sign == ticTacToeGame.whoWin()) {
            text = "CONGRATULATION! You win the game!";
        } else if (opponentChar == ticTacToeGame.whoWin()) {
            text = "Sadly, you lose";
        } else if ('d' == ticTacToeGame.whoWin()) {
            text = "There is a draw";
        } else {
            System.out.println("No one won. The game should not end");
        }
        
        return text;
    }

    /*public void displayGameTable(){
        for (int i = 0 ; i < signTab.length ; i ++) {
            System.out.print(" | ");
            for (int j = 0 ; j < signTab[i].length ; j++) {
                System.out.print(signTab[i][j] + " | ");
            }
            System.out.println();
        }
    }*/

    public char checkColumns(){
        //check columns
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[i][0];
            if (empty != temp) {
                for (int j = 0 ; j < size ; j++) {
                    if (temp != signTab[i][j]) {
                        break;
                    }
                    if (size - 1 == j) {
                        return temp;
                    }
                }
            }
        }
        return 'n';
    }

    public char checkRows(){
        //check rows
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[0][i];
            if (empty != temp) {
                for (int j = 0 ; j < size ; j++) {
                    if (signTab[j][i] != temp) {
                        break;
                    }
                    if (size - 1 == j) {
                        return temp;
                    }
                }
            }
        }
        return 'n';
    }

    public char checkAntiDiagonal(){
        //check anti diagonal
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[0][size - 1];
            if (empty != temp) {
                if (temp != signTab[i][size - 1 - i]) {
                    break;
                }
                if (size - 1 == i) {
                    return temp;
                }
            }
        }
        return 'n';
    }

    public char checkDiagonal(){
        //check diagonal
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[0][0];
            if (empty != temp) {
                if (temp != signTab[i][i]) {
                    break;
                }
                if (size - 1 == i) {
                    return temp;
                }
            }
        }
        return 'n';
    }

    public char checkNoEmptyAreas(){
        //check if every area is taken
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                if (empty == signTab[i][j]) {
                    return 'n';
                }
            }
        }

        return 'd';
    }

    public char[][] getSignTab() {
        return signTab;
    }
}
