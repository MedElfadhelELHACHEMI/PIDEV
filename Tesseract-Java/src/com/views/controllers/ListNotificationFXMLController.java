/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.models.entities.Notification;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.Optional;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.outils.PdfListNotification;

/**
 *
 * @author Sameh
 */
public class ListNotificationFXMLController implements Initializable{
    private ObservableList<Notification> NotificationData;
     public ObservableList<Notification> getNotificationData() {
      return NotificationData;
    }
  @FXML
    private DatePicker recherche;
 @FXML
    private JFXButton RechercherButton;
      @FXML
    private Button  confirmerNotifButton;
        @FXML
    private Button   genButton;
        
       @FXML
    private TableView<Notification> NotifTable;
    @FXML
    private TableColumn<Notification, String> NameNotfi;
    @FXML
    private TableColumn<Notification, String> dateNotif;
     @FXML
    private Button   btretour;
     @FXML
    private AnchorPane pane1;
      @FXML
    private ProgressIndicator p;
    @FXML
    private Region veil;
     
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
       NameNotfi.setCellValueFactory(new PropertyValueFactory<Notification, String>("notification"));
        dateNotif.setCellValueFactory(new PropertyValueFactory<Notification, String>("dateNotification"));

         NotificationData = FXCollections.observableArrayList();
         NotifTable.setItems(NotificationData);
        
         Task<ObservableList<Notification>> task = new GetDailySalesTask();
          p.progressProperty().bind(task.progressProperty());
        veil.visibleProperty().bind(task.runningProperty());
        p.visibleProperty().bind(task.runningProperty());
        NotifTable.itemsProperty().bind(task.valueProperty());
      
        new Thread(task).start();
    }
    
     public class GetDailySalesTask extends Task<ObservableList<Notification>> {       
        @Override protected ObservableList<Notification> call() throws Exception {
            for (int i = 0; i < 200; i++) {
                updateProgress(i, 180);
                Thread.sleep(3);
            } 
             
            ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
            ArrayList<Notification> list = new ArrayList<Notification>();
           list=serviceApprenant.ListNotificationByUserId(CurrentUser.getId());
//            String s="2016-03-04";
//         List list=serviceApprenant.displayNotificationByDate(Date.valueOf(s));
            NotificationData.addAll(list);            
            return NotificationData;
        }
    }
        public class GetListCatByNom extends Task<ObservableList<Notification>> {       
        @Override protected ObservableList<Notification> call() throws Exception {
            for (int i = 0; i < 200; i++) {
                updateProgress(i, 180);
                Thread.sleep(3);
            }          
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        String s=recherche.getValue().toString();
        String s1=s.replace("/","-");
        List list=serviceApprenant.displayNotificationByDate(Date.valueOf(s1),CurrentUser.getId());
        NotificationData.addAll(list);           
        return NotificationData;
        }
    }
    @FXML
    private void confirmerAction(ActionEvent event) {
     if(NotifTable.getSelectionModel().getSelectedItem()!=null){
         try {
             Notification c1 = (Notification)NotifTable.getSelectionModel().getSelectedItem();
             ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
             boolean conf=serviceApprenant.updateNotifcation(CurrentUser.getId(), c1.getNotification().trim());
             if (conf==true){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Notification confirmée.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/com/images/btn_vmenu_hover.png").toString()));
                alert.showAndWait();
              NotificationData.clear();
        Task<ObservableList<Notification>> task = new GetDailySalesTask();
        p.progressProperty().bind(task.progressProperty());
        veil.visibleProperty().bind(task.runningProperty());
        p.visibleProperty().bind(task.runningProperty());
        NotifTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();   
             }
              else
             {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur: Erreur lors de la confirmation.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/com/images/btn_vmenu_hover.png").toString()));               
                alert.showAndWait();
             }
         } catch (SQLException ex) {
             Logger.getLogger(ListNotificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
     }
     else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur: Veuillez selectionner une notification.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/com/images/btn_vmenu_hover.png").toString()));                
            alert.showAndWait();
        }
    }
    
    
    
     @FXML
    private void RechercherNotiButton(ActionEvent event) {
     NotificationData.clear();
        Task<ObservableList<Notification>> task = new GetListCatByNom();
        p.progressProperty().bind(task.progressProperty());
        veil.visibleProperty().bind(task.runningProperty());
        p.visibleProperty().bind(task.runningProperty());
        NotifTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();    
    }
    
   
    
    
     @FXML
    private void  genererPDFAction(ActionEvent event) throws DocumentException, FileNotFoundException {
    FileChooser fileChooser = new FileChooser();
     fileChooser.setTitle("Export en PDF");
      File file = fileChooser.showSaveDialog(null);
      if (file != null) {
          PdfListNotification pdf = new PdfListNotification(file.getPath());
          Alert alert2 = new Alert(Alert.AlertType.INFORMATION); 
          alert2.setTitle("Message");
          alert2.setContentText("Message: Exportation terminé avec succés.");
          Stage stage5 = (Stage) alert2.getDialogPane().getScene().getWindow();
                stage5.getIcons().add(new Image(this.getClass().getResource("/com/images/btn_vmenu_hover.png").toString()));                
                alert2.showAndWait();
      }
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
      
      
}
