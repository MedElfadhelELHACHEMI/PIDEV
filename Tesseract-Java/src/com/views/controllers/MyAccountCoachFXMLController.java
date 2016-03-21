/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.database.CryptographieMOOC;
import com.jfoenix.controls.JFXToggleButton;
import com.models.entities.Formateur;
import com.models.entities.Organisation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class MyAccountCoachFXMLController implements Initializable {

    @FXML
    private Pane bodyAcc;
    @FXML
    private Button btsignout;

    private ImageView profimImg = new ImageView();
    @FXML
    private Label labelNom;
    Rectangle rectangle = new Rectangle(200, 200);
    @FXML
    private AnchorPane body;
    @FXML
    private ImageView organisationPict;
    @FXML
    private Label nomOrganisation;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField number;
    @FXML
    private TextField adress;
    @FXML
    private TextField mail;
    @FXML
    private JFXToggleButton chngePwd;
    @FXML
    private PasswordField pwd1;
    @FXML
    private PasswordField pwd2;
    @FXML
    private Button sumit;
    @FXML
    private Label pwdl1;
    @FXML
    private Label pwdl2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(" ----------------  "+CurrentUser.getUtilisateur().getNom());
        IServiceFormateurs isf = new IServiceFormateursImpl();
        Organisation o = isf.getOrganisationCoach(CurrentUser.getUtilisateur());
        if (o.getNom() == null) {
            nomOrganisation.setText("You Don't belong to any organisation for the moment");
        } else {
            nomOrganisation.setText(o.getNom() + " is your organisation ");
            organisationPict.setImage(new Image(new File(o.getPhoto()).toURI().toString()));
        }
        labelNom.setText("Welcome " + CurrentUser.getUtilisateur().getPrenom() + " " + CurrentUser.getUtilisateur().getNom());
  try {
            profimImg.setImage(new Image(new File(CurrentUser.getUtilisateur().getPhoto()).toURI().toString()));
        } catch (NullPointerException e) {

            Alert alert = new Alert(Alert.AlertType.WARNING, "No Picture", ButtonType.OK);
            alert.show();
        }   
        Circle circle = new Circle(100);
        circle.setLayoutX(123);
        circle.setLayoutY(124);
        circle.setFill(new ImagePattern(new Image(new File(CurrentUser.getUtilisateur().getPhoto()).toURI().toString())));

        circle.setStyle(" -fx-border-top-style: solid;\n"
                + "    -fx-border-color: black;");
        body.getChildren().add(circle);
        //   chngePwd
        fillTextField();
        if (chngePwd.isArmed()) {
            System.out.println("Armed");
            pwdl1.setVisible(false);
            pwdl2.setVisible(false);
            pwd1.setVisible(false);
            pwd2.setVisible(false);
            System.out.println("dis");
        }
        chngePwd.setOnAction((ActionEvent event) -> {
            if (chngePwd.isSelected()) {
                pwdl1.setVisible(false);
                pwdl2.setVisible(false);
                pwd1.setVisible(false);
                pwd2.setVisible(false);
            } else {
                pwdl1.setVisible(true);
                pwdl2.setVisible(true);
                pwd1.setVisible(true);
                pwd2.setVisible(true);
            }
        });
        sumit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                IServiceFormateurs isf = new IServiceFormateursImpl();

                Formateur formateur = new Formateur();
                formateur.setNom(nom.getText());
                formateur.setPrenom(prenom.getText());
                formateur.setMail(mail.getText());
                formateur.setAdresse(adress.getText());
                formateur.setTel(Integer.valueOf(number.getText()));
                if(chngePwd.isSelected()==false){
                if((pwd1.getText().equals(pwd2.getText()))&&(pwd1.getText().length()>8)){
                    try {
                        formateur.setMotDePass(CryptographieMOOC.getCryptage().encrypt(pwd1.getText()));
                    } catch (Exception ex) {
                        Logger.getLogger(MyAccountCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (isf.updateFormateurInformationsWithPass(formateur)) {

                    CurrentUser.setUtilisateur(isf.getUtilisateurById(CurrentUser.getId()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modified", ButtonType.OK);
                    alert.show();
                    fillTextField();
                    

                }
                }else{
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "pasword must be > 8 and equals", ButtonType.OK);
                    alert.show();
                }
                }else{
                if (isf.updateFormateurInformations(formateur)) {

                    CurrentUser.setUtilisateur(isf.getUtilisateurById(CurrentUser.getId()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modified", ButtonType.OK);
                    alert.show();
                    fillTextField();
                    

                }
            }}
        });
    }

    @FXML
    private void onClickSignout(ActionEvent event) {
        try {
                Stage stage = new Stage();
                Parent root1 = FXMLLoader.load(getClass().getResource("/com/fxml/LogInFXML.fxml"));
                Scene scene1 = new Scene(root1);
                stage.setScene(scene1);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
                sumit.getScene().getWindow().hide();
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void onClickSubmit(ActionEvent event) {
    }

    private void fillTextField() {
        nom.setText(CurrentUser.getUtilisateur().getNom());
        prenom.setText(CurrentUser.getUtilisateur().getPrenom());
        mail.setText(CurrentUser.getUtilisateur().getMail());
        adress.setText(CurrentUser.getUtilisateur().getAdresse());
        number.setText(String.valueOf(CurrentUser.getUtilisateur().getTel()));
    }

}
