package com.tictactoe.game;

import com.tictactoe.display.Texts;
import java.util.Random;
import java.util.logging.Logger;

public class ComputerPlayer implements Player {
    private Random generateValue = new Random();
    private Texts texts = new Texts();
    protected final Logger log = Logger.getLogger(getClass().getName());

    private char temp = ' ';
    private int count;

    @Override
    public void getMove(char sign, char[][] tab,
                              double dimension, int matrixSize,
                              Coordinates coordinates, String difficultyLevel) {

        char opponentSign = sign;

        log.info("I am in getMove method in ComputerPlayerClass");

        if ('X' == sign) {
            opponentSign = 'O';
        } else if ('O' == sign) {
            opponentSign = 'X';
        } else {
            log.info("Wrong given sign.\nOpponent sign = given sign");
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
        int col = -1;

        for (int j = 0 ; j < size ; j++){
            count = 0;
            for (int i = 0 ; i < size ; i++) {
                if (tab[i][j] != ' ') {
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
        int row = -1;

        for (int i = 0 ; i < size ; i++){
            count = 0;
            for (int j = 0 ; j < size ; j++) {
                if (tab[i][j] != ' ') {
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

        for (int i = 0 ; i < size ; i++){
            if (tab[i][i] != ' ') {
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

        for (int i = 0 ; i < size ; i++){
            if (tab[i][size - 1 - i] != ' ') {
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
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
