/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

/**
 *
 * @author Maynoo
 */
public class Evenement {
    int idEvenement, nbr_max;
    String nom,description,affiche,date;
    Organisation organisation;

    public Evenement() {
    }

    public Evenement(int idEvenement, int nbr_max, String nom, String description, String affiche, String date, Organisation organisation) {
        this.idEvenement = idEvenement;
        this.nbr_max = nbr_max;
        this.nom = nom;
        this.description = description;
        this.affiche = affiche;
        this.date = date;
        this.organisation = organisation;
    }



    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getNbr_max() {
        return nbr_max;
    }

    public void setNbr_max(int nbr_max) {
        this.nbr_max = nbr_max;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
    
}
