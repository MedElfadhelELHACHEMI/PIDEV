/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;


import com.models.entities.Organisation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class OrganisationMapFXMLController implements Initializable {

    @FXML
    private AnchorPane mapBody;
    private static Organisation organisation;
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
    }

    public AnchorPane getMapBody() {
        return mapBody;
    }

    public void setMapBody(AnchorPane mapBody) {
        this.mapBody = mapBody;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        
       
       OrganisationMapFXMLController.organisation = organisation;
          String replace = organisation.getAdresse().replace(' ', '+');
        String replace2 = organisation.getNom().replace(' ', '+');
        System.out.println(""+replace2+"+"+replace);
        System.out.println(organisation.getAdresse());
        webEngine.load("https://www.google.tn/maps/place/"+replace2+"+"+replace);
    //    System.out.println(browser.getEngine().getDocument().getDocumentElement().toString()); 

        //add the web view to the scene
        mapBody.getChildren().add(browser);
    }

    void test() {
        System.out.println("hello world");
    }

    @FXML
    private void exec(ActionEvent event) {
        String s = browser.getEngine().getDocument().getElementById("searchboxinput").getSchemaTypeInfo().getTypeName();

    }

}
