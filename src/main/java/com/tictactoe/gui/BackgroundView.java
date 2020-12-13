package com.tictactoe.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackgroundView {
    private final static Image imageBackgroundCentre = new Image("file:src/main/resources/grayBackground.jpg");
    private final static Image imageBackgroundTopOrBottom = new Image("file:src/main/resources/darkGrayBackground.jpg");
    private final static BackgroundSize backgroundSizeCentre = new BackgroundSize(100, 100, true, true, true, true);
    private final static BackgroundImage backgroundImageCentre = new BackgroundImage(imageBackgroundCentre, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSizeCentre);
    private final static Background backgroundCentre = new Background(backgroundImageCentre);
    private final static BackgroundSize backgroundSizeTopOrBottom = new BackgroundSize(100, 100, true, true, true, true);
    private final static BackgroundImage backgroundImageTopOrBottom = new BackgroundImage(imageBackgroundTopOrBottom, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSizeTopOrBottom);
    private final static Background backgroundTopOrBottom = new Background(backgroundImageTopOrBottom);

    public Background getBackgroundCentre(){
        return backgroundCentre;
    }

    public Background getBackgroundTopOrBottom(){
        return backgroundTopOrBottom;
    }
}
