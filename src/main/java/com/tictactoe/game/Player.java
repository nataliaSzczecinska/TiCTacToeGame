package com.tictactoe.game;

public interface Player {
    public void getMove(char sign, char [][] tab, double dimension,
                              int size, Coordinates coordinates, String difficultyLevel);
}
