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
import com.models.entities.Notification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class SuivreChapitrefxmlController implements Initializable {
@FXML
    public  AnchorPane pane1;
      @FXML
    private TilePane tile;
       private Cours cours;
        private ImageView  imgcours;
        private ImageView  imgpdf;
        private ImageView  imgvideo;
         private static boolean pointer2 = false;
    private static boolean pointer1 = false;
    private static FadeTransition OldTra;
    private static ImageView OldQuizCh;
    private static ImageView OldQuizNch;
    List<Chapitre> list;
    int nb;
    private ImageView quizCh;
    private ImageView quizNotCh;
    private ImageView viewIcon;
      private FadeTransition ft;
   
        String type;
       public Cours getCours() {
        return cours;
    }
        public void setCours(Cours cours) {
    try {
        this.cours = cours;
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
      
        list=serviceApprenant.findChapitreByIdCours(cours.getIdCours());
        
          
           Button[] buttonsvid = new Button[100];
            Button[] buttonspdf = new Button[100];
             Label[] nameCours= new Label[100];
             Label[] nameMatiere = new Label[100];
        nb=serviceApprenant.nbChapitreTerminerByCours(cours,CurrentUser.getId());
       int nbchap=serviceApprenant.nbChapitreByCours(cours);
       for(int i=nb;i<list.size();i++){
        imgcours = new ImageView(new Image("/com/images/"+list.get(i).getNumero()+".jpg"));
        imgpdf = new ImageView(new Image("/com/images/filetype_pdf.png"));
        imgvideo = new ImageView(new Image("/com/images/corcv.jpg"));
        imgcours.setFitWidth(219);
        imgcours.setFitHeight(150);
        imgcours.setLayoutX(6);
        imgcours.setLayoutY(0);
          imgpdf.setFitWidth(44);
        imgpdf.setFitHeight(36);
        
           imgvideo.setFitWidth(50);
         imgvideo.setFitHeight(37);
         imgvideo.setLayoutX(130);
         imgvideo.setLayoutY(217);
         nameCours[i]=  new Label("    Chapitre "+cours.getNomCours());
               nameCours[i].setPrefSize(187, 27);
                nameCours[i].setLayoutX(14);
              nameCours[i].setLayoutY(167);
              
                nameCours[i].setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
        buttonspdf[i] = new Button(""+i,imgpdf);
            buttonspdf[i].setLayoutX(20);
         buttonspdf[i].setLayoutY(217);
          buttonspdf[i].setFont(new Font(0.1));
          buttonsvid[i] = new Button(""+i,imgvideo);
            buttonsvid[i].setLayoutX(150);
         buttonsvid[i].setLayoutY(217);
          buttonsvid[i].setFont(new Font(0.1));
            VBox vboxMeals = new VBox(10);
                vboxMeals.setSpacing(10);
            Pane p=new Pane();
            p.setPrefSize(230, 265);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
            p.getChildren().addAll(buttonspdf[i],nameCours[i],imgcours,buttonsvid[i]);
            vboxMeals.getChildren().addAll(p);
     buttonspdf[i].setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                Button b = (Button) event.getSource();
                 try {
              File f = new File(list.get(Integer.parseInt(b.getText())).getResume());
              if (f.exists()) {
                   
                        String g="rundll32 url.dll,FileProtocolHandler "+f.getAbsolutePath();
                        Process p = Runtime.getRuntime().exec(g);
                        p.waitFor();
               ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
               
               boolean verif=serviceApprenant.modifierNombreChapCours(cours, CurrentUser.getId(),nb+1);
                    
              }else { 
                 Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur: Le fichier n'existe pas!", ButtonType.FINISH);
          alert.show();  
              }
                 
                 } catch (IOException ex) {
                        Logger.getLogger(SuivreChapitrefxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    
              } catch (InterruptedException ex) {
                    Logger.getLogger(SuivreChapitrefxmlController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SuivreChapitrefxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
       
        buttonsvid[i].setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                try {
                    Button b = (Button) event.getSource();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/VideoCoursShowfxml.fxml"));
                    
                    AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                    
                    VideoCoursShowfxmlController controller = fxmlLoader.<VideoCoursShowfxmlController>getController();
                    controller.setCours(cours);
                    controller.setChapitre(list.get(Integer.parseInt(b.getText())));
                     pane1.getChildren().setAll(anchorPane);
                } catch (IOException ex) {
                    Logger.getLogger(SuivreChapitrefxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
                              
                               
             
            }});
       
      tile.getChildren().add(vboxMeals); }
               
             
    } catch (SQLException ex) {
        Logger.getLogger(SuivreChapitrefxmlController.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
}
