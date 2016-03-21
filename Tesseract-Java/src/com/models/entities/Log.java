
package com.models.entities;

import java.sql.Date;


public class Log {

    private int idLog;
    private int idUtilisateur;
    private String Tache;
    private Date dateTache;

    public Log() {
    }

    public Log(int idUtilisateur, String Tache, Date dateTache) {
        this.idUtilisateur = idUtilisateur;
        this.Tache = Tache;
        this.dateTache = dateTache;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getTache() {
        return Tache;
    }

    public void setTache(String Tache) {
        this.Tache = Tache;
    }

    public Date getDateTache() {
        return dateTache;
    }

    public void setDateTache(Date dateTache) {
        this.dateTache = dateTache;
    }

    @Override
    public String toString() {
        return "Log{" + "idLog=" + idLog + ", idUtilisateur=" + idUtilisateur + ", Tache=" + Tache + ", dateTache=" + dateTache + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idLog;
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
        final Log other = (Log) obj;
        if (this.idLog != other.idLog) {
            return false;
        }
        return true;
    }

}
