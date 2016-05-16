/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Matiere;
import com.models.entities.Organisation;
import java.io.File;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
public class ChercherOrgFormfxmlController implements Initializable {
   @FXML
    private ComboBox searchField;
      @FXML
    private Pane header;
    @FXML
    private TilePane body;
  int pos;
     @FXML
    private AnchorPane pane1;
   @FXML   
 private ImageView img; 
  private ImageView searchIcon;
   private ImageView imgfo; 
    private ImageView imgfobut; 
   @FXML
    private Pane p1;
    private String type;
    private ObservableList<String> NameData;  
   @FXML
    private Button searchCourse;
  List<Formateur> listf = new ArrayList<>(); 
  List<Organisation> listO = new ArrayList<>(); 
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
   ServiceApprenant serviceApprenant=new ServiceApprenantsIpl(); 
   try {   
       if(type.equals("f")){
     searchField.getEditor().setPromptText("Chercher Formateur");
   List<Formateur> list=serviceApprenant.getAllFormateurs();
 NameData = FXCollections.observableArrayList();
  for(int i=0;i<list.size();i++){
   NameData.add(list.get(i).getNom());
  } 
  searchField.getItems().addAll(NameData);
   listf=list;
   generatepaneFormateur(listf);        
           
       }
      if(type.equals("o")){
  searchField.getEditor().setPromptText("Chercher Organisation");
    List<Organisation> list=serviceApprenant.getAllOrganisation();
    
      NameData = FXCollections.observableArrayList();
  for(int i=0;i<list.size();i++){
   NameData.add(list.get(i).getNom());
  } 
  searchField.getItems().addAll(NameData); 
    listO=serviceApprenant.getAllOrganisation();
   generatepaneOrgani(listO);   
    }     
    } catch (SQLException ex) {
                Logger.getLogger(ChercherOrgFormfxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }    
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
           searchIcon = new ImageView(new Image("/com/images/search-formateur.png"));
           searchCourse.setGraphic(searchIcon);
      
    }    
    @FXML
    private void searchCourse(ActionEvent event) {
      try {    
    if(type.equals("f")){
      
            listf.clear();
            ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        
            Formateur f=serviceApprenant.getFormateurByName(searchField.getEditor().getText().toString());
              
       if (f.getIdUtilisateur()!=0){
         listf.add(f);
        generatepaneFormateur( listf);   
       
          
       }
       else{
       Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Formateur n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
         searchField.getEditor().setText(""); 
        if (alert.getResult() == ButtonType.OK) {
      List<Formateur> list=serviceApprenant.getAllFormateurs();
       generatepaneFormateur( list);}
       } 
     
    
     
    } 
        if(type.equals("o")){
      
            listO.clear();
            ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        
           Organisation o=serviceApprenant.getOrganisationByName(searchField.getEditor().getText().toString());
              
       if (o.getIdOrganisation()!=0){
         listO.add(o);
           generatepaneOrgani(listO);   
       
          
       }
       else{
       Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Organisation n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
         searchField.getEditor().setText(""); 
        if (alert.getResult() == ButtonType.OK) {
       List<Organisation> list=serviceApprenant.getAllOrganisation();
        generatepaneOrgani(list);  ;}
       } 
     
    
     
    }
      }  catch (SQLException ex) { 
           Logger.getLogger(ChercherOrgFormfxmlController.class.getName()).log(Level.SEVERE, null, ex);
       } 
    }
    private void generatepaneFormateur(List<Formateur> listf) {
    body.getChildren().clear();
    Button[] buttons = new Button[100];
     Label[] nameFormateur= new Label[100];
    Label[] score = new Label[100];
    Label[] ind = new Label[100];
    ProgressBar[] sc=new ProgressBar[100];
    Hyperlink[] mail=new Hyperlink[100];
    for(int i=0;i<listf.size();i++){
   imgfo= new ImageView(new Image(new File(listf.get(i).getPhoto()).toURI().toString())); 
   imgfobut= new ImageView(new Image("/com/images/cr.png"));
   imgfo.setFitHeight(134);
imgfo.setFitWidth(200);
 imgfo.setLayoutX(7);
 imgfo.setLayoutY(14);
imgfobut.setFitHeight(21);
imgfobut.setFitWidth(80);
 nameFormateur[i]=  new Label("    Name: "+listf.get(i).getNom()+" "+listf.get(i).getPrenom());
 nameFormateur[i].setLayoutX(23);
nameFormateur[i].setLayoutY(151);
nameFormateur[i].setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
score[i]=  new Label("    score: ");
score[i].setLayoutX(65);
score[i].setLayoutY(207);
score[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
ind[i]=  new Label(listf.get(i).getScore()+"%");
ind[i].setPrefSize(40, 17);
ind[i].setLayoutX(175);
ind[i].setLayoutY(230);
ind[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
 mail[i]=new Hyperlink();
 mail[i].setText(""+listf.get(i).getMail());
 mail[i].setId(""+i);
 mail[i].setPrefSize(161, 22);
 mail[i].setLayoutX(30);
 mail[i].setLayoutY(177);
 sc[i]=new ProgressBar();

        System.out.println("ddddddddddddddddddddddddddddddddddddddd"+listf.get(i).getScore());
 sc[i].setProgress(listf.get(i).getScore()/100F);
 sc[i].setMinWidth(163);
 sc[i].setLayoutX(7);
 sc[i].setLayoutY(230);
 pos=i;
 buttons[i] = new Button(""+i,imgfobut);
 buttons[i].setFont(new Font(0.1));
 buttons[i].setMinSize(122,30);
buttons[i].setLayoutX(46);
buttons[i].setLayoutY(265);
 VBox vboxMeals = new VBox(10);
  vboxMeals.setSpacing(1000);
        Pane p=new Pane();
       p.setPrefSize(214,300);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
    p.getChildren().addAll(imgfo,nameFormateur[i],mail[i],sc[i],score[i],buttons[i],ind[i]);
    vboxMeals.getChildren().addAll(p);
     buttons[i].setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) { 
            Button b = (Button) event.getSource();
      try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                controller.setfC(listf.get(Integer.parseInt(b.getText())));
                 controller.setType("fC");
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }      
               
      }});
mail[i].setOnAction((ActionEvent e) -> {
 Hyperlink b = (Hyperlink) e.getSource();  
 try {
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/MailXML.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                MailFXMLController controller = fxmlLoader.<MailFXMLController>getController();
                controller.setF(listf.get(Integer.parseInt(b.getId())));
                controller.setType("f");
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
});    
  body.getChildren().add(vboxMeals);

    }
    }

    private void generatepaneOrgani(List<Organisation> listO) {
      body.getChildren().clear();
    Button[] buttons = new Button[100];
     Label[] nameOrgan= new Label[100];
    Label[] Adress = new Label[100];
    
    Hyperlink[] mail=new Hyperlink[100];
    for(int i=0;i<listO.size();i++){
   imgfo= new ImageView(new Image(new File(listO.get(i).getPhoto()).toURI().toString())); 
   imgfobut= new ImageView(new Image("/com/images/cr.png"));
   imgfo.setFitHeight(150);
imgfo.setFitWidth(200);
 imgfo.setLayoutX(11);
 imgfo.setLayoutY(6);
imgfobut.setFitHeight(32);
imgfobut.setFitWidth(56);
 nameOrgan[i]=  new Label("    Name: "+listO.get(i).getNom());
 nameOrgan[i].setMinSize(156, 17);
 nameOrgan[i].setLayoutX(46);
nameOrgan[i].setLayoutY(164);
nameOrgan[i].setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
//Adress[i]=  new Label("Adresse: ");
//Adress[i].setMinSize(159, 17);
//Adress[i].setLayoutX(45);
//Adress[i].setLayoutY(195);
//Adress[i].setStyle("\n"
//                + "    \n"
//                + "    -fx-font-style: italic;\n"
//                + "    -fx-font-size: 15px;\n"
//                + "      \n"
//                + "");

 mail[i]=new Hyperlink();
 mail[i].setText(""+listO.get(i).geteMail());
 mail[i].setId(""+i);
mail[i].setPrefSize(161, 22);
 mail[i].setLayoutX(66);
 mail[i].setLayoutY(199);


 buttons[i] = new Button(""+i,imgfobut);
 buttons[i].setFont(new Font(0.1));
 buttons[i].setMinSize(120,26);
buttons[i].setLayoutX(56);
buttons[i].setLayoutY(239);
 VBox vboxMeals = new VBox(10);
  vboxMeals.setSpacing(1000);
        Pane p=new Pane();
       p.setPrefSize(221,283);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
    p.getChildren().addAll(imgfo,nameOrgan[i],mail[i],buttons[i]);
    vboxMeals.getChildren().addAll(p);
     buttons[i].setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) { 
            Button b = (Button) event.getSource();
       try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                controller.setoC(listO.get(Integer.parseInt(b.getText())));
                 controller.setType("oC");
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }    
               
      }});
mail[i].setOnAction((ActionEvent e) -> {
 Hyperlink b = (Hyperlink) e.getSource();  
 try {
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/MailXML.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                MailFXMLController controller = fxmlLoader.<MailFXMLController>getController();
                controller.setO(listO.get(Integer.parseInt(b.getId())));
                controller.setType("o");
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
});    
  body.getChildren().add(vboxMeals);

    }
    }
}
