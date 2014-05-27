package de.jukusoft.lateinvokapp.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.jukusoft.lateinvokapp.R;
import de.jukusoft.lateinvokapp.quests.FindCorrectTranslationQuests;
import de.jukusoft.lateinvokapp.quests.QuestChooser;
import de.jukusoft.lateinvokapp.quests.QuestManager;

public class QuestsActivity extends ActionBarActivity {

    protected QuestChooser questChooser = new QuestChooser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        this.questChooser.loadQuests();

        FindCorrectTranslationQuests quest = new FindCorrectTranslationQuests();
        quest.putExtra("lateinword", "appellare");
        quest.putExtra("correct_answer", "rufen, schreien");

        List<String> data = new ArrayList<String>();
        data.add("rennen");
        data.add("rufen");
        data.add("Furcht");

        quest.putExtra("answerlist", data);

        this.questChooser.addQuest(quest);
        QuestManager.setQuestChooser(this.questChooser);

        QuestManager.setAllQuestsFinishedHandler(new QuestManager.AllQuestsFinishedHandler() {
            @Override
            public void questsFinished() {
                Toast toast = Toast.makeText(QuestsActivity.this, "Fertig!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Quests starten
        QuestManager.startQuests(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quests, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
