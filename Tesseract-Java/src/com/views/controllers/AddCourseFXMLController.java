package com.views.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.IMatiereDAO;
import com.models.daos.interfaces.IObjectifDAO;
import com.models.entities.Cours;
import com.models.entities.Matiere;
import com.models.entities.Objectif;
import com.models.enums.Difficulte;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * @author Noor
 */
public class AddCourseFXMLController implements Initializable {
    
    JFXTreeTableColumn<Objectif, String> markers;
    TreeItem<Objectif> root;
    JFXTreeTableView<Objectif> treeView;
    ObservableList<Objectif> init;
    WebView webview;
    WebEngine engine;
    IMatiereDAO matiereDAO;
    Alert alert;
    @FXML
    private AnchorPane body;
    @FXML
    private ImageView addCourseIconIV;
    @FXML
    private Label addCourseLabel;
    @FXML
    private JFXTextField courseTitleTF;
    @FXML
    private JFXTextField courseVideoTF;
    @FXML
    private JFXComboBox<String> subjectsCB;
    @FXML
    private JFXSlider durationSlider;
    List<Matiere> subjects;
    @FXML
    private JFXButton nextBt;
    @FXML
    private JFXButton cancelBt;
    @FXML
    private Pane RightPane;
    @FXML
    private JFXButton backBt;
    @FXML
    private JFXButton submit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addCourseIconIV.setImage(new Image("/com/images/addCourse.png"));

        //tableau des objectifs
        markers = new JFXTreeTableColumn<>("Skills");
        markers.setPrefWidth(350);
        markers.setCellValueFactory((TreeTableColumn.CellDataFeatures<Objectif, String> param) -> {
            if (markers.validateValue(param)) {
                return param.getValue().getValue().NomProperty();
            } else {
                return markers.getComputedValue(param);
            }
        });
        
        markers.setCellFactory((TreeTableColumn<Objectif, String> param) -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        markers.setOnEditCommit((CellEditEvent<Objectif, String> t) -> {
            ((Objectif) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).NomProperty().set(t.getNewValue());
        });
        
        init = FXCollections.observableArrayList();
        init.add(new Objectif(0, 0, 0, "Add 3 to 10 goals for the learner to achieve", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "...", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "...", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "", "", Difficulte.DIFFICILE));
        init.add(new Objectif(0, 0, 0, "", "", Difficulte.DIFFICILE));
        
        root = new RecursiveTreeItem<>(init, RecursiveTreeObject::getChildren);
        
        treeView = new JFXTreeTableView<>(root, init);
        treeView.setShowRoot(false);
        treeView.setEditable(true);
        treeView.getColumns().setAll(markers);
        RightPane.getChildren().add(treeView);
        treeView.setPrefWidth(350);
        treeView.setPrefHeight(300);
        treeView.setLayoutX(140);
        treeView.setLayoutY(110);

        //Video Preview 
        webview = new WebView();
        engine = webview.getEngine();
        courseVideoTF.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (newPropertyValue) {
                engine.load(courseVideoTF.getText());
                
            }
        });
        webview.setPrefSize(400, 350);
        webview.setLayoutX(620);
        webview.setLayoutY(130);
        body.getChildren().add(webview);

        // liste des matières
        matiereDAO = DAOFactory.getMatiereDAO();
        
        try {
            subjects = matiereDAO.findAll();
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCourseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        subjects.stream()
                .forEach(e -> subjectsCB.getItems().add(e.getNomMatiere()));

        //buttons
        nextBt.setStyle(
                "   -fx-background-color: #0b8ba7; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;      -fx-text-fill: white ;    -fx-font-size: 18px;");
        cancelBt.setStyle(
                "   -fx-background-color: #9DA4A6; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;    -fx-text-fill: white ;    -fx-font-size: 18px;");
        submit.setStyle(
                "   -fx-background-color: #0b8ba7; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;      -fx-text-fill: white ;    -fx-font-size: 18px;");
        backBt.setStyle(
                "   -fx-background-color: #9DA4A6; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;    -fx-text-fill: white ;    -fx-font-size: 18px;");

        //#0b8ba7
    }
    
    @FXML
    
    private void nextAction(ActionEvent event) throws IOException {
        verifyFields();
        RightPane.setLayoutX(0);
        
    }
    
    @FXML
    private void cancelAction(ActionEvent event) throws IOException {
        
        body.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/com/fxml/addCourseFXML.fxml")));
        
    }
    
    private boolean verifyFields() throws IOException {
        
        alert = new Alert(AlertType.WARNING);
        alert.setTitle("Verify Fields");
        alert.setHeaderText(null);
        if (courseTitleTF.getText() == null || courseTitleTF.getText().equals("") || courseTitleTF.getText().startsWith("[1-9]+")) {
            alert.setContentText("Please retype a valid course name ");
            alert.setOnCloseRequest((DialogEvent event) -> {
                try {
                    cancelAction(new ActionEvent());
                } catch (IOException ex) {
                    Logger.getLogger(AddCourseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            alert.showAndWait();
            return false;
        } else if (courseVideoTF.getText() == null || courseVideoTF.getText().equals("") || courseVideoTF.getText().startsWith("[1-9]+")) {
            alert.setContentText("Please type a valid link for the summary video ");
            alert.setOnCloseRequest((DialogEvent event) -> {
                try {
                    cancelAction(new ActionEvent());
                } catch (IOException ex) {
                    Logger.getLogger(AddCourseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            alert.showAndWait();
            return false;
        } else if (subjectsCB.getValue() == null) {
            alert.setContentText("Please choose a subject for your new course ");
            alert.setOnCloseRequest((DialogEvent event) -> {
                try {
                    cancelAction(new ActionEvent());
                } catch (IOException ex) {
                    Logger.getLogger(AddCourseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            alert.showAndWait();
            return false;
        } else if (durationSlider.getValue() == 0) {
            alert.setContentText("Course duration has to be at least 1 hour ");
            alert.setOnCloseRequest((DialogEvent event) -> {
                try {
                    cancelAction(new ActionEvent());
                } catch (IOException ex) {
                    Logger.getLogger(AddCourseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            alert.showAndWait();
            return false;
        }
        
        return true;
        
    }
    
    @FXML
    private void backAction(ActionEvent event) {
        RightPane.setLayoutX(1100);
    }
    
    private boolean verifyMarkers() {
       
        if (init.stream().filter(e -> e.getNom().startsWith("Skill ")).count() < 3) {
            
            alert.setContentText("Please add at least 3 Skills ,starting with the prefix 'Skill' then press Enter \n (e.g.: 'Skill 1 : **** , Skill 2:***** , ..')");
            alert.showAndWait();
            return false;
        };
        return true;
    }
    
    @FXML
    private void submitAction(ActionEvent event) throws SQLException {
        if (verifyMarkers()){

        //confirm?
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Submission");
        alert.setHeaderText("Once data submitted , you will not be able to edit it ");
        alert.setContentText("Are you sure you want to submit ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            
            
             //add course
        ICoursDAO coursDAO = DAOFactory.getCoursDAO();
        Cours cours = new Cours();
        cours.setVideo(courseVideoTF.getText());
        cours.setIdFormateur(CurrentUser.getId());
        cours.setNomCours(courseTitleTF.getText());
        cours.setIdMatiere(matiereDAO.findMatiereByName(subjectsCB.getValue()).getIdMatiere());
        cours.setDifficulte(Difficulte.DIFFICILE);
            System.out.println("!!!!!"+cours);
        coursDAO.AjouterCours(cours);

        //add objectives
        IObjectifDAO objectifDAO = DAOFactory.getObjectifDAO();
        int idCours = coursDAO.findCourseByName(cours.getNomCours()).getIdCours();
        init.stream().filter(e -> e.getNom().startsWith("Skill")).forEach(e -> {
            e.setIdChapitre(idCours);
            objectifDAO.addObjectif(e);
        });
           
        
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Course successfully submitted !");
        alert.show();
        
            
        } 
       
    }
    }
}
