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
public class Chapitre {
   private int idChapitre ; 
   private int idCours ;
   private String nom ;
   private int numero ; 
   private String description ;
   private String resume ;

    public Chapitre(int idChapitre, int idCours, String nom, int numero, String description, String resume) {
        this.idChapitre = idChapitre;
        this.idCours = idCours;
        this.nom = nom;
        this.numero = numero;
        this.description = description;
        this.resume = resume;
    }

    public Chapitre(int idCours, String nom, int numero, String description, String resume) {
        this.idCours = idCours;
        this.nom = nom;
        this.numero = numero;
        this.description = description;
        this.resume = resume;
    }

    public Chapitre() {
    }

    public int getIdChapitre() {
        return idChapitre;
    }

    public int getIdCours() {
        return idCours;
    }

    public String getNom() {
        return nom;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescription() {
        return description;
    }

    public String getResume() {
        return resume;
    }

    public void setIdChapitre(int idChapitre) {
        this.idChapitre = idChapitre;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String toString() {
        return "Chapitre{" + "idChapitre=" + idChapitre + ", idCours=" + idCours + ", nom=" + nom + ", numero=" + numero + ", description=" + description + ", resume=" + resume + '}';
    }
   
}
