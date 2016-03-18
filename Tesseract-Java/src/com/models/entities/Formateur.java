
package com.models.entities;

import com.models.enums.Etat;
import java.sql.Date;


public class Formateur extends Utilisateur{
    private String cv ; 
    private Etat etat ;
    private int score ;
private int idOrganisationn ;
    public Formateur() {
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Formateur(String cv, Etat etat, int idUtilisateur, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(idUtilisateur, nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.cv = cv;
        this.etat = etat;
    }

    public Formateur(String cv, Etat etat, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.cv = cv;
        this.etat = etat;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIdOrganisationn() {
        return idOrganisationn;
    }

    public void setIdOrganisationn(int idOrganisation) {
        this.idOrganisationn = idOrganisation;
    }
    
    

   
    
}
