/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.models.entities.Apprenant;
import com.models.entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Sameh
 */
public class ApprenantProfilFXMLController implements Initializable{
 @FXML
    private JFXTextField Name;
  @FXML
    private JFXTextField LastN;
  
  @FXML
    private JFXTextField Login;
   @FXML
    private JFXTextField Birth;
    @FXML
    private JFXTextField Phone;
     @FXML
    private JFXTextField Add;
      @FXML
    private JFXTextField Emai;
       @FXML
    private AnchorPane pane1;
        @FXML
    private Button   btretour;
    @FXML
    private Button ButMap;
     @FXML
     private ImageView imgUser;
      @FXML
     private ProgressBar Score;
//      @FXML
//      private JFXToggleButton a;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     try {
         ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
         Apprenant ap=serviceApprenant.getApprenantByLogin(CurrentUser.getUtilisateur().getNomUtilisateur());
        Name.setText(ap.getNom());
        LastN.setText(ap.getPrenom());
        Login.setText(ap.getNomUtilisateur());
        Birth.setText(ap.getDateNaissance().toString());
        Add.setText(ap.getAdresse());
        Emai.setText(ap.getMail());
        Phone.setText(ap.getTel()+"");
        Score.setProgress(ap.getScore());
        imgUser.setImage(getImageUtilisateur(CurrentUser.getUtilisateur()));
       
     } catch (SQLException ex) {
         Logger.getLogger(ApprenantProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    private Image getImageUtilisateur(Utilisateur utilisateur) {
    File file = new File(utilisateur.getPhoto());
        return new Image(file.toURI().toString());
    }
    @FXML
    void MapAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/MoocMapFXML.fxml"));  
    }
    
  private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
        pane1.getChildren().setAll(node);

    }
       @FXML
      void retourAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  
    }  
}
