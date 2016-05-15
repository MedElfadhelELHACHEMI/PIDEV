/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.Date;

/**
 *
 * @author Sameh
 */
public class Apprenant extends Utilisateur {

   

    public Apprenant() {
    }
 public Apprenant(Utilisateur u) {
      this.idUtilisateur = u.getIdUtilisateur();
        this.nomUtilisateur = u.getNomUtilisateur();
        this.motDePass = u.getMotDePass();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.dateNaissance = u.getDateNaissance();
        this.tel = u.getTel();
        this.adresse = u.getAdresse();
        this.mail = u.getMail();
        this.photo = u.getPhoto();
        this.Role=u.getRole(); 
        this.score=u.getScore();
        this.sexe=u.getSexe();
        this.organisation=u.getOrganisation();
    }
    public Apprenant(int score,  int idUtilisateur, String nomUtilisateur, String motDePass, String nom,String sexe, String prenom, String dateNaissance, int tel, String adresse, String mail, String photo,String role) {
        super(idUtilisateur, nomUtilisateur,sexe, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo,role);
        this.score = score;
       
    }

    public Apprenant(int score, String nomUtilisateur, String motDePass, String nom, String prenom,String sexe, String dateNaissance, int tel, String adresse, String mail, String photo,String role) {
        super(nomUtilisateur, motDePass,sexe, nom, prenom, dateNaissance, tel, adresse, mail, photo,role);
        this.score = score;
       
    }

    public Apprenant(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    

  
    public String toString() {
        return super.toString()+ " /n Apprenant{" + "score=" + score +  '}';
    }
    

}
