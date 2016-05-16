/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Sameh
 */
public class MoocMapFXMLController implements Initializable {
@FXML
    private WebView webView;

    @FXML
    private Button retourButton;
      @FXML
    private AnchorPane Pane1;
      
    @Override
    public void initialize(URL location, ResourceBundle resources) {
 try {

            reloadMap();
       
        
        WebEngine webEngine = webView.getEngine();
        URL urlGoogleMaps;
  
        //urlGoogleMaps = getClass().getResource("/img/googlemaps.html");
        urlGoogleMaps = getClass().getResource("/map/googlemaps.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
    } catch (IOException ex) {
        Logger.getLogger(MoocMapFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }}
     @FXML
    void retourAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  
    }
    private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
        Pane1.getChildren().setAll(node);

    }
     public void reloadMap() throws IOException{

        
        
        
        
        File file = new File("src/map/googlemaps.html");
        file.createNewFile();
FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
//BufferedWriter bw = new BufferedWriter(fw);
//bw.write(content);
//bw.close();

    }
}
