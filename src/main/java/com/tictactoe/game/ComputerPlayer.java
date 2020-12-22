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
    public void getMove(char sign, char[][] tab,
                              double dimension, int matrixSize,
                              Coordinates coordinates, String difficultyLevel) {

        char opponentSign = sign;

        System.out.println("I am in getMove method in ComputerPlayerClass");

        if ('X' == sign) {
            opponentSign = 'O';
        } else if ('O' == sign) {
            opponentSign = 'X';
        } else {
            System.out.println("Wrong given sign.\nOpponent sign = given sign");
        }

        if (texts.easy().equals(difficultyLevel)) {
            do {
                coordinates.setColumn(generateValue.nextInt(matrixSize));
                coordinates.setRow(generateValue.nextInt(matrixSize));

                if (' ' == tab[coordinates.getRow()][coordinates.getColumn()]) {
                    tab[coordinates.getRow()][coordinates.getColumn()] = opponentSign;
                    break;
                }
            } while (true);
        } else if (texts.middle().equals(difficultyLevel)) {
            if (checkingColumns(coordinates, tab, matrixSize) ||
                    checkingRows(coordinates, tab, matrixSize) ||
                    checkingDiagonal(coordinates, tab, matrixSize) ||
                    checkingAntiDiagonal(coordinates, tab, matrixSize)) {
                tab[coordinates.getRow()][coordinates.getColumn()] = opponentSign;
            } else {
                getMove(sign, tab, dimension, matrixSize, coordinates, texts.easy());
            }
        } else {
            getMove(sign, tab, dimension, matrixSize, coordinates, texts.middle());
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
                            //System.out.println("The condition for anti diagonal is true");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
