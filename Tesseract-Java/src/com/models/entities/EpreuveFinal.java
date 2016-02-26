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
public class EpreuveFinal extends Epreuve{
    protected int dureeExamen;

    public EpreuveFinal(int dureeExamen, int id, String difficulte) {
        super(id, difficulte);
        this.dureeExamen = dureeExamen;
    }

    public EpreuveFinal() {
    }

    public int getDureeExamen() {
        return dureeExamen;
    }

    public void setDureeExamen(int dureeExamen) {
        this.dureeExamen = dureeExamen;
    }

    @Override
    public String toString() {
        return "EpreuveFinal{" + super.toString() + "dureeExamen=" + dureeExamen + '}';
    }
    
    
}
