package de.jukusoft.lateinvokapp.downloader;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * Created by Justin on 19.05.2014.
 */
public class Downloader extends AsyncTask {

    protected DownloadFinishedEvent finishedEvent = null;
    protected File downloadpath = null;
    protected String www_path = "";

    public Downloader () {
        //
    }

    public void setPath (File downloadpath) {
        this.downloadpath = downloadpath;
    }

    public void setDownloadFinishedEvent (DownloadFinishedEvent finishedEvent) {
        this.finishedEvent = finishedEvent;
    }

    //Link auf execute Methode
    public void download () {
        this.execute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(this.www_path);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        try {
            String response_str = client.execute(request, responseHandler);
            Log.println(Log.ERROR, "HTTPClient", "Antwort des Requests: " + response_str);

            //Danach Event werfen
            if (this.finishedEvent != null) {
                this.finishedEvent.downloadFinished(new DownloadData(this.www_path, this.downloadpath, response_str, true));
            }
        } catch (ClientProtocolException e) {
            //Danach Event werfen
            if (this.finishedEvent != null) {
                this.finishedEvent.downloadFinished(new DownloadData(this.www_path, this.downloadpath, "", false));
            }

            e.printStackTrace();
        } catch (IOException e) {
            //Danach Event werfen
            if (this.finishedEvent != null) {
                this.finishedEvent.downloadFinished(new DownloadData(this.www_path, this.downloadpath, "", false));
            }

            e.printStackTrace();
        }

        return null;
    }

    class DownloadData {

        protected String www_path = "";
        protected File downloadPath = null;
        protected boolean isFinished = false;
        protected String response_str = "";

        public DownloadData (String www_path, File downloadPath, String response_str, boolean isFinished) {
            this.www_path = www_path;
            this.downloadPath = downloadPath;

            this.isFinished = isFinished;
        }

        public boolean isFinished () {
            return this.isFinished;
        }

        public String getWWWPath () {
            return this.www_path;
        }

        public File getDownloadPath () {
            return this.downloadPath;
        }

    }

    interface DownloadFinishedEvent {
        public void downloadFinished(DownloadData data);
    }

}
