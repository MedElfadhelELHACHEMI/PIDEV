
package tessearctntest;

import java.util.Date;

/**
 *
 * @author Choukou_Tracker
 */
public class Evenement {
    private int idEvenement;
    private int idOrganisation;
    private String nom;
    private String description;
    private int nbrMax;
    private String affiche;
    private Date dateEvenement;
    
    
    public Evenement(){}
    public Evenement(int idEvenement, int idOrganisation, String nom, String description, int nbrMax, String affiche, Date dateEvenement) {
        this.idEvenement = idEvenement;
        this.idOrganisation = idOrganisation;
        this.nom = nom;
        this.description = description;
        this.nbrMax = nbrMax;
        this.affiche = affiche;
        this.dateEvenement = dateEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public int getIdOrganisation() {
        return idOrganisation;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public String getAffiche() {
        return affiche;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public void setIdOrganisation(int idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    
    
}
