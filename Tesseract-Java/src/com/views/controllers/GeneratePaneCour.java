
package com.views.controllers;

import com.models.entities.Cours;
import com.models.enums.Difficulte;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author haikal
 */
public class GeneratePaneCour extends Pane {

    private RotateTransition rt;
    private Label views;
    private Label uploadDate;
    private Label nom;
    private static boolean pointer2 = false;
    private static boolean pointer1 = false;
    private static FadeTransition OldTra;
   public static ImageView OldQuizCh;
    private static ImageView OldQuizNch;
    private ImageView cours;
    public  ImageView quizCh;
    private ImageView quizNotCh;
    private ImageView viewIcon;
private ImageView imgCours;
private Label nameCours,nameMatiere;
private Label dificulte;
private ImageView com;
    private Text text;
    private ProgressBar level;
    private FadeTransition ft;

    public GeneratePaneCour( Cours cours,String NameMatiere,int x,int y, String coursPic,String nameBadge) {
        super();
        setPrefSize(215, 245);
        setLayoutX(x);
        setLayoutY(y);
        setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        addNodes(cours, NameMatiere,coursPic,nameBadge);

    }

    private void addNodes(Cours cours,String NameMatiere , String coursPic,String nameBadge) {
     imgCours = new ImageView(new Image(new File(coursPic).toURI().toString()));
 imgCours.setFitWidth(215);
 imgCours.setFitHeight(132);
 imgCours.setLayoutX(0);
 imgCours.setLayoutY(0);
  nameCours=new Label();
  nameCours.setText("Cours "+cours.getNomCours());
  nameCours.setPrefSize(175, 26);
  nameCours.setLayoutX(20);
  nameCours.setLayoutY(150);
  nameCours.setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
  nameMatiere=new Label();
  nameMatiere.setText("Matiere "+NameMatiere);
  nameMatiere.setPrefSize(170, 26);
  nameMatiere.setLayoutX(20);
  nameMatiere.setLayoutY(184);
  nameMatiere.setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
   dificulte=new Label();
  dificulte.setText("Dificulte "+cours.getDifficulte()+"");
  dificulte.setPrefSize(174, 25);
  dificulte.setLayoutX(20);
  dificulte.setLayoutY(214);
  dificulte.setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
    
//        });
       imgCours.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (ft != null) {

                    OldTra.setToValue(1);
                    OldTra.play();
                    OldQuizCh.setVisible(false);
                   

                }

                ft = new FadeTransition(Duration.seconds(0.1));
                OldTra = ft;
                OldQuizCh =quizCh ;
               
                ft.setNode(imgCours);
                ft.setCycleCount(1);
                ft.setToValue(0.4);
                ft.play();
                quizCh.setVisible(true);
                ;
            }

        });
      if (nameBadge.equals("true")){
        quizCh = new ImageView(new Image("/com/images/com.png"));
      }
      else{
      quizCh = new ImageView(new Image("/com/images/com.jpg"));    
      }

        quizCh.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                rt = new RotateTransition(Duration.seconds(1), quizCh);
                rt.setCycleCount(1);
                rt.setByAngle(360);
                rt.play();
//                try { 
//                    setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));
//                } catch (IOException ex) {
//                    Logger.getLogger(GeneratePaneCour.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });

    
      quizCh.setFitHeight(40);
        quizCh.setFitWidth(40);
        quizCh.setLayoutX(77);
        quizCh.setCursor(Cursor.HAND);
        quizCh.setLayoutY(80);
       
        quizCh.setVisible(false);
     
       
        

      getChildren().addAll( imgCours,nameCours,nameMatiere,dificulte,quizCh);

    }

  
     private Image getImageCours(Cours cours) {

        File file = new File(cours.getAffiche());
        return new Image(file.toURI().toString());
    } 
    
}
