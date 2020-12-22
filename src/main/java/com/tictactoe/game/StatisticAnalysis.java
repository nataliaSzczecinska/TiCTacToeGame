package com.tictactoe.game;

public class StatisticAnalysis {
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

    public void setStatisticOfGame(char playerSign, char winnerSign){
        if ('n' != winnerSign) {
            this.endGame++;
            System.out.println("Current endGames = " + endGame);
            if ('d' == winnerSign) {
                this.drawGame++;
                System.out.println("Current draws = " + drawGame);
            } else if (playerSign == winnerSign) {
                this.winGame++;
                System.out.println("Current won = " + winGame);
            } else {
                this.loseGame++;
                System.out.println("Current lost = " + loseGame);
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
