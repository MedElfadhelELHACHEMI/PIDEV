/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.entities.Organisation;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class OrganismeInformationForCoachFXMLController implements Initializable {

    @FXML
    private Circle prof1;
    @FXML
    private Label l4;
    @FXML
    private Label l3;
    @FXML
    private Label l2;
    @FXML
    private Label l1;

    private Organisation organisation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        IServiceFormateurs formateurs = new IServiceFormateursImpl();

        this.organisation = organisation;
        l1.setText(organisation.getNom());
        l2.setText(organisation.getAdresse());
        l3.setText(organisation.geteMail());
        prof1.setFill(new ImagePattern(new Image(new File(organisation.getPhoto()).toURI().toString())));
        l4.setText("" + formateurs.getNbFormateurOrganisme(organisation));
    }

}
