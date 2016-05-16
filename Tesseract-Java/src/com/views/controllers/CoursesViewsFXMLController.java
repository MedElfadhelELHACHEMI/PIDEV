/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.implementations.ImplCoursDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CoursesViewsFXMLController implements Initializable {

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis();
    @FXML
    private BarChart<String, Number> bc = new BarChart<String,Number>(xAxis,yAxis);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bc.setTitle("Courses Views");
        xAxis.setLabel("Courses");
        yAxis.setLabel("Subscribers");
        
        XYChart.Series series = new XYChart.Series();
        ICoursDAO implCoursDAO = new ImplCoursDAO();
        Map<String, Integer> m = new HashMap();
        try {
            m= implCoursDAO.getCoursAndViews();
            for(Map.Entry<String,Integer> entry : m.entrySet()){
                series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CoursesViewsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bc.getData().add(series);
    }    
    
}
