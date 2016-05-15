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
public class Utilisateur {

    protected int idUtilisateur;
    protected Organisation organisation;
    protected String nomUtilisateur;
    protected String motDePass;
    protected String nom;
    protected String prenom;
    protected String dateNaissance;
    protected int tel;
    protected String adresse;
    protected String mail;
    protected String photo;
    protected String Role;
    protected String sexe;
    protected int score;

    public Utilisateur(int idUtilisateur, String nomUtilisateur, String sexe, String motDePass, String nom, String prenom, String dateNaissance, int tel, String adresse, String mail, String photo, String Role) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePass = motDePass;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
        this.photo = photo;
        this.Role = Role;
        this.sexe = sexe;
    }

    public Utilisateur() {
    }

    public Utilisateur(int idUtilisateur, Organisation organisation, String nomUtilisateur, String motDePass, String nom, String prenom, String dateNaissance, int tel, String adresse, String mail, String photo, String Role, String sexe) {
        this.idUtilisateur = idUtilisateur;
        this.organisation = organisation;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePass = motDePass;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
        this.photo = photo;
        this.Role = Role;
        this.sexe = sexe;
    }

    public Utilisateur(String nomUtilisateur, String motDePass, String sexe, String nom, String prenom, String dateNaissance, int tel, String adresse, String mail, String photo, String Role) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePass = motDePass;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
        this.photo = photo;
        this.Role = Role;
        this.sexe = sexe;
    }

    public Utilisateur(int idUtilisateur, String nomUtilisateur, String motDePass, String nom, int tel, String adresse, String mail, String photo, String Role) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePass = motDePass;
        this.nom = nom;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
        this.photo = photo;
        this.Role = Role;
    }

    public Utilisateur(String nomUtilisateur, String motDePass, String nom, int tel, String adresse, String mail, String photo, String Role) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePass = motDePass;
        this.nom = nom;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
        this.photo = photo;
        this.Role = Role;
    }

    public Utilisateur(Utilisateur u) {
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
        this.Role = u.getRole();
        this.sexe = u.getSexe();
    }

//    public Utilisateur(Apprenant u) {
//        this.idUtilisateur = u.getIdUtilisateur();
//        this.nomUtilisateur = u.getNomUtilisateur();
//        this.motDePass = u.getMotDePass();
//        this.nom = u.getNom();
//        this.prenom = u.getPrenom();
//        this.dateNaissance = u.getDateNaissance();
//        this.tel = u.getTel();
//        this.adresse = u.getAdresse();
//        this.mail = u.getMail();
//        this.photo = u.getPhoto();
//        this.Role = u.getRole();
//        this.sexe = u.getSexe();
//    }
    public String toString() {
        return "Utilisateur{" + "idUtilisateur=" + idUtilisateur + ", nomUtilisateur=" + nomUtilisateur + ", motDePass=" + motDePass + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", tel=" + tel + ", adresse=" + adresse + ", mail=" + mail + ", photo=" + photo + '}';
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public int getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation idOrganisation) {
        this.organisation = idOrganisation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
