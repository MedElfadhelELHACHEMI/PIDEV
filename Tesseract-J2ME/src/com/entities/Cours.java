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
public class Cours {
   private int idCours;
   
    private  String nomCours;
    private  String difficulte;
    private String descriptionCours;
    private int idMatiere;
    private String affiche;
    private int idFormateur; 
    private int id_matiere;
    private String video;

    public Cours(int idCours, String nomCours, String difficulte, String descriptionCours, int idMatiere, String affiche, int idFormateur, int id_matiere, String video) {
        this.idCours = idCours;
        
        this.nomCours = nomCours;
        this.difficulte = difficulte;
        this.descriptionCours = descriptionCours;
        this.idMatiere = idMatiere;
        this.affiche = affiche;
        this.idFormateur = idFormateur;
        this.id_matiere = id_matiere;
        this.video = video;
    }

    public Cours( String nomCours, String difficulte, String descriptionCours, int idMatiere, String affiche, String video) {
       
        this.nomCours = nomCours;
        this.difficulte = difficulte;
        this.descriptionCours = descriptionCours;
        this.idMatiere = idMatiere;
        this.affiche = affiche;
        this.video = video;
    }

    public Cours() {
    }

    public int getIdCours() {
        return idCours;
    }

  

    public String getNomCours() {
        return nomCours;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public String getDescriptionCours() {
        return descriptionCours;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public String getAffiche() {
        return affiche;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public String getVideo() {
        return video;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours = descriptionCours;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String toString() {
        return "Cours{" + "idCours=" + idCours + ", nomCours=" + nomCours + ", difficulte=" + difficulte + ", descriptionCours=" + descriptionCours + ", idMatiere=" + idMatiere + ", affiche=" + affiche + ", idFormateur=" + idFormateur + ", id_matiere=" + id_matiere + ", video=" + video + '}';
    }
   
    
    
    
}
