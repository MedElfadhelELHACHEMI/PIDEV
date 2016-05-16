/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.models.entities.Apprenant;
import com.models.entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Sameh
 */
public class ApprenantUpdateFXMLController implements Initializable{
@FXML
    private JFXTextField Name;
  @FXML
    private JFXTextField LastN;
   private static String picture;
  @FXML
    private JFXTextField Login;
  
    @FXML
    private JFXTextField Phone;
     @FXML
    private JFXTextField Add;
      @FXML
    private JFXTextField Emai;
       @FXML
    private DatePicker birth;
       @FXML
    private JFXPasswordField pass;
        @FXML
    private ImageView desktop;
          @FXML
    private Button ButMap;
     @FXML
     private ImageView imgUser;
      @FXML
    private Button   btretour;
    @FXML
    private Button   Enreg;
    @FXML
    private JFXToggleButton togName;
    @FXML
    private JFXToggleButton toggLastN;
    @FXML
    private JFXToggleButton toggLogin;
    @FXML
    private JFXToggleButton toggPhone;
    @FXML
    private JFXToggleButton ToggAdd;
    @FXML
    private JFXToggleButton toggEmai;
    @FXML
    private JFXToggleButton toggPass;
   @FXML
    private JFXToggleButton toggDate;
     @FXML
    private AnchorPane pane1;
      @Override
    public void initialize(URL location, ResourceBundle resources) {
    try {
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        Apprenant ap=serviceApprenant.getApprenantByLogin(CurrentUser.getUtilisateur().getNomUtilisateur());
        birth.setEditable(false);
        Name.setText(ap.getNom());
        LastN.setText(ap.getPrenom());
        Login.setText(ap.getNomUtilisateur());
        LocalDate localDate = LocalDate.parse(ap.getDateNaissance().toString());
        birth.setValue(localDate);
        Add.setText(ap.getAdresse());
        Emai.setText(ap.getMail());
        Phone.setText(ap.getTel()+"");
        pass.setText(ap.getMotDePass());
        imgUser.setImage(getImageUtilisateur(CurrentUser.getUtilisateur()));
    } catch (SQLException ex) {
        Logger.getLogger(ApprenantUpdateFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
        @FXML
      void togNameAction(ActionEvent event) throws IOException {
      if (Name.isEditable()==true){
       Name.setEditable(false);
      }
      else  if (Name.isEditable()==false){
       Name.setEditable(true);
      }
    } 
        @FXML
      void toggLastNAction(ActionEvent event) throws IOException {
       if (LastN.isEditable()==true){
       LastN.setEditable(false);
      }
      else  if (LastN.isEditable()==false){
       LastN.setEditable(true);
      }
    } 
        @FXML
      void toggLoginAction(ActionEvent event) throws IOException {
       if (Login.isEditable()==true){
       Login.setEditable(false);
      }
      else  if (Login.isEditable()==false){
       Login.setEditable(true);
      }
    } 
        @FXML
      void toggPhoneAction(ActionEvent event) throws IOException {
       if (Phone.isEditable()==true){
       Phone.setEditable(false);
      }
      else  if (Phone.isEditable()==false){
       Phone.setEditable(true);
      }
    } 
        @FXML
      void ToggAddAction(ActionEvent event) throws IOException {
      if (Add.isEditable()==true){
      Add.setEditable(false);
      }
      else  if (Add.isEditable()==false){
       Add.setEditable(true);
      } 
    }   @FXML
      void toggEmaiAction(ActionEvent event) throws IOException {
        if (Emai.isEditable()==true){
      Emai.setEditable(false);
      }
      else  if (Emai.isEditable()==false){
       Emai.setEditable(true);
      }  
    } 
        @FXML
      void toggPassAction(ActionEvent event) throws IOException {
        if (pass.isEditable()==true){
      pass.setEditable(false);
      }
      else  if (pass.isEditable()==false){
       pass.setText("");
       pass.setEditable(true);
      }  
    } 
          @FXML
      void toggDateAction(ActionEvent event) throws IOException {
       if (birth.isEditable()==true){
      birth.setEditable(false);
      }
      else  if (birth.isEditable()==false){
       birth.setEditable(true);
      }
    } 
        @FXML
      void EnregAction(ActionEvent event) throws IOException {
    try {
        Apprenant apprenant = new Apprenant();
        apprenant.setNom(Name.getText());
        apprenant.setPrenom(LastN.getText());
        apprenant.setAdresse(Add.getText());
        apprenant.setMail(Emai.getText());
        apprenant.setNomUtilisateur(Login.getText());
        apprenant.setTel(Integer.parseInt(Phone.getText()));
        apprenant.setMotDePass(pass.getText());
        apprenant.setDateNaissance(Date.valueOf(birth.getValue()));
        apprenant.setPhoto(picture);
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        boolean resultat=serviceApprenant.modifierApprenant(CurrentUser.getUtilisateur().getNomUtilisateur(), apprenant);
           if (resultat) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Update with succes", ButtonType.FINISH);
          alert.show();
          setMain(loadNode("/com/fxml/ProfilApprenantFXML.fxml"));  
           }
    } catch (SQLException ex) {
        Logger.getLogger(ApprenantUpdateFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
    } 
         @FXML
    private void searchDesktop(MouseEvent event) {

        FileChooser fc = new FileChooser();
        fc.setTitle("researching the image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
        File file = fc.showOpenDialog(null);
        imgUser.setImage(new Image(new File(file.getAbsolutePath()).toURI().toString()));
        picture = file.getAbsolutePath();

    }
}
