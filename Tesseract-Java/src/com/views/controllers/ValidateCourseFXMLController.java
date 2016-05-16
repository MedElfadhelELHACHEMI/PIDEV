/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.implementations.ImplCoursDAO;
import com.models.entities.Cours;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Bacem
 */
public class ValidateCourseFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vb = new VBox();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vb.setPadding(new Insets(10));
        vb.setSpacing(8);
        ICoursDAO implCoursDAO = new ImplCoursDAO();
        List<Cours> listC = new ArrayList<>();
        try {
            listC = implCoursDAO.getCoursAtt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ValidateCourseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Cours c:listC){
            //System.out.println(c.getNomCours());
            CoursesMCPItem i = new CoursesMCPItem(c,0);
            VBox.setMargin(i, new Insets(0, 0, 0, 8));
            vb.getChildren().add(i);
        }
    }    
    
}
