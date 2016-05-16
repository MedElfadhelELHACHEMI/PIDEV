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
public class InscriptionEvenement {
    private int id ;
   private int idUtilisateur ;
   private int idEvenement ;
   private Date date;

    public InscriptionEvenement() {
    }

    public int getId() {
        return id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public void setDate(Date date) {
        this.date = date;
    }
   
}
