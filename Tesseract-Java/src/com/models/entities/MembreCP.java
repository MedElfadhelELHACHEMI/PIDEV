
package com.models.entities;

import java.sql.Date;

public class MembreCP extends Utilisateur{

    public MembreCP() {
    }

    public MembreCP(String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
    }

    public MembreCP(int idUtilisateur, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(idUtilisateur, nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
    }
   
}
