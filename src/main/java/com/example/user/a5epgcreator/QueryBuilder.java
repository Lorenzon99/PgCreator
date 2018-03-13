package com.example.user.a5epgcreator;

import java.util.List;


    /*
    public static Pg findPg (String nome) {
        List<Pg> pgs = App.pgBox.query().equal(Pg_.nome, nome).build().find();
        return pgs.get(0);
    }
    */


public class QueryBuilder {

    public static Pg findPg (long id) {
        Pg pg = App.pgBox.query().equal(Pg_.id, id).build().findUnique();
        return pg;
    }

    public static Razza findRazza (String nome) {
        Razza razza = App.razzaBox.query().equal(Razza_.nome, nome).build().findUnique();
        return razza;
    }

    public static Razza findRazza (long id) {
        Razza razze = App.razzaBox.query().equal(Razza_.id, id).build().findUnique();
        return razze;
    }

    public static Classe findClasse (String nome) {
        Classe classi = App.classeBox.query().equal(Classe_.nome, nome).build().findUnique();
        return classi;
    }

    public static Classe findClasse (long id) {
        Classe classi = App.classeBox.query().equal(Classe_.id, id).build().findUnique();
        return classi;
    }

    public static Feature findFeature (String nome) {
        Feature features = App.featureBox.query().equal(Feature_.nome, nome).build().findUnique();
        return features;
    }

    public static Feature findFeature (long id) {
        Feature features = App.featureBox.query().equal(Feature_.id, id).build().findUnique();
        return features;
    }

}
