/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.models.entities.Reclamation;
import com.models.entities.Utilisateur;
import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ReclamationInsertfxmlController implements Initializable {

  @FXML
    private JFXTextField Sujet;
   @FXML
   private JFXComboBox cmbtype;
     @FXML
    private AnchorPane pane1;
    @FXML
    private TextArea contenu;
     @FXML
    private ImageView imguser;
     @FXML
    private Label name;
     
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     name.setText(CurrentUser.getUtilisateur().getNom()+" & "+CurrentUser.getUtilisateur().getPrenom());
     imguser.setImage(getImageUtilisateur(CurrentUser.getUtilisateur()));
      ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Technique",
        "tye2",
         "tyo3"
        
     );
     cmbtype.getItems().addAll(options);
    }     
    private Image getImageUtilisateur(Utilisateur utilisateur) {
    File file = new File(utilisateur.getPhoto());
        return new Image(file.toURI().toString());
    }
    @FXML
      void  validerAction(ActionEvent event) throws IOException {
       if ((Sujet.getText().equals("")) &&(contenu.getText().equals("")) ){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Champs invalides", ButtonType.FINISH);
          alert.show();
       }
       else{
           try {
               ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
               Reclamation r=new Reclamation();
               r.setIdUtilisateur(CurrentUser.getId());
               r.setSujet(Sujet.getText());
               r.setDescription(contenu.getText());
               boolean verif=serviceApprenant.ajouterReclamation(r,CurrentUser.getId());
              if (verif){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Reclamation envoyée", ButtonType.FINISH);
          alert.show(); 
          Sujet.setText("");
          contenu.setText("");
                  
                  
              } 
           } catch (SQLException ex) {
               Logger.getLogger(ReclamationInsertfxmlController.class.getName()).log(Level.SEVERE, null, ex);
           }
         
         
       }
      }
}
