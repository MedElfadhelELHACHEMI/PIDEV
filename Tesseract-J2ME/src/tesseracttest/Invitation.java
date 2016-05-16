
package tesseracttest;

import java.util.Date;



public class Invitation {
    private int idInvitation ;
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

    public Date getDateInvitation() {
        return dateInvitation;
    }

    public void setDateInvitation(Date dateInvitation) {
        this.dateInvitation = dateInvitation;
    }

    public String toString() {
        return "Invitation{" + "idUtilisateur=" + idUtilisateur + ", idOrganisation=" + idOrganisation + ", sens=" + sens + ", etat=" + etat + ", dateInvitation=" + dateInvitation + '}';
    }

   
    
    
    
    
}
