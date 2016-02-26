package com.models.entities;

import java.util.ArrayList;
import java.util.List;

public class Matiere {

    private int idMatiere;
    private String nomMatiere;
    private String descriptionMatiere;
    private List<Cours> listCours;

    public Matiere() {
        listCours = new ArrayList<>();
    }

    public Matiere(String nomMatiere, String descriptionMatiere, List<Cours> listCours) {
        this.descriptionMatiere = descriptionMatiere;
        this.listCours = listCours;
        this.nomMatiere = nomMatiere;

    }

    public Matiere(int idMatiere, String nomMatiere, String descriptionMatiere) {
        this.descriptionMatiere = descriptionMatiere;
        this.idMatiere = idMatiere;
        listCours = new ArrayList<>();
        this.nomMatiere = nomMatiere;

    }

    public Matiere( String nomMatiere, String descriptionMatiere) {
        
        this.nomMatiere = nomMatiere;
        this.descriptionMatiere = descriptionMatiere;
        
    }
    

    @Override
    public String toString() {

        return "la matiÃ©re : " + nomMatiere + " " + descriptionMatiere + " ";
    }

  
    public int getIdMatiere() {
        return idMatiere;
    }

   
    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

   
    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

   
    public String getDescriptionMatiere() {
        return descriptionMatiere;
    }

  
    public void setDescriptionMatiere(String descriptionMatiere) {
        this.descriptionMatiere = descriptionMatiere;
    }

   
    public List<Cours> getListCours() {
        return listCours;
    }

    public void setListCours(List<Cours> listCours) {
        this.listCours = listCours;
    }

}
