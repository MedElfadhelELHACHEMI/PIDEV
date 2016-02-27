/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class InscriptionApprenantFXMLController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField numberPh;
    @FXML
    private JFXTextField addresse;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField password;

    private RotateTransition rotateTransition;
    @FXML
    private DatePicker birth;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private ImageView desktop;
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        generateListeners();
    }

    @FXML
    private void foToshtep2(ActionEvent event) {
        if (nom.getText().equals("") || prenom.getText().equals("") || numberPh.getText().equals("") || addresse.getText().equals("")||Objects.isNull(birth.getValue())||birth.getValue().equals("")){
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

        });
        prenom.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), prenom, newValue);

        });
        numberPh.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), numberPh, newValue);

        });
        addresse.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), addresse, newValue);
            afficherWarning(1, 2);

        });
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), login, newValue);

        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), password, newValue);

        });
        password2.textProperty().addListener((observable, oldValue, newValue) -> {
            verif(Color.PINK, Color.valueOf("#24c5cf"), password2, newValue);

        });

    }

    private void afficherWarning(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

  
    @FXML
    private void rotationMethodDesktop(MouseEvent event) {

    }

    @FXML
    private void searchDesktop(MouseEvent event) {
        rotateTransition = new RotateTransition(Duration.seconds(1), desktop);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1);

        rotateTransition.play();
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

    }

    private void rotationExit2(MouseEvent event) {
     
    

   
    }

    @FXML
    private void rotationExit(MouseEvent event) {
           if (!Objects.isNull(rotateTransition)) {
            rotateTransition.stop();
        }
    }

}
