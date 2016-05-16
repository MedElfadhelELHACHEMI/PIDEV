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
public class CommentaireCours {
     private int idCommentaire;
    private int idCours;
    private String commentaire;
    private Date dateCommentaire;
    private int  idUtilisateur; 

    public CommentaireCours(int idCommentaire, int idCours, String commentaire, Date dateCommentaire, int idUtilisateur) {
        this.idCommentaire = idCommentaire;
        this.idCours = idCours;
        this.commentaire = commentaire;
        this.dateCommentaire = dateCommentaire;
        this.idUtilisateur = idUtilisateur;
    }

    public CommentaireCours(int idCours, String commentaire, Date dateCommentaire, int idUtilisateur) {
        this.idCours = idCours;
        this.commentaire = commentaire;
        this.dateCommentaire = dateCommentaire;
        this.idUtilisateur = idUtilisateur;
    }

    public CommentaireCours() {
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public int getIdCours() {
        return idCours;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String toString() {
        return "CommentaireCours{" + "idCommentaire=" + idCommentaire + ", idCours=" + idCours + ", commentaire=" + commentaire + ", dateCommentaire=" + dateCommentaire + ", idUtilisateur=" + idUtilisateur + '}';
    }
    
}
