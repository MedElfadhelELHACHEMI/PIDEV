/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class InscriptionFXMLController implements Initializable {

    @FXML
    private AnchorPane main;
    @FXML
    private Pane header;
    @FXML
    private AnchorPane body;
    @FXML
    private ImageView formateurIcone;
    @FXML
    private ImageView OrganisationIcone;
    @FXML
    private ImageView apprenantIcone;

    private ScaleTransition scaleTransition;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void afficherFormateursInscription(MouseEvent event) {
        if (Objects.nonNull(scaleTransition)) {
            leaveScale();
            scaleTransition = null;
            doScale(formateurIcone);
        } else {
            doScale(formateurIcone);
        }
    }

    @FXML
    private void afficheOrganismeInscription(MouseEvent event) throws IOException {

        if (Objects.nonNull(scaleTransition)) {

            leaveScale();
            scaleTransition = null;
            doScale(OrganisationIcone);
        } else {
            doScale(OrganisationIcone);
        }
        
        setBody(doAnimation(loadPage("/com/fxml/InscriptionOrganismeFXML.fxml")));
    }

    @FXML
    private void afficheApprenantInscription(MouseEvent event) {
        if (Objects.nonNull(scaleTransition)) {

            leaveScale();
            scaleTransition = null;
            doScale(apprenantIcone);
        } else {
            doScale(apprenantIcone);
        }
        

    }

    private Node doAnimation(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1));
        translateTransition.setNode(node);
        translateTransition.setCycleCount(1);
        translateTransition.setFromX(400);
        translateTransition.setToX(0);
        translateTransition.play();
        return node;
    }

    private void doScale(Node node) {
        scaleTransition = new ScaleTransition(Duration.seconds(0.5));

        scaleTransition.setCycleCount(1);
        scaleTransition.setNode(node);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.play();
    }

    private void leaveScale() {

        scaleTransition.setFromX(2);
        scaleTransition.setFromY(2);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();

    }

    public void setBody(Node node) throws IOException {
       
       
        body.getChildren().setAll(node);
    }
    
    AnchorPane loadPage(String resource) throws IOException{
    return  (AnchorPane)FXMLLoader.load(getClass().getResource(resource));
      
    }

}
