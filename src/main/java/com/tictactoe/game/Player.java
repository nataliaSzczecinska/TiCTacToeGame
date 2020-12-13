package com.tictactoe.game;

import javafx.scene.layout.Background;

public interface Player {
    public Background getMove(char sign, char [][] tab, double dimension, int size, Coordinates coordinates);
}
