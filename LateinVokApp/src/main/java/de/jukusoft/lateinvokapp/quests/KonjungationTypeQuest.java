package de.jukusoft.lateinvokapp.quests;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import java.util.HashMap;

import de.jukusoft.lateinvokapp.R;

/**
 * Created by Justin on 24.05.2014.
 */
public class KonjungationTypeQuest extends Quest {

    protected String lateinword = "";
    protected KONJUNGATIONTYPE correct_konj = null;

    public enum KONJUNGATIONTYPE {A_Konj, E_Konj, I_Konj, KonsKonj1, KonsKonj2};

    public KonjungationTypeQuest() {
        //Quest, bei dem man die richtige Konjungationsart eines lat. Verb auswählen muss.
    }

    @Override
    public void loadQuest () {
        if ((this.getExtra("lateinword")) != null) {
            this.lateinword = (String) this.getExtra("lateinword");
        } else {
            //Neues lat. Verb holen
            this.lateinword = "";
        }
    }

    @Override
    public void showQuest (Activity activity) {
        activity.setContentView(R.layout.quest_konjungationtype);
    }

    @Override
    public boolean check (Activity activity, HashMap<String, Object> data) {
        Button clicked_button = (Button) data.get("button");

        if (this.correct_konj.equals(clicked_button.getText())) {
            return true;
        } else {
            return false;
        }
    }

}
