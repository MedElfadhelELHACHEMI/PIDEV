/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.entities.Organisation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class CoachBrowseOrganisationsFXMLController implements Initializable {

    int x = 52 ;
    int y = 41 ;
    @FXML
    private AnchorPane body;
    @FXML
    private TextField textField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     displayOrganismes(CurrentUser.getId());
    }    

    private void displayOrganismes(int id) {
        IServiceFormateurs formateurs = new IServiceFormateursImpl();
        List<Organisation> lstOrg = formateurs.displayOrganisationWithoutUser(id);
        
      
        
        for (Organisation org : lstOrg) {
            int countF = formateurs.getNbFormateurOrganisme(org);
            OrganisationContainerNI containerNI = new OrganisationContainerNI(org, countF, x, y);
            x=x+310 ;
            if(x>672){
            x=52;
            y=y+348;
            
            }
            
            body.getChildren().add(containerNI);
        }
        
    }

    @FXML
    private void clickSearch(ActionEvent event) {
         x = 52 ;
     y = 41 ;
        body.getChildren().clear();
        IServiceFormateurs formateurs = new IServiceFormateursImpl();
        List<Organisation> lstOrg = formateurs.displayOrganisationWithoutUser(CurrentUser.getId());
        
      
        
        for (Organisation org : lstOrg) {
         if(org.getNom().trim().toLowerCase().contains(textField.getText())||org.getMatricule().trim().toLowerCase().contains(textField.getText())){
            int countF = formateurs.getNbFormateurOrganisme(org);
            OrganisationContainerNI containerNI = new OrganisationContainerNI(org, countF, x, y);
            x=x+310 ;
            if(x>672){
            x=52;
            y=y+348;
            
            }
            
            body.getChildren().add(containerNI);
        }
        }
    }
    
}
