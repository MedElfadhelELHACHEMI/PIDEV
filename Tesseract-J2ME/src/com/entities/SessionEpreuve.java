/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

import java.util.Date;

/**
 *
 * @author Sameh
 */
public class SessionEpreuve {
 private int id_utilisateur;
    private int id_epreuve;
    private float note;
    private int nbr_tentative;
    private String date_Session;

    public SessionEpreuve() {
    }

    public SessionEpreuve(int id_utilisateur, int id_epreuve, float note, int nbr_tentative, String date_Session) {
        this.id_utilisateur = id_utilisateur;
        this.id_epreuve = id_epreuve;
        this.note = note;
        this.nbr_tentative = nbr_tentative;
        this.date_Session = date_Session;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_epreuve() {
        return id_epreuve;
    }

    public void setId_epreuve(int id_epreuve) {
        this.id_epreuve = id_epreuve;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getNbr_tentative() {
        return nbr_tentative;
    }

    public void setNbr_tentative(int nbr_tentative) {
        this.nbr_tentative = nbr_tentative;
    }

    public String getDate_Session() {
        return date_Session;
    }

    public void setDate_Session(String date_Session) {
        this.date_Session = date_Session;
    }

   
   
    public String toString() {
        return "SessionEpreuve{" + "id_utilisateur=" + id_utilisateur + ", id_epreuve=" + id_epreuve + ", note=" + note + ", nbr_tentative=" + nbr_tentative + ", date_Session=" + date_Session + '}';
    }
    

}
