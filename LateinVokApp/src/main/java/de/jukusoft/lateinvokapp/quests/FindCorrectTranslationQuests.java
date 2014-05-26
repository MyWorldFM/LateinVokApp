package de.jukusoft.lateinvokapp.quests;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Justin on 26.05.2014.
 */
public class FindCorrectTranslationQuests extends Quest {

    protected String lateinword = "";
    protected int correct_answer = 1;

    protected List<Button> buttons = new ArrayList<Button>();

    public FindCorrectTranslationQuests () {
        //
    }

    @Override
    public void loadQuest () {
        //Quest laden
    }

    @Override
    public void showQuest (Activity activity) {
        //
    }

    @Override
    public boolean check (Activity activity, View view, HashMap<String,Object> data) {
        if (Integer.parseInt((String) data.get("buttonid")) == this.correct_answer) {
            return true;
        } else {
            Button button = (Button) view;
            button.setBackgroundColor(Color.parseColor("#FF0000"));

            this.buttons.get(this.correct_answer).setBackgroundColor(Color.parseColor("#00FF00"));

            return false;
        }
    }

}
