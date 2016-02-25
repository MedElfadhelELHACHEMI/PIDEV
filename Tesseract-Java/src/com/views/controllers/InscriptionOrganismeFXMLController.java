/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.InscriptionUtilisateurs;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class InscriptionOrganismeFXMLController implements Initializable {

    @FXML
    private JFXTextField nomOrganisme;
    @FXML
    private JFXTextField adresseOrganisme;
    @FXML
    private JFXTextField mailOrganisme;
    @FXML
    private JFXButton signup;
    @FXML
    private JFXTextField logoOrganisme;
    @FXML
    private ImageView imageOrganisme;
    @FXML
    private JFXButton importImage;
    InscriptionUtilisateurs inscriptionUtilisateurs = new InscriptionUtilisateurs();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logoOrganisme.setDisable(true);
        imageOrganisme.setImage(new Image("/com/images/nopicture.jpg"));
        logoOrganisme.textProperty().addListener((observable, oldValue, newValue) -> {
            imageOrganisme.setImage(new Image(new File(logoOrganisme.getText()).toURI().toString()));

        });

    }

    @FXML
    private void enregistrerOrganisme(ActionEvent event) throws IOException {
        if (!InscriptionUtilisateurs.validerMail(mailOrganisme.getText())||(nomOrganisme.getText().equals(""))||(adresseOrganisme.getText().equals(""))||(logoOrganisme.getText().equals("")))
        {
          mailOrganisme.setText("");
           nomOrganisme.setText("");
            logoOrganisme.setText("");
             adresseOrganisme.setText("");
             afficherMessage(event) ;
        }   else { inscriptionUtilisateurs.envoyerEMailUtilisateur("haikal.magrahi@esprit.tn", "league o", mailOrganisme.getText(), "Registration succeded  "+LocalDate.now(), "Wait for the validation of the administrator thank you !");}
     
        
        
    }

    @FXML
    private void getImageOrganisme(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("researching the image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
        File file = fc.showOpenDialog(null);

        logoOrganisme.setText(file.getAbsolutePath());

    }

    private void afficherMessage(ActionEvent event) throws IOException {
     Parent root =   FXMLLoader.load(getClass().getResource("/com/fxml/ValidationFXML.fxml"));
        Scene scene = new Scene(root);
     Stage stage = new Stage() ;
     stage.initOwner(((Node) event.getSource()).getScene().getWindow());
     stage.setResizable(false);
     stage.setScene(scene);
     stage.show();
    }

}
