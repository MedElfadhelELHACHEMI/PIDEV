/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.models.daos.interfaces.IUtilisateurDAO;
import com.models.daos.interfaces.implementations.ImplUtilisateurDAO;
import com.models.entities.Utilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Noor
 */
public class LogInFXMLController implements Initializable {

    @FXML
    private AnchorPane Anchor;
    @FXML
    private Pane MainPane;
    @FXML
    private ImageView img;
    @FXML
    private JFXTextField mailTF;
    @FXML
    private JFXTextField pwdTF;
    @FXML
    private JFXButton LogInButton;
    @FXML
    private JFXCheckBox RememberMe;
    @FXML
    private ImageView facebookLogin;
  
    @FXML
    private Button fbButton;
    @FXML
    private Button google;
    @FXML
    private Button LinkedIn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainPane.setStyle("-fx-background-color: rgba(194,203,204, 0.4); ");
        mailTF.setStyle("-fx-text-inner-color: #d3e2e4; -fx-prompt-text-fill: #3f595d ;");
        pwdTF.setStyle("-fx-text-inner-color: #d3e2e4; -fx-prompt-text-fill: #3f595d ;");
        LogInButton.setStyle("   -fx-background-color: #0B686E; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;    -fx-padding: 3 30 3 30;  -fx-text-fill: white ;    -fx-font-size: 16px;");
       
    }

    @FXML
    private void LogInAction(ActionEvent event) throws SQLException {
        String mail = mailTF.getText();
        String pwd = pwdTF.getText();
        if (verifyFields(mail, pwd)) {

            IUtilisateurDAO utilisateurDAO = new ImplUtilisateurDAO();
            Utilisateur user = utilisateurDAO.getUtilisateurByMail(mail);
            //  System.out.println(user);
            if (user == null) {
                mailTF.setText("Invalid Credentials");
            } else {

                user.setMotDePass(pwd);
                if (!utilisateurDAO.verifyPassword(user)) {
                    pwdTF.setText("wrong password");
                    System.out.println("wrong password");
                } else {
                    CurrentUser.id = user.getIdUtilisateur();
                    CurrentUser.role = user.getRole();
                 //   System.out.println("here!");
                }
            }

        }

    }

    @FXML
    private void rememberAction(ActionEvent event) {
    }

    private boolean verifyFields(String mail, String pwd) {
        if (mail.equals("")) {
            mailTF.setText("enter mail first");
            return false;
        }
        if(pwd.equals("")){
            pwdTF.setText("enter password first");
            return false;
        }
        return true;
    }

    

 

    @FXML
    private void fbLogIn(ActionEvent event) {
          System.out.println("fb");
    }

    @FXML
    private void googleLogIn(ActionEvent event) {
         System.out.println("gg");
    }

    @FXML
    private void LinkedInLogIn(ActionEvent event) {
         System.out.println("li");
    }

}
