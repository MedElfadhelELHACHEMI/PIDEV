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
public class EpreuveObjectif extends Epreuve{

    public EpreuveObjectif(int id, String difficulte) {
        super(id, difficulte);
    }

    public EpreuveObjectif() {
    }

    
    public String toString() {
        return "EpreuveObjectif{" +super.toString() +  '}';
    }
    
    
    
}
