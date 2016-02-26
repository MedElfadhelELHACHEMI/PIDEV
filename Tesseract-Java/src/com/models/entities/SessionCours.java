/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;

import java.util.Date;

/**
 *
 * @author haikal
 */
public class SessionCours {
    
    private int id_utilisateur;
    private int id_cours;
    private Date date_session;

    public SessionCours() {
    }

    public SessionCours(int id_utilisateur, int id_cours, Date date_session) {
        this.id_utilisateur = id_utilisateur;
        this.id_cours = id_cours;
        this.date_session = date_session;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
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

    public Date getDate_session() {
        return date_session;
    }

    public void setDate_session(Date date_session) {
        this.date_session = date_session;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_utilisateur;
        hash = 97 * hash + this.id_cours;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionCours other = (SessionCours) obj;
        if (this.id_utilisateur != other.id_utilisateur) {
            return false;
        }
        if (this.id_cours != other.id_cours) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SessionCours{" + "id_utilisateur=" + id_utilisateur + ", id_cours=" + id_cours + ", date_session=" + date_session + '}';
    }
    
    
    
}
