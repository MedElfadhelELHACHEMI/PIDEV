/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;



/**
 *
 * @author haikal
 */
public class Organisation {
    private int idOrganisation;
    private String nom;
    private String adresse;
    private String matricule;
    private String photo;
    private String eMail ;
   
    public Organisation(){}
    public Organisation(int idOrganisation, String nom, String adresse, String matricule, String photo) {
        this.idOrganisation = idOrganisation;
        this.nom = nom;
        this.adresse = adresse;
        this.matricule = matricule;
        this.photo = photo;
    }

    public int getIdOrganisation() {
        return idOrganisation;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getPhoto() {
        return photo;
    }

    public void setIdOrganisation(int idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    

   
    
}
