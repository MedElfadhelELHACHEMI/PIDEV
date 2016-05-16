/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import com.models.entities.SessionEpreuve;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class AvancementCoursfxmlController implements Initializable {
@FXML
    private AnchorPane pane1;
  @FXML
    private Pane stat;
    @FXML
    private Button   ret;
   Cours c;
String type;

  

    public Cours getC() {
        return c;
    }

    public void setC(Cours c) {
    try {
        this.c = c;
       
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        List<SessionEpreuve> list=serviceApprenant.getSessionEpreuvebyidCoursUtil(c.getIdCours(),CurrentUser.getId());
       
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Note Epreuve Finale ");
         XYChart.Series cours = new XYChart.Series();
         cours.setName("Note Epreuve Finale");
          for(int i=0;i<list.size();i++)
        {
       cours.getData().add(new XYChart.Data(list.get(i).getDate_Session().toString(), list.get(i).getNote()));    
        }
      lineChart.getData().add(cours);   
       lineChart.setPrefSize(1000, 350);
        lineChart.setAnimated(true);
        stat.getChildren().add(lineChart);
       
    } catch (SQLException ex) {
        Logger.getLogger(AvancementCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }
     public String getType() {
        return type;
    }

    public void setType(String type) {
  
        this.type = type;
      if(type.equals("cr")){ 
         try {   
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        List<Cours> list=serviceApprenant.chercherCoursByLoginApprenant(CurrentUser.getUtilisateur().getNomUtilisateur());
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);
        XYChart.Series cours = new XYChart.Series();
        xAxis.setLabel("Cours ");
        yAxis.setLabel("Chapitre lus ");
        
        
        
        for(int i=0;i<list.size();i++){
            int nbChapTerm=serviceApprenant.nbChapitreTerminerByCours(list.get(i),CurrentUser.getId());
            int nbchap=serviceApprenant.nbChapitreByCours(list.get(i));
            cours.getData().add(new XYChart.Data(list.get(i).getNomCours(),nbChapTerm));
        }
        Scene scene = new Scene(sbc, 1000,600);
          sbc.setLayoutX(300);
        sbc.getData().addAll(cours);
        stat.getChildren().add(sbc);
    } catch (SQLException ex) {
        Logger.getLogger(AvancementCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
    }}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
      try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ListCoursAvancementfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ListCoursAvancementfxmlController controller = fxmlLoader.<ListCoursAvancementfxmlController>getController();
                 
                controller.setId_User(CurrentUser.getId());
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
       @FXML
      void retourAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  
    }   
}
