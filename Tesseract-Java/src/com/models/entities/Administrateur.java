
package com.models.entities;

import java.sql.Date;


public class Administrateur  extends Utilisateur{
    
    private String mailSecours ;

    public Administrateur() {
    }

    public Administrateur(String mailSecours, int idUtilisateur, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(idUtilisateur, nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.mailSecours = mailSecours;
    }

    public Administrateur(String mailSecours, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.mailSecours = mailSecours;
    }

    public String getMailSecours() {
        return mailSecours;
    }

    public void setMailSecours(String mailSecours) {
        this.mailSecours = mailSecours;
    }

    @Override
    public String toString() {
        return "Administrateur{" + "mailSecours=" + mailSecours + '}';
    }
    
    
}
