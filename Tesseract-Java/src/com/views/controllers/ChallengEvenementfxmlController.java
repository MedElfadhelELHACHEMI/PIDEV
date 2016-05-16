/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Challenge;
import com.models.entities.Evenement;
import com.models.entities.Formateur;
import com.models.entities.InscriptionChallenge;
import com.models.entities.InscriptionEvenement;
import com.models.entities.Organisation;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ChallengEvenementfxmlController implements Initializable {
 @FXML
    private ComboBox searchField;
 @FXML
    private Pane header;
    @FXML
    private TilePane body;
     @FXML
    private AnchorPane pane1;
      private ObservableList<String> NameData;  
     @FXML
    private Button searchCourse;
     List<Evenement> listEv = new ArrayList<>(); 
  List<Challenge> listCha = new ArrayList<>(); 
 String type;
 Evenement evenement;
 Challenge challenge;
 Organisation organisation;
 private ImageView searchIcon;
 private ImageView imgfobut;
 private ImageView imgvue;
  private ImageView imgfo; 
    public String getType() {
        return type;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setType(String type) {
        this.type = type;
    ServiceApprenant serviceApprenant=new ServiceApprenantsIpl(); 
    try {   
         if(type.equals("e")){
         searchField.getEditor().setPromptText("Chercher Evenement"); 
          List<Organisation> list=serviceApprenant.getAllOrganisation();
                NameData = FXCollections.observableArrayList();
                for(int i=0;i<list.size();i++){
             NameData.add(list.get(i).getNom());
                     } 
                 searchField.getItems().addAll(NameData);
         listEv=serviceApprenant.displayEvenement(CurrentUser.getId());
          generatepaneEvenement(listEv);
         }
          if(type.equals("c")){
              searchField.getEditor().setPromptText("Chercher Challenge"); 
          List<Organisation> list=serviceApprenant.getAllOrganisation();
                NameData = FXCollections.observableArrayList();
                for(int i=0;i<list.size();i++){
             NameData.add(list.get(i).getNom());
                     } 
                 searchField.getItems().addAll(NameData);
              listCha=serviceApprenant.displayChallenge(CurrentUser.getId());
          generatepaneChallenge( listCha);   
         }
          
          
      } catch (SQLException ex) {
                Logger.getLogger(ChercherOrgFormfxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }
    

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchIcon = new ImageView(new Image("/com/images/search-formateur.png"));
           searchCourse.setGraphic(searchIcon);
    }    

    private void generatepaneEvenement(List<Evenement> listEv) {
     body.getChildren().clear();
      Button[] buttons = new Button[100];
       Label[] NameOrg = new Label[100];
    Label[] NameEvenement = new Label[100];
     Label[] descrip = new Label[100];
    Label[] NbMax = new Label[100];
     Label[] date= new Label[100];
  ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();    
for(int i=0;i<listEv.size();i++){
 organisation=serviceApprenant.getOrganisationByid(listEv.get(i).getIdOrganisation());
 imgfo= new ImageView(new Image(new File(listEv.get(i).getAffiche()).toURI().toString())); 
 imgfo.setFitHeight(109);
imgfo.setFitWidth(176);
 imgfo.setLayoutX(11);
 imgfo.setLayoutY(8);
 imgvue = new ImageView(new Image("/com/images/nbins.gif"));
  imgvue.setFitHeight(31);
imgvue.setFitWidth(28);
imgvue.setLayoutX(0);
 imgvue.setLayoutY(227);
 imgfobut= new ImageView(new Image("/com/images/par.gif"));
  imgfobut.setFitHeight(44);
imgfobut.setFitWidth(155);
imgfobut.setLayoutX(0);
imgfobut.setLayoutY(0);
NameOrg[i]=new Label(" Organisation: "+organisation.getNom());
NameOrg[i].setLayoutX(30);
NameOrg[i].setLayoutY(129);
NameOrg[i].setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
NameEvenement[i]=new Label(" Evenement: "+listEv.get(i).getNom());
NameEvenement[i].setPrefSize(150, 23);
NameEvenement[i].setLayoutX(30);
NameEvenement[i].setLayoutY(163);
NameEvenement[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
descrip[i]=new Label(" descrip: "+listEv.get(i).getDescription());
descrip[i].setPrefSize(150, 23);
descrip[i].setLayoutX(30);
descrip[i].setLayoutY(196);
descrip[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
NbMax[i]=new Label(" NbMax: "+listEv.get(i).getNbrMax());
NbMax[i].setPrefSize(100, 23);
NbMax[i].setLayoutX(32);
NbMax[i].setLayoutY(229);
NbMax[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
date[i]=new Label(" Date: "+listEv.get(i).getDateEvenement());
date[i].setPrefSize(120, 23);
date[i].setLayoutX(30);
date[i].setLayoutY(261);
date[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
buttons[i] = new Button(""+i,imgfobut);
buttons[i].setFont(new Font(0.1));
 
buttons[i].setLayoutX(13);
buttons[i].setLayoutY(299);
 VBox vboxMeals = new VBox(10);
  vboxMeals.setSpacing(1000);
        Pane p=new Pane();
       p.setPrefSize(197,384);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
    p.getChildren().addAll(imgfo,NameOrg[i],descrip[i],NameEvenement[i],NbMax[i],buttons[i],date[i], imgvue);
    vboxMeals.getChildren().addAll(p);
     buttons[i].setOnAction(new EventHandler<ActionEvent>() {
       @Override
            public void handle(ActionEvent event) { 
                Button b = (Button) event.getSource();
          try {
              
               InscriptionEvenement inscriptionEvenement=new InscriptionEvenement();
               ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
              int nbins=serviceApprenant.nbrUtilisateurinscritEvenement(listEv.get(Integer.parseInt(b.getText())).getIdEvenement());
              if (nbins==listEv.get(Integer.parseInt(b.getText())).getNbrMax()){
                  Alert alert = new Alert(Alert.AlertType.ERROR, "Desolée pas de place ", ButtonType.OK, ButtonType.CANCEL);
                   alert.showAndWait();  
              }
              else{
                  inscriptionEvenement.setIdEvenement(listEv.get(Integer.parseInt(b.getText())).getIdEvenement());
                  inscriptionEvenement.setIdUtilisateur(CurrentUser.getId());
                  boolean verif=serviceApprenant.ajouterInscriptionEvenement(inscriptionEvenement);
                if (verif){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "ok ", ButtonType.OK, ButtonType.CANCEL);
                   alert.showAndWait();
                 List list=serviceApprenant.displayEvenement(CurrentUser.getId()) ;
                  generatepaneEvenement(list); 
               }    
              }
             
           } catch (SQLException ex) {
               Logger.getLogger(ChallengEvenementfxmlController.class.getName()).log(Level.SEVERE, null, ex);
           }     
        }});
      body.getChildren().add(vboxMeals);
}
    }

    private void generatepaneChallenge(List<Challenge> listCha) {
 body.getChildren().clear();
      Button[] buttons = new Button[100];
       Label[] NameOrg = new Label[100];
    Label[] NameChall = new Label[100];
     Label[] descrip = new Label[100];
    Label[] theme = new Label[100];
     Label[] duree= new Label[100];
      Label[] date= new Label[100];
  ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();    
for(int i=0;i<listCha.size();i++){
 organisation=serviceApprenant.getOrganisationByid(listCha.get(i).getIdOrganisation());
 imgfo= new ImageView(new Image(new File(organisation.getPhoto()).toURI().toString())); 
 imgfo.setFitHeight(131);
imgfo.setFitWidth(186);
 imgfo.setLayoutX(14);
 imgfo.setLayoutY(14);
imgfobut= new ImageView(new Image("/com/images/par.gif"));
  imgfobut.setFitHeight(37);
imgfobut.setFitWidth(187);
imgfobut.setLayoutX(0);
imgfobut.setLayoutY(0);
NameOrg[i]=new Label(" Organisation: "+organisation.getNom());
NameOrg[i].setLayoutX(45);
NameOrg[i].setLayoutY(161);
NameOrg[i].setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
NameChall[i]=new Label(" Challenge: "+listCha.get(i).getNom());
NameChall[i].setPrefSize(150, 23);
NameChall[i].setLayoutX(45);
NameChall[i].setLayoutY(190);
NameChall[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
descrip[i]=new Label(" descrip: "+listCha.get(i).getDescription());
descrip[i].setPrefSize(150, 23);
descrip[i].setLayoutX(45);
descrip[i].setLayoutY(219);
descrip[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
theme[i]=new Label(" theme: "+listCha.get(i).getTheme());
theme[i].setPrefSize(100, 23);
theme[i].setLayoutX(45);
theme[i].setLayoutY(248);
theme[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
date[i]=new Label(" Date: "+listCha.get(i).getDateChallenge());
date[i].setPrefSize(120, 23);
date[i].setLayoutX(45);
date[i].setLayoutY(300);
date[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
duree[i]=new Label(" Duree: "+listCha.get(i).getDurée());
duree[i].setPrefSize(120, 23);
duree[i].setLayoutX(45);
duree[i].setLayoutY(273);
duree[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
buttons[i] = new Button(""+i,imgfobut);
buttons[i].setFont(new Font(0.1));
  buttons[i].setMinSize(175,43);
buttons[i].setLayoutX(14);
buttons[i].setLayoutY(328);
 VBox vboxMeals = new VBox(10);
  vboxMeals.setSpacing(1000);
        Pane p=new Pane();
       p.setPrefSize(213,388);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
    p.getChildren().addAll(imgfo,NameOrg[i],descrip[i],NameChall[i],theme[i],buttons[i],date[i],duree[i]);
    vboxMeals.getChildren().addAll(p);
     buttons[i].setOnAction(new EventHandler<ActionEvent>() {
       @Override
            public void handle(ActionEvent event) { 
           try {
               Button b = (Button) event.getSource();
               InscriptionChallenge inscriptionChallenge=new InscriptionChallenge();
               inscriptionChallenge.setIdUtilisateur(CurrentUser.getId());
               inscriptionChallenge.setIdchallenge(listCha.get(Integer.parseInt(b.getText())).getIdChallenge());
               ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
               boolean verif=serviceApprenant.ajouterInscriptionChallenge(inscriptionChallenge);
               if (verif){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "ok ", ButtonType.OK, ButtonType.CANCEL);
                   alert.showAndWait();
                 List listCha=serviceApprenant.displayChallenge(CurrentUser.getId()) ;
                  generatepaneChallenge(listCha); 
               }
           } catch (SQLException ex) {
               Logger.getLogger(ChallengEvenementfxmlController.class.getName()).log(Level.SEVERE, null, ex);
           }
          
          
        }});
      body.getChildren().add(vboxMeals);
}
    }
   @FXML
    private void searchCourse(ActionEvent event) {
   
     ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();    
         if(type.equals("e")){
          listEv.clear(); 
     List<Evenement> f=serviceApprenant.getEvenementByNomOrganisation(searchField.getEditor().getText().toString(),CurrentUser.getId());     
         if (f.isEmpty()){
          Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Organisation n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
         searchField.getEditor().setText(""); 
        if (alert.getResult() == ButtonType.OK) {
                try {
                    listEv=serviceApprenant.displayEvenement(CurrentUser.getId());   
                    generatepaneEvenement(listEv);
                } catch (SQLException ex) {
                    Logger.getLogger(ChallengEvenementfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
         }      
         }
         else{
            listEv=f;
            generatepaneEvenement(listEv);
             }}
         if(type.equals("c")){
           listCha.clear(); 
      List<Challenge> c=serviceApprenant.getChallengeByNomOrganisation(searchField.getEditor().getText().toString(),CurrentUser.getId()); 
          if (c.isEmpty()){
          Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Organisation n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
         searchField.getEditor().setText(""); 
        if (alert.getResult() == ButtonType.OK) {
            listCha=serviceApprenant.displayChallenge(CurrentUser.getId()) ;
            generatepaneChallenge(listCha);
        }      
         }
         else{
            listCha=c;
            generatepaneChallenge(listCha);
             }}
         }
        
        
    } 

