package com.tictactoe.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackgroundView {
    private static final Image imageBackgroundCentre = new Image("file:src/main/resources/grayBackground.jpg");
    private static final Image imageBackgroundTopOrBottom = new Image("file:src/main/resources/darkGrayBackground.jpg");
    private static final BackgroundSize backgroundSizeCentre = new BackgroundSize(100, 100, true, true, true, true);
    private static final BackgroundImage backgroundImageCentre = new BackgroundImage(imageBackgroundCentre, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSizeCentre);
    private static final Background backgroundCentre = new Background(backgroundImageCentre);
    private static final BackgroundSize backgroundSizeTopOrBottom = new BackgroundSize(100, 100, true, true, true, true);
    private static final BackgroundImage backgroundImageTopOrBottom = new BackgroundImage(imageBackgroundTopOrBottom, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSizeTopOrBottom);
    private static final Background backgroundTopOrBottom = new Background(backgroundImageTopOrBottom);

    public Background getBackgroundCentre(){
        return backgroundCentre;
    }

    public Background getBackgroundTopOrBottom(){
        return backgroundTopOrBottom;
    }
}
