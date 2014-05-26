package de.jukusoft.lateinvokapp.quests;

/**
 * Created by Justin on 26.05.2014.
 */
public class QuestManager {

    protected static Quest currentQuest = null;
    protected static QuestChooser questChooser = null;
    protected static AllQuestsFinishedHandler allQuestsFinishedHandler = null;

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
        }
    }

    public static void setQuestChooser (QuestChooser questChooser) {
        QuestManager.questChooser = questChooser;
    }

    public static void setAllQuestsFinishedHandler (AllQuestsFinishedHandler allQuestsFinishedHandler) {
        QuestManager.allQuestsFinishedHandler = allQuestsFinishedHandler;
    }

    interface AllQuestsFinishedHandler {
        public void questsFinished ();
    }

}
