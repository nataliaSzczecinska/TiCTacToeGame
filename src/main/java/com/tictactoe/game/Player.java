package com.tictactoe.game;

import javafx.scene.layout.Background;

public interface Player {
    public void getMove(char sign, char [][] tab, double dimension,
                              int size, Coordinates coordinates, String difficultyLevel);
}
