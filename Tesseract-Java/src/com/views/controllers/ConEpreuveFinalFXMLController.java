/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Chapitre;
import com.models.entities.Cours;
import com.models.entities.EpreuveFinal;
import com.models.entities.SessionEpreuve;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ConEpreuveFinalFXMLController implements Initializable {
@FXML
    public  AnchorPane pane1;
 @FXML
public Label lCours;
  @FXML
public Label lname;
  @FXML
private ComboBox tyEpEntenaiment;
   @FXML
public Label textTyoeEp;
     @FXML
private ComboBox choixDiff;
   @FXML
public Label textDiff;
public Chapitre chapitre;
  private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
  
    private Cours cours;
       public Cours getCours() {
        return cours;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
      if (type.equals("Entrainement") || type.equals("Objectifs")) { 
             ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Chronometré",
        "Normale"
         
     );
   tyEpEntenaiment.getItems().addAll(options);
    lname.setText(CurrentUser.getUtilisateur().getNom());
    lCours.setText(cours.getNomCours()); 
    ObservableList<String> optionsdiff = 
    FXCollections.observableArrayList(
        "FACILE",
        "NORMALE",
        "DIFFICILE"
         
     );
   choixDiff.getItems().addAll(optionsdiff);
      }
      else if (type.equals("Final")){
       lname.setText(CurrentUser.getUtilisateur().getNom());
    lCours.setText(cours.getNomCours()); 
    tyEpEntenaiment.setVisible(false);
    textTyoeEp.setVisible(false);
    choixDiff.setVisible(false);
    textDiff.setVisible(false);
      }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
      void retourAction(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChoixCoursEpFinal.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChoixCoursEpFinalController controller = fxmlLoader.<ChoixCoursEpFinalController>getController();
                controller.setType(type);
                controller.setId_User(CurrentUser.getId());
               
                 pane1.getChildren().setAll(anchorPane); 
    } 
   @FXML
  void validerAction(ActionEvent event) throws IOException {
    try {
       if (type.equals("Final")){ 
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        EpreuveFinal epreuveFinal=serviceApprenant.searchEpreuveFinalByCours(cours.getIdCours());
        System.out.println("fffffffffffffffffffffffffffffffff"+epreuveFinal.getId());
        SessionEpreuve epreuve=new SessionEpreuve();
        epreuve.setId_epreuve(epreuveFinal.getId());
        epreuve.setId_utilisateur(CurrentUser.getId());
        boolean verif=serviceApprenant.ajouterSessionEpreuve(epreuve);
        if (verif==true){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/EpreuvePlayFXML.fxml"));
            
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            
            EpreuvePlayFXMLController controller = fxmlLoader.<EpreuvePlayFXMLController>getController();
            controller.setType(type);
            controller.setCours(cours);
           
            pane1.getChildren().setAll(anchorPane);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Erreur: Une erreur s'est produite lors de l'insertion");
            alert.showAndWait();
        }}
       else if (type.equals("Entrainement")){
        if (tyEpEntenaiment.getValue().toString().equals("Chronometré")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/EpreuvePlayFXML.fxml"));
            
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            
            EpreuvePlayFXMLController controller = fxmlLoader.<EpreuvePlayFXMLController>getController();
            controller.setType(type);
            controller.setTypeDiffc(choixDiff.getValue().toString());
            controller.setCours(cours);
            pane1.getChildren().setAll(anchorPane);
           
        }
        else if (tyEpEntenaiment.getValue().toString().equals("Normale")){
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/EpreuveNonChronoPlayfxml.fxml"));
            
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            
            EpreuveNonChronoPlayfxmlController controller = fxmlLoader.<EpreuveNonChronoPlayfxmlController>getController();
             controller.setTypeDiffc(choixDiff.getValue().toString());
            controller.setType(type);
            
        controller.setCours(cours);
            
            pane1.getChildren().setAll(anchorPane);   
        }
        else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Erreur: vous devez choisir Le Type ");
            alert.showAndWait();  
        }
       }
       else if (type.equals("Objectifs")){
        if (tyEpEntenaiment.getValue().toString().equals("Chronometré")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/EpreuvePlayFXML.fxml"));
            
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            
            EpreuvePlayFXMLController controller = fxmlLoader.<EpreuvePlayFXMLController>getController();
            controller.setType(type);
            controller.setTypeDiffc(choixDiff.getValue().toString());
              controller.setChapitre(chapitre);
            controller.setCours(cours);
            pane1.getChildren().setAll(anchorPane);
           
        }
        else if (tyEpEntenaiment.getValue().toString().equals("Normale")){
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/EpreuveNonChronoPlayfxml.fxml"));
            
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            
            EpreuveNonChronoPlayfxmlController controller = fxmlLoader.<EpreuveNonChronoPlayfxmlController>getController();
             controller.setTypeDiffc(choixDiff.getValue().toString());
            controller.setType(type);
            controller.setChapitre(chapitre);
        controller.setCours(cours);
            
            pane1.getChildren().setAll(anchorPane);   
        }
        else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Erreur: vous devez choisir Le Type ");
            alert.showAndWait();  
        }
       } 
    
    } catch (SQLException ex) {
        Logger.getLogger(ConEpreuveFinalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }      
}
