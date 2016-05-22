/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;

/**
 *
 * @author Bacem
 */
public abstract class Epreuve {
    
    private int id;

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdChapitre() {
        return idChapitre;
    }

    public void setIdChapitre(int idChapitre) {
        this.idChapitre = idChapitre;
    }
    private int idCours;
    private int idChapitre;
    private String difficulte;

    public Epreuve(int id, String difficulte) {
        this.id = id;
        this.difficulte = difficulte;
    }

    public Epreuve() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
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
        final Epreuve other = (Epreuve) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Epreuve{" +", difficulte=" + difficulte + '}';
    }
    
    
}
