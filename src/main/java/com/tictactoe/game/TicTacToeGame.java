package com.tictactoe.game;

import javafx.scene.layout.Background;

public class TicTacToeGame {
    private char x = 'X';
    private char o = 'O';
    private char empty = ' ';
    private char [][] signTab;
    private Player computer = new ComputerPlayer();

    public TicTacToeGame (int matrixSize) {
        signTab = new char [matrixSize][matrixSize];
        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                this.signTab[i][j] = empty;
            }
        }
    }

    public char[][] getSignTab() {
        return signTab;
    }

    public void addMove (char sign, int row, int col) {

        if (sign == x) {
            this.signTab[row][col] = x;
        } else {
            this.signTab[row][col] = o;
        }
    }

    public char whoWin(){
        int size = signTab.length;

        //check rows
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[i][0];
            if (temp != ' ') {
                for (int j = 0 ; j < size ; j++) {
                    if (signTab[i][j] != temp) {
                        break;
                    }
                    if (j == size - 1) {
                        return temp;
                    }
                }
            }
        }

        //check columns
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[0][i];
            if (temp != ' ') {
                for (int j = 0 ; j < size ; j++) {
                    if (signTab[j][i] != temp) {
                        break;
                    }
                    if (j == size - 1) {
                        return temp;
                    }
                }
            }
        }

        //check diagonal
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[0][0];
            if (temp != ' ') {
                if (temp != signTab[i][i]) {
                    break;
                }
                if (i == size - 1) {
                    return temp;
                }
            }
        }

        //check anti diagonal
        for (int i = 0 ; i < size ; i++) {
            char temp = signTab[0][size - 1];
            if (temp != ' ') {
                if (temp != signTab[i][size - 1 - i]) {
                    break;
                }
                if (i == size - 1) {
                    return temp;
                }
            }
        }

        //check if every area is taken
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                if (signTab[i][j] == ' ') {
                    return 'n';
                }
            }
        }

        return 'd';
    }

    public void cleanTab(int matrixSize) {
        signTab = new char [matrixSize][matrixSize];
        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                this.signTab[i][j] = empty;
            }
        }
    }

    public Background computerMove (char sign, double dimension, int matrixSize,
                                    Coordinates coordinates) {
        return computer.getMove(sign, this.signTab, dimension, matrixSize, coordinates);
    }

    public String displayWinner (TicTacToeGame ticTacToeGame, char sign){
        String text = new String();
        char opponentChar = 'n';

        if (sign == 'X') {
            opponentChar = 'O';
        } else if (sign == 'O') {
            opponentChar = 'X';
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
}
