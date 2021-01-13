package com.tictactoe.game;

import java.util.logging.Logger;

public class StatisticAnalysis {
    protected final Logger log = Logger.getLogger(getClass().getName());

    private boolean playerMove;
    private int numberOfMove;
    private int startGame;
    private int endGame;
    private int winGame;
    private int loseGame;
    private int drawGame;

    public StatisticAnalysis() {
        this.playerMove = true;
        this.numberOfMove = 0;
        this.startGame = 0;
        this.endGame = 0;
        this.startGame = 0;
        this.loseGame = 0;
        this.drawGame = 0;
    }

    public void setStatisticOfGame(char playerSign, EnumResults winnerSign) {

        if (EnumResults.n != winnerSign) {
            this.endGame++;
            log.info("Current endGames = " + endGame);
            switch (winnerSign) {
                case d: {
                    this.drawGame++;
                    log.info("Current draws = " + drawGame);
                    break;
                } case X: {
                    if (EnumResults.X.getSign() == playerSign) {
                        this.winGame++;
                        log.info("Current won = " + winGame);
                    } else {
                        this.loseGame++;
                        log.info("Current lost = " + loseGame);
                    }
                    break;
                }
                default: {
                    log.info("There is an error! Wrong wining sign!" +
                            "The Enum is " + winnerSign);
                }
            }
        }
    }

    public int getNumberOfMove() {
        return numberOfMove;
    }

    public void setNumberOfMove(int numberOfMove) {
        this.numberOfMove = numberOfMove;
    }

    public boolean isPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(boolean playerMove) {
        this.playerMove = playerMove;
    }

    public int getStartGame() {
        return startGame;
    }

    public void setStartGame(int startGame) {
        this.startGame = startGame;
    }

    public int getEndGame() {
        return endGame;
    }

    public void setEndGame(int endGame) {
        this.endGame = endGame;
    }

    public int getWinGame() {
        return winGame;
    }

    public void setWinGame(int winGame) {
        this.winGame = winGame;
    }

    public int getLoseGame() {
        return loseGame;
    }

    public void setLoseGame(int loseGame) {
        this.loseGame = loseGame;
    }

    public int getDrawGame() {
        return drawGame;
    }

    public void setDrawGame(int drawGame) {
        this.drawGame = drawGame;
    }
}
