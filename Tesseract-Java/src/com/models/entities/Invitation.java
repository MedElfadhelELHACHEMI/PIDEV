
package com.models.entities;

import java.sql.Date;
import java.util.Objects;

public class Invitation {
    private int idUtilisateur;
    private int idOrganisation;
    private String sens ; 
    private String etat ; 
    private Date dateInvitation ; 

    public Invitation() {
    }

    public Invitation(int idUtilisateur, int idOrganisation, String sens, String etat, Date dateInvitation) {
        this.idUtilisateur = idUtilisateur;
        this.idOrganisation = idOrganisation;
        this.sens = sens;
        this.etat = etat;
        this.dateInvitation = dateInvitation;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdOrganisation() {
        return idOrganisation;
    }

    public void setIdOrganisation(int idOrganisation) {
        this.idOrganisation = idOrganisation;
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

    public Date getDateInvitation() {
        return dateInvitation;
    }

    public void setDateInvitation(Date dateInvitation) {
        this.dateInvitation = dateInvitation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Invitation other = (Invitation) obj;
        if (this.idUtilisateur != other.idUtilisateur) {
            return false;
        }
        if (this.idOrganisation != other.idOrganisation) {
            return false;
        }
        if (!Objects.equals(this.sens, other.sens)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.dateInvitation, other.dateInvitation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invitation{" + "idUtilisateur=" + idUtilisateur + ", idOrganisation=" + idOrganisation + ", sens=" + sens + ", etat=" + etat + ", dateInvitation=" + dateInvitation + '}';
    }
    
    
    
    
}
