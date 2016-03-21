/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;

/**
 *
 * @author haikal
 */
public class ScoreUtilisateur {

    private String nom;
    private Long numbreOfSubs;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getNumbreOfSubs() {
        return numbreOfSubs;
    }

    public void setNumbreOfSubs(Long numbreOfSubs) {
        this.numbreOfSubs = numbreOfSubs;
    }

    @Override
    public String toString() {
        return "ScoreUtilisateur{" + "nom=" + nom + ", numbreOfSubs=" + numbreOfSubs + '}';
    }

}
