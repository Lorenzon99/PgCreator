package com.example.user.a5epgcreator;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.objectbox.*;

import io.objectbox.BoxStore;

public class App extends Application {
    public static final boolean EXTERNAL_DIR = true;

    public static BoxStore boxStore;
    public static Box<Pg> pgBox;
    public static Box<Razza> razzaBox;
    public static Box<Classe> classeBox;
    public static Box<Feature> featureBox;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        pgBox = boxStore.boxFor(Pg.class);
        razzaBox = boxStore.boxFor(Razza.class);
        classeBox = boxStore.boxFor(Classe.class);
        featureBox = boxStore.boxFor(Feature.class);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {

            Razza umano = new Razza(0, getString(R.string.human));
            Razza nano = new Razza(0, getString(R.string.dwarf));

            Classe guerriero = new Classe(0, getString(R.string.warrior));
            Classe barbaro = new Classe(0, getString(R.string.barbarian));

            Feature conoscenza = new Feature(0, getString(R.string.knowledge), getString(R.string.knowledgeDesc));
            Feature scurovisione = new Feature(0, getString(R.string.darkvision), getString(R.string.darkvisionDesc));
            Feature addestramento = new Feature(0, getString(R.string.training), getString(R.string.trainingDesc));
            Feature esperienza = new Feature(0, getString(R.string.experience), getString(R.string.experienceDesc));
            Feature ira = new Feature(0, getString(R.string.rage), getString(R.string.rageDesc));

            umano.features.add(conoscenza);

            nano.features.add(conoscenza);
            nano.features.add(scurovisione);

            guerriero.features.add(addestramento);
            guerriero.features.add(esperienza);

            barbaro.features.add(esperienza);
            barbaro.features.add(ira);

            conoscenza.razze.add(umano);
            conoscenza.razze.add(nano);

            scurovisione.razze.add(nano);

            addestramento.classi.add(guerriero);

            esperienza.classi.add(guerriero);
            esperienza.classi.add(barbaro);

            ira.classi.add(barbaro);

            razzaBox.put(umano, nano);
            classeBox.put(guerriero, barbaro);
            featureBox.put(conoscenza, scurovisione, addestramento, esperienza, ira);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
    }
}
