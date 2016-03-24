/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.entities.Invitation;
import com.models.entities.Organisation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class MyInvitationsCoachFXMLController implements Initializable {
    List<Invitation> listInvitation ;
   int x = 35;
   int y=38;
    @FXML
    private AnchorPane body;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
        listInvitation = iServiceFormateurs.afficherInvitationEnAttente(CurrentUser.getId());
        
        for (Invitation invitation : listInvitation) {
         
            InvitationContainerCoach coach = new InvitationContainerCoach(invitation, x, y);
            x= x+278;
           body.getChildren().addAll(coach);
        }
        
    
    
    }    
    
}
