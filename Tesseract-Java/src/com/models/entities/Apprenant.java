package com.models.entities;

import com.models.enums.Etat;
import java.sql.Date;

public class Apprenant extends Utilisateur {

    private int score;
    private Etat etat;

    public Apprenant() {
    }

    public Apprenant(int score, Etat etat, int idUtilisateur, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(idUtilisateur, nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.score = score;
        this.etat = etat;
    }

    public Apprenant(int score, Etat etat, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.score = score;
        this.etat = etat;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        
        return "Apprenant{" + "score=" + score + ", etat=" + etat + '}'+super.toString();
    }

  

   

}
