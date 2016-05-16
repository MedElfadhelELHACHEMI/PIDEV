package tesseracttest;

import java.util.Date;

public class Formateur extends Utilisateur {

    private String cv;
    private String etat;
    private int score;

    public Formateur() {
    }
    private int idOrgganisation;

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Formateur(String cv, String etat, int idUtilisateur, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(idUtilisateur, nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.cv = cv;
        this.etat = etat;
    }

    public int getIdOrgganisation() {
        return idOrgganisation;
    }

    public void setIdOrgganisation(int idOrgganisation) {
        this.idOrgganisation = idOrgganisation;
    }

    public Formateur(String cv, String etat, String nomUtilisateur, String motDePass, String nom, String prenom, Date dateNaissance, int tel, String adresse, String mail, String photo) {
        super(nomUtilisateur, motDePass, nom, prenom, dateNaissance, tel, adresse, mail, photo);
        this.cv = cv;
        this.etat = etat;
    }

}
