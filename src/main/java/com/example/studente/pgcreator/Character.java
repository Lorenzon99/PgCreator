package com.example.studente.pgcreator;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Character {

    @Id
    long id;

    String nome;
    String classe;
    String razza;

    public Character(long id, String nome, String classe, String razza) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.razza = razza;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }
}
