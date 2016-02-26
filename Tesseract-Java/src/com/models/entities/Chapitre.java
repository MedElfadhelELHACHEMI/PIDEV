
package com.models.entities;

import java.util.List;
import javafx.beans.property.*;


public class Chapitre {
   private final IntegerProperty id; 
   private final IntegerProperty idCours ;
   private final StringProperty nom ;
   private final IntegerProperty numero ; 
   private final StringProperty description ;
   private final StringProperty resume ;
   
   public Chapitre(){
       this(-1,-1,null,-1,null,null);
   }

   /**
    * Constructor with initial data.
    * 
    * @param id
    * @param idCours
    * @param nom
    * @param numero
    * @param description
    * @param resume
    */
   public Chapitre(int id, int idCours, String nom, int numero, String description, String resume) {
        this.id = new SimpleIntegerProperty(id);
        this.idCours = new SimpleIntegerProperty(idCours);
        this.nom = new SimpleStringProperty(nom);
        this.numero = new SimpleIntegerProperty(numero);
        this.description = new SimpleStringProperty(description);
        this.resume = new SimpleStringProperty(resume);
    }

    public IntegerProperty IdProperty() {
        return id;
    }

    public IntegerProperty IdCoursProperty() {
        return idCours;
    }

    public StringProperty NomProperty() {
        return nom;
    }

    public IntegerProperty NumeroProperty() {
        return numero;
    }

    public StringProperty DescriptionProperty() {
        return description;
    }

    public StringProperty ResumeProperty() {
        return resume;
    }

    public int getId() {
        return id.get();
    }

    public int getIdCours() {
        return idCours.get();
    }

    public String getNom() {
        return nom.get();
    }

    public int getNumero() {
        return numero.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getResume() {
        return resume.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public void setIdCours(int idCours) {
        this.idCours.set(idCours);
    }
    
    public void setNumero(int numero) {
        this.numero.set(numero);
    }
    
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public void setResume(String resume) {
        this.resume.set(resume);
    }
    
    
    
    
    
    
    
   
   
}
