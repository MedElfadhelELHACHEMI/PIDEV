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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Bacem
 */
public class CoachesRatingsFXMController implements Initializable {

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis();
    @FXML
    private BarChart<String, Number> bc = new BarChart<String,Number>(xAxis,yAxis);

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bc.setTitle("Coaches Ratings");
        xAxis.setLabel("Coaches");
        yAxis.setLabel("Score");
        
        XYChart.Series series = new XYChart.Series();
        
        IFormateurDAO implFormateurDAO = new ImplFormateurDAO();
        List<Formateur> list = new ArrayList<>();
        try {
            list =implFormateurDAO.getFormateurAndScore();
            for(Formateur f : list){
                series.getData().add(new XYChart.Data(f.getNomUtilisateur(), f.getScore()));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CoachesRatingsFXMController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bc.getData().add(series);
    }    
    
}
