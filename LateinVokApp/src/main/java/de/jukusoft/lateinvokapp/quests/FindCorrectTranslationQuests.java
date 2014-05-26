package de.jukusoft.lateinvokapp.quests;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.jukusoft.lateinvokapp.R;

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
        //Buttons initialisieren
        this.buttons.add((Button) activity.findViewById(R.id.answer1));
        this.buttons.add((Button) activity.findViewById(R.id.answer2));
        this.buttons.add((Button) activity.findViewById(R.id.answer3));
        this.buttons.add((Button) activity.findViewById(R.id.answer4));

        int i = 0;

        for (Button button : this.buttons) {
            //onClick Listener hinzufügen
            button.setOnClickListener(new QuestOnClickListener(activity, i));

            i++;
        }
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

    class QuestOnClickListener implements View.OnClickListener {

        protected Activity activity = null;
        protected int buttonid = -1;

        public QuestOnClickListener (Activity activity, int buttonid) {
            this.activity = activity;
            this.buttonid = buttonid;
        }

        @Override
        public void onClick(View view) {
            HashMap<String,Object> data = new HashMap<String,Object>();
            data.put("buttonid", this.buttonid);

            boolean correct_answer = FindCorrectTranslationQuests.this.check(activity, view, data);

            if (correct_answer) {
                //Nächsten Quest aufrufen
                QuestManager.nextQuest();
            }
        }
    }

}
