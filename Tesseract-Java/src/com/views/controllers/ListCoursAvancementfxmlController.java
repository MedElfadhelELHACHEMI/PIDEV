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
import javafx.scene.Node;
import javafx.scene.control.Button;
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
public class ListCoursAvancementfxmlController implements Initializable {
@FXML
    public  AnchorPane pane1;
      @FXML
    private TilePane tile;
                @FXML
    private Button   btretour;
          private String type;      
      private int id_User;
 private ImageView  imgcours;
public Cours c;  

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
        for(int i=0;i<list.size();i++){
            String NameMatiere=serviceApprenant.getNameMatiere(list.get(i));
            int nbChapTerm=serviceApprenant.nbChapitreTerminerByCours(list.get(i),CurrentUser.getId());
            int nbchap=serviceApprenant.nbChapitreByCours(list.get(i));
            String nameBadge=serviceApprenant.getNameBadge(list.get(i),CurrentUser.getId());
            
            
            if (nbchap==nbChapTerm){
           String img=list.get(i).getAffiche();
            imgcours = new ImageView(new Image(new File(img).toURI().toString()));
           imgcours.setFitWidth(215);
              imgcours.setFitHeight(132);
                  imgcours.setLayoutX(0);
               imgcours.setLayoutY(0);
//              nameCours[i].setText("Cours "+list.get(i).getNomCours());
            nameCours[i]=  new Label("Cours "+list.get(i).getNomCours());
            nameCours[i].setPrefSize(175, 26);
            
             nameCours[i].setLayoutX(20);
              nameCours[i].setLayoutY(150);
            nameCours[i].setStyle("\n"
                    + "    -fx-font-family: Impact;\n"
                    + "    -fx-font-style: bold;\n"
                    + "    -fx-font-size: 18px;\n"
                    + "      \n"
                    + "");
            nameMatiere[i]=  new Label("Matiere "+NameMatiere);
            
           nameMatiere[i].setPrefSize(170, 26);
   nameMatiere[i].setLayoutX(20);
   nameMatiere[i].setLayoutY(184);
            
            nameMatiere[i].setStyle("\n"
                    + "    -fx-font-family: Impact;\n"
                    + "    -fx-font-style: bold;\n"
                    + "    -fx-font-size: 18px;\n"
                    + "      \n"
                    + "");
            
            c=list.get(i);
            buttons[i] = new Button(""+i,imgcours);
            buttons[i].setMaxHeight(132);
            buttons[i].setMaxWidth(215);
            buttons[i].setStyle("\n"
                    + "    -fx-background-color: white;\n"
//                + "    -fx-border-width: 2px;\n"
                    + "    -fx-border-style: solid;\n"
                    + "  -fx-border-color: #222c37;\n"
                    + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n"
                    + " \n"
                    + "");
            buttons[i].setFont(new Font(0.1));
            
            VBox vboxMeals = new VBox(10);
            vboxMeals.setSpacing(5);
            Pane p=new Pane();
            p.setPrefSize(300, 300);
           
            p.getChildren().addAll(buttons[i],nameCours[i],nameMatiere[i]);
            vboxMeals.getChildren().addAll(p);
            
           buttons[i].setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                try {
                    Button b = (Button) event.getSource();
                    
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/AvancementCoursfxml.fxml"));
                    
                    
                    AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                    
                    AvancementCoursfxmlController controller = fxmlLoader.<AvancementCoursfxmlController>getController();
                    controller.setType(type);
                    controller.setC(list.get(Integer.parseInt(b.getText())));
                      
                    pane1.getChildren().setAll(anchorPane);
                } catch (IOException ex) {
                    Logger.getLogger(SuivreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }  });
            
            
            tile.getChildren().add(vboxMeals);
            }
            
        }
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
