package com.tictactoe.game;

import com.tictactoe.graphicinterface.Sign;
import javafx.scene.layout.Background;
import java.util.Random;

public class ComputerPlayer implements Player{
    private Sign displaySign = new Sign();
    private Random generateValue = new Random();

    @Override
    public Background getMove(char sign, char [][] tab,
                              double dimension, int matrixSize, Coordinates coordinates) {
        if (sign == 'X') {
            sign = 'O';
        } else if (sign == 'O'){
            sign = 'X';
        }

        Background background = displaySign.matrixChoice(sign, dimension, matrixSize);

        do {
            coordinates.setColumn(generateValue.nextInt(matrixSize));
            coordinates.setRow(generateValue.nextInt(matrixSize));

            if (tab[coordinates.getRow()][coordinates.getColumn()] == ' ') {
                tab[coordinates.getRow()][coordinates.getColumn()] = sign;
                System.out.println("RandRow = " + coordinates.getRow() +
                        " randCol = " + coordinates.getColumn());
                return background;
            }
        } while(true);
    }
}
