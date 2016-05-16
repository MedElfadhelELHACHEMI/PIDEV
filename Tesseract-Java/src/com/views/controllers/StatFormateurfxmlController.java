/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class StatFormateurfxmlController implements Initializable {
@FXML
    private AnchorPane pane1;
  @FXML
    private Pane stat;
    @FXML
    private Button   ret;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
      PieChart pieChart = new PieChart();
    pieChart.setData(getChartData());
    pieChart.setTitle("Meilleur Formateur");
    pieChart.setLegendSide(Side.LEFT);
    pieChart.setClockwise(false);
    pieChart.setLabelsVisible(false);
       
      Scene scene = new Scene(pieChart, 1000,600);
         pieChart.setLayoutX(300);
       
  for (final PieChart.Data data : pieChart.getData()) {
data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            System.out.println("ffffffffffffffffffffffffffffffffffffffffff"+String.valueOf(data.getName()));
             try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/CoordoFormateurfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                CoordoFormateurfxmlController controller = fxmlLoader.<CoordoFormateurfxmlController>getController();
                controller.setName(String.valueOf(data.getName()));
               pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         } });
        
       
   
     
    }
   stat.getChildren().add(pieChart);  
    
    }
      private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
        pane1.getChildren().setAll(node);

    }
    @FXML
      void retourAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  
    }   

    private ObservableList<PieChart.Data> getChartData() {
       ObservableList<Data> answer = FXCollections.observableArrayList();
    try {
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        List<Cours>courses=serviceApprenant.findAllCours(CurrentUser.getId());
       List<Formateur> fo=new ArrayList();
        for(int i=0;i<courses.size();i++){
       Formateur f=serviceApprenant.getFormateur(courses.get(i));
       fo.add(f);
     }
        List<Formateur> fod=fo.stream().distinct().collect(Collectors.toList());
     for(int i=0;i<fod.size();i++){
     answer.add(new PieChart.Data(fod.get(i).getNom(), fod.get(i).getScore()));
     }   
       return answer;      
    } catch (SQLException ex) {
        Logger.getLogger(StatFormateurfxmlController.class.getName()).log(Level.SEVERE, null, ex);
    }
      return answer; 
          
        
    }
}
