/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class SuivreCoursfxmlController implements Initializable {
@FXML
    public  AnchorPane pane1;
      @FXML
    private TilePane tile;
                @FXML
    private Button   btretour;
      private int id_User;
      private ImageView img;
       private ImageView searchIcon;
            
        private ImageView  imgc;
        private ImageView  imgvue;
 private ImageView  imgcours;
public Cours c;           
     public int getId_User() {
        return id_User;
    }
      public void setId_User(int id_User) {

    try {
        this.id_User = id_User;
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        List<Cours> list=serviceApprenant.chercherCoursByLoginApprenant(CurrentUser.getUtilisateur().getNomUtilisateur());
        Button[] buttons = new Button[100];
        Label[] nameCours= new Label[100];
        Label[] nameMatiere = new Label[100];
         Label[] nameFormateur = new Label[100];
    Label[] nameDiff = new Label[100];
    Label[] nameNombvue = new Label[100];
        for(int i=0;i<list.size();i++){
            String NameMatiere=serviceApprenant.getNameMatiere(list.get(i));
            int nbChapTerm=serviceApprenant.nbChapitreTerminerByCours(list.get(i),CurrentUser.getId());
            int nbchap=serviceApprenant.nbChapitreByCours(list.get(i));
            String nameBadge=serviceApprenant.getNameBadge(list.get(i),CurrentUser.getId());
             String nMatiere=serviceApprenant.getNameMatiere(list.get(i));
             String nFormateur=serviceApprenant.getNameFormateur(list.get(i));
               int nbvue=serviceApprenant.afficherNombreVue(list.get(i));
            
            
            if (nbchap!=nbChapTerm){
            if (nameBadge.equals("true")) {
                
            }
            else{ String img=list.get(i).getAffiche();
           imgcours = new ImageView(new Image(new File(list.get(i).getAffiche()).toURI().toString()));
 imgcours.setFitHeight(121);
 imgcours.setFitWidth(209);
 imgcours.setLayoutX(15);
 imgcours.setLayoutY(0);
imgvue = new ImageView(new Image("/com/images/view-cours.png"));
imgvue.setFitWidth(27);
imgvue.setFitHeight(31);
imgvue.setLayoutX(40);
imgvue.setLayoutY(241);
imgc = new ImageView(new Image("/com/images/logo.jpg"));
imgc.setFitHeight(45);
imgc.setFitWidth(155);
nameCours[i]=  new Label("    Cours: "+list.get(i).getNomCours());
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
nameDiff[i]=  new Label("    Difficulté: "+list.get(i).getDifficulte());
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
                try {
                    Button b = (Button) event.getSource();
                    
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/chapitreCoursfxml.fxml"));
                    
                    
                    AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                    
                    ChapitreCoursfxmlController  controller = fxmlLoader.<ChapitreCoursfxmlController >getController();
                    
                    controller.setCours(list.get(Integer.parseInt(b.getText())));
                      
                    pane1.getChildren().setAll(anchorPane);
                } catch (IOException ex) {
                    Logger.getLogger(SuivreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }  });
            
            
            tile.getChildren().add(vboxMeals);
            }
            
        }}
    } catch (SQLException ex) {
        Logger.getLogger(SuivreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
   @FXML
      void retourAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  
    }  
       private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
        pane1.getChildren().setAll(node);
     }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
