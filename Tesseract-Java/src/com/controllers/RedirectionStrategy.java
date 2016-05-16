/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author haikal
 */
public   class RedirectionStrategy {
     public  void  redirectAuthentification(Node node) throws IOException{
     
          Parent parent = FXMLLoader.load(getClass().getResource("/com/fxml/LogInFXML.fxml"));
          Stage stage = new Stage();
          Scene s = new Scene(parent);
          stage.setScene(s);
          node.getScene().getWindow().hide();
     
     
     }
}
