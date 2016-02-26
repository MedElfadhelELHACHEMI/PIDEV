package com.models.entities;

import com.models.enums.Etat;
import java.util.Date;

public class Reclamation {

    private int idReclamation;
    private int idUtilisateur;
    private String sujet;
    private String description;
    private Etat etat;
    private Date dateReclamation;

    public Reclamation(int idReclamation, int idUtilisateur, String sujet, String description, Etat etat, Date dateReclamation) {
        this.idReclamation = idReclamation;
        this.idUtilisateur = idUtilisateur;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
        this.dateReclamation = dateReclamation;
    }

    public Reclamation(int idUtilisateur, String sujet, String description, Etat etat, Date dateReclamation) {
        this.idUtilisateur = idUtilisateur;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
        this.dateReclamation = dateReclamation;
    }

    public Reclamation() {
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", idUtilisateur=" + idUtilisateur + ", sujet=" + sujet + ", description=" + description + ", etat=" + etat + ", dateReclamation=" + dateReclamation + '}';
    }

}
