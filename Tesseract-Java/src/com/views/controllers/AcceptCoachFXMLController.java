/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.implementations.ImplCoursDAO;
import com.models.daos.interfaces.implementations.ImplFormateurDAO;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Utilisateur;
import com.skype.Skype;
import com.skype.SkypeException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Bacem
 */
public class AcceptCoachFXMLController implements Initializable {
    
    @FXML
    private TableView<Formateur> coaches_TableView;
    @FXML
    private TableColumn<Formateur, String> coach_name_column;
    @FXML
    private TableColumn<Formateur, String> coach_score_column;

    @FXML
    private Label coach_name_label;
    @FXML
    private Label coach_mail_label;
    @FXML
    private Label coach_nickname_label;
    @FXML
    private Label coach_score_label;
    @FXML
    private Label coach_cours_label;
    @FXML
    private ImageView coach_photo_ImageView;
    @FXML
    private Label coach_skype_add;
    @FXML
    private Button coach_skype_call;
    @FXML
    private VBox vbox_coach;
    
    
    private IFormateurDAO implFormateurDAO;
    

    /**
     * Initializes the controller class.
     */
    public AcceptCoachFXMLController(){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        implFormateurDAO = new ImplFormateurDAO();
        coaches_TableView.setItems(implFormateurDAO.findAllAtt());
        coach_name_column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        coach_score_column.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());
        showCoursDetails(null);
        coaches_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCoursDetails(newValue));
        
    }

    private void showCoursDetails(Formateur formateur) {
        Image photoImg;
        if (formateur!=null) {
            coach_name_label.setText(formateur.getPrenom().substring(0, 1).toUpperCase() + formateur.getPrenom().substring(1) + ' ' + formateur.getNom().substring(0, 1).toUpperCase() + formateur.getPrenom().substring(1));
            coach_mail_label.setText(formateur.getMail());
            coach_nickname_label.setText(formateur.getNomUtilisateur());
            coach_score_label.setText(Integer.toString(formateur.getScore()));
            
            ICoursDAO coursDAO = new ImplCoursDAO();
            Cours cour = new Cours();
            
            try {
                cour =coursDAO.findCourByIdFromateur(formateur.getIdUtilisateur());
            } catch (SQLException ex) {
                Logger.getLogger(AcceptCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            coach_cours_label.setText(cour.getNomCours());
            
            //photoImg = new Image("@/com/images/"+formateur.getPhoto());
            //coach_photo_ImageView.setImage(photoImg);
            
            
            try {
                if (Skype.getContactList().getFriend(formateur.getSkype())!=null) {
                    coach_skype_call.setText("Make a call to "+ formateur.getSkype());
                    coach_skype_add.setText("");
                }
                else{
                    coach_skype_add.setText("Add "+formateur.getSkype()+ "to your skype Contact");
                    coach_skype_call.setText("");
                }
                
            } catch (SkypeException ex) {
                Logger.getLogger(AcceptCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            coach_name_label.setText("- -");
            coach_mail_label.setText(" - ");
            coach_nickname_label.setText(" - ");
            coach_score_label.setText(" - ");
            coach_cours_label.setText(" - ");
            coach_skype_call.setText("");
            coach_skype_add.setText("");
        }
    }
    
    @FXML
    private void handleReject() throws SQLException{
        int selectedIndex = coaches_TableView.getSelectionModel().getSelectedIndex();
        Formateur f = new Formateur();
        f=coaches_TableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Reject Coach !");
            alert.setHeaderText("Are you sure you want to reject this coach : '"+f.getPrenom()+ ' ' +f.getNom()+"'");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                coaches_TableView.getItems().remove(selectedIndex);
                implFormateurDAO = new ImplFormateurDAO();
                implFormateurDAO.rejeterFormateur(f.getIdUtilisateur());
            }
        }
    }
    
    @FXML
    private void handleAccept() throws SQLException{
        int selectedIndex = coaches_TableView.getSelectionModel().getSelectedIndex();
        Formateur f = new Formateur();
        f=coaches_TableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Accept Coach !");
            alert.setHeaderText("Are you sure you want to accept this coach : '"+f.getPrenom()+ ' ' +f.getNom()+"'");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                coaches_TableView.getItems().remove(selectedIndex);
                implFormateurDAO = new ImplFormateurDAO();
                implFormateurDAO.accepterFormateur(f.getIdUtilisateur());
            }
        }
    }
    
    @FXML
    private void handleSkypeCall() {
        int selectedIndex = coaches_TableView.getSelectionModel().getSelectedIndex();
        Formateur f = new Formateur();
        f=coaches_TableView.getSelectionModel().getSelectedItem();
        
        try {
            System.out.println("hello world");
            Skype.call(f.getSkype());
            System.out.println(f.getSkype());
            System.out.println("good bye world");
        } catch (SkypeException ex) {
            Logger.getLogger(AcceptCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Calling '"+f.getPrenom()+ ' ' +f.getNom()+"' via Skype !");
            alert.setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    Skype.call("saiif00").cancel();
                } catch (SkypeException ex) {
                    Logger.getLogger(AcceptCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }*/
        
        
    }
    @FXML
    private void handleSkypeAdd(){
        int selectedIndex = coaches_TableView.getSelectionModel().getSelectedIndex();
        Formateur f = new Formateur();
        f=coaches_TableView.getSelectionModel().getSelectedItem();
        try {
            Skype.getContactList().addFriend(f.getSkype(), "Hi this is Tesseract Coding committee");
        } catch (SkypeException ex) {
            Logger.getLogger(AcceptCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    private void cancelCall(Formateur f) throws SkypeException{
        Skype.call(f.getSkype()).cancel();
    }
    
    
    
}
