package com.tictactoe.display;


import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.*;

public class DisplayTexts {
    private ColorPicker colorPicker = new ColorPicker();

    public Text textDisplayArial20WithUnderline(String text){
        Text displayText = new Text();
        colorPicker.setValue(Color.WHITE);
        displayText.setFont(Font.font ("Arial", 20));
        displayText.setFill(colorPicker.getValue());
        displayText.setText(text);
        displayText.setUnderline(true);

        return displayText;
    }

    public Text textDisplayArial20(String text){
        Text displayText = new Text();
        colorPicker.setValue(Color.WHITE);
        displayText.setFont(Font.font ("Arial", 20));
        displayText.setFill(colorPicker.getValue());
        displayText.setText(text);

        return displayText;
    }

    public Text textDisplayTimesNewRoman16(String text){

        Text displayText = new Text();
        colorPicker.setValue(Color.WHITE);
        displayText.setFont(Font.font ("Times New Roman", 16));
        displayText.setFill(colorPicker.getValue());
        displayText.setText(text);

        return displayText;
    }

    public List<Text> statisticText(){
        return null;
    }
}
