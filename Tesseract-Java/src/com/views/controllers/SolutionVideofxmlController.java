/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import com.models.entities.HistoriqueQuestion;
import com.models.entities.Question;
import com.models.entities.Reponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
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
public class SolutionVideofxmlController implements Initializable {
  @FXML
  private AnchorPane pane1;
   @FXML
  private MediaView vue;
      @FXML
  private Pane pvol;
 @FXML
    private Label numeroQuestionLabel;
  @FXML
    private ProgressBar tempProgressBar;
   @FXML
    private Label questionLabel;
    @FXML
    private Slider volumeSlider;
     @FXML
    private ToggleGroup prop;
       @FXML
    private RadioButton prop1RadioButton;
       
        @FXML
    private RadioButton prop3RadioButton;
         @FXML
    private RadioButton prop2RadioButton;
          @FXML
    private ImageView imgvol;
           @FXML
    private ImageView pro2Imageview;
            @FXML
    private ImageView pro1Imageview;
             @FXML
    private ImageView pro3Imageview;
    @FXML
    private Button  fermerButton;
     @FXML
    private TextArea just;
     @FXML
    private Label tempLabel;
      @FXML
    private ImageView imgbutton;
       @FXML
    private Label tep;
    private List<Question> listeQuestion;
    private List<Reponse> listeReponse;
     private int tmpRestant=20;
      private MediaPlayer mp;
      private int fini;
    private Media a;
     private int index=0;
      private int id_question;
    private Cours cours;
private List<HistoriqueQuestion> listeRep;
String type;

  

    public List<Question> getListeQuestion() {
        return listeQuestion;
    }

   

    public void setListeQuestion(List<Question> listeQuestion) {
        this.listeQuestion = listeQuestion;
    }

   

    public List<HistoriqueQuestion> getListeRep() {
        return listeRep;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
      try {
          this.id_question = id_question;
          numeroQuestionLabel.setText("Question N°"+(index+1)+"/"+listeQuestion.size());
           tep.setText("Epreuve "+type+" :");
          ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
          listeReponse =serviceApprenant.findReponseByIdQuestion(id_question);
          questionLabel.setText(listeQuestion.get(index).getQuestion());
          prop1RadioButton.setText(listeReponse.get(0).getReponse());
          prop2RadioButton.setText(listeReponse.get(1).getReponse());
          prop3RadioButton.setText(listeReponse.get(2).getReponse()); 
          if(listeReponse.get(0).getEtat().equals("true")) {pro1Imageview.setVisible(true);
                                                            just.setText(listeReponse.get(0).getJustification());}
          else if(listeReponse.get(1).getEtat().equals("true")) {pro2Imageview.setVisible(true);
                                                                  just.setText(listeReponse.get(1).getJustification());}
          else if (listeReponse.get(2).getEtat().equals("true")) {pro3Imageview.setVisible(true);
                                                                  just.setText(listeReponse.get(2).getJustification());}
          if(listeRep.get(index).getId_his_rep()==1)
              prop1RadioButton.setSelected(true);
          else if(listeRep.get(index).getId_his_rep()==2)
              prop2RadioButton.setSelected(true);
          else if(listeRep.get(index).getId_his_rep()==3)
              prop3RadioButton.setSelected(true);
          File f = new File("src/son/son_question/67.mp4");
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

      } catch (SQLException ex) {
          Logger.getLogger(SolutionVideofxmlController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public void setListeRep(List<HistoriqueQuestion> listeRep) {
        this.listeRep = listeRep;
       
         if(index<listeQuestion.size())//1 2
        {
            System.out.println("index:"+index);
            
       setId_question(listeQuestion.get(index).getId());

        }
       
        
    }
    
       public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }  

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
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
         
         
    listeReponse = new ArrayList<Reponse>();
//    listeRep=new ArrayList<HistoriqueQuestion>();
//    listeQuestion=new ArrayList<Question>();
    }    
   @FXML
      void fermerAction(ActionEvent event) throws IOException {
           if(index==listeQuestion.size()-1)
          {try {
                             
                              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ResultatEpreuveFinalFXML.fxml"));
                              
                              AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                              
                              ResultatEpreuveFinalFXMLController controller = fxmlLoader.<ResultatEpreuveFinalFXMLController>getController();
                              controller.setCours(cours);
                              controller.setType(type);
                             
                              controller.setListeQuestion(listeQuestion);
                            
                              controller.setListeRep(listeRep);
                              
                              pane1.getChildren().setAll(anchorPane);
                          } catch (IOException ex) {
                              Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                          }
               }
            if (index==listeQuestion.size()-2){
                          imgbutton.setImage(new Image("/com/images/conf.jpg"));
                          fermerButton.setGraphic(imgbutton);
                        }
          index++;
          fini=0;
          pro1Imageview.setVisible(false);
          pro2Imageview.setVisible(false);
          pro3Imageview.setVisible(false);
           prop1RadioButton.setSelected(false);
           prop2RadioButton.setSelected(false);
           prop3RadioButton.setSelected(false); 
           setListeRep(listeRep);
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
        }


    }
      
}
