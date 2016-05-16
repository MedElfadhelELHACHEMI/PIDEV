
package tessearctntest;




public class Chapitre {
    private int idChapitre;
    private int idCours;
    private String nom;
    private int numero;
    private String description;
    private String resume;

    public Chapitre() {
    }

    public Chapitre(int idChapitre, int idCours, String nom, int numero, String description, String resume) {
        this.idChapitre = idChapitre;
        this.idCours = idCours;
        this.nom = nom;
        this.numero = numero;
        this.description = description;
        this.resume = resume;
    }

    public int getIdChapitre() {
        return idChapitre;
    }

    public void setIdChapitre(int idChapitre) {
        this.idChapitre = idChapitre;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    
    
    
}
