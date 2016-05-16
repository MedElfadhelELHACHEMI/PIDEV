/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.CommentaireCours;
import com.models.entities.Cours;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Sameh
 */
public class LikeCoursFXMLController implements Initializable{
 @FXML
    public  AnchorPane pane1;
 @FXML
public Label lCours;
  @FXML
public Label lname;
  private Cours cours;
     @FXML
    private TextArea textCom; 

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
       lname.setText(CurrentUser.getUtilisateur().getNom());
     lCours.setText(cours.getNomCours()); 
    }
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
     
    }
    @FXML
      void retourAction(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/CommCoursFXML.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                CommCoursFXMLController controller = fxmlLoader.<CommCoursFXMLController>getController();
                controller.setId_User(CurrentUser.getId());
               
                 pane1.getChildren().setAll(anchorPane); 
    } 
        @FXML
      void EnregAction(ActionEvent event) throws IOException {
       ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();  
       try {
       if (textCom.getText().isEmpty()==false){
           
           
               String[] lines = textCom.getText().split("\\n");
               String msg="";
               for(String s:lines){
                   msg = msg+s;
               }
               CommentaireCours cc=new CommentaireCours();
               cc.setIdCours(cours.getIdCours());
               cc.setIdUtilisateur(CurrentUser.getId());
               cc.setCommentaire(msg);
               boolean verif=serviceApprenant.AjouterCommentaireCours(cc);
             if (verif==true){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION); 
                 alert.setTitle("Message");
                 alert.setHeaderText(null);
            alert.setContentText("Message: Commentaire ajoutée avec succés.");
             alert.showAndWait();
             textCom.setText("");
             }  
             else {
                 
                  Alert alert = new Alert(Alert.AlertType.ERROR); 
                 alert.setTitle("Message");
                 alert.setHeaderText(null);
            alert.setContentText("Erreur: Une erreur s'est produite lors de l'insertion de comentaire");
             alert.showAndWait();
             }
           }  else{
            Alert alert = new Alert(Alert.AlertType.WARNING); 
                 alert.setTitle("Message");
                 alert.setHeaderText(null);
            alert.setContentText("Erreur: Veillez Saisir votre commentaire");
             alert.showAndWait();  
       }
           } catch (SQLException ex) {
               Logger.getLogger(LikeCoursFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
     
     
    }
 
}
