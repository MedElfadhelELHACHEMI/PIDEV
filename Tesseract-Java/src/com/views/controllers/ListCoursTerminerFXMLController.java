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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Sameh
 */
public class ListCoursTerminerFXMLController implements Initializable {
@FXML
    private Pane header;
    @FXML
    public  AnchorPane body;
     @FXML
    public  AnchorPane pane1;
    
      List<Cours> listCours = new ArrayList<>();
     private RotateTransition rt;  
//     private Pane ccm;
     private Pane paneGene;
private ImageView imgCours;
private Label nameCours;
private Label dificulte;
private ImageView com;
private Label nameFormateur;
private Label nameMatiere;
//private ImageView quizCh;
private static ImageView OldQuizCh;
private static FadeTransition OldTra;
  private FadeTransition ft;
  private HBox vList;
   private VBox HList;
   private Button Course;
   private GeneratePaneCour ccm;
    @FXML
   private ScrollPane scroCours;
       @FXML
    private Button   btretour;
    int X ;
       int Y;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    try {
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
       List<Cours> list=serviceApprenant.chercherCoursByLoginApprenant(CurrentUser.getUtilisateur().getNomUtilisateur());
       
             generateContainersCourses(list); 

//        quizCh = new ImageView(new Image("/com/images/com.png"));
    } catch (SQLException ex) {
        Logger.getLogger(ListCoursTerminerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
   
       
    }
    }
 @FXML
      void retourAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  
    }
    private void generateContainersCourses(List<Cours> list) {
       X = 21;
        Y = 14;

         
          try {
        for (Cours cours : list) {
       
               ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
              
               int nbChapTerm=serviceApprenant.nbChapitreTerminerByCours(cours,CurrentUser.getId());
               int nbchap=serviceApprenant.nbChapitreByCours(cours);
               String nameBadge=serviceApprenant.getNameBadge(cours, CurrentUser.getId());
               System.out.println("dddddddddddddddddddddddddddddddddddddddddddd"+nameBadge);
                String NameMatiere=serviceApprenant.getNameMatiere(cours);
               if (nbChapTerm==nbchap){ 
               

                if (X<=720){
                   ccm = new GeneratePaneCour(cours, NameMatiere, X, Y,cours.getAffiche(),nameBadge);   
                   X=X+233;
                    System.out.println("ffffffffffffffffffffffffffffffffff"+cours.getAffiche()); }
                else {
                  Y=Y+270;
                  X=21;
                 ccm = new GeneratePaneCour(cours, NameMatiere, X, Y,cours.getAffiche(),nameBadge);   
                
                }

            TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5));
            transition.setFromY(1000);
            transition.setToY(0);
            transition.setNode(ccm);
            transition.setCycleCount(1);
             
            body.getChildren().add(ccm);
          
                transition.play();
        }}


        
        } catch (SQLException ex) {
               Logger.getLogger(ListCoursTerminerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
   private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
       pane1.getChildren().setAll(node);

    }
  
    

//    private Pane generatePane(Cours cours,String NameMatiere) {
//    paneGene=new Pane();
// imgCours=new ImageView();
//  imgCours.setImage(getImageCours(cours));
// imgCours.setFitWidth(215);
// imgCours.setFitHeight(132);
// imgCours.setLayoutX(0);
// imgCours.setLayoutY(0);
// Course=new Button();
//   nameCours=new Label();
//  nameCours.setText("Cours "+cours.getNomCours());
//  nameCours.setPrefSize(175, 26);
//  nameCours.setLayoutX(20);
//  nameCours.setLayoutY(150);
//  nameCours.setStyle("\n"
//                + "    -fx-font-family: Impact;\n"
//                + "    -fx-font-style: bold;\n"
//                + "    -fx-font-size: 18px;\n"
//                + "      \n"
//                + "");
//  nameMatiere=new Label();
//  nameMatiere.setText("Matiere "+NameMatiere);
//  nameMatiere.setPrefSize(170, 26);
//  nameMatiere.setLayoutX(20);
//  nameMatiere.setLayoutY(184);
//  nameMatiere.setStyle("\n"
//                + "    -fx-font-family: Impact;\n"
//                + "    -fx-font-style: bold;\n"
//                + "    -fx-font-size: 18px;\n"
//                + "      \n"
//                + "");
//   dificulte=new Label();
//  dificulte.setText("Dificulte "+cours.getDifficulte()+"");
//  dificulte.setPrefSize(174, 25);
//  dificulte.setLayoutX(20);
//  dificulte.setLayoutY(214);
//  dificulte.setStyle("\n"
//                + "    -fx-font-family: Impact;\n"
//                + "    -fx-font-style: bold;\n"
//                + "    -fx-font-size: 18px;\n"
//                + "      \n"
//                + "");
//
//   imgCours.setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                if (ft != null) {
//
//                    OldTra.setToValue(1);
//                    OldTra.play();
//                    OldQuizCh.setVisible(false);
//                   
//
//                }
//
//                ft = new FadeTransition(Duration.seconds(0.1));
//                OldTra = ft;
//                OldQuizCh =quizCh ;
//                
//                ft.setNode(imgCours);
//                ft.setCycleCount(1);
//                ft.setToValue(0.4);
//                ft.play();
//                quizCh.setVisible(true);
//               
//            }
//
//        });
// quizCh = new ImageView(new Image("/com/images/com.png"));
////  quizCh.setOnMouseEntered(new EventHandler<MouseEvent>() {
////
////            @Override
////            public void handle(MouseEvent event) {
////                rt = new RotateTransition(Duration.seconds(1), quizCh);
////                rt.setCycleCount(1);
////                rt.setByAngle(360);
////                rt.play();
////            }
////        });
//  Course.setPrefSize(50, 50);
//  Course.setLayoutX(77);
//  Course.setLayoutY(80);
//  Course.setGraphic(quizCh);
//   Course.setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                rt = new RotateTransition(Duration.seconds(1), Course);
//                rt.setCycleCount(1);
//                rt.setByAngle(360);
//                rt.play();
//            }
//        });
//     quizCh.setFitHeight(40);
//        quizCh.setFitWidth(40);
//        quizCh.setLayoutX(77);
//        quizCh.setCursor(Cursor.HAND);
//        quizCh.setLayoutY(80);
//        
//        Course.setVisible(false);
//   paneGene.setStyle("-fx-background-color: white;"
//                            + "-fx-border-color: black;"
//                            + "-fx-border-width: 1;"
//                            + "-fx-border-radius: 6;"
//                            + "-fx-padding: 6;");
//    paneGene.setPrefSize(215, 245);
//    paneGene.getChildren().addAll( imgCours,nameCours,nameMatiere,dificulte,Course);
// 
// TranslateTransition transition = new TranslateTransition(Duration.seconds(2));
//        transition.setCycleCount(1);
//        transition.setNode(paneGene);
//       transition.setFromX(1000);
//       transition.setToX(0);
//      
//     
//        transition.play();
// return paneGene;
//    }
// private Image getImageCours(Cours cours) {
//
//        File file = new File(cours.getAffiche());
//        return new Image(file.toURI().toString());
    } 
