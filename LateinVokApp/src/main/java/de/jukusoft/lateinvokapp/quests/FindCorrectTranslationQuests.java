package de.jukusoft.lateinvokapp.quests;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import de.jukusoft.lateinvokapp.R;

/**
 * Created by Justin on 26.05.2014.
 */
public class FindCorrectTranslationQuests extends Quest {

    protected String lateinword = "";
    protected int correct_answer = 1;

    protected String correct_answer_str = "";

    protected List<String> answers = new ArrayList<String>();
    protected List<Button> buttons = new ArrayList<Button>();

    public FindCorrectTranslationQuests () {
        //
    }

    @Override
    public void loadQuest () {
        //Quest laden
        if (this.getExtra("lateinword") != null) {
            this.lateinword = (String) this.getExtra("lateinword");
        }

        if (this.getExtra("correct_answer") != null) {
            this.correct_answer_str = (String) this.getExtra("correct_answer");
        }

        if (this.getExtra("answerlist") != null) {
            this.answers = (List<String>) this.getExtra("answerlist");
        }
    }

    @Override
    public void showQuest (Activity activity) {
        //Buttons initialisieren
        this.buttons.add((Button) activity.findViewById(R.id.answer1));
        this.buttons.add((Button) activity.findViewById(R.id.answer2));
        this.buttons.add((Button) activity.findViewById(R.id.answer3));
        this.buttons.add((Button) activity.findViewById(R.id.answer4));

        //Liste mischen
        Collections.shuffle(this.answers);

        //Zufallszahl zwischen 0 und 3 erzeugen
        this.correct_answer = new Random().nextInt(4);

        int i = 0;

        for (Button button : this.buttons) {
            //onClick Listener hinzufügen
            button.setOnClickListener(new QuestOnClickListener(activity, i));

            i++;
        }

        i = 0;

        //Buttons beschriften
        for (Button button : this.buttons) {
            if (i == this.correct_answer) {
                button.setText(this.correct_answer_str);
            } else {
                button.setText(this.answers.get(0));
                this.answers.remove(0);
            }

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
