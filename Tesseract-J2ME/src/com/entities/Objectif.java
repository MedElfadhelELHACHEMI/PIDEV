/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

/**
 *
 * @author Sameh
 */
public class Objectif {
private int id;
private int id_chapitre;
private int numero;
private String nom;
private String description;
private String difficulte;

    public Objectif(int id, int id_chapitre, int numero, String nom, String description, String difficulte) {
        this.id = id;
        this.id_chapitre = id_chapitre;
        this.numero = numero;
        this.nom = nom;
        this.description = description;
        this.difficulte = difficulte;
    }

    public Objectif(int id_chapitre, int numero, String nom, String description, String difficulte) {
        this.id_chapitre = id_chapitre;
        this.numero = numero;
        this.nom = nom;
        this.description = description;
        this.difficulte = difficulte;
    }

    public Objectif() {
    }

    public int getId() {
        return id;
    }

    public int getId_chapitre() {
        return id_chapitre;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_chapitre(int id_chapitre) {
        this.id_chapitre = id_chapitre;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String toString() {
        return "Objectif{" + "id=" + id + ", id_chapitre=" + id_chapitre + ", numero=" + numero + ", nom=" + nom + ", description=" + description + ", difficulte=" + difficulte + '}';
    }

}
