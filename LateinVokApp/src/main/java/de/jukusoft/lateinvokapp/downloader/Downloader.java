package de.jukusoft.lateinvokapp.downloader;

import android.app.Activity;

import java.io.File;

/**
 * Created by Justin on 19.05.2014.
 */
public class Downloader {

    protected DownloadFinishedEvent finishedEvent = null;
    protected File downloadpath = null;

    public Downloader (Activity activity) {
        //
    }

    interface DownloadFinishedEvent {
        public void downloadFinished();
    }

}
