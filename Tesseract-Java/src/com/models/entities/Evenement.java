/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Choukou_Tracker
 */
public class Evenement {
    private int idEvenement;
    private int idOrganisation;
    private String nom;
    private String description;
    private int nbrMax;
    private String affiche;
    private Date dateEvenement;
    
    
    public Evenement(){}
    public Evenement(int idEvenement, int idOrganisation, String nom, String description, int nbrMax, String affiche, Date dateEvenement) {
        this.idEvenement = idEvenement;
        this.idOrganisation = idOrganisation;
        this.nom = nom;
        this.description = description;
        this.nbrMax = nbrMax;
        this.affiche = affiche;
        this.dateEvenement = dateEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public int getIdOrganisation() {
        return idOrganisation;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public String getAffiche() {
        return affiche;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public void setIdOrganisation(int idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idEvenement;
        hash = 37 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.idEvenement != other.idEvenement) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", idOrganisation=" + idOrganisation + ", nom=" + nom + ", description=" + description + ", nbrMax=" + nbrMax + ", affiche=" + affiche + ", dateEvenement=" + dateEvenement + '}';
    }
    
    
    
    
}
