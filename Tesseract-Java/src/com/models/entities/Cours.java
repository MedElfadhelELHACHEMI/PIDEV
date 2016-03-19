package com.models.entities;

import com.models.enums.Difficulte;
import com.models.enums.Etat;
import java.time.LocalDate;
import java.util.Objects;

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
    private String video;
    private Etat validation1;
    private Etat validation2;
    private LocalDate uploadDate;
    private String language;

    public Cours() {
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
     * @param idFormateur /** Constructor with initial data.
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

    public void setIdCours(int IdCours) {
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

    public StringProperty badgeProperty() {
        return badge;
    }

    public String getNomCours() {
        return nomCours.get();
    }

    public void setNomCours(String nomCours) {
        this.nomCours.set(nomCours);
    }

    public StringProperty nomCoursProperty() {
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

    public ObjectProperty<Difficulte> difficulteProperty() {
        return difficulte;
    }

    public String getDescriptionCours() {
        return descriptionCours.get();
    }

    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours.set(descriptionCours);
    }

    public StringProperty descriptionCoursProperty() {
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

    public IntegerProperty idMatiereProperty() {
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

    public StringProperty afficheProperty() {
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

    public IntegerProperty idFormateurProperty() {
        return idFormateur;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Etat getValidation1() {
        return validation1;
    }

    public void setValidation1(Etat validation1) {
        this.validation1 = validation1;
    }

    public Etat getValidation2() {
        return validation2;
    }

    public void setValidation2(Etat validation2) {
        this.validation2 = validation2;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idCours);
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
        final Cours other = (Cours) obj;
        if (!Objects.equals(this.idCours, other.idCours)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Cours{" + "idCours=" + idCours + ", badge=" + badge + ", nomCours=" + nomCours + ", difficulte=" + difficulte + ", descriptionCours=" + descriptionCours + ", idMatiere=" + idMatiere + ", affiche=" + affiche + ", idFormateur=" + idFormateur + ", video=" + video + ", validation1=" + validation1 + ", validation2=" + validation2 + '}';
    }

}
