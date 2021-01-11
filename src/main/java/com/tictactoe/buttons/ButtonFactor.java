package com.tictactoe.buttons;

import com.tictactoe.display.Texts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonFactor {
    private Texts display = new Texts();
    private Image imageEmpty = new Image("file:src/main/resources/emptyArea.jpg");
    private static Font fontType = Font.font("Times New Roman", 16);

    public Button endGame() {
        Button button = new Button (display.endGame());

        button.setFont(fontType);
        button.setPrefHeight(50);
        button.setBackground(bottomButton());
        button.setOpaqueInsets(new Insets(10, 10, 10, 10));

        return button;
    }

    public Button playAgain() {
        Button button = new Button (display.playAgain());

        button.setFont(fontType);
        button.setPrefHeight(50);
        button.setBackground(bottomButton());
        button.setOpaqueInsets(new Insets(10, 10, 10, 10));

        return button;
    }

    public Button statistic() {
        Button button = new Button (display.statistic());

        button.setFont(fontType);
        button.setPrefHeight(50);
        button.setBackground(bottomButton());
        button.setOpaqueInsets(new Insets(10, 10, 10, 10));

        return button;
    }

    public Button clearStatistic() {
        Button button = new Button (display.clear());

        button.setFont(fontType);
        button.setPrefHeight(50);
        button.setBackground(bottomButton());
        button.setOpaqueInsets(new Insets(10, 10, 10, 10));

        return button;
    }

    public Button closeStatisticWindow() {
        Button button = new Button (display.close());

        button.setFont(fontType);
        button.setPrefHeight(50);
        button.setBackground(bottomButton());
        button.setOpaqueInsets(new Insets(10, 10, 10, 10));

        return button;
    }

    public Button matrixElement(double dimension, int matrixSize) {
        Button button = new Button();
        double signSize = signDimension(dimension, matrixSize);

        button.setPrefHeight(signSize);
        button.setPrefWidth(signSize);
        button.setAlignment(Pos.CENTER);
        button.setBackground(buttonBackground(signSize));

        return button;
    }

    public Button[][] matrixButton (double dimension, int matrixSize) {
        Button[][] button = new Button[matrixSize][matrixSize];

        for (int i = 0 ; i < matrixSize ; i++) {
            for (int j = 0 ; j < matrixSize ; j++) {
                button[i][j] = matrixElement(dimension, matrixSize);
            }
        }

        return button;
    }

    public RadioButton radioButtonTimesNewRoman16(String value, boolean isSelected) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.WHITE);

        RadioButton matrixSizeXSize = new RadioButton(value);
        matrixSizeXSize.setFont(Font.font ("Times New Roman", 16));
        matrixSizeXSize.setTextFill(colorPicker.getValue());
        matrixSizeXSize.setSelected(isSelected);
        matrixSizeXSize.setMaxHeight(10);

        return matrixSizeXSize;
    }

    public Background buttonBackground(double size) {
        BackgroundSize backgroundSize = new BackgroundSize(size,
                size,
                false,
                false,
                true,
                true);
        BackgroundImage backgroundImageButton = new BackgroundImage(imageEmpty,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);

        return new Background(backgroundImageButton);
    }

    public double signDimension(double dimension, int matrixSize) {
        return 0.95 * dimension / matrixSize;
    }

    public Background bottomButton(){
        BackgroundSize backgroundSize = new BackgroundSize(200,
                50,
                false,
                false,
                true,
                true);
        BackgroundImage backgroundImageButton = new BackgroundImage(imageEmpty,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        return new Background(backgroundImageButton);
    }
}
