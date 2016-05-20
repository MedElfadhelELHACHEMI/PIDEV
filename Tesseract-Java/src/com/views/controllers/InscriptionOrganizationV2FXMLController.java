/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.fxml.*;
import com.controllers.InscriptionUtilisateurs;
import com.exceptions.FormulaireException;
import com.exceptions.InvalidMailException;
import com.exceptions.InvalidePasswordException;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IOrganisationDAO;
import com.models.entities.Apprenant;
import com.models.entities.Formateur;
import com.models.entities.Organisation;
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
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class InscriptionOrganizationV2FXMLController implements Initializable {

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
    Organisation o;
    private static String s;
    InscriptionUtilisateurs inscriptionUtilisateurs = new InscriptionUtilisateurs();
    @FXML
    private Button file1;
    @FXML
    private JFXTextField t11;
    static String s1;
    @FXML
    private JFXTextField t12;
    @FXML
    private JFXTextField t13;
    @FXML
    private JFXTextField t14;
    @FXML
    private JFXTextField t15;
    @FXML
    private Label l10;
    @FXML
    private Label l11;
    @FXML
    private Label l12;
    @FXML
    private Label l13;
    @FXML
    private Button bt_s1;
    Formateur apprenant;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane pane2;

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
        l10.setStyle("-fx-text-fill: #0aa89e");
        l11.setStyle("-fx-text-fill: #e5e6e6");
        l12.setStyle("-fx-text-fill: #e5e6e6");
        l13.setStyle("-fx-text-fill: #e5e6e6");
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
            Logger.getLogger(InscriptionOrganizationV2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void nohover(MouseEvent event) {

        bt_s.setStyle(" -fx-text-fill: #0aa89e;\n"
                + "   -fx-background-color: transparent");
    }

    @FXML
    private void hover(MouseEvent event) {
        bt_s.setStyle(" -fx-text-fill: black;\n"
                + "   -fx-background-color:#e5e6e6;");
    }

    @FXML
    private void saveAction(ActionEvent event) {
        System.out.println(s);
        apprenant = new Formateur();

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

            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), pane1);
            tt.setAutoReverse(true);
            tt.setCycleCount(1);
            tt.setFromX(0);
            tt.setToX(-745);
            tt.play();

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
            Logger.getLogger(InscriptionOrganizationV2FXMLController.class.getName()).log(Level.SEVERE, null, npe);
            npe.getStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty data.", ButtonType.OK);
            alert.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
            alert.show();
            Logger.getLogger(InscriptionOrganizationV2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void pckCV(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.setTitle("researching the CV");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
        File file = fc.showOpenDialog(null);

        t15.setText(file.getAbsolutePath());
        String ch = file.getAbsolutePath();
        //  String[] t =  ch.split("\\");
        System.out.println("\\");
        s1 = ch.substring(ch.lastIndexOf("\\") + 1);

        try {
            Files.copy(new File(ch).toPath(), new File("C:\\wamp\\www\\symf\\Tesseract-Symfony\\Tesseract-Symfony\\web\\uploads\\pictures\\" + s1).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionOrganizationV2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void l11Action(MouseEvent event) {
        l10.setStyle("-fx-text-fill: #e5e6e6");
        l11.setStyle("-fx-text-fill: #0aa89e");
        l12.setStyle("-fx-text-fill: #e5e6e6");
        l13.setStyle("-fx-text-fill: #e5e6e6");
    }

    @FXML
    private void l12Action(MouseEvent event) {
        l10.setStyle("-fx-text-fill: #e5e6e6");
        l11.setStyle("-fx-text-fill: #e5e6e6");
        l12.setStyle("-fx-text-fill: #0aa89e");
        l13.setStyle("-fx-text-fill: #e5e6e6");
    }

    @FXML
    private void l13Action(MouseEvent event) {
        l10.setStyle("-fx-text-fill: #e5e6e6");
        l11.setStyle("-fx-text-fill: #e5e6e6");
        l12.setStyle("-fx-text-fill: #e5e6e6");
        l13.setStyle("-fx-text-fill: #0aa89e");
    }

    @FXML
    private void savefinalAction(ActionEvent event) {
        o = new Organisation();

        String nom = t11.getText();

        String adresse = t12.getText();
        String mail = t13.getText();
        String UserName = t14.getText();

        String file = t15.getText();

        try {
            if (t1.getText().equals("") || t12.getText().equals("") || t13.getText().equals("") || t14.getText().equals("") || t15.getText().equals("")) {

                throw new FormulaireException("Champ vide");

            }

            if (mail.contains("@") == false) {
                throw new InvalidMailException("Mail invalide");
            }
            o.setNom(nom);

            o.setAdresse(adresse);
            o.seteMail(mail);
            o.setMatricule(UserName);

            o.setPhoto(s1);
         boolean result =inscriptionUtilisateurs.inscriptionOrganisation(o);
            IOrganisationDAO dqo = DAOFactory.getOrganisationDAO();
            
            System.out.println(result);
            if (result) {
               Organisation id = dqo.getOrganisationByName(UserName);
               apprenant.setIdOrganisationn(id.getIdOrganisation());
                    if (inscriptionUtilisateurs.inscriptionFormateurWithOrganization(apprenant)) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Thank you, wait for you validation", ButtonType.FINISH);
                alert.show();

                // redirectionStrategy.redirectAuthentification(birth);
                Parent parent = FXMLLoader.load(getClass().getResource("/com/fxml/LogInFXML.fxml"));
                Stage stage = new Stage();
                Scene s = new Scene(parent);
                stage.setScene(s);
                // birth.getScene().getWindow().hide();
            }
                
            };

//            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), pane1);
//            tt.setAutoReverse(true);
//            tt.setCycleCount(1);
//            tt.setFromX(0);
//            tt.setToX(-740);
//            tt.play();

        } catch (FormulaireException fe) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            alert.show();
        } catch (InvalidMailException im) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid mail", ButtonType.OK);
            alert.show();
        } catch (NumberFormatException ip) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Numero invalide", ButtonType.OK);
            alert.show();
        } catch (NullPointerException npe) {
            Logger.getLogger(InscriptionOrganizationV2FXMLController.class.getName()).log(Level.SEVERE, null, npe);
            npe.getStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty data.", ButtonType.OK);
            alert.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
            alert.show();
            Logger.getLogger(InscriptionOrganizationV2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
