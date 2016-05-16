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
public class SessionCours {
    private int idSession;
    private int id_utilisateur;
    private int id_cours;
    private String date_session;
     private String badge;
    private int nbrchapitre;

    public SessionCours() {
    }

    public SessionCours(int id_utilisateur, int id_cours, String date_session, String badge) {
        
        this.id_utilisateur = id_utilisateur;
        this.id_cours = id_cours;
        this.date_session = date_session;
        this.badge = badge;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public void setNbrchapitre(int nbrchapitre) {
        this.nbrchapitre = nbrchapitre;
    }

    public int getNbrchapitre() {
        return nbrchapitre;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public String getDate_session() {
        return date_session;
    }

    public void setDate_session(String date_session) {
        this.date_session = date_session;
    }

  
  public String getBadge() {
        return badge;
    }
  public void setBadge(String badge) {
        this.badge = badge;
    }
  
    public String toString() {
        return "SessionCours{" + "id_utilisateur=" + id_utilisateur + ", id_cours=" + id_cours + ", date_session=" + date_session + ", badge=" + badge +  '}';
    }
}