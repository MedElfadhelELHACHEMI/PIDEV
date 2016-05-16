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
import com.models.entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
public class CoordoFormateurfxmlController implements Initializable {

   String name;
    @FXML
    private TilePane body;
        @FXML
    private AnchorPane pane1;
        @FXML
    private  ProgressBar scoreprog;
     @FXML
    private  Hyperlink  email;
     @FXML
    private  Label Name;
    @FXML
    private  Label sco; 
        private ImageView  imgcours;
        
        private ImageView  imgc;
        private ImageView  imgvue;
         Formateur f;
       @FXML
    private ImageView img;
      List<Cours> listcritCours = new ArrayList<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
       try {
           this.name = name;
           ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
            f=serviceApprenant.getFormateurByLogin(name);
           listcritCours=serviceApprenant.chercherCoursByNameFormateur(f.getNom(),CurrentUser.getId());
           Name.setText(f.getNom()+"&"+f.getPrenom());
           email.setText(f.getMail());
           scoreprog.setProgress(f.getScore()/100F);
           sco.setText(f.getScore()+"%");
          img.setImage(getImageUtilisateur(f));
           generatepane(listcritCours);  
          
       } catch (SQLException ex) {
           Logger.getLogger(CoordoFormateurfxmlController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    private Image getImageUtilisateur(Utilisateur utilisateur) {
    File file = new File(utilisateur.getPhoto());
        return new Image(file.toURI().toString());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
       @FXML
      void retourAction(ActionEvent event) throws IOException {
    try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/StatFormateurfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                StatFormateurfxmlController controller = fxmlLoader.<StatFormateurfxmlController>getController();
             
               pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
  @FXML
  void Contact(ActionEvent event) throws IOException {
      try {
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/MailXML.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                MailFXMLController controller = fxmlLoader.<MailFXMLController>getController();
                controller.setF(f);
                controller.setType("fc");
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }     
      }
 
    private void generatepane(List<Cours> listCours) {
    body.getChildren().clear();
      Button[] buttons = new Button[100];
     Label[] nameCours= new Label[100];
    Label[] nameMatiere = new Label[100];
    Label[] nameFormateur = new Label[100];
    Label[] nameDiff = new Label[100];
    Label[] nameNombvue = new Label[100];
     ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
    
   try {   
  for(int i=0;i<listCours.size();i++){
 String nMatiere=serviceApprenant.getNameMatiere(listCours.get(i));
 String nFormateur=serviceApprenant.getNameFormateur(listCours.get(i));
 int nbvue=serviceApprenant.afficherNombreVue(listCours.get(i));
   
 imgcours = new ImageView(new Image(new File(listCours.get(i).getAffiche()).toURI().toString()));
 imgcours.setFitHeight(121);
 imgcours.setFitWidth(209);
 imgcours.setLayoutX(15);
 imgcours.setLayoutY(0);
imgvue = new ImageView(new Image("/com/images/view-cours.png"));
imgvue.setFitWidth(27);
imgvue.setFitHeight(31);
imgvue.setLayoutX(40);
imgvue.setLayoutY(241);
imgc = new ImageView(new Image("/com/images/ain.png"));
imgc.setFitHeight(45);
imgc.setFitWidth(155);
nameCours[i]=  new Label("    Cours: "+listCours.get(i).getNomCours());
nameCours[i].setLayoutX(40);
nameCours[i].setLayoutY(131);
nameCours[i].setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
nameMatiere[i]=  new Label("    Matiere: "+nMatiere);
nameMatiere[i].setLayoutX(40);
nameMatiere[i].setLayoutY(157);
nameMatiere[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
nameFormateur[i]=  new Label("    Formateur: "+nFormateur);
nameFormateur[i].setLayoutX(40);

nameFormateur[i].setLayoutY(186);
nameFormateur[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
nameDiff[i]=  new Label("    Difficulté: "+listCours.get(i).getDifficulte());
nameDiff[i].setLayoutX(40);
nameDiff[i].setLayoutY(215);
nameDiff[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
nameNombvue[i]=  new Label("    nbVue: "+nbvue);
nameNombvue[i].setLayoutX(80);
nameNombvue[i].setLayoutY(246);
nameNombvue[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
buttons[i] = new Button(""+i,imgc);
buttons[i].setFont(new Font(0.1));
buttons[i].setLayoutX(52);
buttons[i].setLayoutY(282);
 VBox vboxMeals = new VBox(10);
  vboxMeals.setSpacing(1000);
        Pane p=new Pane();
       p.setPrefSize(250,338);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
    p.getChildren().addAll(imgcours,imgvue,nameDiff[i],nameCours[i],nameMatiere[i],nameNombvue[i],nameFormateur[i],buttons[i]);
    vboxMeals.getChildren().addAll(p);
     buttons[i].setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) { 
            Button b = (Button) event.getSource();
            try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/InscrireCoursfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                InscrireCoursfxmlController controller = fxmlLoader.<InscrireCoursfxmlController>getController();
                controller.setF(f);
                controller.setType("fc");
                 
                 controller.setCours(listCours.get(Integer.parseInt(b.getText())));
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            
            
            }});
  body.getChildren().add(vboxMeals);   
  }   
    } catch (SQLException ex) {
              Logger.getLogger(ChercherCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
          }   
    }     
}
