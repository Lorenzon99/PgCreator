package com.example.studente.pgcreator;

import android.app.Application;
import android.util.Log;

import com.example.studente.pgcreator.BuildConfig;
import com.example.studente.pgcreator.MyObjectBox;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class App extends Application {

    public static final String TAG = "ObjectBoxExample";
    public static final boolean EXTERNAL_DIR = false;

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(this);
        }

        Log.d("App", "Using ObjectBox " + BoxStore.getVersion() + " (" + BoxStore.getVersionNative() + ")");
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
