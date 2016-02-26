package com.models.entities;

import com.models.enums.Difficulte;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cours {

    private final IntegerProperty idCours;
    private final StringProperty badge;
    private final StringProperty nomCours;
    private final ObjectProperty<Difficulte> difficulte;
    private final StringProperty descriptionCours;
    private final IntegerProperty idMatiere;
    private final StringProperty affiche;
    private final IntegerProperty idFormateur;

    public Cours(){
        this(-1, null, null, null, null, -1, null, -1);
    }
    /**
     * Constructor with initial data.
     * 
     * @param idCours
     * @param badge
     * @param nomCours
     * @param difficulte 
     * @param descriptionCours 
     * @param idMatiere 
     * @param affiche 
     * @param idFormateur     /**
     * Constructor with initial data.
     * 
     * @param idCours
     * @param badge
     * @param nomCours
     * @param difficulte 
     * @param descriptionCours 
     * @param idMatiere 
     * @param affiche 
     * @param idFormateur 
     */
    
    public Cours(int idCours, String badge, String nomCours, Difficulte difficulte, String descriptionCours, int idMatiere, String affiche, int idFormateur) {
        this.idCours = new SimpleIntegerProperty(idCours);
        this.badge = new SimpleStringProperty(badge);
        this.nomCours = new SimpleStringProperty(nomCours);
        this.difficulte = new SimpleObjectProperty<>(difficulte);
        this.descriptionCours = new SimpleStringProperty(descriptionCours);
        this.idMatiere = new SimpleIntegerProperty(idMatiere);
        this.affiche = new SimpleStringProperty(affiche);
        this.idFormateur = new SimpleIntegerProperty(idFormateur);
    }

    public int getIdCours() {
        return idCours.get();
    }
    public void setIdCours(int IdCours){
        this.idCours.set(IdCours);
    }
    public IntegerProperty idCoursProperty() {
        return idCours;
    }
    
    
    public String getBadge() {
        return badge.get();
    }
    public void setBadge(String badge) {
        this.badge.set(badge);
    }
    public StringProperty badgeProperty(){
        return badge;
    }

    
    public String getNomCours() {
        return nomCours.get();
    }
    public void setNomCours(String nomCours) {
        this.nomCours.set(nomCours);
    }
    public StringProperty nomCoursProperty(){
        return nomCours;
    }

    /**
     * @return the difficulte
     */
    public Difficulte getDifficulte() {
        return difficulte.get();
    }
    public void setDifficulte(Difficulte difficulte) {
        this.difficulte.set(difficulte);
    }
    public ObjectProperty<Difficulte> difficulteProperty(){
        return difficulte;
    }
    
    
    public String getDescriptionCours() {
        return descriptionCours.get();
    }
    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours.set(descriptionCours);
    }
    public StringProperty descriptionCoursProperty(){
        return descriptionCours;
    }

    /**
     * @return the idMatiere
     */
    public int getIdMatiere() {
        return idMatiere.get();
    }
    public void setIdMatiere(int idMatiere) {
        this.idMatiere.set(idMatiere);
    }
    public IntegerProperty idMatiereProperty(){
        return idMatiere;
    }

    /**
     * @return the affiche
     */
    public String getAffiche() {
        return affiche.get();
    }
    public void setAffiche(String affiche) {
        this.affiche.set(affiche);
    }
    public StringProperty afficheProperty(){
        return affiche;
    }

    /**
     * @return the idFormateur
     */
    public int getIdFormateur() {
        return idFormateur.get();
    }
    public void setIdFormateur(int idFormateur) {
        this.idFormateur.set(idFormateur);
    }
    public IntegerProperty idFormateurProperty(){
        return idFormateur;
    }

}
