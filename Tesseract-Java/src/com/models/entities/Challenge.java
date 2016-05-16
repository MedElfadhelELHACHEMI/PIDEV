
package com.models.entities;

import java.sql.Date;


public class Challenge {
   private int idChallenge ;
   private int idOrganisation ;
   private int idUtilisateur ;
   private int  durée ;
   private String nom ; 
   private String description ;
   private String theme ;

  
   private Date dateChallenge ;
   private Organisation organisation ;


    public Challenge() {
    }

    public Challenge(String nom, String description, String theme, Date dateChallenge, Organisation organisation) {

        this.nom = nom;
        this.description = description;
        this.theme = theme;
        this.dateChallenge = dateChallenge;
        
    }

    public Challenge(int idChallenge, int idOrganisation, String nom, String description, String theme, Date dateChallenge, Organisation organisation) {
        this.idChallenge = idChallenge;
        this.idOrganisation = idOrganisation;
        this.nom = nom;
        this.description = description;
        this.theme = theme;
        this.dateChallenge = dateChallenge;
     
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setDurée(int durée) {
        this.durée = durée;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public int getDurée() {
        return durée;
    }

    public int getIdChallenge() {
        return idChallenge;
    }

    public void setIdChallenge(int idChallenge) {
        this.idChallenge = idChallenge;
    }

    public int getIdOrganisation() {
        return idOrganisation;
    }

    public void setIdOrganisation(int idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDateChallenge() {
        return dateChallenge;
    }

    public void setDateChallenge(Date dateChallenge) {
        this.dateChallenge = dateChallenge;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.idChallenge;
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
        final Challenge other = (Challenge) obj;
        if (this.idChallenge != other.idChallenge) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Challenge{" + "idChallenge=" + idChallenge + ", idOrganisation=" + idOrganisation + ", nom=" + nom + ", description=" + description + ", theme=" + theme + ", dateChallenge=" + dateChallenge + ", ";
    }
   
   
}
