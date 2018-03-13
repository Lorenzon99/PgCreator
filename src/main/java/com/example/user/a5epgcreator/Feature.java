package com.example.user.a5epgcreator;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class Feature {

    @Id long id;

    String nome;
    String descrizione;

    ToMany<Razza> razze;
    ToMany<Classe> classi;

    public Feature(long id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
    }
}
