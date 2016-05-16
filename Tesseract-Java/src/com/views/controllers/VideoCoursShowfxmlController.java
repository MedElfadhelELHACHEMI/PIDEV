/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.entities.Chapitre;
import com.models.entities.Cours;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class VideoCoursShowfxmlController implements Initializable {
 @FXML
  private AnchorPane pane1;
   @FXML
  private MediaView vue;
    @FXML
    private Label titre;
      @FXML
  private Pane pvol;
   Cours cours;   
       @FXML
    private Slider volumeSlider;
        @FXML
    private ImageView imgvol;
     private MediaPlayer mp;
      private Media a;
      private int fini;
    private Chapitre chapitre;

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
       titre.setText("Chapitre "+chapitre.getNumero()+" : "+chapitre.getNom());
       File f = new File(chapitre.getDescription());
         String str = "file:/"+f.getAbsolutePath();
          str=str.replace("\\", "/");
          a = new Media(str);
          mp = new MediaPlayer(a);
          mp.setVolume(volumeSlider.getValue()/100.0);
          vue.setMediaPlayer(mp);
          mp.play();
          mp.setOnEndOfMedia(new Runnable() {
              public void run() {
                  fini=1;
              }});
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Cours getCours() {
        return cours;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     fini=0;
        volumeSlider.valueProperty().addListener(new ChangeListener(){
            @Override public void changed(ObservableValue o, Object oldVal, Object newVal){
                double x1Val = (Double) newVal;
                mp.setVolume(new Double(x1Val/100.0));
               if (x1Val==0.0){
              
              imgvol.setImage(new Image("/com/images/vol1.png"));
           
              pvol.getChildren().add(imgvol);
             }
              else{
               imgvol.setImage(new Image("/com/images/vol2.png"));
           
              pvol.getChildren().add(imgvol);  
             }
             
            } 
        });
    }    
      @FXML
    void repeterAction(ActionEvent event) {
        if(fini==1)
        {
            mp.stop();
            mp=new MediaPlayer(a);
            vue.setMediaPlayer(mp);
            mp.setVolume(volumeSlider.getValue()/100.0);
            mp.play();
             mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                fini=1;
            }});
            //mp.play();
            fini=0;
        }}

  @FXML
    void retourAction(ActionEvent event) {
      try {
                  
                    
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/chapitreCoursfxml.fxml"));
                    
                    
                    AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                    
                    ChapitreCoursfxmlController  controller = fxmlLoader.<ChapitreCoursfxmlController >getController();
                    
                    controller.setCours(cours);
                      
                    pane1.getChildren().setAll(anchorPane);
                } catch (IOException ex) {
                    Logger.getLogger(SuivreCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }  
}
