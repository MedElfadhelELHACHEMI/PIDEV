/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

/**
 *
 * @author Maynoo
 */
public class Organisation {

    int idOrganisation, telephone;
    String nom, adress, email, matricule, photo;

    public Organisation() {
    }

    public Organisation(int idOrganisation, int telephone, String nom, String adress, String email, String matricule, String photo) {
        this.idOrganisation = idOrganisation;
        this.telephone = telephone;
        this.nom = nom;
        this.adress = adress;
        this.email = email;
        this.matricule = matricule;
        this.photo = photo;
    }

    public int getIdOrganisation() {
        return idOrganisation;
    }

    public void setIdOrganisation(int idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
