/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import com.models.entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Sameh
 */
public class ApprenantWelcomFXMLController implements Initializable{
  @FXML
    private VBox VProgress;
    
     @FXML
    private VBox VCompleted;
       @FXML
     private ImageView imgUser;
     @FXML
    private Label Name;
     @FXML
    private Button bt1;
       @FXML
    private AnchorPane Pane1;
      private Pane pane;
    private Pane paneGene;
    private ProgressBar progCene;
    private  ProgressIndicator PourcentGene;
    private Label NomCoursGene;
     private Label scoBadge;
     private Label NomBadge;
     private Label CoursBadge;
     private ImageView imgCours;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
  
  CreatePane();
 GenerateVCompleted();
  GenerateVProgress();
    }

    private void CreatePane() {
  imgUser.setImage(getImageUtilisateur(CurrentUser.getUtilisateur()));
  Name.setText(CurrentUser.getUtilisateur().getNom()+" "+CurrentUser.getUtilisateur().getPrenom());
    }
 @FXML
    void MapAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/MoocMapFXML.fxml"));  
    }
    private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
        Pane1.getChildren().setAll(node);

    }
    private void GenerateVCompleted() {
     try {
            ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
            List<Cours> list=serviceApprenant.getListeMesCoursTerminer(CurrentUser.getId());
            for(Cours cours :list){
            int nbChapTerm=serviceApprenant.nbChapitreTerminerByCours(cours,CurrentUser.getId());
             int nbchap=serviceApprenant.nbChapitreByCours(cours);
             
             pane=generatePane(cours.getNomCours(),nbChapTerm,nbchap,cours);
             VCompleted.getChildren().add(pane);
           
        }
    } catch (SQLException ex) {
          Logger.getLogger(ApprenantWelcomFXMLController.class.getName()).log(Level.SEVERE, null, ex);
      }}

    private void GenerateVProgress() {
     try {
            ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
            List<Cours> list=serviceApprenant.getListeMesCoursEnCours(CurrentUser.getId());
            for(Cours cours :list){
            int nbChapTerm=serviceApprenant.nbChapitreTerminerByCours(cours,CurrentUser.getId());
             int nbchap=serviceApprenant.nbChapitreByCours(cours);
             
             pane=generatePane(cours.getNomCours(),nbChapTerm,nbchap,cours);
             VProgress.getChildren().add(pane);
            }   } catch (SQLException ex) {
            Logger.getLogger(ApprenantWelcomFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image getImageUtilisateur(Utilisateur utilisateur) {
    File file = new File(utilisateur.getPhoto());
        return new Image(file.toURI().toString());
    }

    private Pane generatePane(String nomCours, int nbChapTerm, int nbchap,Cours cours) {
  paneGene=new Pane();
   imgCours=new ImageView();
 imgCours.setImage(getImageCours(cours));
 imgCours.setFitWidth(106);
 imgCours.setFitHeight(63);
 imgCours.setLayoutX(0);
 imgCours.setLayoutY(7);
  NomCoursGene=new Label();
  NomCoursGene.setText(""+nomCours);
 
  NomCoursGene.setLayoutX(156);
 NomCoursGene.setLayoutY(25);
NomCoursGene.setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
progCene=new ProgressBar();
 progCene.setPrefSize(567, 18);
 progCene.setLayoutX(287);
 progCene.setLayoutY(29);
 
  PourcentGene=new ProgressIndicator();

 PourcentGene.setPrefSize(69,56); 
  PourcentGene.setLayoutX(870);
  PourcentGene.setLayoutY(11);   
  
    if (nbChapTerm==nbchap){
    progCene.setProgress(1F);
    PourcentGene.setProgress(1F);}  
    else test1(nbChapTerm,nbchap);
 
           
        
        
         paneGene.setStyle("-fx-background-color: white;"
                            + "-fx-border-color: black;"
                            + "-fx-border-width: 1;"
                            + "-fx-border-radius: 6;"
                            + "-fx-padding:40;");
        
        paneGene.setPrefSize(578,90);
        
         paneGene.getChildren().addAll(imgCours,NomCoursGene,progCene,PourcentGene);
          
 TranslateTransition transition = new TranslateTransition(Duration.seconds(2));
        transition.setCycleCount(1);
        transition.setNode(paneGene);
       transition.setFromX(1000);
       transition.setToX(0);
      
     
        transition.play();
  return paneGene;
    }

    private void test1(int nbChapTerm, int nbchap) {
     if (nbChapTerm==nbchap/2){
       progCene.setProgress(0.5F);
    PourcentGene.setProgress(0.5F);}
        else if (nbChapTerm>nbchap/2){
           
            progCene.setProgress(0.7F); 
               progCene.setProgress(0.7F);
             
           }
           else if ((nbChapTerm<2)&&(nbChapTerm>=1)){
               progCene.setProgress(0.2F);
                   progCene.setProgress(0.2F);
               
              
           }
           else if ((nbChapTerm<nbchap/2)&&(nbChapTerm>1)){
               progCene.setProgress(0.3F);
                   progCene.setProgress(0.3F);
            
           }
           else if (nbChapTerm==0){
            //progCene.setProgress(0.0F);
                   //progCene.setProgress(0.0F);
           }
    }

    private Image getImageCours(Cours cours) {
     File file = new File(cours.getAffiche());
        return new Image(file.toURI().toString());
    }
    
}
