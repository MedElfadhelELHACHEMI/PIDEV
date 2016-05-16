/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.implementations.ImplFormateurDAO;
import com.models.entities.Formateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bacem
 */
public class ValidateCoachFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vb = new VBox();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vb.setPadding(new Insets(10));
        vb.setSpacing(8);
        IFormateurDAO implFormateurDAO = new ImplFormateurDAO();
        List<Formateur> list = new ArrayList<>();
        try {
            list=implFormateurDAO.getAllFormateursAtt();
            for (Formateur f: list) {
                MCPItem c =new MCPItem(f,"joinRequest");
                VBox.setMargin(c, new Insets(0, 0, 0, 8));
                vb.getChildren().add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidateCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*Scene scene = new Scene(vb,700,350);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();*/
    }    
    
}
