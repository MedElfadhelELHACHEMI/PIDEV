/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;

/**
 *
 * @author Bacem
 */
public class Reponse {
    private int id;
    private String reponse;
    private String justification;
    private String etat;
    private int idQuestion;

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Reponse(int id, String reponse, String justification, String etat, int idQuestion) {
        this.id = id;
        this.reponse = reponse;
        this.justification = justification;
        this.etat = etat;
        this.idQuestion = idQuestion;
    }



    public Reponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final Reponse other = (Reponse) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", reponse=" + reponse + ", justification=" + justification + ", etat=" + etat + '}';
    }
    
    
}
