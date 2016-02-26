
package com.models.entities;

import java.sql.Date;
import java.util.Objects;


public class CommentaireForum {

    
    private int id;
    private Utilisateur utilisateur;
    private SujetForum sujetforum;
    private String contenu;
    private Date date;

    public CommentaireForum() {
    }

    public CommentaireForum(int id,Utilisateur utilisateur, SujetForum sujetforum, String contenu, Date date) {
        this.id=id;
        this.utilisateur = utilisateur;
        this.sujetforum = sujetforum;
        this.contenu = contenu;
        this.date = date;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setSujetforum(SujetForum sujetforum) {
        this.sujetforum = sujetforum;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public SujetForum getSujetforum() {
        return sujetforum;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.utilisateur);
        hash = 37 * hash + Objects.hashCode(this.sujetforum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CommentaireForum other = (CommentaireForum) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.utilisateur, other.utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.sujetforum, other.sujetforum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentaireForum{" + "id=" + id + ", utilisateur=" + utilisateur + ", sujetforum=" + sujetforum + ", contenu=" + contenu + ", date=" + date + '}';
    }
    
}
