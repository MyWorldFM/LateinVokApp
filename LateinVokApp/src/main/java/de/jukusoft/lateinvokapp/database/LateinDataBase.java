package de.jukusoft.lateinvokapp.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.NameList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Justin on 19.05.2014.
 */
public class LateinDataBase {

    //Globale Klasse, die ebenfalls Konstanten und alle Daten enthält
    protected int points = 0;
    protected String securekey = "";
    protected String key = "";
    protected boolean sync_points = false;

    //Username für die Highscore Liste
    protected String username = "";

    //MainActivity
    protected Activity activity = null;

    //Feedback
    protected List<String> feedback = new ArrayList<String>();

    protected Date lastSync = null;
    protected SharedPreferences prefs = null;

    protected static LateinDataBase instance = null;

    protected String server_url = "jukusoft.darkburn.eu/api.php?username=" + this.username + "&key=" + this.key + "&secretkey=" + this.securekey;

    public LateinDataBase (Activity activity) {
        LateinDataBase.instance = this;
        this.prefs = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public static LateinDataBase getInstance () {
        return LateinDataBase.instance;
    }

    public int getPoints () {
        return this.points;
    }

    public void setPoints (int points) {
        this.points = points;

        //Punktzahl speichern
        this.prefs.edit().putInt("points", points).commit();

        //Service anstoßen, damit dieser die Punktzahl synchronisiert
        this.sync_points = true;
    }

    public void sync () {
        if (this.sync_points) {
            //Neue Punktzahl senden
        }

        if (this.feedback.size() > 0) {
            //Feedback senden
            this.sendFeedback();
        }

        //SyncDate setzen
        this.lastSync = new GregorianCalendar().getTime();
    }

    public void sendFeedback () {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                // Create a new HttpClient and Post Header
                HttpClient httpclient = new DefaultHttpClient();

                HttpPost httppost = new HttpPost(LateinDataBase.this.server_url + "");

                try {
                    // Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

                    //Feedbacks als Array übergeben
                    for (String feedback_str : LateinDataBase.this.feedback) {
                        nameValuePairs.add(new BasicNameValuePair("[feedback]", feedback_str));
                    }

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);

                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        InputStream instream = entity.getContent();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

                        String result = reader.readLine();

                        if (result == "successful") {
                            //Feedback Liste leeren
                            LateinDataBase.this.feedback.clear();
                        }

                        Log.i("Read from server", result);
                    }

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }

                return null;
            }
        };

        task.execute();
    }

    public void loadDataBase () {
        //Punkte auslesen
        this.points = this.prefs.getInt("points", -1);

        if (this.points == -1) {
            this.points = 0;
            this.prefs.edit().putInt("points", 0).commit();
        }
    }
}
