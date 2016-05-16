/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.itextpdf.text.DocumentException;
import com.models.entities.Evenement;
import com.models.entities.Notification;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import com.outils.PdfListEvenement;
import com.outils.PdfListNotification;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ListeEvenementfxmlController implements Initializable {
 private ObservableList<Evenement> EvenementData;

    public ObservableList<Evenement> getCategorieData() {
      return EvenementData;
    }
     @FXML
    private DatePicker recherche;
      @FXML
    private Button  confirmerNotifButton;
        @FXML
    private Button   genButton;
         @FXML
    private TableView< Evenement> NotifTable;
    @FXML
    private TableColumn< Evenement, String> NameNotfi;
    @FXML
    private TableColumn< Evenement, String> dateNotif;    
      @FXML
    private TableColumn< Evenement, String>  descNotif  ;
         @FXML
    private Button   btretour;
     @FXML
    private AnchorPane pane1;
      @FXML
    private ProgressIndicator p;
    @FXML
    private Region veil;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     NameNotfi.setCellValueFactory(new PropertyValueFactory< Evenement, String>("nom"));
        dateNotif.setCellValueFactory(new PropertyValueFactory< Evenement, String>("dateEvenement"));
        descNotif.setCellValueFactory(new PropertyValueFactory< Evenement, String>("description"));
        EvenementData= FXCollections.observableArrayList();
         NotifTable.setItems(EvenementData);
       Task<ObservableList<Evenement>> task = new ListeEvenementfxmlController .GetDailySalesTask();
          p.progressProperty().bind(task.progressProperty());
        veil.visibleProperty().bind(task.runningProperty());
        p.visibleProperty().bind(task.runningProperty());
        NotifTable.itemsProperty().bind(task.valueProperty());
      
        new Thread(task).start();
    }    
     public class GetDailySalesTask extends Task<ObservableList<Evenement>> {       
        @Override protected ObservableList<Evenement> call() throws Exception {
            for (int i = 0; i < 200; i++) {
                updateProgress(i, 180);
                Thread.sleep(3);
            } 
             
            ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
            List<Evenement> list = new ArrayList<Evenement>();
           list=serviceApprenant.displayEvenementUtilisateur(CurrentUser.getId());

           EvenementData.addAll(list);            
            return EvenementData;
        }
    } 
       public class GetListCatByNom extends Task<ObservableList<Evenement>> {       
        @Override protected ObservableList<Evenement> call() throws Exception {
            for (int i = 0; i < 200; i++) {
                updateProgress(i, 180);
                Thread.sleep(3);
            }          
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        String s=recherche.getValue().toString();
        String s1=s.replace("/","-");
        List<Evenement> list=serviceApprenant.displayByDate(Date.valueOf(s1),CurrentUser.getId());
        EvenementData.addAll(list);           
        return EvenementData;
        }
    }    
 @FXML
    private void RechercherNotiButton(ActionEvent event) {
     EvenementData.clear();
        Task<ObservableList<Evenement>> task = new ListeEvenementfxmlController.GetListCatByNom();
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
          PdfListEvenement pdf = new PdfListEvenement(file.getPath());
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
