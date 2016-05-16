/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.models.enums.Difficulte;
import javafx.beans.property.*;

/**
 *
 * @author Bacem
 */
public class Objectif extends RecursiveTreeObject<Objectif> {
    private final IntegerProperty id;
    private final IntegerProperty idChapitre;
    private final StringProperty nom;
    private final StringProperty description;
    private final ObjectProperty<Difficulte> difficulte;
    private final IntegerProperty numero;
    
    public Objectif(){
        this(-1, -1, -1,null, null, null);
    }
    
    /**
     * Constructor with initial data.
     * 
     * @param id
     * @param idChapitre
     * @param nom
     * @param difficulte 
     * @param description
     */
    public Objectif(int id, int idChapitre, int numero, String nom, String description, Difficulte difficulte){
        this.id = new SimpleIntegerProperty(id);
        this.idChapitre = new SimpleIntegerProperty(idChapitre);
        this.nom = new SimpleStringProperty(nom);
        this.description = new SimpleStringProperty(description);
        this.difficulte = new SimpleObjectProperty<>(difficulte);
        this.numero = new SimpleIntegerProperty(numero);
    }
    
    public IntegerProperty IdProperty() {
        return id;
    }

    public IntegerProperty IdChapitreProperty() {
        return idChapitre;
    }
    
    public IntegerProperty NumeroProperty() {
        return numero;
    }

    public StringProperty NomProperty() {
        return nom;
    }

    public ObjectProperty<Difficulte> difficulteProperty(){
        return difficulte;
    }

    public StringProperty DescriptionProperty() {
        return description;
    }
   
    public int getId() {
        return id.get();
    }

    public int getIdChapitre() {
        return idChapitre.get();
    }
    
    public int getNumero() {
        return numero.get();
    }
    
    public String getNom() {
        return nom.get();
    }

    public Difficulte getDifficulte() {
        return difficulte.get();
    }

    public String getDescription() {
        return description.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public void setIdChapitre(int idCours) {
        this.idChapitre.set(idCours);
    }
    
    public void setNumero(int numero) {
        this.numero.set(numero);
    }
    public void setDifficulte(Difficulte difficulte) {
        this.difficulte.set(difficulte);
    }
    
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    
    public void setDescription(String description) {
        this.description.set(description);
    }
    
}
