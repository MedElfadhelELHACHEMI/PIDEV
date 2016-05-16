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

   

    public String toString() {
        return "Reponse{" + "id=" + id + ", reponse=" + reponse + ", justification=" + justification + ", etat=" + etat + '}';
    }
    
        
}
