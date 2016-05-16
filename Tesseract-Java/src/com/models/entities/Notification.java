
package com.models.entities;

import java.sql.Date;

public class Notification {
    
    private int idNotification;    
    private int idUtilisateur;
    private String notification;
    private Date dateNotification;
private String vue;
    public Notification() {
    }

    public Notification(int idNotification, int idUtilisateur, String notification, Date dateNotification) {
        this.idNotification = idNotification;
        this.idUtilisateur = idUtilisateur;
        this.notification = notification;
        this.dateNotification = dateNotification;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    @Override
    public String toString() {
        return "Notification{" + "idNotification=" + idNotification + ", idUtilisateur=" + idUtilisateur + ", notification=" + notification + ", dateNotification=" + dateNotification + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Notification other = (Notification) obj;
        if (this.idNotification != other.idNotification) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
