/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.entities;

import java.util.Date;

public class BanForum {

    private int idBan;
    private int idUtilisateur;
    private Date dateBan;
    private String Cause;
    private int duree;
 

    public BanForum() {
    }



    public BanForum(Date dateBan, int duree) {
        this.dateBan = dateBan;
        this.duree = duree;
       
    }

  
 
    public BanForum(int idUtilisateur, Date dateBan, String Cause, int duree, Utilisateur utilisateur) {

        this.idUtilisateur = idUtilisateur;
        this.dateBan = dateBan;
        this.Cause = Cause;
        this.duree = duree;
        
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Date getDateBan() {
        return dateBan;
    }

    public void setDateBan(Date dateBan) {
        this.dateBan = dateBan;
    }

    public String getCause() {
        return Cause;
    }

    public void setCause(String Cause) {
        this.Cause = Cause;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idBan;
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
        final BanForum other = (BanForum) obj;
        if (this.idBan != other.idBan) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {


        return "BanForum{" + "idBan=" + idBan + ", idUtilisateur=" + idUtilisateur + ", dateBan=" + dateBan + ", Cause=" + Cause + ", duree=" + duree + " '}'";

   }
    

}
