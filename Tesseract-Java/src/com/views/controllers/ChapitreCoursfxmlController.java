/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Chapitre;
import com.models.entities.Cours;
import com.models.entities.EpreuveEntrainement;
import com.models.entities.Utilisateur;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ChapitreCoursfxmlController implements Initializable {
 private int index=0;
 @FXML
 public  AnchorPane pane1;
   @FXML
    private Button  suivant;
     @FXML
    private Button  prev;
       @FXML
   private ImageView  imgChap;
           @FXML
   private ImageView  imgUser;
            @FXML
   private ImageView   imgnext;
  @FXML
    private Label Nomchapit;
  int nb;
 private Cours cours;
 private List<Chapitre> chapitres;
    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
           try {
    if(index==0){
         
    ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
 chapitres=serviceApprenant.findChapitreByIdCours(cours.getIdCours());
 nb=serviceApprenant.nbChapitreTerminerByCours(cours,CurrentUser.getId());
    index=nb;
  imgChap.setImage(new Image("/com/images/"+chapitres.get(index).getNumero()+".jpg"));
  Nomchapit.setText(chapitres.get(index).getNom());
    }
 if (index<chapitres.size()){
   suivant.setDisable(true);  
  imgChap.setImage(new Image("/com/images/"+chapitres.get(index).getNumero()+".jpg"));
  Nomchapit.setText(chapitres.get(index).getNom());   
     
 }
           
        
    } catch (SQLException ex) {
                Logger.getLogger(ChapitreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }     
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      imgUser.setImage(getImageUtilisateur(CurrentUser.getUtilisateur()));
    }    
  private Image getImageUtilisateur(Utilisateur utilisateur) {
    File file = new File(utilisateur.getPhoto());
        return new Image(file.toURI().toString());
    }
   @FXML
    void pdfAction(ActionEvent event) throws IOException {
        try {
              File f = new File(chapitres.get(index).getResume());
              if (f.exists()) {
                   
                        String g="rundll32 url.dll,FileProtocolHandler "+f.getAbsolutePath();
                        Process p = Runtime.getRuntime().exec(g);
                        p.waitFor();
            
               suivant.setDisable(false);
              
                    
              }else { 
                 Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur: Le fichier n'existe pas!", ButtonType.FINISH);
          alert.show();  
              }
                 
                 } catch (IOException ex) {
                        Logger.getLogger(ChapitreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    
              } catch (InterruptedException ex) {
                    Logger.getLogger(ChapitreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
              }
    }
   @FXML
    void VideoAction(ActionEvent event) throws IOException {
    try {
                    Button b = (Button) event.getSource();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/VideoCoursShowfxml.fxml"));
                    
                    AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                    
                    VideoCoursShowfxmlController controller = fxmlLoader.<VideoCoursShowfxmlController>getController();
                    controller.setCours(cours);
                    controller.setChapitre(chapitres.get(index));
                     pane1.getChildren().setAll(anchorPane);
                } catch (IOException ex) {
                    Logger.getLogger(ChapitreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
    } 
    @FXML
    void suivantAction(ActionEvent event) throws IOException {
     try {
         if (index==chapitres.size()-2){
             imgnext.setImage(new Image("/com/images/va.jpg"));
             suivant.setGraphic(imgnext);
         }
         if(index==chapitres.size()-1){
             try { 
                      ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
         
         boolean verif=serviceApprenant.modifierNombreChapCours(cours, CurrentUser.getId(),index+1);
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/SuivreCoursfxml.fxml"));
                 
                 AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                 
                 SuivreCoursfxmlController controller = fxmlLoader.<SuivreCoursfxmlController>getController();
                 
                 controller.setId_User(CurrentUser.getId());
                 
                 
                 pane1.getChildren().setAll(anchorPane);
             } catch (IOException ex) {
                 Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
         
         boolean verif=serviceApprenant.modifierNombreChapCours(cours, CurrentUser.getId(),index+1);
         index++;
         prev.setDisable(false);
         setCours(cours);
     } catch (SQLException ex) {
         Logger.getLogger(ChapitreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
     }
    } 
    @FXML
    void retourAction(ActionEvent event) throws IOException {
      try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/SuivreCoursfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                SuivreCoursfxmlController controller = fxmlLoader.<SuivreCoursfxmlController>getController();
                 
                controller.setId_User(CurrentUser.getId());
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
    @FXML
    void previousAction(ActionEvent event) throws IOException {
      index--; 
   setCours(cours);
    }
    
}
