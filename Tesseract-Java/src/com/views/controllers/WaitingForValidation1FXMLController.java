/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ICoursDAO;
import com.models.entities.Cours;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author noor
 */
public class WaitingForValidation1FXMLController implements Initializable {

    ICoursDAO coursDAO;
    ObservableList<Cours> Cours = FXCollections.observableArrayList();
    

    @FXML
    private AnchorPane body;
    @FXML
    private Label title;

    @FXML
    private TableView<Cours> content;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> vid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        content.setEditable(false);
        name.setStyle("-fx-text-fill: #032149 ; -fx-font-size: 16px; ");
        vid.setStyle("-fx-text-fill: #794044 ; -fx-font-size: 16px; -fx-label-padding: 0 10px 20px 30px; ");
        name.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        vid.setCellValueFactory(new PropertyValueFactory<>("video"));
        coursDAO = DAOFactory.getCoursDAO();
        try {
            Cours .addAll(coursDAO.getCoursValid1EnAttente(CurrentUser.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(WaitingForValidation1FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        content.setItems(Cours);
    }

}
