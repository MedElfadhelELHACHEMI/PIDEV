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
public class InscriptionChallenge {
 private int id ;
   private int idUtilisateur ;
   private int idchallenge ;
   private Date date; 
   private String Solution;

    public InscriptionChallenge() {
    }

    public int getId() {
        return id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public int getIdchallenge() {
        return idchallenge;
    }

    public Date getDate() {
        return date;
    }

    public String getSolution() {
        return Solution;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setIdchallenge(int idchallenge) {
        this.idchallenge = idchallenge;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSolution(String Solution) {
        this.Solution = Solution;
    }
   
}
