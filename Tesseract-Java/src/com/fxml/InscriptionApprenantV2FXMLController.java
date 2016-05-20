/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fxml;

import com.controllers.InscriptionUtilisateurs;
import com.exceptions.FormulaireException;
import com.exceptions.InvalidMailException;
import com.exceptions.InvalidePasswordException;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.models.entities.Apprenant;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class InscriptionApprenantV2FXMLController implements Initializable {

    @FXML
    private JFXTextField t1;
    @FXML
    private JFXTextField t2;
    @FXML
    private JFXTextField t3;
    @FXML
    private JFXTextField t5;
    @FXML
    private JFXTextField t10;
    @FXML
    private JFXTextField t6;
    @FXML
    private JFXTextField t7;
    @FXML
    private Label l1;
    @FXML
    private Label l3;
    @FXML
    private Label l8;
    @FXML
    private Label l9;

    @FXML
    private Label l7;
    @FXML
    private Label l6;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l2;
    @FXML
    private DatePicker t4;
    @FXML
    private JFXPasswordField t8;
    @FXML
    private JFXPasswordField t9;
    @FXML
    private Button file;
    @FXML
    private Button bt_s;
    private static String s;
    InscriptionUtilisateurs inscriptionUtilisateurs = new InscriptionUtilisateurs();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void l1Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #0aa89e");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #e5e6e6");
        //   l10.setStyle("-fx-text-fill: #e5e6e6");

    }

    @FXML
    private void l2Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #0aa89e");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #e5e6e6");
        //    l10.setStyle("-fx-text-fill: #e5e6e6");
    }

    @FXML
    private void l3Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #0aa89e");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #e5e6e6");
        //   l10.setStyle("-fx-text-fill: #e5e6e6");
    }

    @FXML
    private void l5Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #0aa89e");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #e5e6e6");
        //  l10.setStyle("-fx-text-fill: #e5e6e6");
    }
//
//    @FXML
//    private void l10Action(MouseEvent event) {
//        l1.setStyle("-fx-text-fill: #e5e6e6");
//        l2.setStyle("-fx-text-fill: #e5e6e6");
//        l3.setStyle("-fx-text-fill: #e5e6e6");
//        l4.setStyle("-fx-text-fill: #e5e6e6");
//        l5.setStyle("-fx-text-fill: #e5e6e6");
//        l6.setStyle("-fx-text-fill: #e5e6e6");
//        l7.setStyle("-fx-text-fill: #e5e6e6");
//        l8.setStyle("-fx-text-fill: #e5e6e6");
//        l9.setStyle("-fx-text-fill: #e5e6e6");
//      //  l10.setStyle("-fx-text-fill: #0aa89e");
//    }

    @FXML
    private void l6Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #0aa89e");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #e5e6e6");
        //  l10.setStyle("-fx-text-fill: #e5e6e6");
    }

    @FXML
    private void l7Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #0aa89e");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #e5e6e6");
        //  l10.setStyle("-fx-text-fill: #e5e6e6");
    }

    @FXML
    private void l4Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #0aa89e");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #e5e6e6");
        // l10.setStyle("-fx-text-fill: #e5e6e6");
    }

    @FXML
    private void l8Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #0aa89e");
        l9.setStyle("-fx-text-fill: #e5e6e6");

    }

    @FXML
    private void l9Action(MouseEvent event) {
        l1.setStyle("-fx-text-fill: #e5e6e6");
        l2.setStyle("-fx-text-fill: #e5e6e6");
        l3.setStyle("-fx-text-fill: #e5e6e6");
        l4.setStyle("-fx-text-fill: #e5e6e6");
        l5.setStyle("-fx-text-fill: #e5e6e6");
        l6.setStyle("-fx-text-fill: #e5e6e6");
        l7.setStyle("-fx-text-fill: #e5e6e6");
        l8.setStyle("-fx-text-fill: #e5e6e6");
        l9.setStyle("-fx-text-fill: #0aa89e");

    }

    @FXML
    private void l10Action(MouseEvent event) {
    }

    @FXML
    private void pckPicture(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.setTitle("researching the image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
        File file = fc.showOpenDialog(null);

        t10.setText(file.getAbsolutePath());
        String ch = file.getAbsolutePath();
        //  String[] t =  ch.split("\\");
        System.out.println("\\");
        s = ch.substring(ch.lastIndexOf("\\") + 1);

        try {
            Files.copy(new File(ch).toPath(), new File("C:\\wamp\\www\\symf\\Tesseract-Symfony\\Tesseract-Symfony\\web\\uploads\\pictures\\" + s).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionApprenantV2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void nohover(MouseEvent event) {

        bt_s.setStyle(" -fx-text-fill: transparent;\n"
                + "   -fx-background-color: #0aa89e;");
    }

    @FXML
    private void hover(MouseEvent event) {
        bt_s.setStyle(" -fx-text-fill: black;\n"
                + "   -fx-background-color:#e5e6e6;");
    }

    @FXML
    private void saveAction(ActionEvent event) {
        System.out.println(s);
        Apprenant apprenant = new Apprenant();

        String nom = t1.getText();
        String prenom = t2.getText();
        String phone = t3.getText();
        Date d = Date.valueOf(t4.getValue());
        String adresse = t5.getText();
        String mail = t6.getText();
        String UserName = t7.getText();
        String password = t8.getText();
        String password1 = t9.getText();
        String file = t10.getText();
        try {
            if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getValue().toString().equals("") || t5.getText().equals("") || t6.getText().equals("") || t7.getText().equals("") || t8.getText().equals("") || t9.getText().equals("") || t10.getText().equals("")) {

                throw new FormulaireException("Champ vide");

            }
            if (password1.equals(password) == false) {
                throw new InvalidePasswordException("Password invalide");
            }
            if (mail.contains("@") == false) {
                throw new InvalidMailException("Mail invalide");
            }
            apprenant.setNom(nom);
            apprenant.setPrenom(prenom);
            apprenant.setAdresse(adresse);
            apprenant.setMail(mail);
            apprenant.setNomUtilisateur(UserName);
            apprenant.setMotDePass(password);
            apprenant.setPhoto(s);
            apprenant.setTel(new Integer(phone));
            apprenant.setDateNaissance(d);
            boolean resultat = inscriptionUtilisateurs.inscriptionApprenant(apprenant);

            if (resultat) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sign up with succes", ButtonType.FINISH);
                alert.show();

                // redirectionStrategy.redirectAuthentification(birth);
                Parent parent = FXMLLoader.load(getClass().getResource("/com/fxml/LogInFXML.fxml"));
                Stage stage = new Stage();
                Scene s = new Scene(parent);
                stage.setScene(s);
                // birth.getScene().getWindow().hide();
            }

        } catch (FormulaireException fe) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            alert.show();
        } catch (InvalidMailException im) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid mail", ButtonType.OK);
            alert.show();
        } catch (InvalidePasswordException ip) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalide password", ButtonType.OK);
            alert.show();
        } catch (NumberFormatException ip) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Numero invalide", ButtonType.OK);
            alert.show();
        } catch (NullPointerException npe) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            alert.show();
        } catch (SQLException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Mail or username exist", ButtonType.OK);
            alert.show();

        } catch (IOException ex) {
            Logger.getLogger(InscriptionApprenantV2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
            alert.show();
            Logger.getLogger(InscriptionApprenantV2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
