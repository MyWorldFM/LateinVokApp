package de.jukusoft.lateinvokapp.quests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 24.05.2014.
 */
public class QuestChooser {

    protected List<Quest> quests = new ArrayList<Quest>();

    public QuestChooser () {
        //
    }

    public void loadQuests () {
        //
    }

    public Quest getNextQuest () {
        Quest quest = this.quests.get(0);
        this.quests.remove(0);

        return quest;
    }

    public void addQuest (Quest quest) {
        this.quests.add(quest);
    }

}
