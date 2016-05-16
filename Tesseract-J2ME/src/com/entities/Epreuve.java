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
public class Epreuve {
private int id;
    private String difficulte;

    public Epreuve(int id, String difficulte) {
        this.id = id;
        this.difficulte = difficulte;
    }

    public Epreuve(String difficulte) {
        this.difficulte = difficulte;
    }

    public Epreuve() {
    }

    public int getId() {
        return id;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String toString() {
        return "Epreuve{" + "id=" + id + ", difficulte=" + difficulte + '}';
    }
    
}
