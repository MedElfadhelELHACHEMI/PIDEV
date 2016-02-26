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
public class SujetForum {
    private int id;
    private Utilisateur utilisateur;
    private Matiere matiere;
    private String titre;
    private String description;
    private Date date;

    public SujetForum() {
    }

    public SujetForum(int id, Utilisateur utilisateur,Matiere matiere, String titre, String description, Date date) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.matiere =matiere;
        this.titre = titre;
        this.description = description;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Matiere getMatiere() {
        return matiere;
    }
    
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.titre);
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
        final SujetForum other = (SujetForum) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "SujetForum{" + "id=" + id + ", utilisateur=" + utilisateur.getIdUtilisateur() + ", matiere=" + matiere.getNomMatiere() + ", titre=" + titre + ", description=" + description + ", date=" + date + '}';
    }

    
    
    
}
