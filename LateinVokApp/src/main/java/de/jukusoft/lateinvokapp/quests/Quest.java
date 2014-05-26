package de.jukusoft.lateinvokapp.quests;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import de.jukusoft.lateinvokapp.MainActivity;

/**
 * Created by Justin on 23.05.2014.
 */
public class Quest {

    protected HashMap<String,Object> extra = new HashMap<String,Object>();
    protected Activity activity = null;

    public Quest () {
        //
    }

    public void loadQuest () {
        //
    }

    public void showQuest (Activity activity) {
        //
    }

    public boolean check (Activity activity, View view, HashMap<String,Object> data) {
        return true;
    }

    public void putExtra (String str, Object obj) {
        this.extra.put(str, obj);
    }

    public Object getExtra (String str) {
        if (this.extra.containsKey(str)) {
            return this.extra.get(str);
        } else {
            return null;
        }
    }

    public class QuestClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Quest.this.check(Quest.this.activity, view, new HashMap<String,Object>());
        }
    }

}
