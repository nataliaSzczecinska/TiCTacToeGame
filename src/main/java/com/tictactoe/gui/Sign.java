package com.tictactoe.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Sign {

    private Image imageO = new Image("file:src/main/resources/OArea.jpg");
    private Image imageX = new Image("file:src/main/resources/XArea.jpg");

    public Background matrixChoice(char sign, double dimension, int matrixSize) {
        double signSize =  0.95 * dimension / matrixSize;
        BackgroundSize backgroundSize = new BackgroundSize(signSize,
                signSize,
                false,
                false,
                true,
                true);
        BackgroundImage backgroundImage;

        if (sign == 'X') {
            backgroundImage = new BackgroundImage(imageX,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);
        } else {
            backgroundImage = new BackgroundImage(imageO,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);
        }

        Background background = new Background(backgroundImage);

        return background;
    }
}