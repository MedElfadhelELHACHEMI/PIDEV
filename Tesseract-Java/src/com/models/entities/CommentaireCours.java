package com.models.entities;

import java.util.Date;

public class CommentaireCours {

    private int idCommentaire;
    private int idCours;
    private String commentaire;
    private Date dateCommentaire;
    private Cours cours;
  private int idUtilisateur;
    public CommentaireCours() {
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    

    public CommentaireCours(String commentaire, Date dateCommentaire) {
        this.commentaire = commentaire;
        this.dateCommentaire = dateCommentaire;
    }

    public CommentaireCours(int idCommentaire, int idCours, String commentaire, Date dateCommentaire, Cours cours) {
        this.idCommentaire = idCommentaire;
        this.idCours = idCours;
        this.commentaire = commentaire;
        this.dateCommentaire = dateCommentaire;
        this.cours = cours;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idCommentaire;
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
        final CommentaireCours other = (CommentaireCours) obj;
        if (this.idCommentaire != other.idCommentaire) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentaireCours{" + "idCommentaire=" + idCommentaire + ", idCours=" + idCours + ", commentaire=" + commentaire + ", dateCommentaire=" + dateCommentaire + ", cours=" + cours + '}';
    }

}
