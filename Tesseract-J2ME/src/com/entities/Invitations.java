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
public class Invitations {

    int idInvitation;
    String sens, etat, date;
    Organisation organisation;
    Utilisateur utilisateur;

    public Invitations() {
    }

    public Invitations(int idInvitation, String sens, String etat, String date, Organisation organisation, Utilisateur utilisateur) {
        this.idInvitation = idInvitation;
        this.sens = sens;
        this.etat = etat;
        this.date = date;
        this.organisation = organisation;
        this.utilisateur = utilisateur;
    }

    public int getIdInvitation() {
        return idInvitation;
    }

    public void setIdInvitation(int idInvitation) {
        this.idInvitation = idInvitation;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
