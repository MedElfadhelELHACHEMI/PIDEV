/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.InscriptionUtilisateurs;
import com.controllers.RedirectionStrategy;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.models.entities.Apprenant;
import com.models.entities.Formateur;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class InscriptionFormateurFXMLController implements Initializable {

    @FXML
    private JFXButton bt1;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField numberPh;
    @FXML
    private JFXTextField addresse;
    @FXML
    private DatePicker birth;
    @FXML
    private Label Warning;
    @FXML
    private Pane pane2;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private ImageView desktop;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextField mail;
    private RotateTransition rotateTransition;
    
    @FXML
    private Label Warning2;
    @FXML
    private AnchorPane pane1;
    @FXML
    private JFXTextField addresse1;
    private static String picture;
    InscriptionUtilisateurs inscriptionUtilisateurs = new InscriptionUtilisateurs();
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        generateListeners();
    }

    private void foToshtep2(ActionEvent event) {
     

    }

    @FXML
    private void goToStep1(ActionEvent event) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1));
        translateTransition.setNode(pane1);
        translateTransition.setCycleCount(1);
        translateTransition.setFromX(550);
        translateTransition.setToX(0);
        translateTransition.play();
    }

    private void verif(Color PINK, Color valueOf, JFXTextField nom, String newValue) {
        if (newValue.equals("")) {
            nom.setUnFocusColor(Color.PINK);
        } else {
            nom.setUnFocusColor(Color.valueOf("#24c5cf"));
        }
    }

    private void verif(Color PINK, Color valueOf, JFXPasswordField nom, String newValue) {
        if (newValue.equals("")) {
            nom.setUnFocusColor(Color.PINK);
        } else {
            nom.setUnFocusColor(Color.valueOf("#24c5cf"));
        }

    }

    private void generateListeners() {
        nom.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), nom, newValue);
            if (!nom.getText().matches("[a-zA-Z]+")) {
                Warning.setText("invalide name");
                nom.setText("");
            } else {
                Warning.setText("");
            }

        });
        prenom.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), prenom, newValue);
            if (!prenom.getText().matches("[a-zA-Z]+")) {
                Warning.setText(" invalide Lastname");
                prenom.setText("");
            } else {
                Warning.setText("");
            }

        });
        numberPh.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), numberPh, newValue);
            if (!numberPh.getText().matches("[1-9]+")) {
                Warning.setText(" invalide number");
                numberPh.setText("");
            } else {
                Warning.setText("");
            }

        });
        addresse.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), addresse, newValue);

            if (!addresse.getText().matches("[a-zA-Z]+")) {
                Warning.setText("adress must be CountryName,City,code civil ex :Tunis,Ariana,2081");
                addresse.setText("");
            } else {
                Warning.setText("");
            }

        });
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (inscriptionUtilisateurs.verifierLogin(newValue)) {
                    Warning2.setText("choose another one");
                } else {

                    Warning2.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(InscriptionApprenantFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), password, newValue);
            if (password.getText().length() < 8) {
                Warning2.setText("lenght should be > 8");
            } else {
                Warning2.setText("");
            }

        });
        password2.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), password2, newValue);

        });

    }

    @FXML
    private void rotationMethodDesktop(MouseEvent event) {

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
        image.setImage(new Image(new File(file.getAbsolutePath()).toURI().toString()));
        picture = file.getAbsolutePath();

    }

    private void rotationExit2(MouseEvent event) {

    }

    @FXML
    private void rotationExit(MouseEvent event) {
        if (!Objects.isNull(rotateTransition)) {
            rotateTransition.stop();
        }
    }

    @FXML
    private void ValiderInscription(ActionEvent event) throws SQLException, Exception {
        RedirectionStrategy redirectionStrategy = new RedirectionStrategy();
        if (!(password.getText().equals("")) || !(password2.getText().equals("")) || !(login.getText().equals(""))) {
               System.out.println("stage1");
            if (!Objects.isNull(picture)) {
                 boolean result =inscriptionUtilisateurs.verifierLogin(login.getText());
                    System.out.println("stage2");
                if (result) {
                    System.out.println("stage3");
                    if (password.getText().equals(password2.getText())) {
                        Formateur formateur = new Formateur();
                        formateur.setNom(nom.getText());
                        formateur.setPrenom(prenom.getText());
                        formateur.setDateNaissance(Date.valueOf(birth.getValue()));
                        formateur.setTel(Integer.parseInt(numberPh.getText()));
                        formateur.setMail(mail.getText());
                        formateur.setAdresse(addresse.getText());
                        formateur.setNomUtilisateur(login.getText());
                        formateur.setMotDePass(password.getText());
                        formateur.setCv(pdf.getText());
                        if (inscriptionUtilisateurs.inscriptionFormateur(formateur)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sign up with succes", ButtonType.FINISH);
                            alert.show();

                            redirectionStrategy.redirectAuthentification(birth);
                        }

                    } else {
                        Warning2.setText("Check your passwords");
                        password.setText("");
                        password2.setText("");

                    }

                }
            } else {
                Warning2.setText("you need a picture");
                rotateTransition = new RotateTransition(Duration.seconds(1), desktop);
                rotateTransition.setByAngle(360);
                rotateTransition.setCycleCount(1);
                rotateTransition.play();
            }

        }
    }

    @FXML
    private void goToshtep2(ActionEvent event) {
           if (nom.getText().equals("") || prenom.getText().equals("") || numberPh.getText().equals("") || addresse.getText().equals("") || Objects.isNull(birth.getValue()) || birth.getValue().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Empty data", ButtonType.OK);
            alert.show();
        } else {
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1));
            translateTransition.setNode(pane1);
            translateTransition.setCycleCount(1);
            translateTransition.setFromX(0);
            translateTransition.setToX(550);
            translateTransition.play();
        }
    }

    @FXML
    private void chercherCV(ActionEvent event) {
            FileChooser fc = new FileChooser();
        fc.setTitle("researching CV");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All PDF files", "*.*"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf") );
        File file = fc.showOpenDialog(null);
       
        addresse1.setText(file.getAbsolutePath());
    }

}
