package tessearctntest;


public class Cours {

    private int idCours;
    private String badge;
    private String video;
    private String nomCours;
    private String difficulte;
    private String descriptionCours;
    private int idMatiere;
    private String affiche;
     private String nomMatiere;

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    private int idFormateur;

    public Cours() {

    }

    public Cours(int idCours, String badge, String video, String nomCours, String difficulte, String descriptionCours, int idMatiere, String affiche, int idFormateur) {
        this.idCours = idCours;
        this.badge = badge;
        this.video = video;
        this.nomCours = nomCours;
        this.difficulte = difficulte;
        this.descriptionCours = descriptionCours;
        this.idMatiere = idMatiere;
        this.affiche = affiche;
        this.idFormateur = idFormateur;
    }

    
    public int getIdCours() {
        return idCours;
    }


    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getBadge() {
        return badge;
    }

  
    public void setBadge(String badge) {
        this.badge = badge;
    }

    
    public String getNomCours() {
        return nomCours;
    }

   
    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    /**
     * @return the difficulte
     */
    public String getDifficulte() {
        return difficulte;
    }

    /**
     * @param difficulte the difficulte to set
     */
    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    
    /**
     * @return the descriptionCours
     */
    public String getDescriptionCours() {
        return descriptionCours;
    }

    /**
     * @param descriptionCours the descriptionCours to set
     */
    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours = descriptionCours;
    }

    /**
     * @return the idMatiere
     */
    public int getIdMatiere() {
        return idMatiere;
    }

    /**
     * @param idMatiere the idMatiere to set
     */
    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    /**
     * @return the affiche
     */
    public String getAffiche() {
        return affiche;
    }

    /**
     * @param affiche the affiche to set
     */
    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    /**
     * @return the idFormateur
     */
    public int getIdFormateur() {
        return idFormateur;
    }

    /**
     * @param idFormateur the idFormateur to set
     */
    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

}
