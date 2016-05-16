/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.SessionCours;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class InscrireCoursfxmlController implements Initializable {
 private Cours Cours;
 @FXML
 private AnchorPane pane1;
   @FXML
    private Label lCours1;
   @FXML
    private Label lname;
    Formateur f;
   
 String type;
    public Cours getCours() {
        return Cours;
    }

    public void setCours(Cours Cours) {
        this.Cours = Cours;
      lname.setText(CurrentUser.getUtilisateur().getNom());
      lCours1.setText(Cours.getNomCours());
      
    }

    public void setF(Formateur f) {
        this.f = f;
    }

    public Formateur getF() {
        return f;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    } 
     @FXML
      void Enregistrer(ActionEvent event) throws IOException {
       ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
       SessionCours sc=new SessionCours();
       sc.setId_utilisateur(CurrentUser.getId());
       sc.setId_cours(Cours.getIdCours());
       boolean verif=serviceApprenant.ajouterSessionCours(sc);
       if (verif==true){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Inscription avec succes");
            alert.showAndWait();
            if(type.equals("fc")){
              try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/CoordoFormateurfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                CoordoFormateurfxmlController controller = fxmlLoader.<CoordoFormateurfxmlController>getController();
                 controller.setName(f.getNom());
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }    
            }
            else{   try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                 controller.setType(type);
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }   }
       }
       else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Erreur: Une erreur s'est produite lors de l'insertion");
            alert.showAndWait();  
       }
      } 
    
    @FXML
      void retourAction(ActionEvent event) throws IOException {
       if(type.equals("fc")){
              try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/CoordoFormateurfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                CoordoFormateurfxmlController controller = fxmlLoader.<CoordoFormateurfxmlController>getController();
                 controller.setName(f.getNom());
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }    
            }
            else{   try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                 controller.setType(type);
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }   }
    }  
      
}
