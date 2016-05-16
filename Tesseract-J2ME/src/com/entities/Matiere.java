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
public class Matiere {
 private int idMatiere;
 private String nomMatiere;
private String descriptionMatiere;
     public Matiere( String nomMatiere, String descriptionMatiere) {
        
        this.nomMatiere = nomMatiere;
        this.descriptionMatiere = descriptionMatiere;
        
    }
    
  public Matiere(){
      
  }
    public String toString() {

        return "la matiÃ©re : " + nomMatiere + " " + descriptionMatiere + " ";
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public String getDescriptionMatiere() {
        return descriptionMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public void setDescriptionMatiere(String descriptionMatiere) {
        this.descriptionMatiere = descriptionMatiere;
    }
    
    
}
