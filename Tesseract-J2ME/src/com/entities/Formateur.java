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
public class Formateur extends Utilisateur{
  private String cv ; 
    private String etat ;
     private int score;
    

    public Formateur() {
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Formateur(String cv, String etat, int idUtilisateur,String sexe, String nomUtilisateur, String motDePass, String nom, String prenom,String dateNaissance, int tel, String adresse, String mail, String photo,String role) {
        super(idUtilisateur, nomUtilisateur,sexe, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo,role);
        this.cv = cv;
        this.etat = etat;
    }
 public Formateur(int score) {
        this.score = score;
    } 
    public Formateur(String cv, String etat, String nomUtilisateur,String sexe, String motDePass, String nom, String prenom, String dateNaissance, int tel, String adresse, String mail, String photo,String role) {
        super(nomUtilisateur, motDePass,sexe, nom, prenom, dateNaissance, tel, adresse, mail, photo,role);
        this.cv = cv;
        this.etat = etat;
    }
    
     public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
   
}
