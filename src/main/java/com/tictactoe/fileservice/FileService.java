package com.tictactoe.fileservice;

import com.tictactoe.display.Texts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.*;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    protected final Logger log = Logger.getLogger(getClass().getName());
    private final Texts texts = new Texts();
    private final File file = new File("statistic.txt");
    private int allNumberOfWinPlayer = 0;
    private int allNumberOfWinComputer = 0;
    private int allNumberOfStartGames = 0;
    private int allNumberOfEndGames = 0;
    private int allNumberOfDraws = 0;

    public void fileReader() throws IOException {
        try {
            Stream<String> fileLines = Files.lines(Path.of(file.getPath()));
            List<String> stringList = fileLines.collect(Collectors.toList());


            log.log(Level.INFO, "The reading file is started...");
            for (String list : stringList){
                log.info(list);
            }
            getAllValue(stringList);
            fileLines.close();

        } catch (Exception exception) {
            log.warning("There is an error! The file cannot be read");
            createClearStatistic();
        }
    }

    public void createClearStatistic() throws IOException {
        FileWriter writeStatisticFile = new FileWriter(file.getName());
        try {
            String clearStatisticText = texts.clearStatisticText();
            writeStatisticFile.write(clearStatisticText);
        } catch (Exception exception) {
            log.warning("There is an error! " + exception);
        } finally {
            writeStatisticFile.close();
        }
    }

    public void setStatistic(int allNumberOfWinPlayer, int allNumberOfWinComputer,
                             int allNumberOfStartGames,int allNumberOfEndGames, int allNumberOfDraws)
            throws IOException {

        FileWriter writeStatisticFile = new FileWriter(file.getName());
        try {
            String statisticText= texts.setGeneralStatistic(allNumberOfWinPlayer, allNumberOfWinComputer,
                    allNumberOfStartGames, allNumberOfEndGames, allNumberOfDraws);
            writeStatisticFile.write(statisticText);
        } catch (IOException exception) {
            log.warning("There is an error! " + exception);
        } finally {
            writeStatisticFile.close();
        }
    }

    public int getValue(String text) {
        int value = 0;
        int temp = 1;
        int textLength = text.length();
        char sign;

        for (int i = textLength - 1 ; i >= 0 ; i--){
            sign = text.charAt(i);
            if (sign >= '0' && sign <= '9') {
                value += ((int) sign - 48) * temp;
                temp *= 10;
            }
        }
        return value;
    }

    public void getAllValue(List<String> list){
        int listLength = list.size();

        for (int i = 1 ; i < listLength ; i++) {
            switch (i) {
                case 1:
                    this.allNumberOfStartGames = getValue(list.get(i));
                    break;
                case 2:
                    this.allNumberOfEndGames = getValue(list.get(i));
                    break;
                case 3:
                    this.allNumberOfWinPlayer = getValue(list.get(i));
                    break;
                case 4:
                    this.allNumberOfWinComputer = getValue(list.get(i));
                    break;
                case 5:
                    this.allNumberOfDraws = getValue(list.get(i));
                    break;
                default:
                    log.warning("Wrong file!");
            }
        }
    }

    public int getAllNumberOfWinPlayer() {
        return allNumberOfWinPlayer;
    }

    public int getAllNumberOfWinComputer() {
        return allNumberOfWinComputer;
    }

    public int getAllNumberOfStartGames() {
        return allNumberOfStartGames;
    }

    public int getAllNumberOfEndGames() {
        return allNumberOfEndGames;
    }

    public int getAllNumberOfDraws() {
        return allNumberOfDraws;
    }

}
