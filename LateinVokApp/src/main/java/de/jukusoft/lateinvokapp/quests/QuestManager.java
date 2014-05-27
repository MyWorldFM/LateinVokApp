package de.jukusoft.lateinvokapp.quests;

import android.app.Activity;

/**
 * Created by Justin on 26.05.2014.
 */
public class QuestManager {

    protected static Quest currentQuest = null;
    protected static QuestChooser questChooser = null;
    protected static AllQuestsFinishedHandler allQuestsFinishedHandler = null;
    protected static Activity activity = null;

    public static Quest getCurrentQuest () {
        return QuestManager.currentQuest;
    }

    public static void nextQuest () {
        //Ruft den nächsten Quest auf
        QuestManager.currentQuest = QuestManager.questChooser.getNextQuest();

        if (QuestManager.currentQuest == null) {
            if (QuestManager.allQuestsFinishedHandler != null) {
                QuestManager.allQuestsFinishedHandler.questsFinished();
            }
        } else {
            QuestManager.currentQuest.loadQuest();
            QuestManager.currentQuest.showQuest(QuestManager.activity);
        }
    }

    public static void setQuestChooser (QuestChooser questChooser) {
        QuestManager.questChooser = questChooser;
    }

    public static void setAllQuestsFinishedHandler (AllQuestsFinishedHandler allQuestsFinishedHandler) {
        QuestManager.allQuestsFinishedHandler = allQuestsFinishedHandler;
    }

    public static void startQuests (Activity activity) {
        QuestManager.activity = activity;
        QuestManager.nextQuest();
    }

    public static int countNextQuests () {
        return -1;
    }

    public interface AllQuestsFinishedHandler {
        public void questsFinished ();
    }

}
