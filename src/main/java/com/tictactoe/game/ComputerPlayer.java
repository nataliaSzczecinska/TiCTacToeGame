package com.tictactoe.game;

import com.tictactoe.display.Texts;
import com.tictactoe.gui.Sign;
import javafx.scene.layout.Background;
import java.util.Random;

public class ComputerPlayer implements Player {
    private Sign displaySign = new Sign();
    private Random generateValue = new Random();
    private Texts texts = new Texts();

    @Override
    public Background getMove(char sign, char[][] tab,
                              double dimension, int matrixSize,
                              Coordinates coordinates, String difficultyLevel) {

        char opponentSign = sign;

        if (sign == 'X') {
            opponentSign = 'O';
        } else if (sign == 'O') {
            opponentSign = 'X';
        } else {
            System.out.println("Wrong given sign.\nOpponent sign = sign");
        }

        Background background = displaySign.matrixChoice(opponentSign, dimension, matrixSize);

        if (difficultyLevel.equals(texts.easy())) {
            do {
                coordinates.setColumn(generateValue.nextInt(matrixSize));
                coordinates.setRow(generateValue.nextInt(matrixSize));

                if (tab[coordinates.getRow()][coordinates.getColumn()] == ' ') {
                    tab[coordinates.getRow()][coordinates.getColumn()] = opponentSign;
                    System.out.println("RandRow = " + coordinates.getRow() +
                            " randCol = " + coordinates.getColumn());
                    return background;
                }
            } while (true);
        } else if (difficultyLevel.equals(texts.middle())) {
            if (checkingColumns(coordinates, tab, matrixSize) ||
                    checkingRows(coordinates, tab, matrixSize) ||
                    checkingDiagonal(coordinates, tab, matrixSize) ||
                    checkingAntiDiagonal(coordinates, tab, matrixSize)) {
                System.out.println("In middle there is true\nCordinates: +" +
                        "row = " + coordinates.getRow() + " col = " + coordinates.getColumn());
                tab[coordinates.getRow()][coordinates.getColumn()] = opponentSign;
                return background;
            } else {
                return getMove(sign, tab, dimension, matrixSize, coordinates, texts.easy());
            }
        } else {
            return getMove(sign, tab, dimension, matrixSize, coordinates, texts.middle());
        }
    }

    private boolean checkingColumns(Coordinates coordinates, char [][] tab, int size){
        char temp = ' ';
        int count;
        int col = -1;

        for (int j = 0 ; j < size ; j++){
            count = 0;
            for (int i = 0 ; i < size ; i++) {
                if (tab[i][j] == ' ') {
                    continue;
                } else {
                    if (count == 0) {
                        temp = tab[i][j];
                        count++;
                    } else if (temp == tab[i][j]) {
                        count++;
                    }

                    if (count == size - 1){
                        col = j;
                        j = size;
                        break;
                    }
                }
            }
        }
        if (col != -1) {
            for (int i = 0 ; i < size ; i++) {
                if(tab[i][col] == ' ') {
                    coordinates.setRow(i);
                    coordinates.setColumn(col);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkingRows(Coordinates coordinates, char [][] tab, int size){
        char temp = ' ';
        int count;
        int row = -1;

        for (int i = 0 ; i < size ; i++){
            count = 0;
            for (int j = 0 ; j < size ; j++) {
                if (tab[i][j] == ' ') {
                    continue;
                } else {
                    if (count == 0) {
                        temp = tab[i][j];
                        count++;
                    } else if (temp == tab[i][j]) {
                        count++;
                    }

                    if (count == size - 1){
                        row = i;
                        i = size;
                        break;
                    }
                }
            }
        }
        if (row != -1) {
            for (int i = 0 ; i < size ; i++) {
                if(tab[row][i] == ' ') {
                    coordinates.setRow(row);
                    coordinates.setColumn(i);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkingDiagonal(Coordinates coordinates, char [][] tab, int size){
        char temp = ' ';
        int count = 0;

        for (int i = 0 ; i < size ; i++){
            if (tab[i][i] == ' ') {
                continue;
            } else {
                if (count == 0) {
                    temp = tab[i][i];
                    count++;
                } else if (temp == tab[i][i]) {
                    count++;
                }

                if (count == size - 1){
                    for (int j = 0 ; j < size ; j++) {
                        if(tab[j][j] == ' ') {
                            coordinates.setRow(j);
                            coordinates.setColumn(j);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkingAntiDiagonal(Coordinates coordinates, char [][] tab, int size){
        char temp = ' ';
        int count = 0;

        for (int i = 0 ; i < size ; i++){
            if (tab[i][size - 1 - i] == ' ') {
                continue;
            } else {
                if (count == 0) {
                    temp = tab[i][size - 1 - i];
                    count++;
                } else if (temp == tab[i][size - 1 - i]) {
                    count++;
                }
                if (count == size - 1){
                    for (int j = 0 ; j < size ; j++) {
                        if(tab[j][size - 1 - j] == ' ') {
                            coordinates.setRow(j);
                            coordinates.setColumn(size - 1 - j);
                            System.out.println("The condition for anti diagonal is true");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
