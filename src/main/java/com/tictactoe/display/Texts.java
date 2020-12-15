package com.tictactoe.display;

public class Texts {

    public String startGame (){
        return "Hallo! Let's start the game!";
    }

    public String askMatrixSize (){
        return "Set the size of matrix:  ";
    }

    public String endGame() {
        return "End the game";
    }

    public String playAgain() {
        return "Play again";
    }

    public String whatSign() {
        return "Choose your sign: ";
    }

    public String difficultyLevel(){
        return "Choose the difficulty level: ";
    }

    public String easy() {
        return " easy ";
    }

    public String middle() {
        return " middle ";
    }

    public String difficult(){
        return " difficult ";
    }

    public String statistic() {
        return "Show statistic";
    }

    public String close(){
        return "Close";
    }

    public String clearStatisticNumber() {
        return  "\nNumber of started games: " + 0 +
                "\nNumber of completed games: " + 0 +
                "\nNumber of games won: " + 0 +
                "\nNumber of games lost: " + 0 +
                "\nNumber of draws: " + 0;
    }

    public String currentStatisticTitle() {
        return "Current game statistic:";
    }

    public String allStatisticTitle(){
        return "All games statistic:";
    }

    public String clearStatisticText(){
        String text = allStatisticTitle() + clearStatisticNumber();
        return text;
    }

    public String clear(){
        return "Clear";
    }

    public String setGeneralStatistic(int allNumberOfWinPlayer, int allNumberOfWinComputer, int allNumberOfStartGames,
                               int allNumberOfEndGames, int allNumberOfDraws) {
        String text = allStatisticTitle();
        text += statisticNumbers(allNumberOfWinPlayer, allNumberOfWinComputer, allNumberOfStartGames,
                            allNumberOfEndGames, allNumberOfDraws);

        return text;
    }

    public String setAllStatistic(int numberOfWinPlayer, int numberOfWinComputer, int numberOfStartGames,
            int numberOfEndGames, int numberOfDraws, int allNumberOfWinPlayer, int allNumberOfWinComputer,
            int allNumberOfStartGames, int allNumberOfEndGames, int allNumberOfDraws) {
        String text = currentStatisticTitle();
        text += statisticNumbers(numberOfWinPlayer, numberOfWinComputer, numberOfStartGames,
                numberOfEndGames, numberOfDraws);
        text += "\n\n\n" + allStatisticTitle();
        text += statisticNumbers(allNumberOfWinPlayer, allNumberOfWinComputer, allNumberOfStartGames,
                allNumberOfEndGames, allNumberOfDraws);
        text += "\n";

        return text;
    }

    public String whatNext(){
        return "What will you do next?";
    }

    public String numberOfStarted(int number) {
        return  "\nNumber of started games: " + number;
    }

    public String numberOfEnd(int number) {
        return "\nNumber of completed games: " + number;
    }

    public String numberOfWin(int number){
        return "\nNumber of games won: " + number;
    }

    public String numberOfLost(int number){
        return "\nNumber of games lost: " + number;
    }

    public String numberOfDraws(int number) {
        return "\nNumber of draws: " + number;
    }

    public String statisticNumbers(int numberOfWinPlayer, int numberOfWinComputer,
                                          int numberOfStartGames, int numberOfEndGames, int numberOfDraws) {
        String text = numberOfStarted(numberOfStartGames);
        text += numberOfEnd(numberOfEndGames);
        text += numberOfWin(numberOfWinPlayer);
        text += numberOfLost(numberOfWinComputer);
        text += numberOfDraws(numberOfDraws);

        return text;
    }
}
